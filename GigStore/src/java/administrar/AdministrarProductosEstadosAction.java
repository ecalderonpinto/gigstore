/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import dominio.GeneradorTablas;
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
import producto.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarProductosEstadosAction extends LookupDispatchAction {
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
        ProductoEstadoDAO estDAO=(ProductoEstadoDAO) request.getSession().getServletContext().getAttribute("preDAO");
        
        request.setAttribute("listado_estados_prod", GeneradorTablas.tablaEstadosProd(estDAO.devuelveEstados(), request, response));
        request.setAttribute("error_borrar_est", request.getSession().getAttribute("error_borrar_est"));
        request.getSession().removeAttribute("error_borrar_est");
        
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
        ProductoEstadoForm ef=(ProductoEstadoForm) form;
        ProductoEstado estado=new ProductoEstado();
        ProductoEstadoDAO estDAO=(ProductoEstadoDAO) request.getSession().getServletContext().getAttribute("preDAO");
        
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            estado.setEstado(ef.getEstado());
            estado.setDescripcion(ef.getDescripcion());            
            estDAO.altaEstado(estado);
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoEstadoForm ef=(ProductoEstadoForm) form;
        ProductoEstadoDAO estDAO=(ProductoEstadoDAO) request.getSession().getServletContext().getAttribute("preDAO");
        ProductoEstado estado=estDAO.consultaEstado(Integer.parseInt(request.getParameter("id")));
        
        ef.setEstado(estado.getEstado());
        ef.setDescripcion(estado.getDescripcion());
        
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
        ProductoEstadoForm ef=(ProductoEstadoForm) form;
        ProductoEstadoDAO estDAO=(ProductoEstadoDAO) request.getSession().getServletContext().getAttribute("preDAO");
        ProductoEstado estado=estDAO.consultaEstado(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){     
            estado.setEstado(ef.getEstado());
            estado.setDescripcion(ef.getDescripcion());            
            estDAO.editaEstado(estado);
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoEstadoDAO estDAO=(ProductoEstadoDAO) request.getSession().getServletContext().getAttribute("preDAO");
        ProductoEstado estado=estDAO.consultaEstado(Integer.parseInt(request.getParameter("id")));
        ProductoDAO prodDAO=(ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        MessageResources message = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale = request.getLocale();
        
        if(estado.getId()==1 || estado.getId()==2){
            request.getSession().setAttribute("error_borrar_est", message.getMessage(locale, "errors.borrar_pre"));
            return mapping.findForward(SUCCESS);
        }
        for(Object o: prodDAO.devuelveProductos()){
            Producto p=(Producto) o;
            if(p.getEstado().getId()==estado.getId()){
                request.getSession().setAttribute("error_borrar_est", message.getMessage(locale, "errors.borrar_pre"));
                return mapping.findForward(SUCCESS);
            }
        }
        estDAO.eliminaEstado(estado);
        
        return mapping.findForward(SUCCESS);
    }
}
