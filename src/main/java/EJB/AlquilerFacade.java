/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Alquiler;

/**
 *
 * @author usuario
 */
@Stateless
public class AlquilerFacade extends AbstractFacade<Alquiler> implements AlquilerFacadeLocal {

    @PersistenceContext(unitName = "videotecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlquilerFacade() {
        super(Alquiler.class);
    }
    
}
