package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Pelicula;
import model.Personas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-21T19:16:28")
@StaticMetamodel(Favorito.class)
public class Favorito_ { 

    public static volatile SingularAttribute<Favorito, Pelicula> pelicula;
    public static volatile SingularAttribute<Favorito, Integer> FavoritoID;
    public static volatile SingularAttribute<Favorito, Personas> usuarioID;
    public static volatile SingularAttribute<Favorito, Date> fechaFavorito;

}