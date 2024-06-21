/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
@Table(name="reportes")
public class Reportes implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ReporteID;
    
    @OneToOne
    @JoinColumn(name="UsuarioID", referencedColumnName = "UsuarioID")
    private Personas usuarioID;
    
    @Column(name="FechaReporte")
    private String fechaReporte;
    
    @Column(name="Descripcion")
    private String descripcion;

    public int getReporteID() {
        return ReporteID;
    }

    public void setReporteID(int ReporteID) {
        this.ReporteID = ReporteID;
    }

    public Personas getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Personas usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.ReporteID;
        hash = 19 * hash + Objects.hashCode(this.usuarioID);
        hash = 19 * hash + Objects.hashCode(this.fechaReporte);
        hash = 19 * hash + Objects.hashCode(this.descripcion);
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
        final Reportes other = (Reportes) obj;
        if (this.ReporteID != other.ReporteID) {
            return false;
        }
        if (!Objects.equals(this.fechaReporte, other.fechaReporte)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.usuarioID, other.usuarioID)) {
            return false;
        }
        return true;
    }
    
        
    
    
    
}
