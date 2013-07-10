/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import dominio.GeneradorTablas;
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
import pedido.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarCosteEnvioAction extends LookupDispatchAction {

    /*
     * forward name="success" path=""
     */
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
            HttpServletResponse response) {
        CosteEnvioDAO envDAO=(CosteEnvioDAO) request.getSession().getServletContext().getAttribute("envDAO");
        
        request.setAttribute("listado_costes_envio", GeneradorTablas.tablaCostesEnvio(envDAO.devuelveCosteEnvios(), request, response));
        request.setAttribute("error_borrar_env", request.getSession().getAttribute("error_borrar_env"));
        request.getSession().removeAttribute("error_borrar_env");
        
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
        CosteEnvioForm ef=(CosteEnvioForm) form;
        CosteEnvio coste=new CosteEnvio();
        CosteEnvioDAO costeDAO=(CosteEnvioDAO) request.getSession().getServletContext().getAttribute("envDAO");
        
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            coste.setTipo(ef.getTipo());
            coste.setPrecio(ef.getPrecio());
            coste.setDescripcion(ef.getDescripcion()); 
            coste.setActivarPayPal(ef.isActivarPayPal());
            coste.setActivarReembolso(ef.isActivarReembolso());
            coste.setActivarTransferencia(ef.isActivarTransferencia());
            coste.setActivarEfectivo(ef.isActivarEfectivo());    
            coste.setActivo(ef.isActivo());
            
            costeDAO.altaCosteEnvio(coste);
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CosteEnvioForm ef=(CosteEnvioForm) form;
        CosteEnvioDAO costeDAO=(CosteEnvioDAO) request.getSession().getServletContext().getAttribute("envDAO");
        CosteEnvio coste=costeDAO.consultaCosteEnvio(Integer.parseInt(request.getParameter("id")));
        
        ef.setTipo(coste.getTipo());
        DecimalFormat df=new DecimalFormat("#.##");
        ef.setSprecio(df.format(coste.getPrecio()));
        ef.setDescripcion(coste.getDescripcion());  
        ef.setSactivarPayPal(String.valueOf(coste.isActivarPayPal()));
        ef.setSactivarReembolso(String.valueOf(coste.isActivarReembolso()));
        ef.setSactivarTransferencia(String.valueOf(coste.isActivarTransferencia()));
        ef.setSactivarEfectivo(String.valueOf(coste.isActivarEfectivo()));
        ef.setSactivo(String.valueOf(coste.isActivo()));
        
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
        CosteEnvioForm ef=(CosteEnvioForm) form;
        CosteEnvioDAO costeDAO=(CosteEnvioDAO) request.getSession().getServletContext().getAttribute("envDAO");
        CosteEnvio coste=costeDAO.consultaCosteEnvio(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            coste.setTipo(ef.getTipo());
            coste.setPrecio(ef.getPrecio());
            coste.setDescripcion(ef.getDescripcion());  
            coste.setActivarPayPal(ef.isActivarPayPal());
            coste.setActivarReembolso(ef.isActivarReembolso());
            coste.setActivarTransferencia(ef.isActivarTransferencia());
            coste.setActivarEfectivo(ef.isActivarEfectivo());  
            coste.setActivo(ef.isActivo());
            
            costeDAO.editaCosteEnvio(coste);
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CosteEnvioDAO costeDAO=(CosteEnvioDAO) request.getSession().getServletContext().getAttribute("envDAO");
        CosteEnvio coste=costeDAO.consultaCosteEnvio(Integer.parseInt(request.getParameter("id")));        
        PedidoDAO pedDAO = (PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
        MessageResources message = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale = request.getLocale();
        
        for(Object o: pedDAO.devuelvePedidos()){
            Pedido p=(Pedido) o;
            if(coste.getId()==1 || p.getTipoenv().getId()==coste.getId()){
                request.getSession().setAttribute("error_borrar_env", message.getMessage(locale, "errors.borrar_env"));
                return mapping.findForward(SUCCESS);
            }
        }
        costeDAO.eliminaCosteEnvio(coste);
        
        return mapping.findForward(SUCCESS);
    }
}
