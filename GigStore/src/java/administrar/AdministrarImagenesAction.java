/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import categoria.Categoria;
import categoria.CategoriaDAO;
import dominio.Imagen;
import dominio.ImagenDAO;
import dominio.ImagenForm;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.upload.FormFile;
import producto.Producto;
import producto.ProductoDAO;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarImagenesAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";

    /** Provides the mapping from resource key to method name.
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
        request.getSession().setAttribute("accion", "alta");
        request.getSession().setAttribute("obj_id", request.getParameter("id"));
        request.getSession().setAttribute("obj_tipo", request.getParameter("obj_tipo"));
        request.getSession().removeAttribute("img_alt");
        if(request.getParameter("obj_tipo").equalsIgnoreCase("producto")){
            ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
            Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getParameter("id")));
            request.getSession().setAttribute("obj_nombre", prod.getReferencia() + " " + prod.getNombre());
        }
        if(request.getParameter("obj_tipo").equalsIgnoreCase("categoria")){
            CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
            Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getParameter("id")));
            request.getSession().setAttribute("obj_nombre", cat.getNombre());
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ActionForward af=new ActionForward();
        ImagenForm imf=(ImagenForm) form;
        Imagen img=new Imagen();
                
        if(!this.isCancelled(request)){
            ImagenDAO imgDAO=(ImagenDAO) request.getSession().getServletContext().getAttribute("imgDAO");
            FormFile imagen = imf.getImagen();
            
            img.setImagen(imagen.getFileData());
            img.setTipo(imagen.getContentType());
            img.setAlt(imf.getAlt());
            img.setPrincipal(imf.isPrincipal());
            imgDAO.altaImagen(img);
        }
            
        if(request.getSession().getAttribute("obj_tipo").toString().equalsIgnoreCase("producto")){
            ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
            Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getSession().getAttribute("obj_id").toString()));

            prod.getImagenes().add(img);
            prodDAO.editaProducto(prod);
            af.setPath("/administrar/productos/detalle.do?metodo=mostrar_detalle&id=" + prod.getId());
        }
        if(request.getSession().getAttribute("obj_tipo").toString().equalsIgnoreCase("categoria")){
            CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
            Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getSession().getAttribute("obj_id").toString()));

            cat.setImagen(img);
            catDAO.editaCategoria(cat);
            
            request.getSession().getServletContext().setAttribute("categorias", catDAO.devuelveCategoriasPrincipales(true));
            af.setPath("/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=" + cat.getId());
        }
        
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("obj_id");
        request.getSession().removeAttribute("obj_tipo");
        request.getSession().removeAttribute("obj_nombre");
        
        af.setRedirect(true);
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ImagenForm imf=(ImagenForm) form;
        ImagenDAO imgDAO=(ImagenDAO) request.getSession().getServletContext().getAttribute("imgDAO");
        Imagen img=imgDAO.consultaImagen(Integer.parseInt(request.getParameter("id")));                
        
        if(request.getParameter("obj_tipo").equalsIgnoreCase("producto")){
            ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
            Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getParameter("obj_id")));
            request.getSession().setAttribute("obj_nombre", prod.getReferencia() + " " + prod.getNombre());
        }
        if(request.getParameter("obj_tipo").equalsIgnoreCase("categoria")){
            CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
            Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getParameter("obj_id")));
            request.getSession().setAttribute("obj_nombre", cat.getNombre());
        }
                
        imf.setAlt(img.getAlt());
        imf.setSprincipal(String.valueOf(img.isPrincipal()));
        
        request.getSession().setAttribute("img_id", request.getParameter("id"));
        request.getSession().setAttribute("obj_id", request.getParameter("obj_id"));
        request.getSession().setAttribute("obj_tipo", request.getParameter("obj_tipo"));
        request.getSession().setAttribute("img_alt", img.getAlt());
        request.getSession().setAttribute("accion", "editar");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ActionForward af=new ActionForward();
        ImagenForm imf=(ImagenForm) form;
        
        if(!this.isCancelled(request)){
            ImagenDAO imgDAO=(ImagenDAO) request.getSession().getServletContext().getAttribute("imgDAO");
            Imagen img=imgDAO.consultaImagen(Integer.parseInt(request.getSession().getAttribute("img_id").toString()));
            img.setAlt(imf.getAlt());
            img.setPrincipal(imf.isPrincipal());
            
            imgDAO.editaImagen(img);
        }
        
        if(request.getSession().getAttribute("obj_tipo").toString().equalsIgnoreCase("producto")){
            ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
            Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getSession().getAttribute("obj_id").toString()));
            af.setPath("/administrar/productos/detalle.do?metodo=mostrar_detalle&id=" + prod.getId());
        }
        if(request.getSession().getAttribute("obj_tipo").toString().equalsIgnoreCase("categoria")){
            CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
            Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getSession().getAttribute("obj_id").toString()));
            
            request.getSession().getServletContext().setAttribute("categorias", catDAO.devuelveCategoriasPrincipales(true));
            af.setPath("/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=" + cat.getId());
        }
                
        request.getSession().removeAttribute("img_id");
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("obj_id");
        request.getSession().removeAttribute("obj_tipo");
        request.getSession().removeAttribute("obj_nombre");
        request.getSession().removeAttribute("img_alt");
        
        af.setRedirect(true);        
        return af; 
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ActionForward af=new ActionForward();
        ImagenDAO imgDAO=(ImagenDAO) request.getSession().getServletContext().getAttribute("imgDAO");
        Imagen img=imgDAO.consultaImagen(Integer.parseInt(request.getParameter("id")));
        
        if(request.getParameter("obj_tipo").equalsIgnoreCase("producto")){
            ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
            Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getParameter("obj_id")));
            prod.getImagenes().remove(img);
            prodDAO.editaProducto(prod);
            
            af.setPath("/administrar/productos/detalle.do?metodo=mostrar_detalle&id=" + prod.getId());
        }
        if(request.getParameter("obj_tipo").equalsIgnoreCase("categoria")){
            CategoriaDAO catDAO=(CategoriaDAO) request.getSession().getServletContext().getAttribute("catDAO");
            Categoria cat=catDAO.consultaCategoria(Integer.parseInt(request.getParameter("obj_id")));
            cat.setImagen(null);
            catDAO.editaCategoria(cat);
            
            request.getSession().getServletContext().setAttribute("categorias", catDAO.devuelveCategoriasPrincipales(true));
            af.setPath("/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=" + cat.getId());
        }        
        imgDAO.eliminaImagen(img);
        
        af.setRedirect(true);        
        return af; 
    }
}
