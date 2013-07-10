/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import dominio.ConfiguracionTienda;
import dominio.ConfiguracionTiendaDAO;
import java.text.DecimalFormat;
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
public class AdministrarFormaPagoAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";

    /**
     * Provides the mapping from resource key to method name.
     *
     * @return Resource key / method name map.
     */@Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.mostrar_listado", "mostrar_listado");
        map.put("accion.editar_inicio", "editar_inicio");
        map.put("accion.editar_ejecutar", "editar_ejecutar");
        return map;
    }

    /** Action called on mostrar_listado button click
     */
    public ActionForward mostrar_listado(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        AdministrarFormaPagoForm tf=(AdministrarFormaPagoForm) form;
        ConfiguracionTiendaDAO cfgDAO=(ConfiguracionTiendaDAO) request.getSession().getServletContext().getAttribute("cfgDAO");
        ConfiguracionTienda cfg=cfgDAO.consultaConfiguracionTienda(Integer.parseInt(request.getParameter("id")));
        
        tf.setSactivarPayPal(String.valueOf(cfg.isActivarPayPal()));
        tf.setSactivarReembolso(String.valueOf(cfg.isActivarReembolso()));
        tf.setSactivarTransferencia(String.valueOf(cfg.isActivarTransferencia()));
        tf.setSactivarEfectivo(String.valueOf(cfg.isActivarEfectivo()));
        tf.setCorreoPayPal(cfg.getCorreoPayPal());
        tf.setNumCuenta(cfg.getNumCuenta());
        DecimalFormat df=new DecimalFormat("#.##");
        tf.setSprecioPayPal(df.format(cfg.getPrecioPayPal()));
        tf.setSprecioReembolso(df.format(cfg.getPrecioReembolso()));
        tf.setSprecioTransferencia(df.format(cfg.getPrecioTransferencia()));
        tf.setSprecioEfectivo(df.format(cfg.getPrecioEfectivo()));
        
        request.getSession().setAttribute("accion", "editar");
        request.getSession().setAttribute("id", request.getParameter("id"));
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        AdministrarFormaPagoForm tf=(AdministrarFormaPagoForm) form;
        ConfiguracionTiendaDAO cfgDAO=(ConfiguracionTiendaDAO) request.getSession().getServletContext().getAttribute("cfgDAO");
        ConfiguracionTienda cfg=cfgDAO.consultaConfiguracionTienda(Integer.parseInt(request.getParameter("id")));
        
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("id");
                
        if(this.isCancelled(request)){
            return mapping.findForward(SUCCESS);
        }else{
            cfg.setActivarPayPal(tf.isActivarPayPal());
            cfg.setActivarReembolso(tf.isActivarReembolso());
            cfg.setActivarTransferencia(tf.isActivarTransferencia());
            cfg.setActivarEfectivo(tf.isActivarEfectivo());
            cfg.setCorreoPayPal(tf.getCorreoPayPal().trim());
            cfg.setNumCuenta(tf.getNumCuenta().trim());
            cfg.setPrecioPayPal(tf.getPrecioPayPal());
            cfg.setPrecioReembolso(tf.getPrecioReembolso());
            cfg.setPrecioTransferencia(tf.getPrecioTransferencia());
            cfg.setPrecioEfectivo(tf.getPrecioEfectivo());
        
            cfgDAO.editaConfiguracionTienda(cfg);            
            request.getServletContext().setAttribute("cfg", cfg);
        }
        
        return mapping.findForward(SUCCESS);
    }
}
