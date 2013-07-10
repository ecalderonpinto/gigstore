/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import acceso.AccesosAction;
import acceso.AccesosDAO;
import categoria.CategoriaDAO;
import dominio.ConfiguracionTienda;
import dominio.ConfiguracionTiendaDAO;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.util.MessageResources;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import pedido.PedidoDAO;
import pedido.PedidoEstado;
import pedido.PedidoEstadoDAO;
import producto.ProductoDAO;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarTiendaAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";
    private final static int NREGS_PED=5; //Registros a mostrar en los resumenes Pedidos
    private final static int NREGS_STOCK=10; //Registros a mostrar en los resumenes Stock

    /** Provides the mapping from resource key to method name.
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.menu", "menu");
        map.put("accion.mostrar_listado", "mostrar_listado");
        map.put("accion.editar_inicio", "editar_inicio");
        map.put("accion.editar_ejecutar", "editar_ejecutar");
        return map;
    }

    /** Action called on mostrar_listado button click
     */
    public ActionForward menu(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        PedidoDAO pedDAO=(PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
        PedidoEstadoDAO estDAO=(PedidoEstadoDAO) request.getSession().getServletContext().getAttribute("peDAO");
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        AccesosDAO accDAO = (AccesosDAO) request.getSession().getServletContext().getAttribute("accDAO");
        MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
        request.setAttribute("nprods", prodDAO.devuelveNProductos());
        request.setAttribute("ndisp", prodDAO.devuelveNDisponibles());
        request.setAttribute("ncats", catDAO.devuelveNCategorias(true));
        request.setAttribute("nsubc", catDAO.devuelveNCategorias(false));
        request.setAttribute("ntags", catDAO.devuelveNCategorias(false));
        int i=0;
        for(Object o: estDAO.devuelveEstados()){
            i++;
            PedidoEstado est=(PedidoEstado) o;
            request.setAttribute("pedest" + i, est.getEstado());
            request.setAttribute("npedest" + i, pedDAO.devuelveNPedidos(est));
            if(i>5)
                break;
        }
        
        request.setAttribute("pednuevos", pedDAO.devuelvePedidos(NREGS_PED));
        request.setAttribute("stockbajo", prodDAO.devuelveStockBajo(NREGS_STOCK));
                
        int mes=new GregorianCalendar().get(Calendar.MONTH) + 1;
        int any=new GregorianCalendar().get(Calendar.YEAR);
        DefaultCategoryDataset dataset=AccesosAction.devuelveDatasetMes(new DefaultCategoryDataset(), mes, any, accDAO.devuelveVolumenVentas(mes, any).toArray(), "Ventas totales");        
        JFreeChart chart=ChartFactory.createLineChart(msg.getMessage(locale, "texto.est_volventas"), msg.getMessage(locale, "texto.est_ventas"), msg.getMessage(locale, "texto.est_moneda"), dataset, PlotOrientation.VERTICAL , false, false, false);
        chart.getCategoryPlot().getDomainAxis().setVisible(false);
        chart.getCategoryPlot().getRangeAxis().setRange(0, accDAO.devuelveMaxVolumenVentas(mes, any)+5);
        request.getSession().setAttribute("ventas", chart);  
        
        return mapping.findForward(SUCCESS);
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
        AdministrarTiendaForm tf=(AdministrarTiendaForm) form;
        ConfiguracionTiendaDAO cfgDAO=(ConfiguracionTiendaDAO) request.getSession().getServletContext().getAttribute("cfgDAO");
        ConfiguracionTienda cfg=cfgDAO.consultaConfiguracionTienda(Integer.parseInt(request.getParameter("id")));
        
        tf.setNombreEmpresa(cfg.getNombreEmpresa());
        tf.setDireccionEmpresa(cfg.getDireccionEmpresa());
        tf.setTelefonoEmpresa(cfg.getTelefonoEmpresa());
        tf.setEmailSistema(cfg.getEmailSistema());
        tf.setSocultarProducto(String.valueOf(cfg.isOcultarProducto()));
        tf.setSocultarBotonCompra(String.valueOf(cfg.isOcultarBotonCompra()));
        tf.setSstockMin(String.valueOf(cfg.getStockMin()));
        tf.setSmostrarIVAIncluido(String.valueOf(cfg.isMostrarIVAIncluido()));
        tf.setSusarDirFactura(String.valueOf(cfg.isUsarDirFactura()));
        
        request.getSession().setAttribute("monedaSel", cfg.getMoneda());
        request.getSession().setAttribute("idiomaSel", cfg.getIdioma());
        request.getSession().setAttribute("nRegsSel", cfg.getnRegsPag());
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
        AdministrarTiendaForm tf=(AdministrarTiendaForm) form;
        ConfiguracionTiendaDAO cfgDAO=(ConfiguracionTiendaDAO) request.getSession().getServletContext().getAttribute("cfgDAO");
        ConfiguracionTienda cfg=cfgDAO.consultaConfiguracionTienda(Integer.parseInt(request.getParameter("id")));
        
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("nRegsSel");
        request.getSession().removeAttribute("monedaSel");
        request.getSession().removeAttribute("idiomaSel");
                
        if(this.isCancelled(request)){
            return mapping.findForward(SUCCESS);
        }else{
            cfg.setNombreEmpresa(tf.getNombreEmpresa().trim());
            cfg.setDireccionEmpresa(tf.getDireccionEmpresa().trim());
            cfg.setTelefonoEmpresa(tf.getTelefonoEmpresa().trim());
            cfg.setEmailSistema(tf.getEmailSistema().trim());
            cfg.setIdioma(tf.getIdioma());
            cfg.setnRegsPag(tf.getnRegsPag());
            cfg.setMoneda(tf.getMoneda().trim());
            cfg.setOcultarProducto(tf.isOcultarProducto());
            cfg.setOcultarBotonCompra(tf.isOcultarBotonCompra());
            cfg.setStockMin(tf.getStockMin());
            cfg.setMostrarIVAIncluido(tf.isMostrarIVAIncluido());
            cfg.setUsarDirFactura(tf.isUsarDirFactura());
        
            cfgDAO.editaConfiguracionTienda(cfg);            
            request.getServletContext().setAttribute("cfg", cfg);
        }
        
        return mapping.findForward(SUCCESS);
    }
}
