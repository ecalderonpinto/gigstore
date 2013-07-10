/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import dominio.GeneradorTablas;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opcion.Opcion;
import opcion.OpcionDAO;
import opcion.OpcionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarOpcionesAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";

    /**
     * Provides the mapping from resource key to method name.
     *
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
        OpcionDAO optDAO=(OpcionDAO) request.getSession().getServletContext().getAttribute("optDAO");
        
        request.setAttribute("listado_opciones", GeneradorTablas.tablaOpciones(optDAO.devuelveOpcion(), request, response));
        request.setAttribute("error_borrar_opt", request.getSession().getAttribute("error_borrar_opt"));
        request.getSession().removeAttribute("error_borrar_opt");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on mostrar_detalle button click
     */
    public ActionForward mostrar_detalle(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        OpcionDAO optDAO=(OpcionDAO) request.getSession().getServletContext().getAttribute("optDAO");
        Opcion opt=optDAO.consultaOpcion(Integer.parseInt(request.getParameter("id")));
        
        request.getSession().setAttribute("opcion", opt);
        request.setAttribute("listado_valores", GeneradorTablas.tablaOpcionesValores(opt.getValores(), request, response));
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_inicio button click
     */
    public ActionForward alta_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        
//        request.getSession().setAttribute("idiomaSel", request.getLocale().getLanguage());
        request.getSession().setAttribute("accion", "alta");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        OpcionForm of=(OpcionForm) form;
        Opcion opt=new Opcion();
        OpcionDAO optDAO=(OpcionDAO) request.getSession().getServletContext().getAttribute("optDAO");
        
        request.getSession().removeAttribute("accion");
//        request.getSession().removeAttribute("idiomaSel");
        
        if(!this.isCancelled(request)){
            opt.setOpcion(of.getOpcion());
            opt.setDescripcion(of.getDescripcion());
            opt.setTraducciones(of.getTraducciones());
            
            optDAO.altaOpcion(opt);
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/opciones/valores/editar.do?metodo=alta_inicio&id=" + opt.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        OpcionForm of=(OpcionForm) form;
        OpcionDAO optDAO=(OpcionDAO) request.getSession().getServletContext().getAttribute("optDAO");
        Opcion opt=optDAO.consultaOpcion(Integer.parseInt(request.getParameter("id")));
                
        of.setOpcion(opt.getOpcion());
        of.setDescripcion(opt.getDescripcion());
        
        request.getSession().setAttribute("trads", opt.getTraducciones());
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
        OpcionForm of=(OpcionForm) form;
        OpcionDAO optDAO=(OpcionDAO) request.getSession().getServletContext().getAttribute("optDAO");
        Opcion opt=optDAO.consultaOpcion(Integer.parseInt(request.getSession().getAttribute("id").toString()));
                
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            opt.setOpcion(of.getOpcion());
            opt.setDescripcion(of.getDescripcion());
            opt.setTraducciones(of.getTraducciones());
            
            optDAO.editaOpcion(opt);
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/opciones/detalle.do?metodo=mostrar_detalle&id=" + opt.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        OpcionDAO optDAO=(OpcionDAO) request.getSession().getServletContext().getAttribute("optDAO");
        Opcion opt=optDAO.consultaOpcion(Integer.parseInt(request.getParameter("id")));
        
        optDAO.eliminaOpcion(opt);
        
        return mapping.findForward(SUCCESS);
    }
}
