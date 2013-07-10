/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

import categoria.Categoria;
import categoria.CategoriaDAO;
import categoria.CategoriaDescripcion;
import dominio.Cadenas;
import dominio.ConfiguracionTienda;
import dominio.TextosTienda;
import dominio.TextosTiendaDAO;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
public class ProductoAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";

    /**
     * Provides the mapping from resource key to method name.
     *
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.buscar", "buscar");
        map.put("accion.mostrar_listado", "mostrar_listado");
        map.put("accion.mostrar_detalle", "mostrar_detalle");
        return map;
    }

    /**
     * Action called on buscar link
     */
    public ActionForward buscar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");      
        List prods = null;
        
        if(request.getParameter("busqueda")!=null){
            prods=prodDAO.devuelveProductos(request.getParameter("busqueda"), cfg.isOcultarProducto());
            request.getSession().setAttribute("busqueda", request.getParameter("busqueda"));
            request.getSession().setAttribute("res_busqueda", prods);
        }
        
        ActionForward af=new ActionForward();
        if(prods.isEmpty())
            af.setPath("/buscar/sin-resultados/");
        else if(request.getParameter("busqueda").isEmpty())
            af.setPath("/buscar/todo/");
        else
            af.setPath("/buscar/" + Cadenas.formatoUrl(request.getParameter("busqueda")) + "/");
        af.setRedirect(true);
        
        return af; 
    }

    /**
     * Action called on mostrar_listado link
     */
    public ActionForward mostrar_listado(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        Locale locale = request.getLocale();
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        
        if(request.getParameter("accion").equalsIgnoreCase("categorias")){       
            CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
            Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getParameter("categoria")));
            request.setAttribute("categoria", cat);
            
            CategoriaDescripcion desc=new CategoriaDescripcion();
            for(CategoriaDescripcion d: cat.getDescripciones()){
                if(d.getIdioma().equalsIgnoreCase(locale.getLanguage()))
                    desc=d;
            }
            if(desc.getId()==0){
                for(CategoriaDescripcion d: cat.getDescripciones()){
                    if(d.getIdioma().equalsIgnoreCase(cfg.getIdioma()))
                        desc=d;
                }
            }
            if(desc.getId()==0){
                desc.setNombre(cat.getNombre());
            }
            if(desc.getMdCategorias()==null || desc.getMtCategorias()==null || desc.getMdCategorias().isEmpty() || desc.getMtCategorias().isEmpty()){
                TextosTiendaDAO txtDAO=(TextosTiendaDAO) request.getSession().getServletContext().getAttribute("txtDAO");
                TextosTienda txt=txtDAO.consultaTextosTienda(request.getLocale().getLanguage());
                if(txt==null || txt.getId()<1)
                    txt=txtDAO.consultaTextosTienda(cfg.getIdioma());
                desc.setMdCategorias(txt.getMdCategorias());
                desc.setMtCategorias(txt.getMtCategorias());
            }
            
            request.getSession().setAttribute("categoria_url", cat.getId() + "-" + Cadenas.formatoUrl(cat.getNombre()));
            request.getSession().setAttribute("desc", desc);
        }
        
        request.getSession().setAttribute("accion", request.getParameter("accion"));
        
        return mapping.findForward(SUCCESS);
    }

    /**
     * Action called on mostrar_detalle link
     */
    public ActionForward mostrar_detalle(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        Locale locale = request.getLocale();
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("accion", request.getSession().getAttribute("accion"));
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        
        if(String.valueOf(request.getSession().getAttribute("accion")).equalsIgnoreCase("buscar"))
            request.setAttribute("busqueda", request.getSession().getAttribute("busqueda"));
        if(String.valueOf(request.getSession().getAttribute("accion")).equalsIgnoreCase("categorias")){
            request.setAttribute("categoria_url", request.getSession().getAttribute("categoria"));
            request.setAttribute("categoria_nombre", request.getSession().getAttribute("categoria_nombre"));
        }            
        request.getSession().removeAttribute("accion");        
        request.getSession().removeAttribute("busqueda");        
        request.getSession().removeAttribute("categoria_url");  
        request.getSession().removeAttribute("categoria_nombre");        
                
        ProductoDescripcion desc=new ProductoDescripcion();
        for(ProductoDescripcion d: prod.getDescripciones()){
            if(d.getIdioma().equalsIgnoreCase(locale.getLanguage()))
                desc=d;
        }
        if(desc.getId()==0){
            for(ProductoDescripcion d: prod.getDescripciones()){
                if(d.getIdioma().equalsIgnoreCase(cfg.getIdioma()))
                    desc=d;
            }
        }
        if(desc.getId()==0){
            desc.setNombre(prod.getNombre());
        }
        if(desc.getMdProductos().isEmpty() || desc.getMtProductos().isEmpty()){
            TextosTiendaDAO txtDAO=(TextosTiendaDAO) request.getSession().getServletContext().getAttribute("txtDAO");
            TextosTienda txt=txtDAO.consultaTextosTienda(request.getLocale().getLanguage());
            if(txt==null || txt.getId()<1)
                txt=txtDAO.consultaTextosTienda(cfg.getIdioma());
            desc.setMdProductos(txt.getMdProductos());
            desc.setMtProductos(txt.getMtProductos());
        }
        
        BigDecimal precioFinal=prod.getPrecio();
        
        if(prod.getDescuento().compareTo(BigDecimal.ZERO)>0){
            if(prod.isDescporcentaje()) {
                BigDecimal totaldesc=prod.getPrecio().multiply(prod.getDescuento()).divide(new BigDecimal(100));
                precioFinal=prod.getPrecio().subtract(totaldesc);
            } else
                precioFinal=prod.getPrecio().subtract(prod.getDescuento());
        }
        
        if(prod.getCategoria().getDescuento().compareTo(BigDecimal.ZERO)>0){
            if(prod.getCategoria().isDescporcentaje()) {
                BigDecimal totaldesc=precioFinal.multiply(prod.getCategoria().getDescuento()).divide(new BigDecimal(100));
                precioFinal=precioFinal.subtract(totaldesc);
            } else
                precioFinal=precioFinal.subtract(prod.getCategoria().getDescuento());
        }
            
        request.setAttribute("precio_final", precioFinal);
        request.setAttribute("descripcion", desc);
        request.setAttribute("producto", prod);
        request.setAttribute("opts", prod.getOpciones());
        request.setAttribute("sinopts", prod.getOpciones().isEmpty());
        request.setAttribute("id", request.getParameter("id"));
        
        return mapping.findForward(SUCCESS);
    }
}
