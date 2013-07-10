/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opcion;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.CollectionOfElements;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="opciones")
public class Opcion implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String opcion;
    private String descripcion;
    private Set<String> traducciones;
    private Set<Valor> valores;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="opt_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="opt_opcion")
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    @Column(name="opt_descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @CollectionOfElements(fetch= FetchType.EAGER)
    @JoinTable(
            name="opt_traducciones",
            joinColumns = @JoinColumn(name="oxt_idopcion") 
    )
    @Column(name="oxt_traduccion", nullable=false)
    public Set<String> getTraducciones() {
        return traducciones;
    }

    public void setTraducciones(Set<String> traducciones) {
        this.traducciones = traducciones;
    }
    
    @OneToMany(mappedBy = "opcion",fetch= FetchType.EAGER)
    public Set<Valor> getValores() {
        return valores;
    }

    public void setValores(Set<Valor> valores) {
        this.valores = valores;
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
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return opcion;
    }
    
}
