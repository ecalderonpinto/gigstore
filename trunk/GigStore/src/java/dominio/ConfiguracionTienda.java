/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="tienda_cfg")
public class ConfiguracionTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombreEmpresa;
    private String direccionEmpresa;
    private String telefonoEmpresa;
    private String emailSistema;
    private String idioma;
    private int nRegsPag;
    private String moneda;
    private boolean activarPayPal;
    private boolean activarReembolso;
    private boolean activarTransferencia;
    private boolean activarEfectivo;
    private BigDecimal precioPayPal;
    private BigDecimal precioReembolso;
    private BigDecimal precioTransferencia;
    private BigDecimal precioEfectivo;
    private String correoPayPal;
    private String numCuenta;
    private boolean ocultarProducto;
    private boolean ocultarBotonCompra;
    private int stockMin;
    private boolean mostrarIVAIncluido;
    private boolean usarDirFactura;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cfg_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="cfg_emailsistema", length=150)
    public String getEmailSistema() {
        return emailSistema;
    }

    public void setEmailSistema(String emailSistema) {
        this.emailSistema = emailSistema;
    }

    @Column(name="cfg_monedasistema", length=10)
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Column(name="cfg_nregs")
    public int getnRegsPag() {
        return nRegsPag;
    }

    public void setnRegsPag(int nRegsPag) {
        this.nRegsPag = nRegsPag;
    }

    @Column(name="cfg_paypal")
    public boolean isActivarPayPal() {
        return activarPayPal;
    }

    public void setActivarPayPal(boolean activarPayPal) {
        this.activarPayPal = activarPayPal;
    }

    @Column(name="cfg_reembolso")
    public boolean isActivarReembolso() {
        return activarReembolso;
    }

    public void setActivarReembolso(boolean activarReembolso) {
        this.activarReembolso = activarReembolso;
    }

    @Column(name="cfg_transferencia")
    public boolean isActivarTransferencia() {
        return activarTransferencia;
    }

    public void setActivarTransferencia(boolean activarTransferencia) {
        this.activarTransferencia = activarTransferencia;
    }

    @Column(name="cfg_efectivo")
    public boolean isActivarEfectivo() {
        return activarEfectivo;
    }

    public void setActivarEfectivo(boolean activarEfectivo) {
        this.activarEfectivo = activarEfectivo;
    }

    @Column(name="cfg_precioefectivo",columnDefinition="DECIMAL(16,3)")
    public BigDecimal getPrecioEfectivo() {
        return precioEfectivo;
    }

    public void setPrecioEfectivo(BigDecimal precioEfectivo) {
        this.precioEfectivo = precioEfectivo;
    }

    @Column(name="cfg_preciopaypal",columnDefinition="DECIMAL(16,3)")
    public BigDecimal getPrecioPayPal() {
        return precioPayPal;
    }

    public void setPrecioPayPal(BigDecimal precioPayPal) {
        this.precioPayPal = precioPayPal;
    }

    @Column(name="cfg_precioreembolso",columnDefinition="DECIMAL(16,3)")
    public BigDecimal getPrecioReembolso() {
        return precioReembolso;
    }

    public void setPrecioReembolso(BigDecimal precioReembolso) {
        this.precioReembolso = precioReembolso;
    }

    @Column(name="cfg_preciotransferencia",columnDefinition="DECIMAL(16,3)")
    public BigDecimal getPrecioTransferencia() {
        return precioTransferencia;
    }

    public void setPrecioTransferencia(BigDecimal precioTransferencia) {
        this.precioTransferencia = precioTransferencia;
    }

    @Column(name="cfg_correopaypal")
    public String getCorreoPayPal() {
        return correoPayPal;
    }

    public void setCorreoPayPal(String correoPayPal) {
        this.correoPayPal = correoPayPal;
    }

    @Column(name="cfg_dirempresa")
    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    @Column(name="cfg_idiomabase")
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Column(name="cfg_ivainc")
    public boolean isMostrarIVAIncluido() {
        return mostrarIVAIncluido;
    }

    public void setMostrarIVAIncluido(boolean mostrarIVAIncluido) {
        this.mostrarIVAIncluido = mostrarIVAIncluido;
    }

    @Column(name="cfg_nombreempresa")
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    @Column(name="cfg_numcuenta")
    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    @Column(name="cfg_ocultarboton")
    public boolean isOcultarBotonCompra() {
        return ocultarBotonCompra;
    }

    public void setOcultarBotonCompra(boolean ocultarBotonCompra) {
        this.ocultarBotonCompra = ocultarBotonCompra;
    }

    @Column(name="cfg_ocultarprod")
    public boolean isOcultarProducto() {
        return ocultarProducto;
    }

    public void setOcultarProducto(boolean ocultarProducto) {
        this.ocultarProducto = ocultarProducto;
    }

    @Column(name="cfg_stockmin")
    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    @Column(name="cfg_telfempresa")
    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    @Column(name="cfg_usardirfact")
    public boolean isUsarDirFactura() {
        return usarDirFactura;
    }

    public void setUsarDirFactura(boolean usarDirFactura) {
        this.usarDirFactura = usarDirFactura;
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
        if (!(object instanceof ConfiguracionTienda)) {
            return false;
        }
        ConfiguracionTienda other = (ConfiguracionTienda) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return emailSistema;
    }    
}
