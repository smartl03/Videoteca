
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="comentarios")
public class Comentarios implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ComentarioID;
    
    @OneToOne
    @JoinColumn(name="UsuarioID", referencedColumnName = "UsuarioID")
    private Personas usuarioID;
    
    @OneToOne
    @JoinColumn(name="PeliculaID", referencedColumnName = "PeliculaID")
    private Pelicula peliculaID;
    
    @Column(name="FechaComentario")
    private Date fechaComentario;
    
    @Column(name="contenido")
    private String contenido;
    
    @Column(name="resumen")
    private String resumen;

    public int getComentarioID() {
        return ComentarioID;
    }

    public void setComentarioID(int ComentarioID) {
        this.ComentarioID = ComentarioID;
    }

    public Personas getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Personas usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Pelicula getPeliculaID() {
        return peliculaID;
    }

    public void setPeliculaID(Pelicula peliculaID) {
        this.peliculaID = peliculaID;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        System.out.println("model.Comentarios.setFechaComentario()");
        this.fechaComentario = fechaComentario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.ComentarioID;
        hash = 11 * hash + Objects.hashCode(this.usuarioID);
        hash = 11 * hash + Objects.hashCode(this.peliculaID);
        hash = 11 * hash + Objects.hashCode(this.fechaComentario);
        hash = 11 * hash + Objects.hashCode(this.contenido);
        hash = 11 * hash + Objects.hashCode(this.resumen);
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
        final Comentarios other = (Comentarios) obj;
        if (this.ComentarioID != other.ComentarioID) {
            return false;
        }
        if (!Objects.equals(this.contenido, other.contenido)) {
            return false;
        }
        if (!Objects.equals(this.resumen, other.resumen)) {
            return false;
        }
        if (!Objects.equals(this.usuarioID, other.usuarioID)) {
            return false;
        }
        if (!Objects.equals(this.peliculaID, other.peliculaID)) {
            return false;
        }
        if (!Objects.equals(this.fechaComentario, other.fechaComentario)) {
            return false;
        }
        return true;
    }

    
    
   
    
    
    
    
}
