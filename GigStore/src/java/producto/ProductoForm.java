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
public class ProductoForm extends org.apache.struts.action.ActionForm {
    private String referencia;
    private String nombre;
    private BigDecimal precio;
    private String sprecio;
    private int categoria;
    private String scategoria;
    private boolean destacado;
    private String sdestacado;
    private int iva;
    private String siva;
    private int estado;
    private String sestado;
    private int stock;
    private String sstock;
    private String marca;
    private boolean descporcentaje;
    private String sdescporcentaje;
    private BigDecimal descuento;
    private String sdescuento;

    public boolean isDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    public String getSdestacado() {
        return sdestacado;
    }

    public void setSdestacado(String sdestacado) {
        this.sdestacado = sdestacado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSestado() {
        return sestado;
    }

    public void setSestado(String sestado) {
        this.sestado = sestado;
    }

    public String getSiva() {
        return siva;
    }

    public void setSiva(String siva) {
        this.siva = siva;
    }

    public String getSprecio() {
        return sprecio;
    }

    public void setSprecio(String sprecio) {
        this.sprecio = sprecio;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getScategoria() {
        return scategoria;
    }

    public void setScategoria(String scategoria) {
        this.scategoria = scategoria;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
    public ProductoForm() {
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
        
        if (getReferencia() == null || getReferencia().length() < 1) {
            errors.add("referencia", new ActionMessage("errors.required", messages.getMessage(locale, "texto.referencia")));
        }
        
        if (getNombre() == null || getNombre().length() < 1) {
            errors.add("nombre", new ActionMessage("errors.required", messages.getMessage(locale, "texto.nombre")));
        }
        
        if (getSprecio() == null || getSprecio().length() < 1) {
            errors.add("precio", new ActionMessage("errors.required", messages.getMessage(locale, "texto.precio")));
        } else {
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
        
        try{
            if(getSdestacado() == null)
                destacado = false;
            else
                destacado = true;
        }catch(Exception Ex){
            errors.add("destacado", new ActionMessage("errors.required", messages.getMessage(locale, "texto.destacado")));
        }
        
        if (getSiva() == null || getSiva().length() < 1) {
            errors.add("iva", new ActionMessage("errors.required", messages.getMessage(locale, "texto.iva")));
        } else {
            try{
                iva= Integer.parseInt(getSiva());
                if(iva < 1)
                    errors.add("iva", new ActionMessage("errors.required", messages.getMessage(locale, "texto.iva")));
            }catch(Exception Ex){
                errors.add("iva", new ActionMessage("errors.required", messages.getMessage(locale, "texto.iva")));
            }
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
        
        if (getScategoria() == null || getScategoria().length() < 1) {
            errors.add("categoria", new ActionMessage("errors.required", messages.getMessage(locale, "texto.categoria")));
        } else {
            try{
                categoria= Integer.parseInt(getScategoria());
                if(categoria < 1)
                    errors.add("categoria", new ActionMessage("errors.required", messages.getMessage(locale, "texto.categoria")));
            }catch(Exception Ex){
                errors.add("categoria", new ActionMessage("errors.required", messages.getMessage(locale, "texto.categoria")));
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

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        destacado=false;
    }
    
    
}
