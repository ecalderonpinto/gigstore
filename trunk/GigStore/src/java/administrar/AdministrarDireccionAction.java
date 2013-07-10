/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import direccion.Direccion;
import direccion.DireccionDAO;
import direccion.DireccionForm;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import usuario.Usuario;
import usuario.UsuarioDAO;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarDireccionAction extends LookupDispatchAction {
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
        request.getSession().setAttribute("usr_id", request.getParameter("usr_id"));
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        DireccionForm df=(DireccionForm) form;
        Direccion dir=new Direccion();
        DireccionDAO dirDAO=(DireccionDAO) request.getSession().getServletContext().getAttribute("dirDAO");
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        Usuario usr=usrDAO.consultaUsuario(Integer.parseInt(request.getSession().getAttribute("usr_id").toString()));
        
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("usr_id");
                
        if(!this.isCancelled(request)){
            dir.setDirecciona(df.getDirecciona());
            dir.setDireccionb(df.getDireccionb());
            dir.setCp(df.getCp());
            dir.setPoblacion(df.getPoblacion());
            dir.setProvincia(df.getProvincia());
            dir.setPais(df.getPais());
            dir.setObservaciones(df.getObservaciones());
            
            dirDAO.altaDireccion(dir);
            usr.getDirecciones().add(dir);            
            usrDAO.editaUsuario(usr);
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/usuarios/detalle.do?metodo=mostrar_detalle&id=" + usr.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        DireccionForm df=(DireccionForm) form;
        DireccionDAO dirDAO=(DireccionDAO) request.getSession().getServletContext().getAttribute("dirDAO");
        Direccion dir=dirDAO.consultaDireccion(Integer.parseInt(request.getParameter("id")));
        
        df.setDirecciona(dir.getDirecciona());
        df.setDireccionb(dir.getDireccionb());
        df.setCp(dir.getCp());
        df.setPoblacion(dir.getPoblacion());
        df.setProvincia(dir.getProvincia());
        df.setPais(dir.getPais());
        df.setObservaciones(dir.getObservaciones());
        
        request.getSession().setAttribute("id", request.getParameter("id"));
        request.getSession().setAttribute("usr_id", request.getParameter("usr_id"));
        request.getSession().setAttribute("accion", "editar");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        DireccionForm df=(DireccionForm) form;
        DireccionDAO dirDAO=(DireccionDAO) request.getSession().getServletContext().getAttribute("dirDAO");
        Direccion dir=dirDAO.consultaDireccion(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        Usuario usr=usrDAO.consultaUsuario(Integer.parseInt(request.getSession().getAttribute("usr_id").toString()));
                
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("usr_id");
        request.getSession().removeAttribute("accion");    
        
        if(!this.isCancelled(request)){
            dir.setDirecciona(df.getDirecciona());
            dir.setDireccionb(df.getDireccionb());
            dir.setCp(df.getCp());
            dir.setPoblacion(df.getPoblacion());
            dir.setProvincia(df.getProvincia());
            dir.setPais(df.getPais());
            dir.setObservaciones(df.getObservaciones());
            
            dirDAO.editaDireccion(dir);            
            usr=usrDAO.consultaUsuario(usr.getId());
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/usuarios/detalle.do?metodo=mostrar_detalle&id=" + usr.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        DireccionDAO dirDAO=(DireccionDAO) request.getSession().getServletContext().getAttribute("dirDAO");
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        Direccion dir=dirDAO.consultaDireccion(Integer.parseInt(request.getParameter("id")));
        Usuario usr=usrDAO.consultaUsuario(Integer.parseInt(request.getParameter("usr_id")));
        
        usr.getDirecciones().remove(dir);
        usrDAO.editaUsuario(usr);
        dirDAO.eliminaDireccion(dir);
            
        ActionForward af=new ActionForward();
        af.setPath("/administrar/usuarios/detalle.do?metodo=mostrar_detalle&id=" + usr.getId());
        af.setRedirect(true);
        
        return af; 
    }
}
