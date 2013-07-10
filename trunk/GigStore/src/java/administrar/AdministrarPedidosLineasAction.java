/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import dominio.ConfiguracionTienda;
import dominio.TipoIvaDAO;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import pedido.Pedido;
import pedido.PedidoDAO;
import pedido.PedidoLinea;
import pedido.PedidoLineaDAO;
import producto.ProductoDAO;
import producto.ProductoOpcionDAO;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarPedidosLineasAction extends LookupDispatchAction {
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
        AdministrarPedidoLineaForm plf=(AdministrarPedidoLineaForm) form;
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        ProductoOpcionDAO optDAO=(ProductoOpcionDAO) request.getSession().getServletContext().getAttribute("poptDAO");
        
        plf.setScantidad("1");
        
        request.getSession().setAttribute("id_ped", request.getParameter("id"));
        request.getSession().setAttribute("productoSel", "0");
        request.getSession().setAttribute("productos", prodDAO.devuelveProductos());
        request.getSession().setAttribute("opcionSel", "0");
        request.getSession().setAttribute("opciones", optDAO.devuelveProductoOpcion());
        request.getSession().setAttribute("accion", "alta");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        AdministrarPedidoLineaForm plf=(AdministrarPedidoLineaForm) form;
        PedidoLinea lin=new PedidoLinea();
        PedidoLineaDAO linDAO=(PedidoLineaDAO) request.getSession().getServletContext().getAttribute("linDAO");
        PedidoDAO pedDAO=(PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
        Pedido ped=pedDAO.consultaPedido(Integer.parseInt(request.getSession().getAttribute("id_ped").toString()));
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        ProductoOpcionDAO optDAO=(ProductoOpcionDAO) request.getSession().getServletContext().getAttribute("poptDAO");        
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        BigDecimal totalIVA=BigDecimal.ZERO;
        totalIVA.setScale(2);
        
        if(!this.isCancelled(request)){
            lin.setPedido(ped);
            lin.setProducto(prodDAO.consultaProducto(plf.getProducto()));
            if(plf.getOpcion()>1){
                lin.setOpcion(optDAO.consultaProductoOpcion(plf.getOpcion()));
                lin.setOpcionProducto(lin.getOpcion().getOpcion()); 
            }
            lin.setCantidad(plf.getCantidad());
            lin.setIva(lin.getProducto().getIva().getIva());
            lin.setPrecio(lin.getProducto().getPrecio());
            if(plf.getOpcion()>1 && lin.getOpcion().getPrecio()!=null)
                lin.setPrecio(lin.getOpcion().getPrecio());
            lin.setSubtotal(lin.getPrecio().multiply(new BigDecimal(lin.getCantidad())));
            lin.setNlinea(lin.getPedido().getLineas().size()+1);
            
            linDAO.altaLinea(lin);
            
            ped=pedDAO.consultaPedido(Integer.parseInt(request.getSession().getAttribute("id_ped").toString()));           
            
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
        
        request.getSession().removeAttribute("id_ped");
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("productos");
        request.getSession().removeAttribute("opciones");
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/pedidos/detalle.do?metodo=mostrar_detalle&id=" + ped.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        AdministrarPedidoLineaForm plf=(AdministrarPedidoLineaForm) form;
        PedidoLineaDAO linDAO=(PedidoLineaDAO) request.getSession().getServletContext().getAttribute("linDAO");
        PedidoLinea lin=linDAO.consultaLinea(Integer.parseInt(request.getParameter("id")));
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
                
        plf.setScantidad(String.valueOf(lin.getCantidad()));
                
        request.getSession().setAttribute("productos", prodDAO.devuelveProductos());
        request.getSession().setAttribute("productoSel", lin.getProducto().getId());
        request.getSession().setAttribute("opciones", lin.getProducto().getOpciones()); 
        if(lin.getOpcion()!=null)
            request.getSession().setAttribute("opcionSel", lin.getOpcion().getId() + ";" + lin.getProducto().getId());
        
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
        AdministrarPedidoLineaForm plf=(AdministrarPedidoLineaForm) form;
        PedidoLineaDAO linDAO=(PedidoLineaDAO) request.getSession().getServletContext().getAttribute("linDAO");
        PedidoLinea lin=linDAO.consultaLinea(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        PedidoDAO pedDAO=(PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
        ProductoOpcionDAO optDAO=(ProductoOpcionDAO) request.getSession().getServletContext().getAttribute("poptDAO");
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        BigDecimal totalIVA=BigDecimal.ZERO;
        totalIVA.setScale(2);
        
        request.getSession().removeAttribute("opciones");
        request.getSession().removeAttribute("opcionSel");
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            lin.setCantidad(plf.getCantidad()); 
            
            lin.setOpcion(optDAO.consultaProductoOpcion(plf.getOpcion()));            
            if(lin.getOpcion()!=null) {
                lin.setOpcionProducto(lin.getOpcion().getOpcion());
                if(lin.getOpcion().getPrecio()!=null && lin.getOpcion().getPrecio().compareTo(BigDecimal.ZERO)>0)
                    lin.setPrecio(lin.getOpcion().getPrecio());
                else 
                    lin.setPrecio(lin.getProducto().getPrecio());
            }
            
            lin.setSubtotal(lin.getPrecio().multiply(new BigDecimal(lin.getCantidad())));
            linDAO.editaLinea(lin);
            Pedido ped=pedDAO.consultaPedido(lin.getPedido().getId());
            
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
        af.setPath("/administrar/pedidos/detalle.do?metodo=mostrar_detalle&id=" + lin.getPedido().getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        PedidoLineaDAO linDAO=(PedidoLineaDAO) request.getSession().getServletContext().getAttribute("linDAO");
        PedidoLinea lin=linDAO.consultaLinea(Integer.parseInt(request.getParameter("id")));
        PedidoDAO pedDAO=(PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        BigDecimal totalIVA=BigDecimal.ZERO;
        totalIVA.setScale(2);
        
        for(PedidoLinea lp: lin.getPedido().getLineas()){
            if(lp.getNlinea()>lin.getNlinea()){
                lp.setNlinea(lp.getNlinea()-1);
                linDAO.editaLinea(lp);
            }
        }       
        
        linDAO.eliminaLinea(lin);
            
        Pedido ped=pedDAO.consultaPedido(lin.getPedido().getId());
        ped.setTotal(BigDecimal.ZERO);
        for(PedidoLinea lp: ped.getLineas()){
            ped.setTotal(ped.getTotal().add(lp.getSubtotal()));
            totalIVA=totalIVA.add(TipoIvaDAO.calcIva(lp.getSubtotal(),lp.getIva()));
        }
        ped.setTotal(ped.getTotal().add(ped.getTipoenv().getPrecio()));
        if(!cfg.isMostrarIVAIncluido())
            ped.setTotal(ped.getTotal().add(totalIVA));

        pedDAO.editaPedido(ped);
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/pedidos/detalle.do?metodo=mostrar_detalle&id=" + ped.getId());
        af.setRedirect(true);
        
        return af; 
    }
}