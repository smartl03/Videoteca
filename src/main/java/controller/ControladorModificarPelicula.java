/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import EJB.PeliculaFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pelicula;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ControladorModificarPelicula implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @EJB
    private PeliculaFacadeLocal peliculaEJB;

    private Pelicula pelicula;
    private List<String> generos;
    private List<Pelicula> peliculas;
    private List<String> titulos;
    private List<String> anios;
    private String genero;
    
    @PostConstruct
    public void init(){
        pelicula = new Pelicula();
        generos = new ArrayList();
        peliculas = new ArrayList();
        anios = new ArrayList();
        titulos = new ArrayList();
        consigueGeneros();
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        System.out.println("controller.ControladorModificarPelicula.setPelicula()+++++++++++++++++++++++++++++++++++++++++++");
        this.pelicula = pelicula;
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
    
    public String getGenero(){
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<String> getAnios() {
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
    }

    public List<String> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<String> titulos) {
        this.titulos = titulos;
    }

    
    
    
    
    
    
     public void modificar(){

       if(peliculaEJB.modificar(pelicula)){
           try {
              // peliculaEJB.create(pelicula);
               FacesContext.getCurrentInstance().getExternalContext().redirect("Administrador.xhtml?faces-redirect=true");
           } catch (IOException ex) {
               Logger.getLogger(ControladorNuevaPelicula.class.getName()).log(Level.SEVERE, null, ex);
           }
            
       }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                   "La película ya está en la base de datos", pelicula.getTitulo()+" del año " + pelicula.getAnio()+" ya se encuentra en nuestra base de datos."));
       }                
    }
    
    public void consigueGeneros(){
        generos = peliculaEJB.getGeneros();
    }
    
    public void conseguir(){
        peliculas = peliculaEJB.getPeliculas(pelicula);
        Pelicula seleccionar = new Pelicula();
        seleccionar.setTitulo("Seleccione película");
        peliculas.add(0, seleccionar);
        quitarNombresRepetidos();
    }
    
    private void quitarNombresRepetidos(){
        
        int i=0;
        while(i < peliculas.size()) {
            if(!titulos.contains(peliculas.get(i).getTitulo())){
                titulos.add(peliculas.get(i).getTitulo());
            }
            i++;
        }
    }
    
    public void ponerAnio(){
        anios = peliculaEJB.getAnios(pelicula);
        anios.add(0, "Año");
    }
    
    public void ponerPelicula(){

        for (int i = 0; i < peliculas.size(); i++) {
            if(peliculas.get(i).getTitulo().equals(pelicula.getTitulo()) && 
                peliculas.get(i).getAnio().equals(pelicula.getAnio()) && 
                peliculas.get(i).getGenero().equals(pelicula.getGenero())){
                System.out.println("controller.ControladorModificarPelicula.ponerPelicula()");
                pelicula.setTitulo(peliculas.get(i).getTitulo());
                pelicula.setAnio(peliculas.get(i).getAnio());
                pelicula.setDirector(peliculas.get(i).getDirector());
                pelicula.setDistribuidora(peliculas.get(i).getDistribuidora());
                pelicula.setDuracion(peliculas.get(i).getDuracion());
                pelicula.setGenero(peliculas.get(i).getGenero());
                pelicula.setPeliculaID(peliculas.get(i).getPeliculaID());
                pelicula.setActores(peliculas.get(i).getActores());
                pelicula.setCartel(peliculas.get(i).getCartel());
                pelicula.setPrecio(peliculas.get(i).getPrecio());
            }
        }
    }
    
    public void borra(){
        peliculaEJB.remove(pelicula);
        regresar();
    }
    
    public void actualiza(){
        peliculaEJB.edit(pelicula);
        regresar();
        
    }
    
    public void regresar(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Administrador.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(ControladorModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
