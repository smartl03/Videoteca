/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Personas;

/**
 *
 * @author usuario
 */
@Stateless
public class PersonasFacade extends AbstractFacade<Personas> implements PersonasFacadeLocal {

    @PersistenceContext(unitName = "videotecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasFacade() {
        super(Personas.class);
    }

    @Override
    public boolean nueva(Personas persona) {
        List<Personas> personas = findAll();
        
        for (int i = 0; i < personas.size(); i++) {
            if(persona.getCorreo().equals(personas.get(i).getCorreo())) return false;
        }
        
        return true;
    }

    @Override
    public List<Personas> login(Personas persona) {
        Query consulta = em.createQuery("FROM Personas usuario WHERE usuario.correo=:correo and usuario.password=:pass");
        consulta.setParameter("correo", persona.getCorreo());
        consulta.setParameter("pass", persona.getPassword());
        
        List<Personas> usuarios = consulta.getResultList();
        return usuarios;
        
    }
    
}
