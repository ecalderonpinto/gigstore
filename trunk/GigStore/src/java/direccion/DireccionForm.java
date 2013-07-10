/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package direccion;

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
public class DireccionForm extends org.apache.struts.action.ActionForm {
    private String direcciona;
    private String direccionb;
    private String cp;
    private String poblacion;
    private String provincia;
    private String pais;
    private String observaciones;
    /**
     *
     */
    public DireccionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getDirecciona() {
        return direcciona;
    }

    public void setDirecciona(String direcciona) {
        this.direcciona = direcciona;
    }

    public String getDireccionb() {
        return direccionb;
    }

    public void setDireccionb(String direccionb) {
        this.direccionb = direccionb;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
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
        
        if (getCp() == null || getCp().length() < 1) {
            errors.add("direcciona", new ActionMessage("errors.required", messages.getMessage(locale, "texto.direcciona")));
        }
        if (getCp() == null || getCp().length() < 1) {
            errors.add("cp", new ActionMessage("errors.required", messages.getMessage(locale, "texto.cp")));
        }
        
        return errors;
    }
}
