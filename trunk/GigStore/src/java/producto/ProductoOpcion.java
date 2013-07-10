/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.CollectionOfElements;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="pro_opciones")
public class ProductoOpcion implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Producto producto;
    private String opcion;
    private Set<String> traducciones;
    private BigDecimal precio;
    private int stock;
    private boolean activa;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pop_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @CollectionOfElements(fetch= FetchType.EAGER)
    @JoinTable(
            name="pro_opts_vals",
            joinColumns = @JoinColumn(name="pxo_prodoptid") 
    )
    @Column(name="pxo_traduccion", nullable=false)
    public Set<String> getTraducciones() {
        return traducciones;
    }

    public void setTraducciones(Set<String> traducciones) {
        this.traducciones = traducciones;
    }

    @Column(name="pop_opcion", length=90)
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    @Column(name="pop_precio",columnDefinition="DECIMAL(16,3)")
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="pop_productoid")
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Column(name="pop_stock")
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Column(name="pop_activa")
    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
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
        if (!(object instanceof ProductoOpcion)) {
            return false;
        }
        ProductoOpcion other = (ProductoOpcion) object;
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
