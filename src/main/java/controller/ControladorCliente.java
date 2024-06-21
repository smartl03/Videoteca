/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import EJB.PersonasFacade;
import EJB.PersonasFacadeLocal;
import java.io.IOException;
import model.Personas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ControladorCliente implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @EJB
    private PersonasFacadeLocal personasEJB;
    
    private Personas persona;

    
    @PostConstruct
    public void init(){
        persona = (Personas) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario"));
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }
    
    

    
    public void conseguir(){

        /*persona.setNombre(usuarios.get(i).getNombre());
        persona.setApellidos(usuarios.get(i).getApellidos());
        persona.setCorreo(usuarios.get(i).getCorreo());
        persona.setPassword(usuarios.get(i).getPassword());
        persona.setUsuarioID(usuarios.get(i).getUsuarioID());
        persona.setTipoUsuario(usuarios.get(i).getTipoUsuario());*/

    }
    
    public void modificarUsuario(){
       
        personasEJB.edit(persona);
        regresarCliente();
                
    }
    
    public void borrarUsuario(){
        try {
            personasEJB.remove(persona);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public void regresarCliente(){
        try {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("Cliente.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(ControladorModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salir(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
