/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import java.math.BigDecimal;
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
public class CosteEnvioForm extends org.apache.struts.action.ActionForm {
    private String tipo;
    private String descripcion;
    private String sprecio;
    private BigDecimal precio;
    private boolean activarPayPal;
    private String sactivarPayPal;
    private boolean activarReembolso;
    private String sactivarReembolso;
    private boolean activarTransferencia;
    private String sactivarTransferencia;
    private boolean activarEfectivo;
    private String sactivarEfectivo;
    private boolean activo;
    private String sactivo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getSprecio() {
        return sprecio;
    }

    public void setSprecio(String sprecio) {
        this.sprecio = sprecio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isActivarEfectivo() {
        return activarEfectivo;
    }

    public void setActivarEfectivo(boolean activarEfectivo) {
        this.activarEfectivo = activarEfectivo;
    }

    public boolean isActivarPayPal() {
        return activarPayPal;
    }

    public void setActivarPayPal(boolean activarPayPal) {
        this.activarPayPal = activarPayPal;
    }

    public boolean isActivarReembolso() {
        return activarReembolso;
    }

    public void setActivarReembolso(boolean activarReembolso) {
        this.activarReembolso = activarReembolso;
    }

    public boolean isActivarTransferencia() {
        return activarTransferencia;
    }

    public void setActivarTransferencia(boolean activarTransferencia) {
        this.activarTransferencia = activarTransferencia;
    }

    public String getSactivarEfectivo() {
        return sactivarEfectivo;
    }

    public void setSactivarEfectivo(String sactivarEfectivo) {
        this.sactivarEfectivo = sactivarEfectivo;
    }

    public String getSactivarPayPal() {
        return sactivarPayPal;
    }

    public void setSactivarPayPal(String sactivarPayPal) {
        this.sactivarPayPal = sactivarPayPal;
    }

    public String getSactivarReembolso() {
        return sactivarReembolso;
    }

    public void setSactivarReembolso(String sactivarReembolso) {
        this.sactivarReembolso = sactivarReembolso;
    }

    public String getSactivarTransferencia() {
        return sactivarTransferencia;
    }

    public void setSactivarTransferencia(String sactivarTransferencia) {
        this.sactivarTransferencia = sactivarTransferencia;
    }

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
    
    /**
     *
     */
    public CosteEnvioForm() {
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
        
        if (getTipo() == null || getTipo().length() < 1) {
            errors.add("tipo", new ActionMessage("errors.required", messages.getMessage(locale, "texto.tipo")));
        }
        
        if (getSprecio() == null || getSprecio().length() < 1) {
            errors.add("precio", new ActionMessage("errors.required", messages.getMessage(locale, "texto.precio")));
        }else{
            try{
                Pattern pat = Pattern.compile("^[0-9]+([,.][0-9]{1,2})?$");
                Matcher mat = pat.matcher(getSprecio());
                
                if (mat.find()) {
                    precio=new BigDecimal(getSprecio().replace(',', '.'));
                }else{
                    errors.add("precio", new ActionMessage("errors.precio.mask"));
                }
            }catch(Exception ex){
                errors.add("precio", new ActionMessage("errors.float", messages.getMessage(locale, "texto.precio")));
            }
        }
        
        try{
            if(getSactivarPayPal() == null)
                activarPayPal = false;
            else
                activarPayPal = true;
        }catch(Exception Ex){
            errors.add("activarPayPal", new ActionMessage("errors.required", messages.getMessage(locale, "texto.activarPayPal")));
        }
        
        try{
            if(getSactivarReembolso() == null)
                activarReembolso = false;
            else
                activarReembolso = true;
        }catch(Exception Ex){
            errors.add("activarReembolso", new ActionMessage("errors.required", messages.getMessage(locale, "texto.activarReembolso")));
        }
        
        try{
            if(getSactivarTransferencia() == null)
                activarTransferencia = false;
            else
                activarTransferencia = true;
        }catch(Exception Ex){
            errors.add("activarTransferencia", new ActionMessage("errors.required", messages.getMessage(locale, "texto.activarTransferencia")));
        }
        
        try{
            if(getSactivarEfectivo() == null)
                activarEfectivo = false;
            else
                activarEfectivo = true;
        }catch(Exception Ex){
            errors.add("activarEfectivo", new ActionMessage("errors.required", messages.getMessage(locale, "texto.activarEfectivo")));
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
