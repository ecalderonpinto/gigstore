package usuario;

import dominio.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.util.MessageResources;
import pedido.Pedido;
import pedido.PedidoDAO;
import pedido.PedidoLinea;
import producto.Producto;
import producto.ProductoDAO;

/**
 * @author Manel Márquez Bonilla
 */
public class UsuarioAction extends LookupDispatchAction {
    private final static String FORMATO_FECHA="dd/MM/yyyy";
    private final static int ESTADO_POR_DEFECTO = 1; // El estado por defecto ACTIVO debería tener siempre id=1
    private final static String ROL_POR_DEFECTO = "usuario"; // El rol por defecto debería ser "usuario"
    private final static String ROL_ADMINISTRADOR = "administrador"; // El rol por administrador debería ser "administrador"
    private final static String USUARIO_SESION = "usrSesion"; // Nombre de la variable de sesión que contendrá al usuario logeado
    private final static String SUCCESS = "success";
    private final static String FAIL = "fail";
    private final static int NUM = 8;

    /** Provides the mapping from resource key to method name.
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.acceso_inicio", "acceso_inicio");
        map.put("accion.acceso_login", "acceso_login");
        map.put("accion.mostrar_recordatorio", "mostrar_recordatorio");
        map.put("accion.enviar_recordatorio", "enviar_recordatorio");
        map.put("accion.salir", "salir");
        map.put("accion.alta_inicio", "alta_inicio");  
        map.put("accion.alta_ejecutar", "alta_ejecutar");
        map.put("accion.mostrar_detalle", "mostrar_detalle");
        map.put("accion.editar_inicio", "editar_inicio");
        map.put("accion.editar_ejecutar", "editar_ejecutar");
        map.put("accion.agregar_seguimiento", "agregar_seguimiento");
        map.put("accion.eliminar_seguimiento", "eliminar_seguimiento");
        map.put("accion.mostrar_pedido", "mostrar_pedido");
        map.put("accion.contactar_inicio", "contactar_inicio");
        map.put("accion.contactar_enviar", "contactar_enviar");
        
        return map;
    }

    /** Action called on acceso_login button click
     */
    public ActionForward acceso_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {  
        String origen=request.getHeader("Referer");
        
        if(origen!=null && !origen.contains("/usuario/"))
            request.getSession().setAttribute("origen", origen);
        else
            request.getSession().setAttribute("origen", "/");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on acceso_login button click
     */
    public ActionForward acceso_login(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {  
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        boolean admin=false;
                
        try{
            Usuario usr=usrDAO.consultaUsuario(request.getParameter("user"), request.getParameter("pass"));
            
            if(usr.getRol().equals(ROL_ADMINISTRADOR))
                admin=true;
            
            request.getSession().setAttribute(USUARIO_SESION, usr);
        }catch(Exception Ex){
            MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource");  
            Locale locale=request.getLocale();   
            request.setAttribute("error", msg.getMessage(locale, "texto.error_login", request.getParameter("user")));
            
            return mapping.findForward(FAIL);
        }
        
        ActionForward af=new ActionForward();
        if(admin){
            af.setPath("/administrar/");
        }else if(request.getSession().getAttribute("login_pedido")!=null){
            af.setPath("/pedido/confirmar/paso-1/");
            request.getSession().removeAttribute("login_pedido");
        } else {
            af.setPath(request.getSession().getAttribute("origen").toString());
        }
        request.getSession().removeAttribute("origen");
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on mostrar_recordatorio button click
     */
    public ActionForward mostrar_recordatorio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {        
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on enviar_recordatorio button click
     */
    public ActionForward enviar_recordatorio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {           
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        MessageResources mgs = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();   

        if (request.getParameter("email") !=null && !request.getParameter("email").isEmpty()) {
            Pattern pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
            Matcher mat = pat.matcher(request.getParameter("email"));
            if (mat.find()){ 
                try{
                    Usuario usr=usrDAO.consultaUsuario(request.getParameter("email"));
                    String pass=PasswordGenerator.getPassword(PasswordGenerator.MINUSCULAS+PasswordGenerator.MAYUSCULAS,8);

                    usr.setContrasenya(pass);
                    usrDAO.editaUsuario(usr);

                    Correo mail=new Correo();
                    TextosTienda txt = (TextosTienda) request.getSession().getAttribute("txt");
                    ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");

                    mail.setFrom(cfg.getEmailSistema());
                    mail.setTo(request.getParameter("email"));
                    mail.setSubject(mgs.getMessage(locale, "email.recordarpass"));
                    mail.setText(txt.getEmailPassword().replace("[PASSWORD]", pass));

                    mail.send();   
                }catch(Exception ex){
                    request.setAttribute("error_recordar", mgs.getMessage(locale, "errors.email_notexists"));
        
                    ActionForward af=new ActionForward();
                    af.setPath("/usuario/recordatorio/");

                    return af; 
                }
            }else{
                request.setAttribute("error_recordar", mgs.getMessage(locale, "errors.email", request.getParameter("email")));
        
                ActionForward af=new ActionForward();
                af.setPath("/usuario/recordatorio/");

                return af; 
            }
        }        
        
        ActionForward af=new ActionForward();
        af.setPath("/usuario/acceder/");
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on acceso_salir button click
     */
    public ActionForward salir(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {        
        request.getSession().invalidate();
        
        return mapping.findForward(SUCCESS);
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
            HttpServletResponse response) throws MessagingException {
        UsuarioForm uf=(UsuarioForm) form;
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        Usuario usr=new Usuario();
        UsuarioEstadoDAO ueDAO=(UsuarioEstadoDAO) request.getSession().getServletContext().getAttribute("ueDAO");
        MessageResources mgs = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
        request.getSession().removeAttribute("accion");
        
        if(this.isCancelled(request)){
            return mapping.findForward(FAIL);
        }else{
            usr.setNif(uf.getNif());
            usr.setNombre(uf.getNombre());
            usr.setApellidos(uf.getApellidos());
            usr.setFechaNacimiento(uf.getFechaNacimiento());
            usr.setTelefono(uf.getTelefono());
            usr.setEmail(uf.getEmail());
            usr.setUsuario(uf.getEmail());
            usr.setContrasenya(uf.getContrasenya1());
            usr.setFechaAlta(new Date());
            usr.setRol(ROL_POR_DEFECTO);
            usr.setEstado(ueDAO.consultaEstado(ESTADO_POR_DEFECTO));
            
            usrDAO.altaUsuario(usr);
            
            Correo mail=new Correo();
            TextosTienda txt = (TextosTienda) request.getSession().getAttribute("txt");
            ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");

            mail.setFrom(cfg.getEmailSistema());
            mail.setTo(usr.getEmail());
            mail.setSubject(mgs.getMessage(locale, "email.altausr"));
            mail.setText(txt.getEmailAlta());

            mail.send();
        }        
        request.getSession().setAttribute(USUARIO_SESION, usrDAO.consultaUsuario(usr.getId()));
        
        ActionForward af=new ActionForward();
        af.setPath("/usuario/datos-personales/direcciones/alta/");
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on mostrar_detalle button click
     */
    public ActionForward mostrar_detalle(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        Usuario usr=(Usuario) request.getSession().getAttribute(USUARIO_SESION);
        
        if(usr==null || usr.getId()==0){
            ActionForward af=new ActionForward();
            af.setPath("/");
            af.setRedirect(true);

            return af; 
        }
        
        request.getSession().setAttribute("origen", "/usuario/datos-personales/");
        request.setAttribute("listado_direcciones_usr", GeneradorTablas.tablaDireccionesUsr(usr.getDirecciones(), request, response));
        request.setAttribute("listado_pedidos_usr", GeneradorTablas.tablaPedidosUsr(usr.getPedidos(), request, response));
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        SimpleDateFormat df=new SimpleDateFormat(FORMATO_FECHA);
        Usuario usr=(Usuario) request.getSession().getAttribute(USUARIO_SESION);
        UsuarioForm uf=(UsuarioForm) form; 
        
        uf.setNif(usr.getNif());
        uf.setNombre(usr.getNombre());
        uf.setApellidos(usr.getApellidos());
        uf.setContrasenya1(usr.getContrasenya());
        uf.setContrasenya2(usr.getContrasenya());
        uf.setEmail(usr.getEmail());
        uf.setSfechaNacimiento(df.format(usr.getFechaNacimiento()));
        uf.setTelefono(usr.getTelefono());
          
        request.getSession().setAttribute("accion", "editar");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception { 
        UsuarioForm uf=(UsuarioForm) form;
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        Usuario usr=(Usuario) request.getSession().getAttribute(USUARIO_SESION);
        MessageResources mgs = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            usr.setNif(uf.getNif());
            usr.setNombre(uf.getNombre());
            usr.setApellidos(uf.getApellidos());
            usr.setFechaNacimiento(uf.getFechaNacimiento());
            usr.setTelefono(uf.getTelefono());
            usr.setEmail(uf.getEmail());
            usr.setUsuario(uf.getEmail());
            usr.setContrasenya(uf.getContrasenya1());

            usrDAO.editaUsuario(usr);
            
            Correo mail=new Correo();               
            ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
            TextosTienda txt = (TextosTienda) request.getSession().getAttribute("txt");
                
            mail.setFrom(cfg.getEmailSistema());
            mail.setTo(usr.getEmail());
            mail.setSubject(mgs.getMessage(locale, "email.modifusr"));
            mail.setText(txt.getEmailModificar());

            mail.send();
        }
        request.getSession().setAttribute(USUARIO_SESION, usr);
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on agregar_seguimiento link click
     */
    public ActionForward agregar_seguimiento(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception { 
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        Usuario usr=(Usuario) request.getSession().getAttribute(USUARIO_SESION);
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getParameter("id")));
                
        usr.getSeguimiento().add(prod);
        usrDAO.editaUsuario(usr);  
        
        request.getSession().setAttribute(USUARIO_SESION, usr);
                
        ActionForward af=new ActionForward();
        af.setPath("/categorias/" + Cadenas.formatoUrl(prod.getCategoria().getNombre()) + "/" + prod.getId() + "-" + Cadenas.formatoUrl(prod.getNombre()) + "/");
        af.setRedirect(true);

        return af;
    }

    /** Action called on eliminar_seguimiento link click
     */
    public ActionForward eliminar_seguimiento(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception { 
        UsuarioDAO usrDAO=(UsuarioDAO) request.getSession().getServletContext().getAttribute("usrDAO");
        Usuario usr=(Usuario) request.getSession().getAttribute(USUARIO_SESION);
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getParameter("id")));
                
        usr.getSeguimiento().remove(prod);
        usrDAO.editaUsuario(usr);  
        
        request.getSession().setAttribute(USUARIO_SESION, usr);
                
        ActionForward af=new ActionForward();
        af.setPath("/categorias/" + Cadenas.formatoUrl(prod.getCategoria().getNombre()) + "/" + prod.getId() + "-" + Cadenas.formatoUrl(prod.getNombre()) + "/");
        af.setRedirect(true);

        return af;
    }

    /** Action called on mostrar_pedido link click
     */
    public ActionForward mostrar_pedido(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception { 
        PedidoDAO pedDAO=(PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
        Pedido ped=pedDAO.consultaPedido(Integer.parseInt(request.getParameter("id")));
        BigDecimal subtotal=new BigDecimal(BigInteger.ZERO);
        
        for(PedidoLinea lin: ped.getLineas()){
            subtotal=subtotal.add(lin.getPrecio().multiply(new BigDecimal(lin.getCantidad())));
        }
        
        request.setAttribute("pedido", ped);
        request.setAttribute("listado_lineas", GeneradorTablas.tablaLineasPedidoUsr(ped.getLineas(), request, response));
        request.setAttribute("subtotal", subtotal);
                
        return mapping.findForward(SUCCESS);
    }

    /** Action called on contactar_inicio link click
     */
    public ActionForward contactar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception { 
        request.getSession().setAttribute("texto", PasswordGenerator.getPassword(NUM)); 
        request.setAttribute("num", NUM); 
                
        return mapping.findForward(SUCCESS);
    }

    /** Action called on contactar_enviar link click
     */
    public ActionForward contactar_enviar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception { 
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        String codigo=request.getSession().getAttribute("texto").toString();
        String param=request.getParameter("codigo");
        
        request.getSession().removeAttribute("error_msg");
        request.getSession().removeAttribute("texto");
        
        if(param.equals(codigo)){            
            Pattern pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
            Matcher mat = pat.matcher(request.getParameter("email"));
            if (mat.find()) {
                Correo mail=new Correo();               

                mail.setFrom(request.getParameter("email"));
                mail.setTo(cfg.getEmailSistema());
                mail.setSubject(request.getParameter("asunto"));
                mail.setText("<h2>" + msg.getMessage(locale, "email.solicitud_info") + "</h2> " + request.getParameter("nombre") + " (" + request.getParameter("email") + ") <br /><h2>" + msg.getMessage(locale, "texto.mensaje") + ":</h2> " + request.getParameter("mensaje"));

                mail.send();       
                request.getSession().removeAttribute("nombre_c");   
                request.getSession().removeAttribute("email_c");    
                request.getSession().removeAttribute("asunto_c");    
                request.getSession().removeAttribute("msn_c");         
            }else{
                request.getSession().setAttribute("error_msg", msg.getMessage(locale, "errors.email", request.getParameter("email")));
            }
        }else{
            request.getSession().setAttribute("error_msg", msg.getMessage(locale, "errors.codigo"));
            request.getSession().setAttribute("nombre_c", request.getParameter("nombre"));
            request.getSession().setAttribute("email_c", request.getParameter("email"));
            request.getSession().setAttribute("asunto_c", request.getParameter("asunto"));
            request.getSession().setAttribute("msn_c", request.getParameter("mensaje"));
        }
        
        
        return mapping.findForward(SUCCESS);
    }
}
