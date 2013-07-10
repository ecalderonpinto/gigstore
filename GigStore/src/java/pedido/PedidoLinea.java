/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import producto.Producto;
import producto.ProductoOpcion;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="ped_lineas")
public class PedidoLinea implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int nlinea;
    private Pedido pedido;
    private Producto producto;
    private int cantidad;
    private BigDecimal precio;
    private BigDecimal iva; //Tipo de IVA (%)
    private BigDecimal subtotal;
    private ProductoOpcion opcion;
    private String opcionProducto;

    public PedidoLinea() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pln_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="pln_nlinea")
    public int getNlinea() {
        return nlinea;
    }

    public void setNlinea(int nlinea) {
        this.nlinea = nlinea;
    }

    @ManyToOne
    @JoinColumn(name="pln_pedidoid"/*,referencedColumnName="pln_pedidoid"*/)
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Column(name="pln_cantidad")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name="pln_iva",columnDefinition="DECIMAL(15,2)")
    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    @Column(name="pln_precio",columnDefinition="DECIMAL(15,2)")
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="pln_productoid")
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="pln_opcionid")
    public ProductoOpcion getOpcion() {
        return opcion;
    }

    public void setOpcion(ProductoOpcion opcion) {
        this.opcion = opcion;
    }

    @Column(name="pln_subtotal",columnDefinition="DECIMAL(15,2)")
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Column(name="pln_opcionproducto")
    public String getOpcionProducto() {
        return opcionProducto;
    }

    public void setOpcionProducto(String opcionProducto) {
        this.opcionProducto = opcionProducto;
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
        if (!(object instanceof PedidoLinea)) {
            return false;
        }
        PedidoLinea other = (PedidoLinea) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cantidad + " - " + producto.getNombre() + " - " + producto.getReferencia();
    }
    
}
