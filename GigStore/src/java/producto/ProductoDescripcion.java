/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="pro_descripciones")
public class ProductoDescripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Producto producto;
    private String idioma;
    private String nombre;
    private String formato;
    private String dimensiones;
    private String etiquetas;
    private String descripcion;
    private String mtProductos;
    private String mdProductos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pds_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="pds_nombre", length=50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Lob
    @Column(name="pds_descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name="pds_idioma",length=5)
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="pds_productoid")
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Column(name="pds_dimensiones")
    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    @Column(name="pds_formato")
    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    @Column(name="pds_etiquetas")
    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Column(name="pds_mdproductos")
    public String getMdProductos() {
        return mdProductos;
    }

    public void setMdProductos(String mdProductos) {
        this.mdProductos = mdProductos;
    }

    @Column(name="pds_mtproductos")
    public String getMtProductos() {
        return mtProductos;
    }

    public void setMtProductos(String mtProductos) {
        this.mtProductos = mtProductos;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoDescripcion)) {
            return false;
        }
        ProductoDescripcion other = (ProductoDescripcion) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
