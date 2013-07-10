/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import dominio.ConfiguracionTienda;
import dominio.GeneradorTablas;
import dominio.TipoIvaDAO;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import pedido.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarPedidosAction extends LookupDispatchAction {
    private final static String FORMATO_FECHA="dd/MM/yyyy HH:mm:ss";
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
        map.put("accion.editar_inicio", "editar_inicio");
        map.put("accion.editar_ejecutar", "editar_ejecutar");
        return map;
    }

    /** Action called on mostrar_listado button click
     */
    public ActionForward mostrar_listado(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        PedidoDAO pedDAO=(PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
        
        request.setAttribute("listado_pedidos", GeneradorTablas.tablaPedidos(pedDAO.devuelvePedidos(), request, response));
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on mostrar_detalle button click
     */
    public ActionForward mostrar_detalle(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        PedidoDAO pedDAO=(PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
        Pedido ped=pedDAO.consultaPedido(Integer.parseInt(request.getParameter("id")));
        BigDecimal subtotal=new BigDecimal(BigInteger.ZERO);
        
        for(PedidoLinea lin: ped.getLineas()){
            subtotal=subtotal.add(lin.getPrecio().multiply(new BigDecimal(lin.getCantidad())));
        }
        
        request.setAttribute("pedido", ped);
        request.setAttribute("listado_lineas", GeneradorTablas.tablaLineasPedido(ped.getLineas(), request, response));
        request.setAttribute("subtotal", subtotal);
                
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        SimpleDateFormat df=new SimpleDateFormat(FORMATO_FECHA);
        PedidoDAO pedDAO=(PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
        PedidoEstadoDAO estDAO=(PedidoEstadoDAO) request.getSession().getServletContext().getAttribute("peDAO");
        Pedido ped=pedDAO.consultaPedido(Integer.parseInt(request.getParameter("id")));
        AdministrarPedidoForm pf=(AdministrarPedidoForm) form; 
        CosteEnvioDAO envDAO=(CosteEnvioDAO) request.getSession().getServletContext().getAttribute("envDAO");
        
        pf.setDirEnvio(ped.getDireccionEnvio());
        pf.setDirFactura(ped.getDireccionFactura());
        if(ped.getFechaPago()!=null)
            pf.setSfechaPago(df.format(ped.getFechaPago()));
        else
            pf.setSfechaPago("");
        request.getSession().setAttribute("id", ped.getId());
        request.getSession().setAttribute("envios", envDAO.devuelveCosteEnvios()); 
        request.getSession().setAttribute("estados", estDAO.devuelveEstados());
        request.getSession().setAttribute("formaPagoSel", String.valueOf(ped.getFormaPago()));
        request.getSession().setAttribute("envioSel", String.valueOf(ped.getTipoenv().getId()));
        request.getSession().setAttribute("estadoSel", String.valueOf(ped.getEstado().getId()));
              
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception { 
        PedidoDAO pedDAO=(PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
        Pedido ped=pedDAO.consultaPedido(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        PedidoEstadoDAO estDAO=(PedidoEstadoDAO) request.getSession().getServletContext().getAttribute("peDAO");
        AdministrarPedidoForm pf=(AdministrarPedidoForm) form; 
        CosteEnvioDAO envDAO=(CosteEnvioDAO) request.getSession().getServletContext().getAttribute("envDAO");
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        BigDecimal totalIVA=BigDecimal.ZERO;
        totalIVA.setScale(2);
        
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("envios");
        request.getSession().removeAttribute("estados");
        request.getSession().removeAttribute("formaPagoSel");
        request.getSession().removeAttribute("envioSel");
        request.getSession().removeAttribute("estadoSel");
                 
        if(!this.isCancelled(request)){
            ped.setDireccionEnvio(pf.getDirEnvio());
            ped.setDireccionFactura(pf.getDirFactura());
            if(!pf.getSfechaPago().equals(""))
                ped.setFechaPago(pf.getFechaPago());
            
            ped.setFormaPago(pf.getFormaPago());  
            if(ped.getFormaPago().equalsIgnoreCase("pp"))
                ped.setPrecioFP(cfg.getPrecioPayPal());
            else if(ped.getFormaPago().equalsIgnoreCase("tr"))
                ped.setPrecioFP(cfg.getPrecioTransferencia());
            else if(ped.getFormaPago().equalsIgnoreCase("ef"))
                ped.setPrecioFP(cfg.getPrecioEfectivo());
            else if(ped.getFormaPago().equalsIgnoreCase("cr"))
                ped.setPrecioFP(cfg.getPrecioReembolso());
            else 
                ped.setPrecioFP(BigDecimal.ZERO);
            
            ped.setTipoenv(envDAO.consultaCosteEnvio(pf.getTipoenv()));
            ped.setEnvio(ped.getTipoenv().getPrecio());
            ped.setEstado(estDAO.consultaEstado(pf.getEstado()));
            
            ped.setTotal(BigDecimal.ZERO);
            for(PedidoLinea lp: ped.getLineas()){
                ped.setTotal(ped.getTotal().add(lp.getSubtotal()));
                totalIVA=totalIVA.add(TipoIvaDAO.calcIva(lp.getSubtotal(),lp.getIva()));
            }
            ped.setTotal(ped.getTotal().add(ped.getTipoenv().getPrecio()));
            ped.setTotal(ped.getTotal().add(ped.getPrecioFP()));
            if(!cfg.isMostrarIVAIncluido())
                ped.setTotal(ped.getTotal().add(totalIVA));
            
            pedDAO.editaPedido(ped);
        }
            
        ActionForward af=new ActionForward();
        af.setPath("/administrar/pedidos/detalle.do?metodo=mostrar_detalle&id=" + ped.getId());
        af.setRedirect(true);

        return af; 
    }
}
