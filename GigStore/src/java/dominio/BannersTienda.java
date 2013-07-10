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
@Table(name="tienda_banners")
public class BannersTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private boolean bannerHomeActivo;
    private boolean bannerFichaActivo;
    private boolean bannerLateral1Activo;
    private boolean bannerLateral2Activo;
    private byte[] bannerHome;
    private byte[] bannerFicha;
    private byte[] bannerLateral1;
    private byte[] bannerLateral2;
    private String bannerHomeTipo;
    private String bannerFichaTipo;
    private String bannerLateral1Tipo;
    private String bannerLateral2Tipo;
    private String bannerHomeAlt;
    private String bannerFichaAlt;
    private String bannerLateral1Alt;
    private String bannerLateral2Alt;
    private TextosTienda textos;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ban_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Lob
    @Basic(fetch= FetchType.LAZY)
    @Column(name="ban_bannerficha")
    public byte[] getBannerFicha() {
        return bannerFicha;
    }

    public void setBannerFicha(byte[] bannerFicha) {
        this.bannerFicha = bannerFicha;
    }

    @Lob
    @Basic(fetch= FetchType.LAZY)
    @Column(name="ban_bannerhome")
    public byte[] getBannerHome() {
        return bannerHome;
    }

    public void setBannerHome(byte[] bannerHome) {
        this.bannerHome = bannerHome;
    }

    @Lob
    @Basic(fetch= FetchType.LAZY)
    @Column(name="ban_bannerlateral1")
    public byte[] getBannerLateral1() {
        return bannerLateral1;
    }

    public void setBannerLateral1(byte[] bannerLateral1) {
        this.bannerLateral1 = bannerLateral1;
    }

    @Lob
    @Basic(fetch= FetchType.LAZY)
    @Column(name="ban_bannerlateral2")
    public byte[] getBannerLateral2() {
        return bannerLateral2;
    }

    public void setBannerLateral2(byte[] bannerLateral2) {
        this.bannerLateral2 = bannerLateral2;
    }

    @Column(name="ban_bannerfichaact")
    public boolean isBannerFichaActivo() {
        return bannerFichaActivo;
    }

    public void setBannerFichaActivo(boolean bannerFichaActivo) {
        this.bannerFichaActivo = bannerFichaActivo;
    }

    @Column(name="ban_bannerhomeact")
    public boolean isBannerHomeActivo() {
        return bannerHomeActivo;
    }

    public void setBannerHomeActivo(boolean bannerHomeActivo) {
        this.bannerHomeActivo = bannerHomeActivo;
    }

    @Column(name="ban_bannerlateral1act")
    public boolean isBannerLateral1Activo() {
        return bannerLateral1Activo;
    }

    public void setBannerLateral1Activo(boolean bannerLateral1Activo) {
        this.bannerLateral1Activo = bannerLateral1Activo;
    }

    @Column(name="ban_bannerlateral2act")
    public boolean isBannerLateral2Activo() {
        return bannerLateral2Activo;
    }

    public void setBannerLateral2Activo(boolean bannerLateral2Activo) {
        this.bannerLateral2Activo = bannerLateral2Activo;
    }

    @Column(name="ban_bannerfichatipo", length=25)
    public String getBannerFichaTipo() {
        return bannerFichaTipo;
    }

    public void setBannerFichaTipo(String bannerFichaTipo) {
        this.bannerFichaTipo = bannerFichaTipo;
    }

    @Column(name="ban_bannerhometipo", length=25)
    public String getBannerHomeTipo() {
        return bannerHomeTipo;
    }

    public void setBannerHomeTipo(String bannerHomeTipo) {
        this.bannerHomeTipo = bannerHomeTipo;
    }

    @Column(name="ban_bannerlateraltipo", length=25)
    public String getBannerLateral1Tipo() {
        return bannerLateral1Tipo;
    }

    public void setBannerLateral1Tipo(String bannerLateral1Tipo) {
        this.bannerLateral1Tipo = bannerLateral1Tipo;
    }

    @Column(name="ban_bannerlateral2tipo", length=25)
    public String getBannerLateral2Tipo() {
        return bannerLateral2Tipo;
    }

    public void setBannerLateral2Tipo(String bannerLateral2Tipo) {
        this.bannerLateral2Tipo = bannerLateral2Tipo;
    }

    @Column(name="ban_bannerfichaalt")
    public String getBannerFichaAlt() {
        return bannerFichaAlt;
    }

    public void setBannerFichaAlt(String bannerFichaAlt) {
        this.bannerFichaAlt = bannerFichaAlt;
    }

    @Column(name="ban_bannerhomealt")
    public String getBannerHomeAlt() {
        return bannerHomeAlt;
    }

    public void setBannerHomeAlt(String bannerHomeAlt) {
        this.bannerHomeAlt = bannerHomeAlt;
    }

    @Column(name="ban_bannerlateral1alt")
    public String getBannerLateral1Alt() {
        return bannerLateral1Alt;
    }

    public void setBannerLateral1Alt(String bannerLateral1Alt) {
        this.bannerLateral1Alt = bannerLateral1Alt;
    }

    @Column(name="ban_bannerlateral2alt")
    public String getBannerLateral2Alt() {
        return bannerLateral2Alt;
    }

    public void setBannerLateral2Alt(String bannerLateral2Alt) {
        this.bannerLateral2Alt = bannerLateral2Alt;
    }
    
    @OneToOne  
    @JoinColumn(name="ban_textosid")  
    public TextosTienda getTextos() {
        return textos;
    }

    public void setTextos(TextosTienda textos) {
        this.textos = textos;
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
        if (!(object instanceof BannersTienda)) {
            return false;
        }
        BannersTienda other = (BannersTienda) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return bannerHomeAlt;
    }
    
}
