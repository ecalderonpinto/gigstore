/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package categoria;

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
public class CategoriaForm extends org.apache.struts.action.ActionForm {
    
    private String nombre;
    private boolean activo;
    private String sactivo;
    private int categoriaMadre;
    private String scategoriaMadre;
    private boolean descporcentaje;
    private String sdescporcentaje;
    private BigDecimal descuento;
    private String sdescuento;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoriaMadre() {
        return categoriaMadre;
    }

    public void setCategoriaMadre(int categoriaMadre) {
        this.categoriaMadre = categoriaMadre;
    }

    public String getScategoriaMadre() {
        return scategoriaMadre;
    }

    public void setScategoriaMadre(String scategoriaMadre) {
        this.scategoriaMadre = scategoriaMadre;
    }

    public boolean isDescporcentaje() {
        return descporcentaje;
    }

    public void setDescporcentaje(boolean descporcentaje) {
        this.descporcentaje = descporcentaje;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getSdescporcentaje() {
        return sdescporcentaje;
    }

    public void setSdescporcentaje(String sdescporcentaje) {
        this.sdescporcentaje = sdescporcentaje;
    }

    public String getSdescuento() {
        return sdescuento;
    }

    public void setSdescuento(String sdescuento) {
        this.sdescuento = sdescuento;
    }

    /**
     *
     */
    public CategoriaForm() {
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
        
        if (getNombre() == null || getNombre().length() < 1) {
            errors.add("nombre", new ActionMessage("errors.required", messages.getMessage(locale, "texto.nombre")));
        }
        if (getSactivo() == null || getSactivo().equalsIgnoreCase("-1")) {
            errors.add("activo", new ActionMessage("errors.required", messages.getMessage(locale, "texto.activo")));
        }else{
            if(getSactivo().equalsIgnoreCase("0"))
                activo=false;
            else
                activo=true;
        }
        
        if (getScategoriaMadre() != null && getScategoriaMadre().length() > 0) {
            try{
                categoriaMadre= Integer.parseInt(getScategoriaMadre());
            }catch(Exception Ex){
                errors.add("categoria", new ActionMessage("errors.catsup", messages.getMessage(locale, "texto.categoria")));
            }
        }  
        
        
        if(!getSdescuento().isEmpty()){
            try{
                Pattern pat = Pattern.compile("^[0-9]+([,.][0-9]{1,2})?$");
                Matcher mat = pat.matcher(getSdescuento());

                if (mat.find()) {
                    descuento=new BigDecimal(getSdescuento().replace(',', '.'));
                }else{
                    errors.add("descuento", new ActionMessage("errors.desc.mask"));
                }
            }catch(Exception Ex){
                errors.add("descuento", new ActionMessage("errors.float", messages.getMessage(locale, "texto.descuento")));
            }
        }
        
        try{
            if(getSdescporcentaje().equals("0"))
                descporcentaje = false;
            else
                descporcentaje = true;
        }catch(Exception Ex){
            errors.add("descporcentaje", new ActionMessage("errors.required", messages.getMessage(locale, "texto.descporcentaje")));
        }
        return errors;
    }
}
