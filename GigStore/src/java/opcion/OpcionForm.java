/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opcion;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class OpcionForm extends org.apache.struts.action.ActionForm {
    private String opcion;
    private String descripcion;
    private Set<String> traducciones;
    private String[] sel_list;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Set<String> getTraducciones() {
        return traducciones;
    }

    public void setTraducciones(Set<String> traducciones) {
        this.traducciones = traducciones;
    }

    public String[] getSel_list() {
        return sel_list;
    }

    public void setSel_list(String[] sel_list) {
        this.sel_list = sel_list;
    }

    /**
     *
     */
    public OpcionForm() {
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
                
        if (getOpcion() == null || getOpcion().length() < 1) {
            errors.add("opcion", new ActionMessage("errors.required", messages.getMessage(locale, "texto.opcion")));
        }
               
        if (getSel_list() != null && getSel_list().length > 0) {
            traducciones=new HashSet<String>();
            for(int i=0;i<getSel_list().length;i++)
                traducciones.add(getSel_list()[i]);
        }        
        
        return errors;
    }
}
