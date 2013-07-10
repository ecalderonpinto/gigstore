/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

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
public class ProductoDescripcionForm extends org.apache.struts.action.ActionForm {
    private String idioma;
    private String nombre;
    private String descripcion;
    private String formato;
    private String etiquetas;
    private String dimensiones;
    private String mtProductos;
    private String mdProductos;

    /**
     *
     */
    public ProductoDescripcionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getMdProductos() {
        return mdProductos;
    }

    public void setMdProductos(String mdProductos) {
        this.mdProductos = mdProductos;
    }

    public String getMtProductos() {
        return mtProductos;
    }

    public void setMtProductos(String mtProductos) {
        this.mtProductos = mtProductos;
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
        
        if (getIdioma() == null || getIdioma().length() < 1 || getIdioma().equalsIgnoreCase("0")) {
            errors.add("idioma", new ActionMessage("errors.required", messages.getMessage(locale, "texto.idioma")));
        }
        if (getNombre() == null || getNombre().length() < 1) {
            errors.add("nombre", new ActionMessage("errors.required", messages.getMessage(locale, "texto.nombre")));
        }
        
        return errors;
    }
}
