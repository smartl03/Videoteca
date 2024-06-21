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
import model.Pelicula;
import model.Personas;

@Named
@ViewScoped
public class ControladorAlquilar implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private PeliculaFacadeLocal peliculasEJB;
    @EJB
    private FavoritoFacadeLocal favoritosEJB;
    @EJB
    private AlquilerFacadeLocal alquilerEJB;

    private List<Pelicula> peliculas;
    private List<String> anios;
    private List<String> tarjetas;
    private Personas persona;
    private Alquiler alquilerPelicula;
    private Pelicula pelicula;
    boolean activo = true;

    @PostConstruct
    public void init() {
        persona = (Personas) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario"));
        peliculas = new ArrayList();
        anios = new ArrayList();
        tarjetas = new ArrayList();
        tarjetas.add("Visa");
        tarjetas.add("Master Card");
        tarjetas.add("American Express");
        anios.add("Año");
        pelicula = new Pelicula();
        alquilerPelicula = new Alquiler();
       
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<String> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<String> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public Alquiler getAlquilerPelicula() {
        return alquilerPelicula;
    }

    public void setAlquilerPelicula(Alquiler alquilerPelicula) {
        this.alquilerPelicula = alquilerPelicula;
    }

    public void ponerAnio() {
        consiguePeliculas();
        anios = peliculasEJB.getAniosPorGenero(pelicula);
        anios.add(0, "Año");
    }

    public void consiguePeliculas() {
        if (activo) {
            peliculas = favoritosEJB.getPeliculasPendientes(persona);
        } else {
            if (pelicula.getGenero() == null || pelicula.getGenero().equals("Seleccione Género")) {
                peliculas = peliculasEJB.findAll();
            } else {
                peliculas = peliculasEJB.getPeliculasAlquilar(pelicula);
            }
        }
    }

    public void alquilar() {
        if (this.pelicula == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No hay película", "Para alquilar seleccione una película de la lista."));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Pelicula", this.pelicula);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Pago.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(ControladorComentarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void pagoTarjeta() {
        pelicula = (Pelicula) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Pelicula"));
        alquilerPelicula.setPeliculaID(pelicula);
        alquilerPelicula.setUsuarioId(persona);
        alquilerPelicula.setPrecio(pelicula.getPrecio());
        alquilerPelicula.setMetodoPago("Tarjeta");
        Date fecha = new Date();
        Date fechaDevolucion = new Date(fecha.getYear(), fecha.getMonth() + 1, fecha.getDay());
        alquilerPelicula.setFechaAlquiler(fecha);
        alquilerPelicula.setFechaDevolucion(fechaDevolucion);
        alquilerEJB.create(alquilerPelicula);
        quitarPeliculaFavoritos();
    }
    
    public void pagoBizum() {
        pelicula = (Pelicula) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Pelicula"));
        alquilerPelicula.setPeliculaID(pelicula);
        alquilerPelicula.setUsuarioId(persona);
        alquilerPelicula.setPrecio(pelicula.getPrecio());
        alquilerPelicula.setMetodoPago("Bizum");
        Date fecha = new Date();
        Date fechaDevolucion = new Date(fecha.getYear(), fecha.getMonth() + 1, fecha.getDay());
        alquilerPelicula.setFechaAlquiler(fecha);
        alquilerPelicula.setFechaDevolucion(fechaDevolucion);
        alquilerEJB.create(alquilerPelicula);
        quitarPeliculaFavoritos();
    }

    public void paypal() {
        pelicula = (Pelicula) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Pelicula"));
        alquilerPelicula.setPeliculaID(pelicula);
        alquilerPelicula.setUsuarioId(persona);
        alquilerPelicula.setPrecio(pelicula.getPrecio());
        alquilerPelicula.setMetodoPago("PayPal");
        Date fecha = new Date();
        Date fechaDevolucion = new Date(fecha.getYear(), fecha.getMonth() + 1, fecha.getDay());
        alquilerPelicula.setFechaAlquiler(fecha);
        alquilerPelicula.setFechaDevolucion(fechaDevolucion);
        alquilerEJB.create(alquilerPelicula);
        /*try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("https://paypal.com");
        } catch (IOException ex) {
            Logger.getLogger(ControladorComentarios.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        quitarPeliculaFavoritos();
    }

    public void regresarCliente() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Cliente.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(ControladorComentarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void quitarPeliculaFavoritos() {
        favoritosEJB.quitarPendientes(persona, pelicula);
        try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Cliente.xhtml?faces-redirect=true");
            } catch (IOException ex) {
                Logger.getLogger(ControladorComentarios.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
