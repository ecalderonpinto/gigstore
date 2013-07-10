/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package categoria;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="pro_cat_descripciones")
public class CategoriaDescripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Categoria categoria;
    private String idioma;
    private String nombre;
    private String descripcion;
    private String mtCategorias;
    private String mdCategorias;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cds_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="cds_categoriaid")
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Column(name="cds_nombre", length=25)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Lob
    @Column(name="cds_descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name="cds_idioma",length=5)
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Column(name="cds_mdcategorias")
    public String getMdCategorias() {
        return mdCategorias;
    }

    public void setMdCategorias(String mdCategorias) {
        this.mdCategorias = mdCategorias;
    }

    @Column(name="cds_mtcategorias")
    public String getMtCategorias() {
        return mtCategorias;
    }

    public void setMtCategorias(String mtCategorias) {
        this.mtCategorias = mtCategorias;
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
        if (!(object instanceof CategoriaDescripcion)) {
            return false;
        }
        CategoriaDescripcion other = (CategoriaDescripcion) object;
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
