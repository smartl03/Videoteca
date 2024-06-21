package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Pelicula;
import model.Personas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-21T19:16:28")
@StaticMetamodel(Alquiler.class)
public class Alquiler_ { 

    public static volatile SingularAttribute<Alquiler, String> metodoPago;
    public static volatile SingularAttribute<Alquiler, String> precio;
    public static volatile SingularAttribute<Alquiler, Date> fechaDevolucion;
    public static volatile SingularAttribute<Alquiler, String> tipoTarjeta;
    public static volatile SingularAttribute<Alquiler, Pelicula> peliculaID;
    public static volatile SingularAttribute<Alquiler, Integer> AlquilerID;
    public static volatile SingularAttribute<Alquiler, Personas> usuarioId;
    public static volatile SingularAttribute<Alquiler, String> numeroTarjeta;
    public static volatile SingularAttribute<Alquiler, Date> fechaAlquiler;

}