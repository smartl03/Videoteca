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
public class ControladorNuevaPelicula implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @EJB
    private PeliculaFacadeLocal peliculaEJB;

    private Pelicula pelicula;
    
    @PostConstruct
    public void init(){
        pelicula = new Pelicula();
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    
    
    
     public void nueva(){

       if(peliculaEJB.nueva(pelicula)){
               peliculaEJB.create(pelicula);
               regresar();
            
       }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                   "La película ya está en la base de datos", pelicula.getTitulo()+" del año " + pelicula.getAnio()+" ya se encuentra en nuestra base de datos 😶🙄."));
       }                
    }

    public void regresar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Administrador.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(ControladorNuevaPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
