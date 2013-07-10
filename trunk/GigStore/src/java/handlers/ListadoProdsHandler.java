/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import categoria.Categoria;
import categoria.CategoriaDescripcion;
import dominio.Cadenas;
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

/**
 *
 * @author Manel Márquez Bonilla
 */
public class ListadoProdsHandler extends SimpleTagSupport {
    private final static int NCOLS=3;
    private String id;
    private String coleccion;
    
    public enum TipoListado
    {
        DESTACADOS, VENDIDOS, NUEVOS, BUSCAR, CATEGORIAS, LISTACATS; 
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
        ProductoDAO prodDAO = (ProductoDAO) contexto.getServletContext().getAttribute("prodDAO");
        List prods = null;
        Categoria cat = null;
        ConfiguracionTienda cfg=(ConfiguracionTienda) contexto.getServletContext().getAttribute("cfg");
        
        try {
            out.println("                <div class=\"listaprods_listado\">");

            switch(TipoListado.valueOf(coleccion.toUpperCase())){
                case DESTACADOS: 
                    prods=prodDAO.devuelveDestacados(0, cfg.isOcultarProducto());
                    break;
                case VENDIDOS:  
                    prods=prodDAO.devuelveVendidos(0, cfg.isOcultarProducto());
                    break;
                case NUEVOS: 
                    prods=prodDAO.devuelveNuevos(0, cfg.isOcultarProducto());
                    break;
                case BUSCAR: 
                    prods=(List) contexto.getSession().getAttribute("res_busqueda");
                    break;
                case CATEGORIAS: 
                    cat=(Categoria) contexto.getRequest().getAttribute("categoria");
                    prods=prodDAO.devuelveProductos(cat, cfg.isOcultarProducto());
                    break;
            }

            out.println("                            <div id=\"" + id + "\">");
                 
            List categorias=new ArrayList();
            String titcat="";
            if(coleccion.equalsIgnoreCase("LISTACATS")){
                categorias=(List) contexto.getServletContext().getAttribute("categorias");
                titcat=messages.getMessage(locale, "texto.categorias");
            }else if(coleccion.equalsIgnoreCase("CATEGORIAS")){
                for(Categoria c:cat.getSubcategorias())
                    categorias.add(c);
                titcat=messages.getMessage(locale, "texto.subcategorias");
            }else
                categorias=null;
             
            if(categorias!=null && !categorias.isEmpty()){
                out.println("                               <h3>" + titcat + "</h3>");  
                out.println("                               <ul>");  
                int cont=1;
                for(Object o: categorias){
                    Categoria c=(Categoria) o;
                    if(cont==1 || (cont-1)%cfg.getnRegsPag()==0)
                        out.println("                                 <li>"); 

                    if(cont==1 || (cont-1)%NCOLS==0)
                        out.println("                                 <div class=\"fila\">"); 

                    out.println("                                  <a href=\"" + contexto.getServletContext().getContextPath() + "/categorias/" + c.getId() + "-" + Cadenas.formatoUrl(c.getNombre()) + "/\">");  
                    out.println("                                    <div class=\"producto\">");
                    out.println("                                        <div class=\"imgprod\">");

                    String aux;
                    String alt;
                    if(c.getImagen()!=null){
                        aux=contexto.getServletContext().getContextPath() + "/imagen?id=" + String.valueOf(c.getImagen().getId());
                        alt = c.getImagen().getAlt();
                    }else{
                        aux= contexto.getServletContext().getContextPath() + "/images/tienda/Gift.png";
                        alt="";
                    }

                    if(aux.isEmpty())
                        aux= contexto.getServletContext().getContextPath() + "/images/tienda/Gift.png";
                    if(alt.isEmpty())
                        alt=c.getNombre();
                    out.println("                                            <img src=\"" + aux + "\" alt=\"" + alt + "\" />");  
                    out.println("                                        </div>");

                    out.println("                                        <div class=\"textosprod\">");

                    aux="";
                    for(CategoriaDescripcion d: c.getDescripciones()){
                        if(d.getIdioma().equalsIgnoreCase(locale.getLanguage()))
                            aux=d.getNombre();
                    }
                    if(aux.isEmpty()){
                        for(CategoriaDescripcion d: c.getDescripciones()){
                            if(d.getIdioma().equalsIgnoreCase(cfg.getIdioma()))
                                aux=d.getNombre();
                        }
                    }
                    if(aux.isEmpty())
                        aux=c.getNombre();
                    out.println("                                            <strong><p class=\"nombre\">" + aux + "</p></strong>");

                    aux="";
                    for(CategoriaDescripcion d: c.getDescripciones()){
                        if(d.getIdioma().equalsIgnoreCase(locale.getLanguage()) && !d.getDescripcion().isEmpty())
                            aux=d.getDescripcion();
                    }
                    if(aux.isEmpty()){
                        for(CategoriaDescripcion d: c.getDescripciones()){
                            if(d.getIdioma().equalsIgnoreCase(cfg.getIdioma()))
                                aux=d.getDescripcion();
                        }
                    }
                    if(aux.trim().length()>60)
                        aux=aux.trim().substring(0, 60) + "...";
                    if(!aux.isEmpty())
                        out.println("                                            <p class=\"descprod\">" + aux + "</p>");

                    out.println("                                        </div>");
                    out.println("                                    </div>");
                    out.println("                                  </a>");

                    if(cont==NCOLS || cont%NCOLS==0 || cont==categorias.size())
                        out.println("                                </div>"); 

                    if(cont==cfg.getnRegsPag() || cont%cfg.getnRegsPag()==0 || cont==categorias.size())
                        out.println("                                </li>"); 

                    cont++;
                }
                out.println("                           </ul>");
            }
             
            if(prods!=null && !prods.isEmpty()) {
                out.println("                               <h3>" + messages.getMessage(locale, "texto.productos") + "</h3>");  
                out.println("                               <ul>");  
                int cont=1;
                for(Object o: prods){
                    Producto p=(Producto) o;
                    if(cont==1 || (cont-1)%cfg.getnRegsPag()==0)
                        out.println("                                 <li>"); 

                    if(cont==1 || (cont-1)%NCOLS==0)
                        out.println("                                 <div class=\"fila\">"); 

                    out.println("                                  <a href=\"" + contexto.getServletContext().getContextPath() + p.getUrl() + "\">");  
                    out.println("                                    <div class=\"producto\">");
                    out.println("                                        <div class=\"imgprod\">");

                    String aux = "";
                    String alt = "";
                    for(Imagen i: p.getImagenes()) {
                        if(i.isPrincipal()){
                            aux = contexto.getServletContext().getContextPath() + "/imagen?id=" + String.valueOf(i.getId()); 
                            alt = i.getAlt();
                        }
                    }
                    if(aux.isEmpty())
                        aux= contexto.getServletContext().getContextPath() + "/images/tienda/Gift.png";
                    if(alt.isEmpty())
                        alt=p.getNombre();
                    out.println("                                            <img src=\"" + aux + "\" alt=\"" + alt + "\" />");  
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
                    if(aux.trim().length()>60)
                        aux=aux.trim().substring(0, 60) + "...";
                    if(!aux.isEmpty())
                        out.println("                                            <p class=\"descprod\">" + aux + "</p>");

                    NumberFormat nf=new DecimalFormat("#.##");
                    String moneda="";
                    switch(MostrarDestacadosHandler.Monedas.valueOf(cfg.getMoneda())){
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

                    if(cont==NCOLS || cont%NCOLS==0 || cont==prods.size())
                        out.println("                                </div>"); 

                    if(cont==cfg.getnRegsPag() || cont%cfg.getnRegsPag()==0 || cont==prods.size())
                        out.println("                                </li>"); 

                    cont++;
                }
                out.println("                           </ul>");
            } 
            
            if((categorias==null || categorias.isEmpty()) && (prods==null || prods.isEmpty()))
                out.println("                               <h3>" + messages.getMessage(locale, "texto.noresult") + "</h3>");  
            
            
            out.println("                      </div>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }
            out.println("                </div>");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in ListadoProdsHandler tag", ex);
        }
    }
}
