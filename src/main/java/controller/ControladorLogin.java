/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import EJB.PersonasFacadeLocal;
import java.io.IOException;
import model.Personas;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ControladorLogin implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @EJB
    private PersonasFacadeLocal personasEJB;

    private Personas persona;
    
    @PostConstruct
    public void init(){
        persona = new Personas();
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        this.persona = persona;
    }
    
    public void entradaUsuario() throws IOException{
       List<Personas> listado = personasEJB.login(persona);
       if(listado.size()>0){
           this.persona = listado.get(0);
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", this.persona);
           switch(persona.getTipoUsuario()){
               case "Admin":
                   FacesContext.getCurrentInstance().getExternalContext().redirect("Administrador.xhtml?faces-redirect=true");
                   break;
               case "Cliente":
                   FacesContext.getCurrentInstance().getExternalContext().redirect("Cliente.xhtml?faces-redirect=true");
                   break;
               case "Invitado":
                   FacesContext.getCurrentInstance().getExternalContext().redirect("Cliente.xhtml?faces-redirect=true");
                   break;
           }
       }
       else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                   "Usuario o contraseña no válidos", "No se ha encontrado un usuario con esos datos en nuestra base de datos."));
       } 
                
    }   
    
}
