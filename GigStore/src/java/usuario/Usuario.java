/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import direccion.Direccion;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import pedido.Pedido;
import producto.Producto;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nif;
    private String nombre; 
    private String apellidos;
    private Date fechaNacimiento;
    private String telefono;
    private String email;
    private String usuario;
    private String contrasenya;
    private String rol;
    private Date fechaAlta;
    private UsuarioEstado estado;
    private Set<Direccion> direcciones;
    private Set<Pedido> pedidos;
    private Set<Producto> seguimiento;

    public Usuario() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usr_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="usr_nif", length=25)
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Column(name="usr_apellidos", length=50)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Column(name="usr_contrasenya", length=25)
    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "usr_direcciones", 
        joinColumns = @JoinColumn(name = "uxd_usuarioid"),
        inverseJoinColumns = @JoinColumn(name = "uxd_direccionid")
    )     
    public Set<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Set<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Column(name="usr_email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @ManyToOne
    @JoinColumn(name="usr_estadoid")
    public UsuarioEstado getEstado() {
        return estado;
    }

    public void setEstado(UsuarioEstado estado) {
        this.estado = estado;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="usr_fechanac")
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name="usr_nombre", length=25)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(mappedBy="usuario", fetch= FetchType.EAGER)
    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "usr_seguimientos", 
        joinColumns = @JoinColumn(name = "seg_usuarioid"),
        inverseJoinColumns = @JoinColumn(name = "seg_productoid")
    )     
    public Set<Producto> getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(Set<Producto> seguimiento) {
        this.seguimiento = seguimiento;
    }

    @Column(name="usr_rol", length=15)
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Column(name="usr_telf", length=12)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name="usr_usuario")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="usr_fechaalta")
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " - " + nif  + " "  + nombre  + " " + apellidos  + " " + email;
    }
    
}
