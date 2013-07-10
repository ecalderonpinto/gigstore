/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import categoria.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarCategoriasDescripcionAction extends LookupDispatchAction {
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
        request.getSession().setAttribute("id_cat", request.getParameter("id"));
        request.getSession().setAttribute("accion", "alta");
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        CategoriaDescripcionForm cf=(CategoriaDescripcionForm) form;
        CategoriaDescripcion desc=new CategoriaDescripcion();
        CategoriaDescripcionDAO descDAO=(CategoriaDescripcionDAO) request.getSession().getServletContext().getAttribute("cdDAO");
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getSession().getAttribute("id_cat").toString()));
        
        request.getSession().removeAttribute("idiomaSel");
        request.getSession().removeAttribute("id_cat");
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            desc.setNombre(cf.getNombre());
            desc.setDescripcion(cf.getDescripcion());
            desc.setIdioma(cf.getIdioma());
            desc.setMdCategorias(cf.getMdCategorias());
            desc.setMtCategorias(cf.getMtCategorias());
            desc.setCategoria(cat);
            
            cat.getDescripciones().add(desc);
            
            descDAO.altaDesc(desc);
            catDAO.editaCategoria(cat);
            
            request.getSession().getServletContext().setAttribute("categorias", catDAO.devuelveCategoriasPrincipales(true));
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=" + cat.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CategoriaDescripcionForm cf=(CategoriaDescripcionForm) form;        
        CategoriaDescripcionDAO descDAO=(CategoriaDescripcionDAO) request.getSession().getServletContext().getAttribute("cdDAO");
        CategoriaDescripcion desc=descDAO.consultaDesc(Integer.parseInt(request.getParameter("id")));
        
        cf.setNombre(desc.getNombre());
        cf.setDescripcion(desc.getDescripcion());
        cf.setIdioma(desc.getIdioma());
        cf.setMdCategorias(desc.getMdCategorias());
        cf.setMtCategorias(desc.getMtCategorias());
        
        request.getSession().setAttribute("idiomaSel", desc.getIdioma());   
        request.getSession().setAttribute("id_cat", desc.getCategoria().getId());
        request.getSession().setAttribute("id", request.getParameter("id"));
        request.getSession().setAttribute("accion", "editar");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CategoriaDescripcionForm cf=(CategoriaDescripcionForm) form;
        CategoriaDescripcionDAO descDAO=(CategoriaDescripcionDAO) request.getSession().getServletContext().getAttribute("cdDAO");
        CategoriaDescripcion desc=descDAO.consultaDesc(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        
        request.getSession().removeAttribute("idiomaSel");
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("id_cat");
        request.getSession().removeAttribute("accion");
                
        if(!this.isCancelled(request)){
            desc.setNombre(cf.getNombre());
            desc.setDescripcion(cf.getDescripcion());
            desc.setIdioma(cf.getIdioma());
            desc.setMdCategorias(cf.getMdCategorias());
            desc.setMtCategorias(cf.getMtCategorias());
            
//            desc.getCategoria().getDescripciones().add(desc);
            
            Categoria c=catDAO.consultaCategoria(desc.getCategoria().getId());
            c.getDescripciones().add(desc);
            descDAO.editaDesc(desc);
            catDAO.editaCategoria(c);            
            
            request.getSession().getServletContext().setAttribute("categorias", catDAO.devuelveCategoriasPrincipales(true));
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=" + desc.getCategoria().getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CategoriaDescripcionDAO descDAO=(CategoriaDescripcionDAO) request.getSession().getServletContext().getAttribute("cdDAO");
        CategoriaDescripcion desc=descDAO.consultaDesc(Integer.parseInt(request.getParameter("id")));
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        
        desc.getCategoria().getDescripciones().remove(desc);
        catDAO.editaCategoria(desc.getCategoria());
        descDAO.eliminaDesc(desc);
        
        request.getSession().getServletContext().setAttribute("categorias", catDAO.devuelveCategoriasPrincipales(true));
                
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=" + desc.getCategoria().getId());
        af.setRedirect(true);
        
        return af; 
    }
}
