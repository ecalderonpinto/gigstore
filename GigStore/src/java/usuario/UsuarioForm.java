/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class UsuarioForm extends org.apache.struts.action.ActionForm {
    private String nif;
    private String nombre; 
    private String apellidos;    
    private String sfechaNacimiento;
    private Date fechaNacimiento;
    private String telefono;
    private String email;
    private String contrasenya1;
    private String contrasenya2;

    public UsuarioForm() {
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        UsuarioDAO userDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        
        if (getNombre()==null || getNombre().length() < 1) {
            errors.add("nombre", new ActionMessage("errors.required", messages.getMessage(locale, "texto.nombre")));
        }
        
        if (getApellidos()==null || getApellidos().length() < 1) {
            errors.add("apellidos", new ActionMessage("errors.required", messages.getMessage(locale, "texto.apellidos")));
        }
        
        if (getSfechaNacimiento()!=null && getSfechaNacimiento().length() > 0) {
            try {
                fechaNacimiento=df.parse(getSfechaNacimiento());
            } catch (Exception ex) {
                errors.add("fechaNacimiento", new ActionMessage("errors.date", getSfechaNacimiento()));
            }
        }
                
        if (getEmail()==null || getEmail().length() < 1) {
            errors.add("email", new ActionMessage("errors.required", messages.getMessage(locale, "texto.email")));
        } else {
            Pattern pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
            Matcher mat = pat.matcher(getEmail());
            if (mat.find()) {
                boolean existe=false;
                if(userDAO.existeUsuario(getEmail()) && request.getSession().getAttribute("accion").toString().equals("alta"))
                    existe=true;
                if(!existe)
                    email=getEmail();
                else
                    errors.add("email", new ActionMessage("errors.email_exists", getEmail()));
            }else{
                errors.add("email", new ActionMessage("errors.email", getEmail()));
            }
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
