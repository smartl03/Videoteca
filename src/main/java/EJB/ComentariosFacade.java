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
import model.Comentarios;
import model.Personas;

/**
 *
 * @author usuario
 */
@Stateless
public class ComentariosFacade extends AbstractFacade<Comentarios> implements ComentariosFacadeLocal {

    @PersistenceContext(unitName = "videotecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentariosFacade() {
        super(Comentarios.class);
    }

    @Override
    public List<Comentarios> consigueComentariosCliente(Personas persona) {
        Query consulta = em.createQuery("FROM Comentarios c WHERE c.usuarioID =:usuario");
        consulta.setParameter("usuario", persona);
        
        List<Comentarios> listado = consulta.getResultList();
        return listado;
    }
    
}
