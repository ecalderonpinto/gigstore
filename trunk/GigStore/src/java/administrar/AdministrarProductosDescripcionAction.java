/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import producto.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarProductosDescripcionAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";

    /** Provides the mapping from resource key to method name.
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.alta_inicio", "alta_inicio");
        map.put("accion.alta_ejecutar", "alta_ejecutar");
        map.put("accion.editar_inicio", "editar_inicio");
        map.put("accion.editar_ejecutar", "editar_ejecutar");
        map.put("accion.eliminar", "eliminar");
        return map;
    }

    /** Action called on alta_inicio button click
     */
    public ActionForward alta_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        
        request.getSession().setAttribute("idiomaSel", request.getLocale().getLanguage());
        request.getSession().setAttribute("id_prod", request.getParameter("id"));
        request.getSession().setAttribute("accion", "alta");
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ProductoDescripcionForm cf=(ProductoDescripcionForm) form;
        ProductoDescripcion desc=new ProductoDescripcion();
        ProductoDescripcionDAO descDAO=(ProductoDescripcionDAO) request.getSession().getServletContext().getAttribute("prdDAO");
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getSession().getAttribute("id_prod").toString()));
        
        request.getSession().removeAttribute("id_prod");
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("idiomaSel");
        
        if(!this.isCancelled(request)){
            desc.setNombre(cf.getNombre());
            desc.setDescripcion(cf.getDescripcion());
            desc.setDimensiones(cf.getDimensiones());
            desc.setFormato(cf.getFormato());
            desc.setIdioma(cf.getIdioma());
            desc.setEtiquetas(cf.getEtiquetas());
            desc.setMtProductos(cf.getMtProductos());
            desc.setMdProductos(cf.getMdProductos());
            desc.setProducto(prod);
            
            prod.getDescripciones().add(desc);
            
            descDAO.altaDesc(desc);
            prodDAO.editaProducto(prod);
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/detalle.do?metodo=mostrar_detalle&id=" + prod.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoDescripcionForm cf=(ProductoDescripcionForm) form;
        ProductoDescripcionDAO descDAO=(ProductoDescripcionDAO) request.getSession().getServletContext().getAttribute("prdDAO");
        ProductoDescripcion desc=descDAO.consultaDesc(Integer.parseInt(request.getParameter("id")));
        
        cf.setNombre(desc.getNombre());
        cf.setDescripcion(desc.getDescripcion());
        cf.setDimensiones(desc.getDimensiones());
        cf.setFormato(desc.getFormato());
        cf.setIdioma(desc.getIdioma());
        cf.setEtiquetas(desc.getEtiquetas());
        cf.setMtProductos(desc.getMtProductos());
        cf.setMdProductos(desc.getMdProductos());
        
        request.getSession().setAttribute("idiomaSel", desc.getIdioma());   
        request.getSession().setAttribute("id", request.getParameter("id"));
        request.getSession().setAttribute("id_prod", desc.getProducto().getId());
        request.getSession().setAttribute("accion", "editar");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoDescripcionForm cf=(ProductoDescripcionForm) form;
        ProductoDescripcionDAO descDAO=(ProductoDescripcionDAO) request.getSession().getServletContext().getAttribute("prdDAO");
        ProductoDescripcion desc=descDAO.consultaDesc(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
                
        request.getSession().removeAttribute("idiomaSel");
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("id_prod");
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            desc.setNombre(cf.getNombre());
            desc.setDescripcion(cf.getDescripcion());
            desc.setDimensiones(cf.getDimensiones());
            desc.setFormato(cf.getFormato());
            desc.setIdioma(cf.getIdioma());
            desc.setEtiquetas(cf.getEtiquetas());
            desc.setMtProductos(cf.getMtProductos());
            desc.setMdProductos(cf.getMdProductos());
            desc.getProducto().getDescripciones().add(desc);
            
            descDAO.editaDesc(desc);
            prodDAO.editaProducto(desc.getProducto());
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/detalle.do?metodo=mostrar_detalle&id=" + desc.getProducto().getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoDescripcionDAO descDAO=(ProductoDescripcionDAO) request.getSession().getServletContext().getAttribute("prdDAO");
        ProductoDescripcion desc=descDAO.consultaDesc(Integer.parseInt(request.getParameter("id")));
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        
        desc.getProducto().getDescripciones().remove(desc);
        prodDAO.editaProducto(desc.getProducto());
        descDAO.eliminaDesc(desc);
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/detalle.do?metodo=mostrar_detalle&id=" + desc.getProducto().getId());
        af.setRedirect(true);
        
        return af; 
    }
}