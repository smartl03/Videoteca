/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Favorito;
import model.Pelicula;
import model.Personas;

/**
 *
 * @author usuario
 */
@Stateless
public class FavoritoFacade extends AbstractFacade<Favorito> implements FavoritoFacadeLocal {

    @PersistenceContext(unitName = "videotecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FavoritoFacade() {
        super(Favorito.class);
    }

    @Override
    public List<Pelicula> getPeliculasPendientes(Personas persona) {
        Query consulta = em.createQuery("FROM Favorito f WHERE f.usuarioID=:parametro");
        consulta.setParameter("parametro", persona);
        List<Favorito> listado = consulta.getResultList();
        List<Pelicula> peliculas = new ArrayList();
        for (int i = 0; i < listado.size(); i++) {
            peliculas.add(listado.get(i).getPelicula());
        }

        return peliculas;
    }

    @Override
    public void quitarPendientes(Personas persona, Pelicula pelicula) {
        Query consulta = em.createQuery("FROM Favorito f WHERE f.usuarioID=:parametro AND f.pelicula=:parametro2");
        consulta.setParameter("parametro", persona);
        consulta.setParameter("parametro2", pelicula);
        List<Favorito> listado = consulta.getResultList();
        remove(listado.get(0));
    }

}
