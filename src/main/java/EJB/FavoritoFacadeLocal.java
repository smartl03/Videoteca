/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Favorito;
import model.Pelicula;
import model.Personas;

/**
 *
 * @author usuario
 */
@Local
public interface FavoritoFacadeLocal {

    void create(Favorito favorito);

    void edit(Favorito favorito);

    void remove(Favorito favorito);

    Favorito find(Object id);

    List<Favorito> findAll();

    List<Favorito> findRange(int[] range);

    int count();

    public List<Pelicula> getPeliculasPendientes(Personas persona);
    public void quitarPendientes(Personas persona, Pelicula pelicula);
    
}
