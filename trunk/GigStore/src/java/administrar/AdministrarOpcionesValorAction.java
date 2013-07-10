/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import opcion.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarOpcionesValorAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";

    /**
     * Provides the mapping from resource key to method name.
     *
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
        
//        request.getSession().setAttribute("idiomaSel", request.getLocale().getLanguage());
        request.getSession().setAttribute("opt_id", request.getParameter("id"));
        request.getSession().setAttribute("accion", "alta");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ValorForm vf=(ValorForm) form;
        Valor val=new Valor();
        ValorDAO valDAO=(ValorDAO) request.getSession().getServletContext().getAttribute("valDAO");
        OpcionDAO optDAO=(OpcionDAO) request.getSession().getServletContext().getAttribute("optDAO");
        Opcion opt=optDAO.consultaOpcion(Integer.parseInt(request.getSession().getAttribute("opt_id").toString()));
        
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("opt_id");
        
        ActionForward af=new ActionForward();
        if(!this.isCancelled(request)){
            val.setValor(vf.getValor());
            val.setTraducciones(vf.getTraducciones());
            val.setOpcion(opt);
            
            opt.getValores().add(val);
            
            valDAO.altaValor(val);
            optDAO.editaOpcion(opt);
            
            af.setPath("/administrar/opciones/valores/editar.do?metodo=alta_inicio&id=" + opt.getId());
        }else{
            af.setPath("/administrar/opciones/detalle.do?metodo=mostrar_detalle&id=" + opt.getId());
        }
        
        af.setRedirect(true);
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ValorForm vf=(ValorForm) form;
        ValorDAO valDAO=(ValorDAO) request.getSession().getServletContext().getAttribute("valDAO");
        Valor val=valDAO.consultaValor(Integer.parseInt(request.getParameter("id")));
                
        vf.setValor(val.getValor());
                
        request.getSession().setAttribute("trads", val.getTraducciones());
        request.getSession().setAttribute("id", request.getParameter("id"));
        request.getSession().setAttribute("opt_id", val.getOpcion().getId());
        request.getSession().setAttribute("accion", "editar");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ValorForm vf=(ValorForm) form;
        ValorDAO valDAO=(ValorDAO) request.getSession().getServletContext().getAttribute("valDAO");
        Valor val=valDAO.consultaValor(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        OpcionDAO optDAO=(OpcionDAO) request.getSession().getServletContext().getAttribute("optDAO");
                
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("opt_id");
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            val.setValor(vf.getValor());
            val.setTraducciones(vf.getTraducciones());
            
            val.getOpcion().getValores().add(val);
            
            valDAO.editaValor(val);
            optDAO.editaOpcion(val.getOpcion());
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/opciones/detalle.do?metodo=mostrar_detalle&id=" + val.getOpcion().getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ValorDAO valDAO=(ValorDAO) request.getSession().getServletContext().getAttribute("valDAO");
        Valor val=valDAO.consultaValor(Integer.parseInt(request.getParameter("id")));
        OpcionDAO optDAO=(OpcionDAO) request.getSession().getServletContext().getAttribute("optDAO");
        
        val.getOpcion().getValores().remove(val);
        optDAO.editaOpcion(val.getOpcion());
        valDAO.eliminaValor(val);
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/opciones/detalle.do?metodo=mostrar_detalle&id=" + val.getOpcion().getId());
        af.setRedirect(true);
        
        return af; 
    }
}
