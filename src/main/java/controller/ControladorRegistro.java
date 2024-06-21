/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import EJB.PersonasFacade;
import EJB.PersonasFacadeLocal;
import model.Personas;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ControladorRegistro implements Serializable{
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
        this.persona = persona;
    }
    
    public void nuevoUsuario(){
       persona.setTipoUsuario("Cliente");
       System.out.println(persona.getNombre() + " " + persona.getApellidos() + " " + persona.getPassword() + " " + persona.getTipoUsuario() + " " + persona.getCorreo());

       if(personasEJB.nueva(persona)){
            personasEJB.create(persona);
       }
        
                
    }
    
    
}
