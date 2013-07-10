/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

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
public class AdministrarFormaPagoForm extends org.apache.struts.action.ActionForm {
    private boolean activarPayPal;
    private String sactivarPayPal;
    private boolean activarReembolso;
    private String sactivarReembolso;
    private boolean activarTransferencia;
    private String sactivarTransferencia;
    private boolean activarEfectivo;
    private String sactivarEfectivo;
    private String sprecioPayPal;
    private BigDecimal precioPayPal;
    private String sprecioReembolso;
    private BigDecimal precioReembolso;
    private String sprecioTransferencia;
    private BigDecimal precioTransferencia;
    private String sprecioEfectivo;
    private BigDecimal precioEfectivo;
    private String correoPayPal;
    private String numCuenta;

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

    public String getCorreoPayPal() {
        return correoPayPal;
    }

    public void setCorreoPayPal(String correoPayPal) {
        this.correoPayPal = correoPayPal;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
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

    public boolean isActivarEfectivo() {
        return activarEfectivo;
    }

    public void setActivarEfectivo(boolean activarEfectivo) {
        this.activarEfectivo = activarEfectivo;
    }

    public BigDecimal getPrecioEfectivo() {
        return precioEfectivo;
    }

    public void setPrecioEfectivo(BigDecimal precioEfectivo) {
        this.precioEfectivo = precioEfectivo;
    }

    public BigDecimal getPrecioPayPal() {
        return precioPayPal;
    }

    public void setPrecioPayPal(BigDecimal precioPayPal) {
        this.precioPayPal = precioPayPal;
    }

    public BigDecimal getPrecioReembolso() {
        return precioReembolso;
    }

    public void setPrecioReembolso(BigDecimal precioReembolso) {
        this.precioReembolso = precioReembolso;
    }

    public BigDecimal getPrecioTransferencia() {
        return precioTransferencia;
    }

    public void setPrecioTransferencia(BigDecimal precioTransferencia) {
        this.precioTransferencia = precioTransferencia;
    }

    public String getSactivarEfectivo() {
        return sactivarEfectivo;
    }

    public void setSactivarEfectivo(String sactivarEfectivo) {
        this.sactivarEfectivo = sactivarEfectivo;
    }

    public String getSprecioEfectivo() {
        return sprecioEfectivo;
    }

    public void setSprecioEfectivo(String sprecioEfectivo) {
        this.sprecioEfectivo = sprecioEfectivo;
    }

    public String getSprecioPayPal() {
        return sprecioPayPal;
    }

    public void setSprecioPayPal(String sprecioPayPal) {
        this.sprecioPayPal = sprecioPayPal;
    }

    public String getSprecioReembolso() {
        return sprecioReembolso;
    }

    public void setSprecioReembolso(String sprecioReembolso) {
        this.sprecioReembolso = sprecioReembolso;
    }

    public String getSprecioTransferencia() {
        return sprecioTransferencia;
    }

    public void setSprecioTransferencia(String sprecioTransferencia) {
        this.sprecioTransferencia = sprecioTransferencia;
    }
    
    /**
     *
     */
    public AdministrarFormaPagoForm() {
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
        
        if (getCorreoPayPal()==null || getCorreoPayPal().length() < 1) {
            errors.add("correoPayPal", new ActionMessage("errors.required", messages.getMessage(locale, "texto.correoPayPal")));
        } else {
            Pattern pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
            Matcher mat = pat.matcher(getCorreoPayPal());
            if (mat.find()) {
                correoPayPal=getCorreoPayPal();
            }else{
                errors.add("correoPayPal", new ActionMessage("errors.email", getCorreoPayPal()));
            }
        }
                
        if (getNumCuenta()== null || getNumCuenta().length() < 1) {
            errors.add("numCuenta", new ActionMessage("errors.required",messages.getMessage(locale, "texto.numCuenta")));
        }
        
        try{
            Pattern pat = Pattern.compile("^[0-9]+([,.][0-9]{1,2})?$");
            Matcher mat = pat.matcher(getSprecioPayPal());

            if (mat.find()) {
                precioPayPal=new BigDecimal(getSprecioPayPal().replace(',', '.'));
            }else{
                errors.add("precioPayPal", new ActionMessage("errors.precio.mask"));
            }
        }catch(Exception ex){
            errors.add("precioPayPal", new ActionMessage("errors.float", messages.getMessage(locale, "texto.precioPayPal")));
        }
        
        try{
            Pattern pat = Pattern.compile("^[0-9]+([,.][0-9]{1,2})?$");
            Matcher mat = pat.matcher(getSprecioReembolso());

            if (mat.find()) {
                precioReembolso=new BigDecimal(getSprecioReembolso().replace(',', '.'));
            }else{
                errors.add("precioReembolso", new ActionMessage("errors.precio.mask"));
            }
        }catch(Exception ex){
            errors.add("precioReembolso", new ActionMessage("errors.float", messages.getMessage(locale, "texto.precioReembolso")));
        }
        
        try{
            Pattern pat = Pattern.compile("^[0-9]+([,.][0-9]{1,2})?$");
            Matcher mat = pat.matcher(getSprecioTransferencia());

            if (mat.find()) {
                precioTransferencia=new BigDecimal(getSprecioTransferencia().replace(',', '.'));
            }else{
                errors.add("precioTransferencia", new ActionMessage("errors.precio.mask"));
            }
        }catch(Exception ex){
            errors.add("precioTransferencia", new ActionMessage("errors.float", messages.getMessage(locale, "texto.precioTransferencia")));
        }
        
        try{
            Pattern pat = Pattern.compile("^[0-9]+([,.][0-9]{1,2})?$");
            Matcher mat = pat.matcher(getSprecioEfectivo());

            if (mat.find()) {
                precioEfectivo=new BigDecimal(getSprecioEfectivo().replace(',', '.'));
            }else{
                errors.add("precioEfectivo", new ActionMessage("errors.precio.mask"));
            }
        }catch(Exception ex){
            errors.add("precioEfectivo", new ActionMessage("errors.float", messages.getMessage(locale, "texto.precioEfectivo")));
        }
        
        return errors;
    }
}
