/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="costes_envio")
public class CosteEnvio implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String tipo;
    private String descripcion;
    private BigDecimal precio;
    private boolean activarPayPal;
    private boolean activarReembolso;
    private boolean activarTransferencia;
    private boolean activarEfectivo;
    private boolean activo;

    public CosteEnvio() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="env_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Lob
    @Column(name="env_descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name="env_precio",columnDefinition="DECIMAL(16,3)")
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Column(name="env_tipo", length=25)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(name="env_efectivo")
    public boolean isActivarEfectivo() {
        return activarEfectivo;
    }

    public void setActivarEfectivo(boolean activarEfectivo) {
        this.activarEfectivo = activarEfectivo;
    }

    @Column(name="env_paypal")
    public boolean isActivarPayPal() {
        return activarPayPal;
    }

    public void setActivarPayPal(boolean activarPayPal) {
        this.activarPayPal = activarPayPal;
    }

    @Column(name="env_reembolso")
    public boolean isActivarReembolso() {
        return activarReembolso;
    }

    public void setActivarReembolso(boolean activarReembolso) {
        this.activarReembolso = activarReembolso;
    }

    @Column(name="env_transferencia")
    public boolean isActivarTransferencia() {
        return activarTransferencia;
    }

    public void setActivarTransferencia(boolean activarTransferencia) {
        this.activarTransferencia = activarTransferencia;
    }

    @Column(name="env_activo")
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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
        if (!(object instanceof CosteEnvio)) {
            return false;
        }
        CosteEnvio other = (CosteEnvio) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipo;
    }
    
}
