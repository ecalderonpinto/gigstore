/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import dominio.ConfiguracionTienda;
import dominio.Imagen;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.struts.util.MessageResources;
import producto.Producto;
import producto.ProductoDAO;
import producto.ProductoDescripcion;
import usuario.Usuario;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class MostrarDestacadosHandler extends SimpleTagSupport {
    private final static String USUARIO_SESION = "usrSesion"; // Nombre de la variable de sesión que contendrá al usuario logeado
    private static int NRP=3; //Número de productos en cada página del Slider
    private String id;
    private String coleccion;
    
    public enum TipoListado
    {
        DESTACADOS, VENDIDOS, NUEVOS, SEGUIMIENTO; 
    }
    
    public enum Monedas
    {
        EUR, USD, GBP; 
    }

    public String getColeccion() {
        return coleccion;
    }

    public void setColeccion(String coleccion) {
        this.coleccion = coleccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        MessageResources messages = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        PageContext contexto=(PageContext) getJspContext();
        Locale locale = contexto.getRequest().getLocale();
        ConfiguracionTienda cfg=(ConfiguracionTienda) contexto.getServletContext().getAttribute("cfg");
                
        ProductoDAO prodDAO = (ProductoDAO) contexto.getServletContext().getAttribute("prodDAO");
        List prods = null;
        String titulo="";
        String link="";
        
        try {

             switch(TipoListado.valueOf(coleccion.toUpperCase())){
                 case DESTACADOS: 
                     prods=prodDAO.devuelveDestacados(cfg.getnRegsPag(), cfg.isOcultarProducto());
                     contexto.getSession().setAttribute("accion", "inicio");
                     titulo=messages.getMessage(locale, "enlace.destacados");
                     link="<a href=\"" + contexto.getServletContext().getContextPath() + "/recomendaciones/destacados/\" class=\"slider\">" + titulo + "</a>";
                     break;
                 case VENDIDOS:  
                     prods=prodDAO.devuelveVendidos(cfg.getnRegsPag(), cfg.isOcultarProducto());
                     contexto.getSession().setAttribute("accion", "inicio");
                     titulo=messages.getMessage(locale, "enlace.vendidos");
                     link="<a href=\"" + contexto.getServletContext().getContextPath() + "/recomendaciones/vendidos/\" class=\"slider\">" + titulo + "</a>";
                     break;
                 case NUEVOS: 
                     prods=prodDAO.devuelveNuevos(cfg.getnRegsPag(), cfg.isOcultarProducto());
                     contexto.getSession().setAttribute("accion", "inicio");
                     titulo=messages.getMessage(locale, "enlace.nuevos");
                     link="<a href=\"" + contexto.getServletContext().getContextPath() + "/recomendaciones/nuevos/\" class=\"slider\">" + titulo + "</a>";
                     break;
                 case SEGUIMIENTO: 
                     Usuario usr=(Usuario) contexto.getSession().getAttribute(USUARIO_SESION);
                     prods=new ArrayList();
                     for(Object o: usr.getSeguimiento()){
                         prods.add(o);
                     }                         
                     contexto.getSession().setAttribute("accion", "seguimiento");
                     titulo=messages.getMessage(locale, "texto.seguimiento");
                     break;
             }
             
             if(!prods.isEmpty()){
                out.println("<div class=\"cuadro\">");
                out.println("                  <p class=\"meta\"><span class=\"izda\"></span><span class=\"dcha\">" + titulo + "</span></p>");
                out.println("                  <div class=\"entrada\">");   
                out.println("                      <div class=\"listaprods\">");          

                out.println("                          <div id=\"" + id + "\">");
                out.println("                              <ul>");  
                int cont=1;
                for(Object o: prods){
                    Producto p=(Producto) o;
                    if(cont==1 || (cont-1)%NRP==0)
                        out.println("                               <li>");  
                    out.println("                                  <a href=\"" + contexto.getServletContext().getContextPath() + p.getUrl() +"\">");  
                    out.println("                                    <div class=\"producto\">");
                    out.println("                                        <div class=\"imgprod\">");
                    String aux = "";
                    for(Imagen i: p.getImagenes()) {
                        if(i.isPrincipal())
                            aux = contexto.getServletContext().getContextPath() + "/imagen?id=" + String.valueOf(i.getId()); 
                    }
                    if(aux.isEmpty())
                        aux= contexto.getServletContext().getContextPath() + "/images/tienda/Gift.png";
                    out.println("                                         <img src=\"" + aux + "\" alt=\"" + p.getNombre() + "\" />");  
                    out.println("                                        </div>");

                    out.println("                                        <div class=\"textosprod\">");

                    aux="";
                    for(ProductoDescripcion d: p.getDescripciones()){
                        if(d.getIdioma().equalsIgnoreCase(locale.getLanguage()))
                            aux=d.getNombre();
                    }
                    if(aux.isEmpty()){
                        for(ProductoDescripcion d: p.getDescripciones()){
                            if(d.getIdioma().equalsIgnoreCase(cfg.getIdioma()))
                                aux=d.getNombre();
                        }
                    }
                    if(aux.isEmpty())
                        aux=p.getNombre();
                    out.println("                                            <strong><p class=\"nombre\">" + aux + "</p></strong>");
                    out.println("                                            <p class=\"ref\">" + p.getReferencia() + "</p>");

                    aux="";
                    for(ProductoDescripcion d: p.getDescripciones()){
                        if(d.getIdioma().equalsIgnoreCase(locale.getLanguage()) && !d.getDescripcion().isEmpty())
                            aux=d.getDescripcion();
                    }
                    if(aux.isEmpty()){
                        for(ProductoDescripcion d: p.getDescripciones()){
                            if(d.getIdioma().equalsIgnoreCase(cfg.getIdioma()))
                                aux=d.getDescripcion();
                        }
                    }
                    if(aux.length()>60)
                        aux=aux.substring(0, 60) + "...";
                    if(!aux.isEmpty())
                        out.println("                                            <p class=\"descprod\">" + aux + "</p>");

                    NumberFormat nf=new DecimalFormat("#.##");
                    String moneda="";
                    switch(Monedas.valueOf(cfg.getMoneda())){
                        case EUR: moneda="&euro;"; break;
                        case USD: moneda="&#36;"; break;
                        case GBP: moneda="&#163;"; break;
                    }
                    
                    BigDecimal precio=p.getPrecio();
                    if(p.getDescuento().compareTo(BigDecimal.ZERO)>0){
                        if(p.isDescporcentaje()) {
                            BigDecimal totaldesc=p.getPrecio().multiply(p.getDescuento()).divide(new BigDecimal(100));
                            precio=p.getPrecio().subtract(totaldesc);
                        } else
                            precio=p.getPrecio().subtract(p.getDescuento());
                    }

                    if(p.getCategoria().getDescuento().compareTo(BigDecimal.ZERO)>0){
                        if(p.getCategoria().isDescporcentaje()) {
                            BigDecimal totaldesc=precio.multiply(p.getCategoria().getDescuento()).divide(new BigDecimal(100));
                            precio=precio.subtract(totaldesc);
                        } else
                            precio=precio.subtract(p.getCategoria().getDescuento());
                    }
                    
                    String prant="";
                    if(p.getDescuento().compareTo(BigDecimal.ZERO)>0 || p.getCategoria().getDescuento().compareTo(BigDecimal.ZERO)>0)
                        prant="<strong class=\"prant\">" + nf.format(p.getPrecio()) + moneda + "</strong>";
                    
                    out.println("                                            <p class=\"precio\">" + messages.getMessage(locale, "texto.precio") + ": " + prant +" <strong>" + nf.format(precio) + moneda + "</strong></p>");
                    out.println("                                        </div>");
                    out.println("                                    </div>");
                    out.println("                                  </a>");
                    if(cont==NRP || cont%NRP==0 || cont==prods.size())
                        out.println("                              </li>"); 
                    cont++;
                }
                out.println("                         </ul>");
                out.println("                      </div>");

                JspFragment f = getJspBody();
                if (f != null) {
                    f.invoke(out);
                }

                out.println("                  </div>");

                out.println("              </div>");
                out.println("              <p class=\"links\">" + link + "</p>");
                out.println("          </div>");
             }             

        } catch (java.io.IOException ex) {
            throw new JspException("Error in ListadoProdsHandler tag", ex);
        }
    }
}
