/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import categoria.Categoria;
import categoria.CategoriaDAO;
import categoria.CategoriaForm;
import dominio.GeneradorTablas;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.util.MessageResources;
import producto.ProductoDAO;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarCategoriasAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";
    private final static String CANCEL = "cancel";
    
    /** Provides the mapping from resource key to method name.
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.mostrar_listado", "mostrar_listado");
        map.put("accion.mostrar_detalle", "mostrar_detalle");
        map.put("accion.alta_inicio", "alta_inicio");
        map.put("accion.alta_ejecutar", "alta_ejecutar");
        map.put("accion.editar_inicio", "editar_inicio");
        map.put("accion.editar_ejecutar", "editar_ejecutar");
        map.put("accion.eliminar", "eliminar");
        return map;
    }

    /** Action called on mostrar_listado button click
     */
    public ActionForward mostrar_listado(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        
        request.setAttribute("listado_cats", GeneradorTablas.tablaCategorias(catDAO.devuelveCategorias(false), request, response));
        request.setAttribute("error_borrar_cat", request.getSession().getAttribute("error_borrar_cat"));
        request.getSession().removeAttribute("error_borrar_cat");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on mostrar_detalle button click
     */
    public ActionForward mostrar_detalle(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getParameter("id")));
        
        request.setAttribute("listado_cat_desc", GeneradorTablas.tablaDescCategorias(cat.getDescripciones(), request, response));
        request.setAttribute("categoria", cat);
        if(cat.getMadre()!=null && cat.getMadre().getId()>0)
            request.setAttribute("categoriamadre", cat.getMadre().getNombre());
        else
            request.setAttribute("categoriamadre", "N/A");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_inicio button click
     */
    public ActionForward alta_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        
        request.getSession().setAttribute("lista_categorias", catDAO.devuelveCategorias(false));
        request.getSession().setAttribute("estadoSel", "1");
        request.getSession().setAttribute("accion", "alta");
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        CategoriaForm cf=(CategoriaForm) form;
        Categoria cat=new Categoria();
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("estadoSel");
        request.getSession().removeAttribute("lista_categorias");
                
        if(this.isCancelled(request)){
            return mapping.findForward(CANCEL);
        }else{
            cat.setNombre(cf.getNombre());
            cat.setActivo(cf.isActivo());
            cat.setDescuento(cf.getDescuento());
            cat.setDescporcentaje(cf.isDescporcentaje());
            Categoria c=catDAO.consultaCategoria(cf.getCategoriaMadre());
            if(c!=null && c.getId()>0){
                cat.setNodo(c.getNodo()+1);
                cat.setMadre(c);
            }else{
                cat.setNodo(0);
                cat.setMadre(null);
            }
            
            catDAO.altaCategoria(cat);
            
            request.getSession().getServletContext().setAttribute("categorias", catDAO.devuelveCategoriasPrincipales(true));
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/categorias/descripciones/editar.do?metodo=alta_inicio&id=" + cat.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CategoriaForm cf=(CategoriaForm) form;
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getParameter("id")));          
        DecimalFormat df=new DecimalFormat("#.##");
        int madreid=0;
        
        cf.setNombre(cat.getNombre());
        cf.setActivo(cat.isActivo());
        cf.setSdescuento(df.format(cat.getDescuento()));
        cf.setDescporcentaje(cat.isDescporcentaje());
        if(cat.getMadre()!=null)
            madreid=cat.getMadre().getId();
        cf.setCategoriaMadre(madreid);
        
        if(cat.isDescporcentaje())
            request.getSession().setAttribute("tipoDescSel", "1");
        else
            request.getSession().setAttribute("tipoDescSel", "0");
        
        if(cat.isActivo())
            request.getSession().setAttribute("estadoSel", "1");
        else
            request.getSession().setAttribute("estadoSel", "0");
        request.getSession().setAttribute("catSel", madreid);
        request.getSession().setAttribute("id", request.getParameter("id"));
        request.getSession().setAttribute("accion", "editar");
        request.getSession().setAttribute("lista_categorias", catDAO.devuelveCategorias(false));
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CategoriaForm cf=(CategoriaForm) form;
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getSession().getAttribute("id").toString()));
                     
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("estadoSel");
        request.getSession().removeAttribute("catSel");
        request.getSession().removeAttribute("lista_categorias");
        
        if(!this.isCancelled(request)){
            cat.setNombre(cf.getNombre());
            cat.setActivo(cf.isActivo());
            cat.setDescuento(cf.getDescuento());
            cat.setDescporcentaje(cf.isDescporcentaje());
            Categoria c=catDAO.consultaCategoria(cf.getCategoriaMadre());
            if(c!=null && c.getId()>0){
                cat.setNodo(c.getNodo()+1);
                cat.setMadre(c);
            }else{
                cat.setNodo(0);
                cat.setMadre(null);
            }
            
            catDAO.editaCategoria(cat);
            
            request.getSession().getServletContext().setAttribute("categorias", catDAO.devuelveCategoriasPrincipales(true));
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=" + cat.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getParameter("id")));
        ProductoDAO prodDAO=(ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        MessageResources message = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale = request.getLocale();
        
        if(!prodDAO.devuelveProductos(cat).isEmpty()){
            request.getSession().setAttribute("error_borrar_cat", message.getMessage(locale, "errors.borrar_cat"));
            return mapping.findForward(SUCCESS);
        }
        
        if(!catDAO.devuelveSubCategorias(cat.getId(), false).isEmpty()){
            request.getSession().setAttribute("error_borrar_cat", message.getMessage(locale, "errors.borrar_cat"));
            return mapping.findForward(SUCCESS);
        }
        
        catDAO.eliminaCategoria(cat);
        request.getSession().getServletContext().setAttribute("categorias", catDAO.devuelveCategoriasPrincipales(true));
        
        return mapping.findForward(SUCCESS);
    }
}
