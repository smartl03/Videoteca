/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.AlquilerFacadeLocal;
import EJB.FavoritoFacadeLocal;
import EJB.PeliculaFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Alquiler;
import model.Favorito;
import model.Pelicula;
import model.Personas;

@Named
@ViewScoped
public class ControladorMarcar implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private PeliculaFacadeLocal peliculasEJB;
    @EJB
    private FavoritoFacadeLocal favoritosEJB;

    private List<Pelicula> peliculas;
    private List<String> anios;
    private Personas persona;
    private Pelicula pelicula;

    @PostConstruct
    public void init() {
        persona = (Personas) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario"));
        peliculas = new ArrayList();
        anios = new ArrayList();
        anios.add("Año");
        pelicula = new Pelicula();
        consiguePeliculas();
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public List<String> getAnios() {
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void ponerAnio() {
        consiguePeliculas();
        anios = peliculasEJB.getAniosPorGenero(pelicula);
        anios.add(0, "Año");
    }

    public void consiguePeliculas() {
        if (pelicula.getGenero() == null || pelicula.getGenero().equals("Seleccione Género")) {
            peliculas = peliculasEJB.findAll();
        } else {
            peliculas = peliculasEJB.getPeliculasAlquilar(pelicula);
        }

        List<Favorito> yaEnFavoritos = favoritosEJB.findAll();
        for (int i = 0; i < peliculas.size(); i++) {
            Pelicula candidata = peliculas.get(i);
            for (int j = 0; j < yaEnFavoritos.size(); j++) {
                if (yaEnFavoritos.get(j).getUsuarioID().getUsuarioID() == persona.getUsuarioID()
                        && yaEnFavoritos.get(j).getPelicula().getPeliculaID() == candidata.getPeliculaID()) {
                    peliculas.remove(i);
                    i--;
                }
            }
        }

    }

    public void marcar() {
        System.out.println("controller.ControladorMarcar.marcar()");
        if (this.pelicula == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No hay película", "Para marcar como pendiente de ver seleccione una película de la lista."));
        } else {
            Favorito favorito = new Favorito();
            favorito.setPelicula(pelicula);
            favorito.setUsuarioID(persona);
            favorito.setFechaFavorito(new Date());

            favoritosEJB.create(favorito);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Cliente.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(ControladorComentarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void regresarCliente() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Cliente.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(ControladorComentarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void quitarPeliculaFavoritos() {
        favoritosEJB.quitarPendientes(persona, pelicula);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Cliente.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(ControladorComentarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
