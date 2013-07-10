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
public class ProductoOpcionCombinacionesForm extends org.apache.struts.action.ActionForm {    
    private String[] valores;
    private BigDecimal precio;
    private String sprecio;
    private int stock;
    private String sstock;

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

    public String[] getValores() {
        return valores;
    }

    public void setValores(String[] valores) {
        this.valores = valores;
    }
    
    /**
     *
     */
    public ProductoOpcionCombinacionesForm() {
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
                
        if (getValores() == null || getValores().length < 1) {
            errors.add("valores", new ActionMessage("errors.required", messages.getMessage(locale, "texto.valores")));
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
        
        return errors;
    }
}
