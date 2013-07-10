/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import dominio.GeneradorTablas;
import dominio.TipoIVA;
import dominio.TipoIvaDAO;
import dominio.TipoIvaForm;
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
import producto.Producto;
import producto.ProductoDAO;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarTiposIvaAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";

    /** Provides the mapping from resource key to method name.
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.mostrar_listado", "mostrar_listado");
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
        TipoIvaDAO ivaDAO=(TipoIvaDAO) request.getSession().getServletContext().getAttribute("ivaDAO");    
        
        request.setAttribute("listado_tipos_iva", GeneradorTablas.tablaIvas(ivaDAO.devuelveTiposIva(), request, response));
        request.setAttribute("error_borrar", request.getSession().getAttribute("error_borrar"));
        request.getSession().removeAttribute("error_borrar");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_inicio button click
     */
    public ActionForward alta_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        
        request.getSession().setAttribute("accion", "alta");
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        TipoIvaForm tf=(TipoIvaForm) form;
        TipoIVA iva=new TipoIVA();
        TipoIvaDAO ivaDAO=(TipoIvaDAO) request.getSession().getServletContext().getAttribute("ivaDAO");
                
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            iva.setIva(tf.getIva());
            iva.setDescripcion(tf.getDescripcion());
            
            ivaDAO.altaTipoIva(iva);
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        TipoIvaForm tf=(TipoIvaForm) form;
        TipoIvaDAO ivaDAO=(TipoIvaDAO) request.getSession().getServletContext().getAttribute("ivaDAO");
        TipoIVA iva=ivaDAO.consultaTipoIva(Integer.parseInt(request.getParameter("id")));
        
        tf.setIva(iva.getIva());
        tf.setSiva(String.valueOf(iva.getIva()));
        tf.setDescripcion(iva.getDescripcion()); 
        
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
        TipoIvaForm tf=(TipoIvaForm) form;
        TipoIvaDAO ivaDAO=(TipoIvaDAO) request.getSession().getServletContext().getAttribute("ivaDAO");
        TipoIVA iva=ivaDAO.consultaTipoIva(Integer.parseInt(request.getSession().getAttribute("id").toString()));
                
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("id");
        
        if(!this.isCancelled(request)){
            iva.setIva(tf.getIva());
            iva.setDescripcion(tf.getDescripcion());
            
            ivaDAO.editaTipoIva(iva);
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        TipoIvaDAO ivaDAO=(TipoIvaDAO) request.getSession().getServletContext().getAttribute("ivaDAO");
        TipoIVA iva=ivaDAO.consultaTipoIva(Integer.parseInt(request.getParameter("id")));        
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        MessageResources message = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale = request.getLocale();
        
        for(Object o: prodDAO.devuelveProductos()){
            Producto p=(Producto) o;
            if(p.getIva().getId()==iva.getId()){
                request.getSession().setAttribute("error_borrar", message.getMessage(locale, "errors.borrar_iva"));
                return mapping.findForward(SUCCESS);
            }
        }
        ivaDAO.eliminaTipoIva(iva);
        
        return mapping.findForward(SUCCESS);
    }
}
