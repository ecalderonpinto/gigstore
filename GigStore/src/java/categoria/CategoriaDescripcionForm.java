/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package categoria;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class CategoriaDescripcionForm extends org.apache.struts.action.ActionForm {
    private String idioma;
    private String nombre;
    private String mtCategorias;
    private String mdCategorias;
    private String descripcion;
    
    /**
     *
     */
    public CategoriaDescripcionForm() {
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getMdCategorias() {
        return mdCategorias;
    }

    public void setMdCategorias(String mdCategorias) {
        this.mdCategorias = mdCategorias;
    }

    public String getMtCategorias() {
        return mtCategorias;
    }

    public void setMtCategorias(String mtCategorias) {
        this.mtCategorias = mtCategorias;
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
        
        if (getIdioma() == null || getIdioma().length() < 1 || getIdioma().equalsIgnoreCase("0")) {
            errors.add("idioma", new ActionMessage("errors.required","Idioma"));
        }
        if (getNombre() == null || getNombre().length() < 1) {
            errors.add("nombre", new ActionMessage("errors.required","Nombre"));
        }
        return errors;
    }
}
