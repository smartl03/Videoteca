
package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="peliculas")
public class Pelicula implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int PeliculaID;
    
    @Column(name="Titulo")
    private String titulo;
    
    @Column(name="Genero")
    private String genero;
    
    @Column(name="Anio")
    private String Anio;
    
    @Column(name="Director")
    private String director;
    
    @Column(name="Actores")
    private String actores;
    
    @Column(name="Duracion")
    private int duracion;
    
    @Column(name="Cartel")
    private String cartel;
    
    @Column(name="Distribuidora")
    private String distribuidora;
    
    @Column(name="Precio")
    private String precio;

    public int getPeliculaID() {
        return PeliculaID;
    }

    public void setPeliculaID(int PeliculaID) {
        this.PeliculaID = PeliculaID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAnio() {
        return Anio;
    }

    public void setAnio(String Anio) {
        this.Anio = Anio;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    public String getCartel() {
        return cartel;
    }

    public void setCartel(String cartel) {
        this.cartel = cartel;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.PeliculaID;
        hash = 29 * hash + Objects.hashCode(this.titulo);
        hash = 29 * hash + Objects.hashCode(this.genero);
        hash = 29 * hash + Objects.hashCode(this.Anio);
        hash = 29 * hash + Objects.hashCode(this.director);
        hash = 29 * hash + Objects.hashCode(this.actores);
        hash = 29 * hash + this.duracion;
        hash = 29 * hash + Objects.hashCode(this.cartel);
        hash = 29 * hash + Objects.hashCode(this.distribuidora);
        hash = 29 * hash + Objects.hashCode(this.precio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pelicula other = (Pelicula) obj;
        if (this.PeliculaID != other.PeliculaID) {
            return false;
        }
        if (this.duracion != other.duracion) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        if (!Objects.equals(this.Anio, other.Anio)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.actores, other.actores)) {
            return false;
        }
        if (!Objects.equals(this.cartel, other.cartel)) {
            return false;
        }
        if (!Objects.equals(this.distribuidora, other.distribuidora)) {
            return false;
        }
        if (!Objects.equals(this.precio, other.precio)) {
            return false;
        }
        return true;
    }
    
    
}
