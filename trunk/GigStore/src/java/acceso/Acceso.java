/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="accesos")
public class Acceso implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String uri;
    private Date cuando;
    private String ip;
    private int puerto;
    private long duracion;
    private String origen;
    private String accion;
    private String sesionId;
    private int usuarioId;
    private int productoId;
    private String navegador;
    private String plataforma;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="acc_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="acc_fechahora")
    public Date getCuando() {
        return cuando;
    }

    public void setCuando(Date cuando) {
        this.cuando = cuando;
    }
    
    @Column(name="acc_duracion")
    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }
    
    @Column(name="acc_ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name="acc_puerto")
    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    @Column(name="acc_uri")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Column(name="acc_accion", length=25)
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @Column(name="acc_navegador")
    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }

    @Column(name="acc_origen")
    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    @Column(name="acc_plataforma")
    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Column(name="acc_sesionid")
    public String getSesionId() {
        return sesionId;
    }

    public void setSesionId(String sesionId) {
        this.sesionId = sesionId;
    }

    @Column(name="acc_usuarioid")
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Column(name="acc_productoid")
    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
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
        if (!(object instanceof Acceso)) {
            return false;
        }
        Acceso other = (Acceso) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sesionId + "-" + cuando.toString();
    }
    
}
