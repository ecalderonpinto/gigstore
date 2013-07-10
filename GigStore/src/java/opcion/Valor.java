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
@Table(name="valores")
public class Valor implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String valor;
    private Set<String> traducciones;
    private Opcion opcion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="val_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @CollectionOfElements(fetch= FetchType.EAGER)
    @JoinTable(
        name="val_traducciones",
        joinColumns = @JoinColumn(name="vxt_idvalor") 
    )
    @Column(name="vxt_traduccion", nullable=false)
    public Set<String> getTraducciones() {
        return traducciones;
    }

    public void setTraducciones(Set<String> traducciones) {
        this.traducciones = traducciones;
    }

    @Column(name="val_valor")
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="val_opcionid")
    public Opcion getOpcion() {
        return opcion;
    }

    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
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
        if (!(object instanceof Valor)) {
            return false;
        }
        Valor other = (Valor) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return valor;
    }
    
}
