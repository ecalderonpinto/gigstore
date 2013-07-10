/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

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
public class ProductoOpcionForm extends org.apache.struts.action.ActionForm {
    private String opcion;
    private BigDecimal precio;
    private String sprecio;
    private int stock;
    private String sstock;
    private boolean activa;
    private String sactiva;

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
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

    public String getSstock() {
        return sstock;
    }

    public void setSstock(String sstock) {
        this.sstock = sstock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getSactiva() {
        return sactiva;
    }

    public void setSactiva(String sactiva) {
        this.sactiva = sactiva;
    }

    /**
     *
     */
    public ProductoOpcionForm() {
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
        
        if (getSprecio() != null && getSprecio().length() > 0) {
            try{
                Pattern pat = Pattern.compile("^[0-9]+([,.][0-9]{1,2})?$");
                Matcher mat = pat.matcher(getSprecio());
                
                if (mat.find()) {
                    precio=new BigDecimal(getSprecio().replace(',', '.'));
                }else{
                    errors.add("precio", new ActionMessage("errors.precio.mask"));
                }
            }catch(Exception Ex){
                errors.add("precio", new ActionMessage("errors.float", messages.getMessage(locale, "texto.precio")));
            }
        }
        
        if (getSstock() == null || getSstock().length() < 1) {
            stock=-1;
        } else {
            try{
                stock= Integer.parseInt(getSstock());
            }catch(Exception Ex){
                errors.add("stock", new ActionMessage("errors.integer", messages.getMessage(locale, "texto.stock")));
            }
        }   
        
        try{
            if(getSactiva() == null)
                activa = false;
            else
                activa = true;
        }catch(Exception Ex){
            errors.add("activa", new ActionMessage("errors.required", messages.getMessage(locale, "texto.activo")));
        }
        
        return errors;
    }
}
