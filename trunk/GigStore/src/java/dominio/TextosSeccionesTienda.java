/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="tienda_secciones")
public class TextosSeccionesTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String textoBienvenidaHome;
    private String textoConocenos;
    private String textoComprar;
    private String textoContactar;
    private String contactarDias;
    private String contactarHorario;
    private String contactarTelefono;
    private String textoPrivacidad;
    private String textoVenta;
    private String textoCostes;
    private TextosTienda textos;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sec_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="sec_diascontacto", length=50)
    public String getContactarDias() {
        return contactarDias;
    }

    public void setContactarDias(String contactarDias) {
        this.contactarDias = contactarDias;
    }

    @Column(name="sec_horariocontacto", length=50)
    public String getContactarHorario() {
        return contactarHorario;
    }

    public void setContactarHorario(String contactarHorario) {
        this.contactarHorario = contactarHorario;
    }

    @Column(name="sec_tlfcontacto", length=50)
    public String getContactarTelefono() {
        return contactarTelefono;
    }

    public void setContactarTelefono(String contactarTelefono) {
        this.contactarTelefono = contactarTelefono;
    }

    @Lob
    @Column(name="sec_txtbienvenidahome")
    public String getTextoBienvenidaHome() {
        return textoBienvenidaHome;
    }

    public void setTextoBienvenidaHome(String textoBienvenidaHome) {
        this.textoBienvenidaHome = textoBienvenidaHome;
    }

    @Lob
    @Column(name="sec_txtcomprar")
    public String getTextoComprar() {
        return textoComprar;
    }

    public void setTextoComprar(String textoComprar) {
        this.textoComprar = textoComprar;
    }

    @Lob
    @Column(name="sec_txtconocenos")
    public String getTextoConocenos() {
        return textoConocenos;
    }

    public void setTextoConocenos(String textoConocenos) {
        this.textoConocenos = textoConocenos;
    }

    @Lob
    @Column(name="sec_txtcontactar")
    public String getTextoContactar() {
        return textoContactar;
    }

    public void setTextoContactar(String textoContactar) {
        this.textoContactar = textoContactar;
    }

    @Lob
    @Column(name="sec_txtcostes")
    public String getTextoCostes() {
        return textoCostes;
    }

    public void setTextoCostes(String textoCostes) {
        this.textoCostes = textoCostes;
    }

    @Lob
    @Column(name="sec_txtprivacidad")
    public String getTextoPrivacidad() {
        return textoPrivacidad;
    }

    public void setTextoPrivacidad(String textoPrivacidad) {
        this.textoPrivacidad = textoPrivacidad;
    }

    @Lob
    @Column(name="sec_txtventa")
    public String getTextoVenta() {
        return textoVenta;
    }
    
    @OneToOne  
    @JoinColumn(name="sec_textosid")  
    public TextosTienda getTextos() {
        return textos;
    }

    public void setTextos(TextosTienda textos) {
        this.textos = textos;
    }

    public void setTextoVenta(String textoVenta) {
        this.textoVenta = textoVenta;
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
        if (!(object instanceof TextosSeccionesTienda)) {
            return false;
        }
        TextosSeccionesTienda other = (TextosSeccionesTienda) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return textoBienvenidaHome;
    }
    
}
