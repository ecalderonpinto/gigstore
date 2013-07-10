/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import dominio.GeneradorTablas;
import java.text.SimpleDateFormat;
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
import usuario.UsuarioEstadoDAO;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarUsuariosAction extends LookupDispatchAction {
    private final static String FORMATO_FECHA="dd/MM/yyyy";
    private final static String FORMATO_FECHA_COMPLETA="dd/MM/yyyy HH:mm:ss";
    private final static String SUCCESS = "success";

    /** Provides the mapping from resource key to method name.
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.mostrar_listado", "mostrar_listado");
        map.put("accion.mostrar_detalle", "mostrar_detalle");
        map.put("accion.editar_inicio", "editar_inicio");
        map.put("accion.editar_ejecutar", "editar_ejecutar");
        
        return map;
    }

    /** Action called on mostrar_listado button click
     */
    public ActionForward mostrar_listado(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        
        request.setAttribute("listado_usuarios", GeneradorTablas.tablaUsuarios(usrDAO.devuelveUsuarios(), request, response));
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on mostrar_detalle button click
     */
    public ActionForward mostrar_detalle(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        Usuario usr=usrDAO.consultaUsuario(Integer.parseInt(request.getParameter("id")));
        
        request.setAttribute("listado_direcciones_usr", GeneradorTablas.tablaDireccionesUsr(usr.getDirecciones(), request, response, request.getParameter("id")));
        request.setAttribute("usuario", usr);
                
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        SimpleDateFormat df1=new SimpleDateFormat(FORMATO_FECHA);
        SimpleDateFormat df2=new SimpleDateFormat(FORMATO_FECHA_COMPLETA);
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        UsuarioEstadoDAO estDAO=(UsuarioEstadoDAO) request.getSession().getServletContext().getAttribute("ueDAO");
        Usuario usr=usrDAO.consultaUsuario(Integer.parseInt(request.getParameter("id")));
        AdministrarUsuariosForm uf=(AdministrarUsuariosForm) form; 
        
        uf.setNif(usr.getNif());
        uf.setNombre(usr.getNombre());
        uf.setApellidos(usr.getApellidos());
        uf.setUsuario(usr.getUsuario());
        uf.setContrasenya1(usr.getContrasenya());
        uf.setContrasenya2(usr.getContrasenya());
        uf.setEmail(usr.getEmail());
        uf.setSfechaNacimiento(df1.format(usr.getFechaNacimiento()));
        uf.setTelefono(usr.getTelefono());
        uf.setRol(usr.getRol());
        uf.setSfechaAlta(df2.format(usr.getFechaAlta()));
        uf.setEstado(usr.getEstado().getId());
        
        request.getSession().setAttribute("id", usr.getId());
        request.getSession().setAttribute("rolSel", usr.getRol());
        request.getSession().setAttribute("estados", estDAO.devuelveEstados());
        request.getSession().setAttribute("estadoSel", String.valueOf(usr.getEstado().getId()));
              
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception { 
        AdministrarUsuariosForm uf=(AdministrarUsuariosForm) form;
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        UsuarioEstadoDAO uesDAO=(UsuarioEstadoDAO) request.getSession().getServletContext().getAttribute("ueDAO");
        Usuario usr=usrDAO.consultaUsuario(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("rolSel");
        request.getSession().removeAttribute("estados");
        request.getSession().removeAttribute("estadoSel");
                 
        if(!this.isCancelled(request)){
            usr.setNif(uf.getNif());
            usr.setNombre(uf.getNombre());
            usr.setApellidos(uf.getApellidos());
            usr.setFechaNacimiento(uf.getFechaNacimiento());
            usr.setTelefono(uf.getTelefono());
            usr.setEmail(uf.getEmail());
            usr.setUsuario(uf.getUsuario());
            usr.setContrasenya(uf.getContrasenya1());
            usr.setRol(uf.getRol());
            usr.setEstado(uesDAO.consultaEstado(uf.getEstado()));

            usrDAO.editaUsuario(usr);
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/usuarios/detalle.do?metodo=mostrar_detalle&id=" + usr.getId());
        af.setRedirect(true);
        
        return af; 
    }
}
