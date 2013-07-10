/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

import categoria.Categoria;
import dominio.Imagen;
import dominio.TipoIVA;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="productos")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String referencia;
    private String nombre;
    private Set<ProductoDescripcion> descripciones;
    private BigDecimal precio;
    private boolean destacado;
    private TipoIVA iva;
    private ProductoEstado estado;
    private Set<Imagen> imagenes;
    private Categoria categoria;
    private int stock;
    private Set<ProductoOpcion> opciones;
    private String url;
    private String marca;
    private BigDecimal descuento;
    private boolean descporcentaje;

    public Producto() {
    }
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pro_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="pro_nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(mappedBy = "producto",fetch= FetchType.EAGER)
    public Set<ProductoDescripcion> getDescripciones() {
        return descripciones;
    }

    public void setDescripciones(Set<ProductoDescripcion> descripciones) {
        this.descripciones = descripciones;
    }

    @ManyToOne
    @JoinColumn(name="pro_estadoid")
    public ProductoEstado getEstado() {
        return estado;
    }

    public void setEstado(ProductoEstado estado) {
        this.estado = estado;
    }

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
        name="pro_imagenes",
        joinColumns={@JoinColumn(name="ixp_idproducto")},
        inverseJoinColumns={@JoinColumn(name="ixp_idimagen")}
    )
    public Set<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    @Column(name="pro_precio", columnDefinition="DECIMAL(16,3)")
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Column(name="pro_referencia", length=25)
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Column(name="pro_destacado")
    public boolean isDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    @ManyToOne
    @JoinColumn(name="pro_ivaid")
    public TipoIVA getIva() {
        return iva;
    }

    public void setIva(TipoIVA iva) {
        this.iva = iva;
    }
    
    @ManyToOne
    @JoinColumn(name="pro_categoriaid")
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Column(name="pro_stock")
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @OneToMany(mappedBy = "producto",fetch= FetchType.EAGER)
    public Set<ProductoOpcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(Set<ProductoOpcion> opciones) {
        this.opciones = opciones;
    }

    @Column(name="pro_descporcentaje")
    public boolean isDescporcentaje() {
        return descporcentaje;
    }

    public void setDescporcentaje(boolean descporcentaje) {
        this.descporcentaje = descporcentaje;
    }

    @Column(name="pro_descuento", columnDefinition="DECIMAL(16,3)")
    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    @Column(name="pro_marca", length=125)
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Column(name="pro_url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return referencia + " - " + nombre;
    }
    
}
