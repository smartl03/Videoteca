package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Personas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-21T19:16:28")
@StaticMetamodel(Reportes.class)
public class Reportes_ { 

    public static volatile SingularAttribute<Reportes, String> descripcion;
    public static volatile SingularAttribute<Reportes, String> fechaReporte;
    public static volatile SingularAttribute<Reportes, Personas> usuarioID;
    public static volatile SingularAttribute<Reportes, Integer> ReporteID;

}