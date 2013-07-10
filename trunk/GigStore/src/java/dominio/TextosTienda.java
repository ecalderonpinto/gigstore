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
@Table(name="tienda_txt")
public class TextosTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String idioma;
    private String tituloTienda;
    private String subtituloTienda;
    private String textoBienvenidaUsr;
    private String mtHome;
    private String mtCategorias;
    private String mtProductos;
    private String mdHome;
    private String mdCategorias;
    private String mdProductos;
    private String emailNews;
    private String emailAlta;
    private String emailModificar;
    private String emailPedido;
    private String emailPassword;
    private BannersTienda banners;
    private TextosSeccionesTienda secciones;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="txt_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="txt_idioma", length=5)
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Column(name="txt_titulo", length=150)
    public String getTituloTienda() {
        return tituloTienda;
    }

    public void setTituloTienda(String nombreTienda) {
        this.tituloTienda = nombreTienda;
    }

    @Column(name="txt_subtitulo")
    public String getSubtituloTienda() {
        return subtituloTienda;
    }

    public void setSubtituloTienda(String subtituloTienda) {
        this.subtituloTienda = subtituloTienda;
    }

    @Column(name="txt_bienvenidausr")
    public String getTextoBienvenidaUsr() {
        return textoBienvenidaUsr;
    }

    public void setTextoBienvenidaUsr(String textoBienvenidaUsr) {
        this.textoBienvenidaUsr = textoBienvenidaUsr;
    }

    @Column(name="txt_mdcategorias")
    public String getMdCategorias() {
        return mdCategorias;
    }

    public void setMdCategorias(String mdCategorias) {
        this.mdCategorias = mdCategorias;
    }

    @Column(name="txt_mdhome")
    public String getMdHome() {
        return mdHome;
    }

    public void setMdHome(String mdHome) {
        this.mdHome = mdHome;
    }

    @Column(name="txt_mdproductos")
    public String getMdProductos() {
        return mdProductos;
    }

    public void setMdProductos(String mdProductos) {
        this.mdProductos = mdProductos;
    }

    @Column(name="txt_mtcategorias")
    public String getMtCategorias() {
        return mtCategorias;
    }

    public void setMtCategorias(String mtCategorias) {
        this.mtCategorias = mtCategorias;
    }

    @Column(name="txt_mthome")
    public String getMtHome() {
        return mtHome;
    }

    public void setMtHome(String mtHome) {
        this.mtHome = mtHome;
    }

    @Column(name="txt_mtproductos")
    public String getMtProductos() {
        return mtProductos;
    }

    public void setMtProductos(String mtProductos) {
        this.mtProductos = mtProductos;
    }

    @Lob
    @Column(name="txt_emailalta")
    public String getEmailAlta() {
        return emailAlta;
    }

    public void setEmailAlta(String emailAlta) {
        this.emailAlta = emailAlta;
    }

    @Lob
    @Column(name="txt_emailmodificar")
    public String getEmailModificar() {
        return emailModificar;
    }

    public void setEmailModificar(String emailModificar) {
        this.emailModificar = emailModificar;
    }

    @Lob
    @Column(name="txt_emailnews")
    public String getEmailNews() {
        return emailNews;
    }

    public void setEmailNews(String emailNews) {
        this.emailNews = emailNews;
    }

    @Lob
    @Column(name="txt_emailpass")
    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    @Lob
    @Column(name="txt_emailpedido")
    public String getEmailPedido() {
        return emailPedido;
    }

    public void setEmailPedido(String emailPedido) {
        this.emailPedido = emailPedido;
    }

    @OneToOne(mappedBy="textos", cascade=CascadeType.ALL)  
    public BannersTienda getBanners() {
        return banners;
    }

    public void setBanners(BannersTienda banners) {
        this.banners = banners;
    }

    @OneToOne(mappedBy="textos", cascade=CascadeType.ALL)  
    public TextosSeccionesTienda getSecciones() {
        return secciones;
    }

    public void setSecciones(TextosSeccionesTienda secciones) {
        this.secciones = secciones;
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
        if (!(object instanceof TextosTienda)) {
            return false;
        }
        TextosTienda other = (TextosTienda) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idioma + " - " + tituloTienda;
    }
}
