/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import acceso.Acceso;
import acceso.AccesosDAO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import producto.Producto;
import usuario.Usuario;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class FiltroAccesos implements Filter {
    private final static String USUARIO_SESION = "usrSesion"; // Nombre de la variable de sesión que contendrá al usuario logeado
//    private final static int ID_CONFIG = 1;
    private long llegada;
    private long salida;
    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public FiltroAccesos() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltroAccesos:DoBeforeProcessing");
        }
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltroAccesos:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpSession sesion = ((HttpServletRequest)request).getSession();
        HttpServletRequest sr=(HttpServletRequest)request;
        String uri=sr.getRequestURI();
            
        llegada=System.currentTimeMillis();
        
        if(uri.contains("/administrar/") || uri.contains("/datos-personales/")){
            Usuario usr=(Usuario) sr.getSession().getAttribute(USUARIO_SESION);
            if(usr==null || usr.getId()==0){
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect(sr.getContextPath() + "/usuario/acceder/"); 
            }else
                chain.doFilter(request, response);
        }else
            chain.doFilter(request, response);
        
        salida=System.currentTimeMillis();
        
        try{

            if(!uri.contains("/administrar/") && !uri.contains("/chart") && !uri.contains("/img/") && !uri.contains("/images/") && !uri.contains("/js/") && !uri.contains("/META-INF/") && !uri.contains("/WEB-INF/") && !uri.contains("/css/") && !uri.contains("/ckeditor/") && !uri.contains("/imagen") && !uri.contains("/banner") && !uri.contains("/adminbanner")){
                Acceso a=new Acceso();
                AccesosDAO accDAO = (AccesosDAO) sesion.getServletContext().getAttribute("accDAO");
                Usuario usr=(Usuario) sr.getSession().getAttribute(USUARIO_SESION);
                Producto prod=(Producto) sr.getAttribute("producto");
                if(sr.getQueryString()!=null)
                    uri+= "?" + sr.getQueryString();

                a.setUri(uri);
                a.setCuando(new Date());
                a.setIp(request.getRemoteAddr());
                a.setPuerto(request.getRemotePort());
                a.setDuracion(salida-llegada);
                a.setSesionId(sr.getSession().getId());
                a.setOrigen(null);//<----------------------------------- ¡¡FALTA!!
                a.setAccion("CONSULTA");
                if(uri.contains("/pedido/agregar"))
                    a.setAccion("COMPRA");
                if(uri.contains("/pedido/confirmar/paso3"))
                    a.setAccion("VENTA");
                if(usr!=null)
                    a.setUsuarioId(usr.getId());        
                if(prod!=null)
                    a.setProductoId(prod.getId());
                String agent=sr.getHeader("User-Agent");
                a.setNavegador(agent.substring(0,agent.indexOf("(")));
                a.setPlataforma(agent.substring(agent.indexOf("(")+1,agent.indexOf(")")));

                accDAO.altaAcceso(a);
            }
        }catch(Exception e){
//            sendProcessingError(e, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("FiltroAccesos:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroAccesos()");
        }
        StringBuilder sb = new StringBuilder("FiltroAccesos(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
}
