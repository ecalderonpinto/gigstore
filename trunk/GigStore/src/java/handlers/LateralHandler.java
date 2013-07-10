/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import categoria.Categoria;
import categoria.CategoriaDescripcion;
import dominio.Cadenas;
import dominio.ConfiguracionTienda;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class LateralHandler extends SimpleTagSupport {
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        PageContext contexto=(PageContext) getJspContext();
        Locale locale = contexto.getRequest().getLocale();
        ConfiguracionTienda cfg=(ConfiguracionTienda) contexto.getServletContext().getAttribute("cfg");
        List categorias=(List) contexto.getServletContext().getAttribute("categorias");
        String clase="";
        
        try {
            if(categorias!=null){
                for(Object o: categorias){
                    Categoria c=(Categoria) o;              
                    
                    if(c.getSubcategorias()!=null && !c.getSubcategorias().isEmpty())
                        clase=" class=\"desplegable\"";
                                
                    out.println("<li class=\"cat\">");
                    
                    if(c.getNodo()>0){
                        for(int i=0;i<c.getNodo();i++)
                            out.println("&nbsp&nbsp&nbsp");
                                        
                        out.println("&gt;");
                    }
                        
                    if(c.getDescripciones().isEmpty()){
                        out.println("<a href=\"" + contexto.getServletContext().getContextPath() + "/categorias/" + c.getId() + "-" + Cadenas.formatoUrl(c.getNombre()) + "/\" "+ clase +" >" + c.getNombre() + "</a>"); 
                    }else{
                        String nombre="";
                        for(CategoriaDescripcion desc: c.getDescripciones()){
                            if(desc.getIdioma().equalsIgnoreCase(locale.getLanguage())){
                                nombre=desc.getNombre();
                            }
                        }
                        if(nombre.isEmpty()){
                            for(CategoriaDescripcion desc: c.getDescripciones()){
                                if(desc.getIdioma().equalsIgnoreCase(cfg.getIdioma())){
                                    nombre=desc.getNombre();
                                }
                            }
                        }
                        if(nombre.isEmpty()){
                            nombre=c.getNombre();
                        }
                        out.println("<a href=\"" + contexto.getServletContext().getContextPath() + "/categorias/" + c.getId() + "-" + Cadenas.formatoUrl(nombre) + "/\" "+ clase +" >" + nombre + "</a>");
                    }
                    if(!c.getSubcategorias().isEmpty()){
                        out.println("<ul class=\"subnavegador\">");
                        out.println(generarSubCategorias(c.getSubcategorias() , contexto, locale, cfg));
                        out.println("</ul>");
                    }
                    
                    out.println("</li>");
                }   
            }
            
//            if(contexto.getSession().getAttribute("etiquetas")==null){
//                ProductoDAO prodDAO = (ProductoDAO) contexto.getServletContext().getAttribute("prodDAO");
//                Map<String, Integer> map=new HashMap<String, Integer>();
//                ArrayList<String> etiquetas=new ArrayList<String>();
//                
//                for(Object o: prodDAO.devuelveProductos()){
//                    Producto p=(Producto) o;
//                    for(ProductoDescripcion desc: p.getDescripciones()){
//                        if(desc.getIdioma().equalsIgnoreCase(locale.getLanguage())){
//                            String tags[]=desc.getEtiquetas().split(",");
//                            etiquetas.addAll(Arrays.asList(tags));
//                        }
//                    }
//                }
//                if(etiquetas.isEmpty()){
//                    for(Object o: prodDAO.devuelveProductos()){
//                        Producto p=(Producto) o;
//                        for(ProductoDescripcion desc: p.getDescripciones()){
//                            if(desc.getIdioma().equalsIgnoreCase(cfg.getIdioma())){
//                                String tags[]=desc.getEtiquetas().split(",");
//                                etiquetas.addAll(Arrays.asList(tags));
//                            }
//                        }
//                    }
//                }
                
//                for(String tag: etiquetas){
//                    boolean existe=false;
//                    Iterator it = map.entrySet().iterator();
//
//                    while (it.hasNext()) {
//                        Map.Entry e = (Map.Entry)it.next();
//                        System.out.println(e.getKey() + " " + e.getValue());
//                    }
//                }
//            }

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in LateralHandler tag", ex);
        }
    }
    
    private String generarSubCategorias(Set<Categoria>  categorias, PageContext contexto, Locale locale, ConfiguracionTienda cfg){
        String resultado="";
        String clase="";
        
        if(categorias!=null && !categorias.isEmpty()){
            for(Object o: categorias){
                Categoria c=(Categoria) o;     
                
                if(c.getSubcategorias()!=null && !c.getSubcategorias().isEmpty())
                    clase=" class=\"desplegable2\"";
                    
                resultado+="<li>";

                if(c.getNodo()>0){
                    for(int i=0;i<c.getNodo();i++)
                        resultado+="&nbsp&nbsp&nbsp";

                    resultado+="&gt;";
                }

                if(c.getDescripciones().isEmpty()){
                    resultado+="<a href=\"" + contexto.getServletContext().getContextPath() + "/categorias/" + c.getId() + "-" + Cadenas.formatoUrl(c.getNombre()) + "/\""+ clase +" >" + c.getNombre() + "</a>"; 
                }else{
                    String nombre="";
                    for(CategoriaDescripcion desc: c.getDescripciones()){
                        if(desc.getIdioma().equalsIgnoreCase(locale.getLanguage())){
                            nombre=desc.getNombre();
                        }
                    }
                    if(nombre.isEmpty()){
                        for(CategoriaDescripcion desc: c.getDescripciones()){
                            if(desc.getIdioma().equalsIgnoreCase(cfg.getIdioma())){
                                nombre=desc.getNombre();
                            }
                        }
                    }
                    if(nombre.isEmpty()){
                        nombre=c.getNombre();
                    }
                    resultado+="<a href=\"" + contexto.getServletContext().getContextPath() + "/categorias/" + c.getId() + "-" + Cadenas.formatoUrl(nombre) + "/\""+ clase +" >" + nombre + "</a>";
                }
                if(!c.getSubcategorias().isEmpty()){
                    resultado+="<ul class=\"subnavegador2\">";
                    resultado+=generarSubCategorias(c.getSubcategorias() , contexto, locale, cfg);
                    resultado+="</ul>";
                }
                
                resultado+="</li>";
            }
        }
        
        return resultado;
    }
}
