/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

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
public class AdministrarPedidoLineaForm extends org.apache.struts.action.ActionForm {
    private int producto;
    private String sproducto;
    private int cantidad;
    private String scantidad;
    private int opcion;
    private String sopcion;

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public String getSproducto() {
        return sproducto;
    }

    public void setSproducto(String sproducto) {
        this.sproducto = sproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public String getScantidad() {
        return scantidad;
    }

    public void setScantidad(String scantidad) {
        this.scantidad = scantidad;
    }

    public String getSopcion() {
        return sopcion;
    }

    public void setSopcion(String sopcion) {
        this.sopcion = sopcion;
    }

    /**
     *
     */
    public AdministrarPedidoLineaForm() {
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
        
        if (getSproducto()!=null && getSproducto().length() > 0 && !getSproducto().equalsIgnoreCase("0")) {
            try{
                producto= Integer.parseInt(getSproducto());
            }catch(Exception Ex){
                errors.add("producto", new ActionMessage("errors.required", messages.getMessage(locale, "texto.producto")));
            }
        }else{
            errors.add("producto", new ActionMessage("errors.required", messages.getMessage(locale, "texto.producto")));
        }
                
        if (getScantidad() == null || getScantidad().length() < 1) {
            errors.add("cantidad", new ActionMessage("errors.required", messages.getMessage(locale, "texto.cantidad")));
        } else {
            try{
                cantidad= Integer.parseInt(getScantidad());
                if(cantidad < 1)
                    errors.add("cantidad", new ActionMessage("errors.required", messages.getMessage(locale, "texto.cantidad")));
            }catch(Exception Ex){
                errors.add("cantidad", new ActionMessage("errors.integer", messages.getMessage(locale, "texto.cantidad")));
            }
        }  
        
        if (getSopcion()!=null && getSopcion().length() > 0) {
            try{
                opcion= Integer.parseInt(getSopcion().split(";")[0]);
            }catch(Exception Ex){
                errors.add("opcion", new ActionMessage("errors.required", messages.getMessage(locale, "texto.opcion")));
            }
        }
        
        return errors;
    }
}
