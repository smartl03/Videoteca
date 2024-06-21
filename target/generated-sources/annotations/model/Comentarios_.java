package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Pelicula;
import model.Personas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-21T19:16:28")
@StaticMetamodel(Comentarios.class)
public class Comentarios_ { 

    public static volatile SingularAttribute<Comentarios, Integer> ComentarioID;
    public static volatile SingularAttribute<Comentarios, String> contenido;
    public static volatile SingularAttribute<Comentarios, Pelicula> peliculaID;
    public static volatile SingularAttribute<Comentarios, Date> fechaComentario;
    public static volatile SingularAttribute<Comentarios, String> resumen;
    public static volatile SingularAttribute<Comentarios, Personas> usuarioID;

}