/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

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
public class PedidoForm extends org.apache.struts.action.ActionForm {
    private String stipoenv;
    private int tipoenv;
    private String direccionFactura;
    private String direccionEnvio;
    private String formaPago;

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getDireccionFactura() {
        return direccionFactura;
    }

    public void setDireccionFactura(String direccionFactura) {
        this.direccionFactura = direccionFactura;
    }

    public String getStipoenv() {
        return stipoenv;
    }

    public void setStipoenv(String stipoenv) {
        this.stipoenv = stipoenv;
    }

    public int getTipoenv() {
        return tipoenv;
    }

    public void setTipoenv(int tipoenv) {
        this.tipoenv = tipoenv;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    /**
     *
     */
    public PedidoForm() {
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
        
        if (getDireccionEnvio() == null || getDireccionEnvio().length() < 1 || getDireccionEnvio().equals("0")) {
            errors.add("direccionEnvio", new ActionMessage("errors.required", messages.getMessage(locale, "texto.direccionEnvio")));
        }
        
        if (getStipoenv() == null || getStipoenv().length() < 1 || getStipoenv().equals("0")) {
            errors.add("tipoenvio", new ActionMessage("errors.required", messages.getMessage(locale, "texto.tipoenvio")));
        }else{
            try{
                tipoenv=Integer.parseInt(getStipoenv());
                if(formaPago.equalsIgnoreCase("ef") && tipoenv!=1)
                    errors.add("tipoenvio", new ActionMessage("errors.efectivo"));
                if(formaPago.equalsIgnoreCase("cr") && tipoenv==1)
                    errors.add("tipoenvio", new ActionMessage("errors.reembolso"));
            }catch(Exception ex){
                errors.add("tipoenvio", new ActionMessage("errors.required", messages.getMessage(locale, "texto.tipoenvio")));
            }
        }
        
        if(tipoenv==1){
            setDireccionEnvio(messages.getMessage(locale, "texto.recogida"));
            setDireccionFactura(messages.getMessage(locale, "texto.recogida"));
        }
        return errors;
    }
}
