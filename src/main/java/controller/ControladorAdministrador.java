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
public class ControladorAdministrador implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @EJB
    private PersonasFacadeLocal personasEJB;

    private Personas persona;
    private List<Personas> usuarios;
    private List<String> tipos;
    
    @PostConstruct
    public void init(){
        persona = new Personas();
        usuarios = new ArrayList();
        tipos = new ArrayList();
        tipos.add("Cliente");
        tipos.add("Admin");
        consigueUsuarios();
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
        
    }

    public List<Personas> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Personas> usuarios) {
        this.usuarios = usuarios;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public void setTipos(List<String> tipos) {
        this.tipos = tipos;
    }
    
    
    
    public void consigueUsuarios(){
        usuarios = personasEJB.findAll();
    }
    
    public void conseguir(){
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getCorreo().equals(persona.getCorreo())){
                persona.setNombre(usuarios.get(i).getNombre());
                persona.setApellidos(usuarios.get(i).getApellidos());
                persona.setCorreo(usuarios.get(i).getCorreo());
                persona.setPassword(usuarios.get(i).getPassword());
                persona.setUsuarioID(usuarios.get(i).getUsuarioID());
                persona.setTipoUsuario(usuarios.get(i).getTipoUsuario());
                        
            }
        }
    }
    
    public void modificarUsuario(){
       
        personasEJB.edit(persona);
        regresar();
                
    }
    
    public void borrarUsuario(){
       
        personasEJB.remove(persona);
        regresar();
                
    }
    
    public void regresar(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Administrador.xhtml?faces-redirect=true");
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
