/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import direccion.Direccion;
import direccion.DireccionDAO;
import direccion.DireccionForm;
import java.util.HashMap;
import java.util.HashSet;
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
public class UsuarioDireccionAction extends LookupDispatchAction {
    private final static String USUARIO_SESION = "usrSesion"; // Nombre de la variable de sesión que contendrá al usuario logeado
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
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        DireccionForm df=(DireccionForm) form;
        DireccionDAO dirDAO=(DireccionDAO) request.getSession().getServletContext().getAttribute("dirDAO");
        Direccion dir=new Direccion();
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        Usuario usr=(Usuario) request.getSession().getAttribute(USUARIO_SESION);
                
        request.getSession().removeAttribute("accion");
        if(!this.isCancelled(request)){
            dir.setDirecciona(df.getDirecciona());
            dir.setDireccionb(df.getDireccionb());
            dir.setCp(df.getCp());
            dir.setPoblacion(df.getPoblacion());
            dir.setProvincia(df.getProvincia());
            dir.setPais(df.getPais());
            dir.setObservaciones(df.getObservaciones());
            
            dirDAO.altaDireccion(dir);
            if(usr.getDirecciones()==null){
                usr.setDirecciones(new HashSet<Direccion>());
            }
            usr.getDirecciones().add(dir);            
            usrDAO.editaUsuario(usr);    
            
            request.getSession().setAttribute(USUARIO_SESION, usr);
        }
        
        ActionForward af=new ActionForward();
        af.setPath(request.getSession().getAttribute("origen").toString());
        af.setRedirect(true);
        request.getSession().removeAttribute("origen");
        
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
        Usuario usr=(Usuario) request.getSession().getAttribute(USUARIO_SESION);
                
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request) && usr.getDirecciones().contains(dir)){
            dir.setDirecciona(df.getDirecciona());
            dir.setDireccionb(df.getDireccionb());
            dir.setCp(df.getCp());
            dir.setPoblacion(df.getPoblacion());
            dir.setProvincia(df.getProvincia());
            dir.setPais(df.getPais());
            dir.setObservaciones(df.getObservaciones());
            
            dirDAO.editaDireccion(dir);            
            usr=usrDAO.consultaUsuario(usr.getId());
            
            request.getSession().setAttribute(USUARIO_SESION, usr);
        }
        
        return mapping.findForward(SUCCESS);
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
        Usuario usr=(Usuario) request.getSession().getAttribute(USUARIO_SESION);
        
        if(usr.getDirecciones().contains(dir)){
            usr=usrDAO.consultaUsuario(usr.getId());

            usr.getDirecciones().remove(dir);
            usrDAO.editaUsuario(usr);
            dirDAO.eliminaDireccion(dir);

            request.getSession().removeAttribute(USUARIO_SESION);
            request.getSession().setAttribute(USUARIO_SESION, usr);
        }
        
        return mapping.findForward(SUCCESS);
    }
}
