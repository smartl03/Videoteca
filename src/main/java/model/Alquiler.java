
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
@Table(name="alquileres")
public class Alquiler implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int AlquilerID;
    
    @OneToOne
    @JoinColumn(name="UsuarioID", referencedColumnName = "UsuarioID")
    private Personas usuarioId;
    
    @OneToOne
    @JoinColumn(name="PeliculaID", referencedColumnName = "PeliculaID")
    private Pelicula peliculaID;
    
    @Column(name="FechaAlquiler")
    private Date fechaAlquiler;
    
    @Column(name="FechaDevolucion")
    private Date fechaDevolucion;
    
    @Column(name="Precio")
    private String precio;
    
    @Column(name="MetodoPago")
    private String metodoPago;
    
    @Column(name="TipoTarjeta")
    private String tipoTarjeta;
    
    @Column(name="NumeroTarjeta")
    private String numeroTarjeta;

    public int getAlquilerID() {
        return AlquilerID;
    }

    public void setAlquilerID(int AlquilerID) {
        this.AlquilerID = AlquilerID;
    }

    public Personas getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Personas usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Pelicula getPeliculaID() {
        return peliculaID;
    }

    public void setPeliculaID(Pelicula peliculaID) {
        this.peliculaID = peliculaID;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.AlquilerID;
        hash = 67 * hash + Objects.hashCode(this.usuarioId);
        hash = 67 * hash + Objects.hashCode(this.peliculaID);
        hash = 67 * hash + Objects.hashCode(this.fechaAlquiler);
        hash = 67 * hash + Objects.hashCode(this.fechaDevolucion);
        hash = 67 * hash + Objects.hashCode(this.precio);
        hash = 67 * hash + Objects.hashCode(this.metodoPago);
        hash = 67 * hash + Objects.hashCode(this.tipoTarjeta);
        hash = 67 * hash + Objects.hashCode(this.numeroTarjeta);
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
        final Alquiler other = (Alquiler) obj;
        if (this.AlquilerID != other.AlquilerID) {
            return false;
        }
        if (!Objects.equals(this.precio, other.precio)) {
            return false;
        }
        if (!Objects.equals(this.metodoPago, other.metodoPago)) {
            return false;
        }
        if (!Objects.equals(this.tipoTarjeta, other.tipoTarjeta)) {
            return false;
        }
        if (!Objects.equals(this.numeroTarjeta, other.numeroTarjeta)) {
            return false;
        }
        if (!Objects.equals(this.usuarioId, other.usuarioId)) {
            return false;
        }
        if (!Objects.equals(this.peliculaID, other.peliculaID)) {
            return false;
        }
        if (!Objects.equals(this.fechaAlquiler, other.fechaAlquiler)) {
            return false;
        }
        if (!Objects.equals(this.fechaDevolucion, other.fechaDevolucion)) {
            return false;
        }
        return true;
    }
    
}
