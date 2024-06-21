/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import model.Reportes;

/**
 *
 * @author usuario
 */
@Local
public interface ReportesFacadeLocal {

    void create(Reportes reportes);

    void edit(Reportes reportes);

    void remove(Reportes reportes);

    Reportes find(Object id);

    List<Reportes> findAll();

    List<Reportes> findRange(int[] range);

    int count();
    
}
