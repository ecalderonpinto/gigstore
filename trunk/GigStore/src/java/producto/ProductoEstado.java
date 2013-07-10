/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

import dominio.Imagen;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="pro_estados")
public class ProductoEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String estado;
    private String descripcion;

    public ProductoEstado() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pes_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="pes_descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name="pes_nombre", length=25)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        if (!(object instanceof Imagen)) {
            return false;
        }
        ProductoEstado other = (ProductoEstado) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return estado;
    }
    
}
