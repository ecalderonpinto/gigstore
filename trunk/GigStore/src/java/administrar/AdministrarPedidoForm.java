/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class AdministrarPedidoForm extends org.apache.struts.action.ActionForm {
    private final static String FORMATO_FECHA="dd/MM/yyyy";
    private String dirEnvio;
    private String dirFactura;
    private Date fechaPago;
    private String sfechaPago;
    private String formaPago;
    private int tipoenv;
    private String stipoenv;
    private int estado;
    private String sestado;

    public String getDirEnvio() {
        return dirEnvio;
    }

    public void setDirEnvio(String dirEnvio) {
        this.dirEnvio = dirEnvio;
    }

    public String getDirFactura() {
        return dirFactura;
    }

    public void setDirFactura(String dirFactura) {
        this.dirFactura = dirFactura;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getSestado() {
        return sestado;
    }

    public void setSestado(String sestado) {
        this.sestado = sestado;
    }

    public String getSfechaPago() {
        return sfechaPago;
    }

    public void setSfechaPago(String sfechaPago) {
        this.sfechaPago = sfechaPago;
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
    
    /**
     *
     */
    public AdministrarPedidoForm() {
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
        SimpleDateFormat df1=new SimpleDateFormat(FORMATO_FECHA);
        
        if (getDirEnvio() == null || getDirEnvio().length() < 1) {
            errors.add("nombre", new ActionMessage("errors.required", messages.getMessage(locale, "texto.nombre")));
        }
        
        if (getSestado() == null || getSestado().length() < 1) {
            errors.add("estado", new ActionMessage("errors.required", messages.getMessage(locale, "texto.estado")));
        } else {
            try{
                estado= Integer.parseInt(getSestado());
                if(estado < 1)
                    errors.add("estado", new ActionMessage("errors.required", messages.getMessage(locale, "texto.estado")));
            }catch(Exception Ex){
                errors.add("estado", new ActionMessage("errors.required", messages.getMessage(locale, "texto.estado")));
            }
        }   
        
        if (getStipoenv() == null || getStipoenv().length() < 1) {
            errors.add("tipoenv", new ActionMessage("errors.required", messages.getMessage(locale, "texto.tipoenvio")));
        } else {
            try{
                tipoenv= Integer.parseInt(getStipoenv());
                if(tipoenv < 1)
                    errors.add("tipoenv", new ActionMessage("errors.required", messages.getMessage(locale, "texto.tipoenvio")));
            }catch(Exception Ex){
                errors.add("tipoenv", new ActionMessage("errors.required", messages.getMessage(locale, "texto.tipoenvio")));
            }
        }    
        
        if (getSfechaPago()!=null && getSfechaPago().length() > 0) {
            try {
                fechaPago=df1.parse(getSfechaPago());
            } catch (Exception ex) {
                errors.add("fechaNacimiento", new ActionMessage("errors.date", getSfechaPago()));
            }
        }
        
        return errors;
    }
}
