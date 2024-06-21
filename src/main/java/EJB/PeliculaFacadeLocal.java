/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Pelicula;

/**
 *
 * @author usuario
 */
@Local
public interface PeliculaFacadeLocal {

    void create(Pelicula pelicula);

    void edit(Pelicula pelicula);

    void remove(Pelicula pelicula);

    Pelicula find(Object id);

    List<Pelicula> findAll();

    List<Pelicula> findRange(int[] range);

    int count();

    public boolean nueva(Pelicula pelicula);

    public List<String> getGeneros();

    public boolean modificar(Pelicula pelicula);

    public List<Pelicula> getPeliculas(Pelicula pelicula);

    public List<String> getAnios(Pelicula pelicula);
    
    public List<String> getAniosPorGenero(Pelicula pelicula);

    public List<Pelicula> getPeliculasAlquilar(Pelicula pelicula);
}
