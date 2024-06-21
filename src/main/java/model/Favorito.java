/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name="favoritos")
public class Favorito implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int FavoritoID;
    
    @OneToOne
    @JoinColumn(name="UsuarioID", referencedColumnName = "UsuarioID")
    private Personas usuarioID;
    
    @OneToOne
    @JoinColumn(name="PeliculaID", referencedColumnName = "PeliculaID")
    private Pelicula pelicula;
    
    @Column(name="FechaFavorito")
    private Date fechaFavorito;

    public int getFavoritoID() {
        return FavoritoID;
    }

    public void setFavoritoID(int FavoritoID) {
        this.FavoritoID = FavoritoID;
    }

    public Personas getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Personas usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Date getFechaFavorito() {
        return fechaFavorito;
    }

    public void setFechaFavorito(Date fechaFavorito) {
        this.fechaFavorito = fechaFavorito;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.FavoritoID;
        hash = 67 * hash + Objects.hashCode(this.usuarioID);
        hash = 67 * hash + Objects.hashCode(this.pelicula);
        hash = 67 * hash + Objects.hashCode(this.fechaFavorito);
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
        final Favorito other = (Favorito) obj;
        if (this.FavoritoID != other.FavoritoID) {
            return false;
        }
        if (!Objects.equals(this.usuarioID, other.usuarioID)) {
            return false;
        }
        if (!Objects.equals(this.pelicula, other.pelicula)) {
            return false;
        }
        if (!Objects.equals(this.fechaFavorito, other.fechaFavorito)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
}
