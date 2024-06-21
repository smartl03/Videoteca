/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.ComentariosFacadeLocal;
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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Comentarios;
import model.Pelicula;
import model.Personas;

@Named
@ViewScoped
public class ControladorComentarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ComentariosFacadeLocal comentariosEJB;
    @EJB
    private PeliculaFacadeLocal peliculaEJB;
    private Pelicula pelicula;
    private List<String> generos;
    private List<Pelicula> peliculas;
    private List<String> titulos;
    private List<String> anios;
    private List<Comentarios> comentarios;
    private Comentarios comentario;
    private Personas usuario;
    private Personas persona;

    @PostConstruct
    public void init() {
        comentarios = comentariosEJB.findAll();
        comentario = new Comentarios();
        usuario = new Personas();
        persona = (Personas) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario"));
        pelicula = new Pelicula();
        generos = new ArrayList();
        peliculas = new ArrayList();
        anios = new ArrayList();
        titulos = new ArrayList();
        consigueGeneros();
        
        if(persona.getTipoUsuario().equals("Cliente")) consigueComentariosUsuario();
    }

    public List<Comentarios> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentarios> comentarios) {
        this.comentarios = comentarios;
    }

    public Comentarios getComentario() {
        return comentario;
    }

    public void setComentario(Comentarios comentario) {
        this.comentario = comentario;
    }

    public Personas getUsuario() {
        return usuario;
    }

    public void setUsuario(Personas usuario) {
        this.usuario = usuario;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void consigueComentarios() {
        comentarios = comentariosEJB.findAll();
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public List<String> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<String> titulos) {
        this.titulos = titulos;
    }

    public List<String> getAnios() {
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
    }

    public void consigueGeneros() {
        generos = peliculaEJB.getGeneros();
    }

    public void conseguirPeliculas() {
        peliculas = peliculaEJB.getPeliculas(pelicula);
        Pelicula seleccionar = new Pelicula();
        seleccionar.setTitulo("Seleccione película");
        peliculas.add(0, seleccionar);
        quitarNombresRepetidos();
    }

    public void ponerAnio() {
        anios = peliculaEJB.getAnios(pelicula);
        anios.add(0, "Año");
    }

    private void quitarNombresRepetidos() {

        int i = 0;
        while (i < peliculas.size()) {
            if (!titulos.contains(peliculas.get(i).getTitulo())) {
                titulos.add(peliculas.get(i).getTitulo());
            }
            i++;
        }
    }

    public void conseguir() {
        for (int i = 0; i < comentarios.size(); i++) {
            if (comentarios.get(i).getComentarioID() == comentario.getComentarioID()) {
                comentario.setContenido(comentarios.get(i).getContenido());
                comentario.setFechaComentario(comentarios.get(i).getFechaComentario());
                comentario.setPeliculaID(comentarios.get(i).getPeliculaID());
                comentario.setUsuarioID(comentarios.get(i).getUsuarioID());
                comentario.setResumen(comentarios.get(i).getResumen());
            }
        }
    }

    public void borrarComentario() {
        comentariosEJB.remove(comentario);
        if(persona.getTipoUsuario().equals("Admin")) regresar();
        else regresarCliente();
    }

    public void nuevoComentario() {
        for (int i = 0; i < peliculas.size(); i++) {
            if(peliculas.get(i).getTitulo().equals(pelicula.getTitulo()) && 
                peliculas.get(i).getAnio().equals(pelicula.getAnio()) && 
                peliculas.get(i).getGenero().equals(pelicula.getGenero())){
                comentario.setPeliculaID(peliculas.get(i));
            }
        }       
        comentario.setUsuarioID(persona);
        Date fecha = new Date();

        comentario.setFechaComentario(fecha);
        comentario.setResumen(comentario.getContenido().substring(0, 50)+"...");
        comentariosEJB.create(comentario);
        regresarCliente();
        
    }
    
    private void consigueComentariosUsuario(){
        
        comentarios = comentariosEJB.consigueComentariosCliente(persona);
        Comentarios coment = new Comentarios();
        coment.setResumen("Seleccione");
        comentarios.add(0, coment);
    }
    
    public void regresarCliente(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Cliente.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(ControladorComentarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void regresar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Administrador.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(ControladorModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
