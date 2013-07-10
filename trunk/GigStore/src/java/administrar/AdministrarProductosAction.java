/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import categoria.CategoriaDAO;
import dominio.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.util.MessageResources;
import pedido.PedidoLinea;
import pedido.PedidoLineaDAO;
import producto.*;
import usuario.Usuario;
import usuario.UsuarioDAO;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarProductosAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";
    private final static String CANCEL = "cancel";
    private final static int PRODUCTO_ESTADO = 1; // Id del Estado por defecto con el que se generan los productos

    /** Provides the mapping from resource key to method name.
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.mostrar_listado", "mostrar_listado");
        map.put("accion.mostrar_detalle", "mostrar_detalle");
        map.put("accion.alta_inicio", "alta_inicio");
        map.put("accion.alta_ejecutar", "alta_ejecutar");
        map.put("accion.editar_inicio", "editar_inicio");
        map.put("accion.editar_ejecutar", "editar_ejecutar");
        map.put("accion.eliminar", "eliminar");
        return map;
    }

    /** Action called on mostrar_listado button click
     */
    public ActionForward mostrar_listado(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        
        request.setAttribute("listado_prods", GeneradorTablas.tablaProductos(prodDAO.devuelveProductos(), request, response));
        request.setAttribute("error_borrar_pro", request.getSession().getAttribute("error_borrar_pro"));
        request.getSession().removeAttribute("error_borrar_pro");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on mostrar_detalle button click
     */
    public ActionForward mostrar_detalle(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getParameter("id")));
        
        request.getSession().setAttribute("producto", prod);
        request.setAttribute("listado_prod_desc", GeneradorTablas.tablaDescProductos(prod.getDescripciones(), request, response));
        request.setAttribute("listado_prod_opt", GeneradorTablas.tablaOpcionesProductos(prod.getOpciones(), request, response));
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_inicio button click
     */
    public ActionForward alta_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        TipoIvaDAO ivaDAO=(TipoIvaDAO) request.getSession().getServletContext().getAttribute("ivaDAO");      
        ProductoEstadoDAO estDAO=(ProductoEstadoDAO) request.getSession().getServletContext().getAttribute("preDAO");
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        
        request.getSession().setAttribute("lista_categorias", catDAO.devuelveCategorias(false));                
        request.getSession().setAttribute("accion", "alta");
        request.getSession().setAttribute("estados", estDAO.devuelveEstados());
        request.getSession().setAttribute("tiposiva", ivaDAO.devuelveTiposIva());
        request.getSession().setAttribute("estadoSel", PRODUCTO_ESTADO);
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ProductoForm pf=(ProductoForm) form;
        Producto prod=new Producto();
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        TipoIvaDAO ivaDAO=(TipoIvaDAO) request.getSession().getServletContext().getAttribute("ivaDAO"); 
        ProductoEstadoDAO estDAO=(ProductoEstadoDAO) request.getSession().getServletContext().getAttribute("preDAO");
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("estados");
        request.getSession().removeAttribute("tiposiva");
        request.getSession().removeAttribute("estadoSel");
        request.getSession().removeAttribute("lista_categorias");
        
        if(this.isCancelled(request)){
            return mapping.findForward(CANCEL);
        }else{
            prod.setReferencia(pf.getReferencia());
            prod.setNombre(pf.getNombre());
            prod.setPrecio(pf.getPrecio());
            prod.setCategoria(catDAO.consultaCategoria(pf.getCategoria())); 
            prod.setIva(ivaDAO.consultaTipoIva(pf.getIva()));
            prod.setDestacado(pf.isDestacado());
            prod.setEstado(estDAO.consultaEstado(pf.getEstado()));
            prod.setStock(pf.getStock());
            prod.setMarca(pf.getMarca());
            prod.setDescuento(pf.getDescuento());
            prod.setDescporcentaje(pf.isDescporcentaje());
            
            prodDAO.altaProducto(prod);
            
            prod.setUrl(Cadenas.formatoUrl("/categorias/" + prod.getCategoria().getNombre() + "/" + prod.getId() + "-" + prod.getNombre() + "/"));
            
            prodDAO.editaProducto(prod);
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/descripciones/editar.do?metodo=alta_inicio&id=" + prod.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoForm pf=(ProductoForm) form;
        TipoIvaDAO ivaDAO=(TipoIvaDAO) request.getSession().getServletContext().getAttribute("ivaDAO");    
        ProductoEstadoDAO estDAO=(ProductoEstadoDAO) request.getSession().getServletContext().getAttribute("preDAO");
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getParameter("id")));
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");                
        DecimalFormat df=new DecimalFormat("#.##");
        
        pf.setReferencia(prod.getReferencia());
        pf.setNombre(prod.getNombre());
        pf.setSprecio(df.format(prod.getPrecio()));
        pf.setSdestacado(String.valueOf(prod.isDestacado()));
        pf.setDestacado(prod.isDestacado());
        pf.setSstock(String.valueOf(prod.getStock()));
        pf.setMarca(prod.getMarca());
        pf.setSdescuento(df.format(prod.getDescuento()));
        pf.setDescporcentaje(prod.isDescporcentaje());
        pf.setSdescporcentaje(String.valueOf(prod.isDescporcentaje()));
        
        request.getSession().setAttribute("estados", estDAO.devuelveEstados());
        request.getSession().setAttribute("tiposiva", ivaDAO.devuelveTiposIva());
        request.getSession().setAttribute("catSel", prod.getCategoria().getId());
        request.getSession().setAttribute("ivaSel", prod.getIva().getId());
        if(prod.isDescporcentaje())
            request.getSession().setAttribute("tipoDescSel", "1");
        else
            request.getSession().setAttribute("tipoDescSel", "0");
        request.getSession().setAttribute("estadoSel", prod.getEstado().getId());
        request.getSession().setAttribute("accion", "editar");
        request.getSession().setAttribute("id", request.getParameter("id"));
        request.getSession().setAttribute("lista_categorias", catDAO.devuelveCategorias(false));
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoForm pf=(ProductoForm) form;
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        TipoIvaDAO ivaDAO=(TipoIvaDAO) request.getSession().getServletContext().getAttribute("ivaDAO");
        ProductoEstadoDAO estDAO=(ProductoEstadoDAO) request.getSession().getServletContext().getAttribute("preDAO");
        CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
        
        request.getSession().removeAttribute("estados");
        request.getSession().removeAttribute("tiposiva");
        request.getSession().removeAttribute("catSel");
        request.getSession().removeAttribute("ivaSel");
        request.getSession().removeAttribute("estadoSel");
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("lista_categorias");
        
        if(!this.isCancelled(request)){
            prod.setReferencia(pf.getReferencia());
            prod.setNombre(pf.getNombre());
            prod.setPrecio(pf.getPrecio());
            prod.setCategoria(catDAO.consultaCategoria(pf.getCategoria()));
            prod.setIva(ivaDAO.consultaTipoIva(pf.getIva()));
            prod.setDestacado(pf.isDestacado());
            prod.setEstado(estDAO.consultaEstado(pf.getEstado()));
            prod.setStock(pf.getStock());
            prod.setMarca(pf.getMarca());
            prod.setDescuento(pf.getDescuento());
            prod.setDescporcentaje(pf.isDescporcentaje());
            prod.setUrl(Cadenas.formatoUrl("/categorias/" + prod.getCategoria().getNombre() + "/" + prod.getId() + "-" + prod.getNombre() + "/"));

            prodDAO.editaProducto(prod);
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/detalle.do?metodo=mostrar_detalle&id=" + prod.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getParameter("id")));
        PedidoLineaDAO linDAO=(PedidoLineaDAO) request.getSession().getServletContext().getAttribute("linDAO");        
        MessageResources message = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale = request.getLocale();
        
        for(Object o: linDAO.devuelveLineas()){
            PedidoLinea pl=(PedidoLinea) o;
            if(pl.getProducto().getId()==prod.getId()){
                request.getSession().setAttribute("error_borrar_pro", message.getMessage(locale, "errors.borrar_pro"));
                return mapping.findForward(SUCCESS);
            }
        }
        
        ImagenDAO imgDAO=(ImagenDAO) request.getSession().getServletContext().getAttribute("imgDAO");
        for(Imagen img:prod.getImagenes()){
            imgDAO.eliminaImagen(img);
        }
        
        ProductoOpcionDAO optDAO=(ProductoOpcionDAO) request.getSession().getServletContext().getAttribute("poptDAO");
        for(ProductoOpcion opt:prod.getOpciones()){
            optDAO.eliminaProductoOpcion(opt);
        }        
        
        ProductoDescripcionDAO descDAO=(ProductoDescripcionDAO) request.getSession().getServletContext().getAttribute("prdDAO");
        for(ProductoDescripcion desc:prod.getDescripciones()){
            descDAO.eliminaDesc(desc);
        }
        
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        for(Object o:usrDAO.devuelveUsuarios()){
            Usuario usr=(Usuario) o;
            for(Producto p:usr.getSeguimiento()){
                if(p.getId()==prod.getId())
                    usr.getSeguimiento().remove(p);
            }
        }
        
        prodDAO.eliminaProducto(prod);
        
        return mapping.findForward(SUCCESS);
    }
}
