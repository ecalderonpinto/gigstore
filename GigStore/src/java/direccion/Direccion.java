/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package direccion;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="direcciones")
public class Direccion implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String direcciona;
    private String direccionb;
    private String cp;
    private String poblacion;
    private String provincia;
    private String pais;
    private String observaciones;
    
    public Direccion() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dir_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="dir_direcciona")
    public String getDirecciona() {
        return direcciona;
    }

    public void setDirecciona(String direcciona) {
        this.direcciona = direcciona;
    }

    @Column(name="dir_direccionb")
    public String getDireccionb() {
        return direccionb;
    }

    public void setDireccionb(String direccionb) {
        this.direccionb = direccionb;
    }

    @Column(name="dir_cp",length=10)
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Lob
    @Column(name="dir_observaciones")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Column(name="dir_pais", length=50)
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Column(name="dir_poblacion", length=50)
    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    @Column(name="dir_provincia", length=50)
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return direcciona + " " + direccionb + " " + cp + " " + poblacion + " " + provincia + " " + pais + " " + observaciones;
    }
    
}
