/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

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
public class AdministrarTiendaForm extends org.apache.struts.action.ActionForm {
    private int nRegsPag;
    private String snRegsPag;
    private String emailSistema;
    private String moneda;
    private String nombreEmpresa;
    private String direccionEmpresa;
    private String telefonoEmpresa;
    private String idioma;
    private boolean ocultarProducto;
    private String socultarProducto;
    private boolean ocultarBotonCompra;
    private String socultarBotonCompra;
    private int stockMin;
    private String sstockMin;
    private boolean mostrarIVAIncluido;
    private String smostrarIVAIncluido;
    private boolean usarDirFactura;
    private String susarDirFactura;

    public String getEmailSistema() {
        return emailSistema;
    }

    public void setEmailSistema(String emailSistema) {
        this.emailSistema = emailSistema;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getnRegsPag() {
        return nRegsPag;
    }

    public void setnRegsPag(int nRegsPag) {
        this.nRegsPag = nRegsPag;
    }

    public String getSnRegsPag() {
        return snRegsPag;
    }

    public void setSnRegsPag(String snRegsPag) {
        this.snRegsPag = snRegsPag;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isMostrarIVAIncluido() {
        return mostrarIVAIncluido;
    }

    public void setMostrarIVAIncluido(boolean mostrarIVAIncluido) {
        this.mostrarIVAIncluido = mostrarIVAIncluido;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public boolean isOcultarBotonCompra() {
        return ocultarBotonCompra;
    }

    public void setOcultarBotonCompra(boolean ocultarBotonCompra) {
        this.ocultarBotonCompra = ocultarBotonCompra;
    }

    public boolean isOcultarProducto() {
        return ocultarProducto;
    }

    public void setOcultarProducto(boolean ocultarProducto) {
        this.ocultarProducto = ocultarProducto;
    }

    public String getSmostrarIVAIncluido() {
        return smostrarIVAIncluido;
    }

    public void setSmostrarIVAIncluido(String smostrarIVAIncluido) {
        this.smostrarIVAIncluido = smostrarIVAIncluido;
    }

    public String getSocultarBotonCompra() {
        return socultarBotonCompra;
    }

    public void setSocultarBotonCompra(String socultarBotonCompra) {
        this.socultarBotonCompra = socultarBotonCompra;
    }

    public String getSocultarProducto() {
        return socultarProducto;
    }

    public void setSocultarProducto(String socultarProducto) {
        this.socultarProducto = socultarProducto;
    }

    public String getSstockMin() {
        return sstockMin;
    }

    public void setSstockMin(String sstockMin) {
        this.sstockMin = sstockMin;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getSusarDirFactura() {
        return susarDirFactura;
    }

    public void setSusarDirFactura(String susarDirFactura) {
        this.susarDirFactura = susarDirFactura;
    }

    public boolean isUsarDirFactura() {
        return usarDirFactura;
    }

    public void setUsarDirFactura(boolean usarDirFactura) {
        this.usarDirFactura = usarDirFactura;
    }
    
    /**
     *
     */
    public AdministrarTiendaForm() {
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
        
        if (getEmailSistema()==null || getEmailSistema().length() < 1) {
            errors.add("emailSistema", new ActionMessage("errors.required", messages.getMessage(locale, "texto.emailsistema")));
        } else {
            Pattern pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
            Matcher mat = pat.matcher(getEmailSistema());
            if (mat.find()) {
                emailSistema=getEmailSistema();
            }else{
                errors.add("emailSistema", new ActionMessage("errors.email", getEmailSistema()));
            }
        }
        
        if (getMoneda()== null || getMoneda().length() < 1) {
            errors.add("moneda", new ActionMessage("errors.required",messages.getMessage(locale, "texto.monedasistema")));
        }
        
        if (getSnRegsPag() != null && getSnRegsPag().length() > 0) {
            try{
                nRegsPag= Integer.parseInt(getSnRegsPag());
            }catch(Exception Ex){
                errors.add("nRegsPag", new ActionMessage("errors.integer",messages.getMessage(locale, "texto.nregs")));
            }
        } else {
                errors.add("nRegsPag", new ActionMessage("errors.required",messages.getMessage(locale, "texto.nregs")));            
        }
                
        if (getNombreEmpresa()== null || getNombreEmpresa().length() < 1) {
            errors.add("nombreEmpresa", new ActionMessage("errors.required",messages.getMessage(locale, "texto.nombreempresa")));
        }
                
        if (getDireccionEmpresa()== null || getDireccionEmpresa().length() < 1) {
            errors.add("direccionEmpresa", new ActionMessage("errors.required",messages.getMessage(locale, "texto.direccionempresa")));
        }
                
        if (getTelefonoEmpresa()== null || getTelefonoEmpresa().length() < 1) {
            errors.add("telefonoEmpresa", new ActionMessage("errors.required",messages.getMessage(locale, "texto.telefonoempresa")));
        }
                
        if (getIdioma()== null || getIdioma().length() < 1) {
            errors.add("idioma", new ActionMessage("errors.required",messages.getMessage(locale, "texto.idioma")));
        }
        
        try{
            if(getSocultarProducto() == null)
                ocultarProducto = false;
            else
                ocultarProducto = true;
        }catch(Exception Ex){
            errors.add("ocultarProducto", new ActionMessage("errors.required", messages.getMessage(locale, "texto.ocultarProducto")));
        }
        
        try{
            if(getSocultarBotonCompra() == null)
                ocultarBotonCompra = false;
            else
                ocultarBotonCompra = true;
        }catch(Exception Ex){
            errors.add("ocultarBotonCompra", new ActionMessage("errors.required", messages.getMessage(locale, "texto.ocultarBotonCompra")));
        }
        
        if (getSstockMin() != null && getSstockMin().length() > 0) {
            try{
                stockMin= Integer.parseInt(getSstockMin());
            }catch(Exception Ex){
                errors.add("stockMin", new ActionMessage("errors.integer",messages.getMessage(locale, "texto.stockMin")));
            }
        } else {
                errors.add("stockMin", new ActionMessage("errors.required",messages.getMessage(locale, "texto.stockMin")));            
        }
        
        try{
            if(getSmostrarIVAIncluido() == null)
                mostrarIVAIncluido = false;
            else
                mostrarIVAIncluido = true;
        }catch(Exception Ex){
            errors.add("mostrarIVAIncluido", new ActionMessage("errors.required", messages.getMessage(locale, "texto.mostrarIVAIncluido")));
        }
        
        try{
            if(getSusarDirFactura() == null)
                usarDirFactura = false;
            else
                usarDirFactura = true;
        }catch(Exception Ex){
            errors.add("usarDirFactura", new ActionMessage("errors.required", messages.getMessage(locale, "texto.usarDirFactura")));
        }
        
        return errors;
    }
}
