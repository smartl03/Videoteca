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
import model.Pelicula;

/**
 *
 * @author usuario
 */
@Stateless
public class PeliculaFacade extends AbstractFacade<Pelicula> implements PeliculaFacadeLocal {

    @PersistenceContext(unitName = "videotecaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeliculaFacade() {
        super(Pelicula.class);
    }
    
     @Override
    public boolean nueva(Pelicula pelicula) {
        List<Pelicula> peliculas = findAll();
        
        for (int i = 0; i < peliculas.size(); i++) {
            if(pelicula.getTitulo().equals(peliculas.get(i).getTitulo()) &&
                    pelicula.getAnio().equals(peliculas.get(i).getAnio())) return false;
        }
        
        return true;
    }    

    @Override
    public List<String> getGeneros() {
       Query consulta = em.createQuery("FROM Pelicula p WHERE p.PeliculaID>0");
       List<Pelicula> pelis = consulta.getResultList();
       List<String> generos = new ArrayList();
       generos.add("Seleccione Género");
        for (int i = 0; i < pelis.size(); i++) {
            if(!generos.contains(pelis.get(i).getGenero())){
                generos.add(pelis.get(i).getGenero());
            }
        }
        return generos;
    }

    @Override
    public boolean modificar(Pelicula pelicula) {
        
        return false;
    }

    @Override
    public List<Pelicula> getPeliculas(Pelicula pelicula) {
        Query consulta = em.createQuery("FROM Pelicula p WHERE p.genero=:genero");
        consulta.setParameter("genero", pelicula.getGenero());
        return consulta.getResultList();
    }

    @Override
    public List<String> getAnios(Pelicula pelicula) {
        Query consulta = em.createQuery("FROM Pelicula p WHERE p.titulo=:titulo");
        consulta.setParameter("titulo", pelicula.getTitulo());
        List<Pelicula> pelis = consulta.getResultList();
        List<String> anios = new ArrayList();
        for (int i = 0; i < pelis.size(); i++) {
            anios.add(pelis.get(i).getAnio());
        }
        return anios;
    }

    @Override
    public List<String> getAniosPorGenero(Pelicula pelicula) {
        Query consulta = em.createQuery("FROM Pelicula p WHERE p.genero=:parametro");
        consulta.setParameter("parametro", pelicula.getGenero());
        List<Pelicula> pelis = consulta.getResultList();
        List<String> anios = new ArrayList();
        for (int i = 0; i < pelis.size(); i++) {
            if(anios.contains(pelis.get(i).getAnio())==false) anios.add(pelis.get(i).getAnio());
        }
        return anios;
    }

    @Override
    public List<Pelicula> getPeliculasAlquilar(Pelicula pelicula) {
        Query consulta = null;
        if(pelicula.getAnio()!=null && !pelicula.getAnio().equals("Año")) consulta = em.createQuery("FROM Pelicula p WHERE p.genero=:parametro1 AND p.Anio=:parametro2");
        else{
            System.out.println("EJB.PeliculaFacade.getPeliculasAlquilar()");
            consulta = em.createQuery("FROM Pelicula p WHERE p.genero=:parametro1");
        }
        consulta.setParameter("parametro1", pelicula.getGenero());
        if(pelicula.getAnio()!=null && !pelicula.getAnio().equals("Año")) consulta.setParameter("parametro2", pelicula.getAnio());
        return consulta.getResultList();
    }
}
