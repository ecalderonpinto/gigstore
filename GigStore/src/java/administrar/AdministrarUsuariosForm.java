/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarUsuariosForm extends org.apache.struts.action.ActionForm {
    private final static String FORMATO_FECHA="dd/MM/yyyy";
    private String nif;
    private String nombre; 
    private String apellidos;    
    private String sfechaNacimiento;
    private Date fechaNacimiento;
    private String telefono;
    private String email;
    private String usuario;
    private String contrasenya1;
    private String contrasenya2;
    private String rol;
    private String sfechaAlta;
    private Date fechaAlta;
    private String sestado;
    private int estado;

    public AdministrarUsuariosForm() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasenya1() {
        return contrasenya1;
    }

    public void setContrasenya1(String contrasenya) {
        this.contrasenya1 = contrasenya;
    }

    public String getContrasenya2() {
        return contrasenya2;
    }

    public void setContrasenya2(String contrasenya) {
        this.contrasenya2 = contrasenya;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSfechaNacimiento() {
        return sfechaNacimiento;
    }

    public void setSfechaNacimiento(String sfechaNacimiento) {
        this.sfechaNacimiento = sfechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono1) {
        this.telefono = telefono1;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getSestado() {
        return sestado;
    }

    public void setSestado(String sestado) {
        this.sestado = sestado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getSfechaAlta() {
        return sfechaAlta;
    }

    public void setSfechaAlta(String sfechaAlta) {
        this.sfechaAlta = sfechaAlta;
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        MessageResources messages = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        SimpleDateFormat df1=new SimpleDateFormat(FORMATO_FECHA);
        
        if (getNombre()==null || getNombre().length() < 1) {
            errors.add("nombre", new ActionMessage("errors.required", messages.getMessage(locale, "texto.nombre")));
        }
        
        if (getApellidos()==null || getApellidos().length() < 1) {
            errors.add("apellidos", new ActionMessage("errors.required", messages.getMessage(locale, "texto.apellidos")));
        }
        
        if (getSfechaNacimiento()!=null && getSfechaNacimiento().length() > 0) {
            try {
                fechaNacimiento=df1.parse(getSfechaNacimiento());
            } catch (Exception ex) {
                errors.add("fechaNacimiento", new ActionMessage("errors.date", getSfechaNacimiento()));
            }
        }
        
        try {
            estado=Integer.parseInt(getSestado());
            if (estado < 1) {
                errors.add("estado", new ActionMessage("errors.required", messages.getMessage(locale, "texto.estado")));
            }            
        } catch (Exception ex) {
            errors.add("estado", new ActionMessage("errors.integer", messages.getMessage(locale, "texto.estado")));
        }
        
        if (getContrasenya1()==null || getContrasenya1().length() < 1) {
            errors.add("contrasenya", new ActionMessage("errors.required", messages.getMessage(locale, "texto.pass")));
        } else {
            if (getContrasenya1().length() < 6) {
                errors.add("contrasenya", new ActionMessage("errors.minlength", messages.getMessage(locale, "texto.pass"),"6"));
            } else {
                if (getContrasenya1().equals(getContrasenya2())==false) {
                    errors.add("contrasenya", new ActionMessage("errors.equals", messages.getMessage(locale, "texto.pass"), messages.getMessage(locale, "texto.pass2")));
                } 
            }
        }
        
        return errors;
    }
}
