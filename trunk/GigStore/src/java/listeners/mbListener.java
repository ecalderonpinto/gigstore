/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import acceso.AccesosDAO;
import categoria.CategoriaDAO;
import categoria.CategoriaDescripcionDAO;
import direccion.DireccionDAO;
import dominio.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TreeMap;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import listacorreo.ListaCorreoDAO;
import opcion.OpcionDAO;
import opcion.ValorDAO;
import pedido.CosteEnvioDAO;
import pedido.PedidoDAO;
import pedido.PedidoEstadoDAO;
import pedido.PedidoLineaDAO;
import producto.ProductoDAO;
import producto.ProductoDescripcionDAO;
import producto.ProductoEstadoDAO;
import producto.ProductoOpcionDAO;
import usuario.UsuarioDAO;
import usuario.UsuarioEstadoDAO;

/**
 * Web application lifecycle listener.
 * @author Manel Márquez Bonilla
 */
@WebListener()
public class mbListener implements HttpSessionListener, ServletRequestListener, ServletContextListener {
    private final static int ID_CONFIG = 1;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession sesion = se.getSession();
        if(sesion.getAttribute("txt")==null){
            TextosTiendaDAO txtDAO=(TextosTiendaDAO) sesion.getServletContext().getAttribute("txtDAO");
            ConfiguracionTienda cfg=(ConfiguracionTienda) sesion.getServletContext().getAttribute("cfg");
            TextosTienda txt=txtDAO.consultaTextosTienda(cfg.getIdioma());
            sesion.setAttribute("txt", txt);
        }
        
        ProductoDAO prodDAO = (ProductoDAO) sesion.getServletContext().getAttribute("prodDAO");
        sesion.setAttribute("marcas", prodDAO.devuelveMarcas());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        se.getSession().removeAttribute("txt");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request=(HttpServletRequest) sre.getServletRequest();
        TextosTienda txt=(TextosTienda) request.getSession().getAttribute("txt");
        if(txt.getIdioma()==null || !txt.getIdioma().equalsIgnoreCase(sre.getServletRequest().getLocale().getLanguage())){
            TextosTiendaDAO txtDAO=(TextosTiendaDAO) sre.getServletContext().getAttribute("txtDAO");
            txt=txtDAO.consultaTextosTienda(sre.getServletRequest().getLocale().getLanguage());
            if(txt.getIdioma()==null){
                ConfiguracionTienda cfg=(ConfiguracionTienda) sre.getServletContext().getAttribute("cfg");
                txt=txtDAO.consultaTextosTienda(cfg.getIdioma());
            }
            request.getSession().setAttribute("txt", txt);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {        
        ConfiguracionTiendaDAO cfgDAO=new ConfiguracionTiendaDAO();
        sce.getServletContext().setAttribute("cfgDAO", cfgDAO); 
                
        TextosTiendaDAO txtDAO=new TextosTiendaDAO();
        sce.getServletContext().setAttribute("txtDAO", txtDAO); 
        
        TipoIvaDAO ivaDAO=new TipoIvaDAO();  
        sce.getServletContext().setAttribute("ivaDAO", ivaDAO); 
        
        ListaCorreoDAO listaDAO=new ListaCorreoDAO();
        sce.getServletContext().setAttribute("listaDAO", listaDAO); 
        
        AccesosDAO accDAO = new AccesosDAO();
        sce.getServletContext().setAttribute("accDAO", accDAO);         
        
        UsuarioDAO usrDAO=new UsuarioDAO();
        sce.getServletContext().setAttribute("usrDAO", usrDAO);  
        
        UsuarioEstadoDAO ueDAO=new UsuarioEstadoDAO();
        sce.getServletContext().setAttribute("ueDAO", ueDAO);  
        
        DireccionDAO dirDAO=new DireccionDAO();
        sce.getServletContext().setAttribute("dirDAO", dirDAO); 
        
        ProductoDAO prodDAO = new ProductoDAO();  
        sce.getServletContext().setAttribute("prodDAO", prodDAO); 
        
        ProductoDescripcionDAO prdDAO=new ProductoDescripcionDAO();
        sce.getServletContext().setAttribute("prdDAO", prdDAO); 
        
        ProductoEstadoDAO preDAO=new ProductoEstadoDAO();
        sce.getServletContext().setAttribute("preDAO", preDAO); 
        
        ProductoOpcionDAO poptDAO=new ProductoOpcionDAO();
        sce.getServletContext().setAttribute("poptDAO", poptDAO); 
        
        OpcionDAO optDAO=new OpcionDAO();
        sce.getServletContext().setAttribute("optDAO", optDAO); 
        
        ValorDAO valDAO=new ValorDAO();
        sce.getServletContext().setAttribute("valDAO", valDAO); 
        
        ImagenDAO imgDAO=new ImagenDAO();
        sce.getServletContext().setAttribute("imgDAO", imgDAO); 
        
        CategoriaDAO catDAO=new CategoriaDAO();
        sce.getServletContext().setAttribute("catDAO", catDAO); 
        
        CategoriaDescripcionDAO cdDAO=new CategoriaDescripcionDAO();
        sce.getServletContext().setAttribute("cdDAO", cdDAO); 
        
        PedidoDAO pedDAO=new PedidoDAO();
        sce.getServletContext().setAttribute("pedDAO", pedDAO); 
        
        PedidoLineaDAO linDAO=new PedidoLineaDAO();
        sce.getServletContext().setAttribute("linDAO", linDAO); 
        
        PedidoEstadoDAO peDAO=new PedidoEstadoDAO();
        sce.getServletContext().setAttribute("peDAO", peDAO); 
        
        CosteEnvioDAO envDAO=new CosteEnvioDAO();
        sce.getServletContext().setAttribute("envDAO", envDAO); 
        
        ConfiguracionTienda  cfg=cfgDAO.consultaConfiguracionTienda(ID_CONFIG);
        sce.getServletContext().setAttribute("cfg", cfg);    
        
        sce.getServletContext().setAttribute("categorias", catDAO.devuelveCategoriasPrincipales(true));
        
        Locale locales[] = SimpleDateFormat.getAvailableLocales();
        TreeMap<String, String> map = new TreeMap<String, String>();
        for (int i = 0; i < locales.length; i++) {
            if(locales[i].toString().length() < 3)
                map.put(locales[i].toString(), locales[i].getDisplayName());
        }            
        sce.getServletContext().setAttribute("locales", map);  
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("categorias");
        sce.getServletContext().removeAttribute("locales");
        sce.getServletContext().removeAttribute("cfg");
    }
}
