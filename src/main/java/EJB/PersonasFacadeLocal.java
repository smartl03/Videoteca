/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Personas;

/**
 *
 * @author usuario
 */
@Local
public interface PersonasFacadeLocal {

    void create(Personas personas);

    void edit(Personas personas);

    void remove(Personas personas);

    Personas find(Object id);

    List<Personas> findAll();

    List<Personas> findRange(int[] range);

    int count();

    public boolean nueva(Personas persona);

    public List<Personas> login(Personas persona);
    
}
