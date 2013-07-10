/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

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
public class ProductoEstadoForm extends org.apache.struts.action.ActionForm {
    private String estado;
    private String descripcion;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     */
    public ProductoEstadoForm() {
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
        MessageResources messages = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        ActionErrors errors = new ActionErrors();
        if (getEstado() == null || getEstado().length() < 1) {
            errors.add("estado", new ActionMessage("errors.required", messages.getMessage(locale, "texto.estado")));
        }
        return errors;
    }
}
