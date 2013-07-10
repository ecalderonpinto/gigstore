/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class ImagenForm extends org.apache.struts.action.ActionForm {
    private FormFile imagen;
    private String alt;
    private boolean principal;
    private String sprincipal;

    public FormFile getImagen() {
        return imagen;
    }

    public void setImagen(FormFile imagen) {
        this.imagen = imagen;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public String getSprincipal() {
        return sprincipal;
    }

    public void setSprincipal(String sprincipal) {
        this.sprincipal = sprincipal;
    }
    
    /**
     *
     */
    public ImagenForm() {
        super();
        // TODO Auto-generated constructor stub
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
        String accion=String.valueOf(request.getSession().getAttribute("accion"));
        
        if (accion.equalsIgnoreCase("alta") && (getImagen() == null || getImagen().getFileSize() < 1)) {
            errors.add("imagen", new ActionMessage("errors.required", messages.getMessage(locale, "texto.imagen")));
        }
        
        if (getImagen() != null && (getImagen().getFileSize() > 655350 || !(getImagen().getContentType().equalsIgnoreCase("image/gif") || getImagen().getContentType().equalsIgnoreCase("image/jpeg") || getImagen().getContentType().equalsIgnoreCase("image/png")))) {
            errors.add("imagen", new ActionMessage("errors.imagen", messages.getMessage(locale, "texto.imagen")));
        }
        
        try{
            if(getSprincipal() == null)
                principal = false;
            else
                principal = true;
        }catch(Exception Ex){
            errors.add("principal", new ActionMessage("errors.required", messages.getMessage(locale, "texto.principal")));
        }
        
        return errors;
    }
}
