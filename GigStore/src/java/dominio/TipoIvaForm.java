/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.math.BigDecimal;
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
public class TipoIvaForm extends org.apache.struts.action.ActionForm {
    
    private BigDecimal iva;
    private String siva;
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public String getSiva() {
        return siva;
    }

    public void setSiva(String siva) {
        this.siva = siva;
    }
    
    /**
     *
     */
    public TipoIvaForm() {
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
        
        if (getSiva() == null || getSiva().length() < 1) {
            errors.add("iva", new ActionMessage("errors.required",messages.getMessage(locale, "texto.iva")));
        }else{
            try{
                iva=new BigDecimal(getSiva());
            }catch(Exception Ex){
                errors.add("iva", new ActionMessage("errors.float",messages.getMessage(locale, "texto.iva")));
            }
        }
        
        return errors;
    }
}
