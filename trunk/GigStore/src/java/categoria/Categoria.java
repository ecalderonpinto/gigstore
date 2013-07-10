/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package categoria;

import dominio.Imagen;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="pro_categorias")
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private boolean activo;
    private Set<CategoriaDescripcion> descripciones;
    private Imagen imagen;
//    private int idmadre; /* ID de la Categoría Madre si es subcategoría */
    private int nodo; /* Nivel de la subcategoria si lo es */
    private Categoria madre;
    private Set<Categoria> subcategorias;
    private BigDecimal descuento;
    private boolean descporcentaje;
    
    public Categoria() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prc_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="prc_nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name="prc_activo")
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @OneToMany(mappedBy = "categoria", fetch= FetchType.EAGER)
    public Set<CategoriaDescripcion> getDescripciones() {
        return descripciones;
    }

    public void setDescripciones(Set<CategoriaDescripcion> descripciones) {
        this.descripciones = descripciones;
    }

    @OneToOne
    @JoinColumn(name = "prc_imagenid")
    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

//    @Column(name="prc_madreid")
//    public int getIdmadre() {
//        return idmadre;
//    }
//
//    public void setIdmadre(int idmadre) {
//        this.idmadre = idmadre;
//    }

    @Column(name="prc_nodo")
    public int getNodo() {
        return nodo;
    }

    public void setNodo(int nodo) {
        this.nodo = nodo;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="prc_madreid")
    public Categoria getMadre() {
        return madre;
    }

    public void setMadre(Categoria madre) {
        this.madre = madre;
    }

    @OneToMany(mappedBy = "madre", fetch= FetchType.EAGER)
    public Set<Categoria> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(Set<Categoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

    @Column(name="prc_descporcentaje")
    public boolean isDescporcentaje() {
        return descporcentaje;
    }

    public void setDescporcentaje(boolean descporcentaje) {
        this.descporcentaje = descporcentaje;
    }

    @Column(name="prc_descuento", columnDefinition="DECIMAL(16,3)")
    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
