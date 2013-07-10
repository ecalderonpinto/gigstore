/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listacorreo;

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
public class ListaCorreoForm extends org.apache.struts.action.ActionForm {
    
    private String email;
    private boolean activo;
    private String sactivo;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getSactivo() {
        return sactivo;
    }

    public void setSactivo(String sactivo) {
        this.sactivo = sactivo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     */
    public ListaCorreoForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        MessageResources messages = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
                
        if (getEmail()==null || getEmail().length() < 1) {
            errors.add("email", new ActionMessage("errors.required", messages.getMessage(locale, "texto.email")));
        } else {
            Pattern pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
            Matcher mat = pat.matcher(getEmail());
            if (mat.find()) {
                email=getEmail();
            }else{
                errors.add("email", new ActionMessage("errors.email", getEmail()));
            }
        }
        
        try{
            if(getSactivo() == null)
                activo = false;
            else
                activo = true;
        }catch(Exception Ex){
            errors.add("activo", new ActionMessage("errors.required", messages.getMessage(locale, "texto.activo")));
        }
        
        return errors;
    }
}
