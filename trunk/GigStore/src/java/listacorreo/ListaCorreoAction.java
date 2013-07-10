/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listacorreo;

import com.ckeditor.CKEditorConfig;
import dominio.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class ListaCorreoAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";

    /**
     * Provides the mapping from resource key to method name.
     *
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.mostrar_listado", "mostrar_listado");
        map.put("accion.alta_ejecutar", "alta_ejecutar");
        map.put("accion.editar_inicio", "editar_inicio");
        map.put("accion.editar_ejecutar", "editar_ejecutar");
        map.put("accion.eliminar", "eliminar");
        map.put("accion.generar_email", "generar_email");
        map.put("accion.enviar_email", "enviar_email");
        return map;
    }

    /** Action called on mostrar_listado button click
     */
    public ActionForward mostrar_listado(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ListaCorreoDAO listaDAO=(ListaCorreoDAO) request.getSession().getServletContext().getAttribute("listaDAO");
        
        request.setAttribute("listado_news", GeneradorTablas.tablaListaCorreos(listaDAO.devuelveListaCorreos(), request, response));
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ListaCorreo lista=new ListaCorreo();
        ListaCorreoDAO listaDAO=(ListaCorreoDAO) request.getSession().getServletContext().getAttribute("listaDAO");
        MessageResources mgs = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
        request.getSession().removeAttribute("error_email");
        
        Pattern pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        Matcher mat = pat.matcher(request.getParameter("email"));
        if (mat.find()) {
            lista.setEmail(request.getParameter("email").trim());
            lista.setActivo(true);
            
            boolean existe=false;
            
            for (Object o: listaDAO.devuelveListaCorreos()){
                ListaCorreo l=(ListaCorreo) o;
                if(l.getEmail().equalsIgnoreCase(lista.getEmail())){
                    existe=true;
                    break;
                }
            }

            if(!existe){
                TextosTienda txt = (TextosTienda) request.getSession().getAttribute("txt");
                ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
            
                listaDAO.altaListaCorreo(lista);
                Correo mail=new Correo();
                mail.setFrom(cfg.getEmailSistema());
                mail.setTo(lista.getEmail());
                mail.setSubject(mgs.getMessage(locale, "email.news"));
                mail.setText(txt.getEmailNews());
                
                mail.send();
            }else{
                request.getSession().setAttribute("error_email", mgs.getMessage(locale, "errors.email_exists", request.getParameter("email").trim()));
            }
        }else{
            request.getSession().setAttribute("error_email", mgs.getMessage(locale, "errors.email", request.getParameter("email").trim()));
        }
        
        String referer=request.getHeader("Referer");
        ActionForward af=new ActionForward();
        af.setPath(referer);
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ListaCorreoForm lf=(ListaCorreoForm) form;
        ListaCorreoDAO listaDAO=(ListaCorreoDAO) request.getSession().getServletContext().getAttribute("listaDAO");
        ListaCorreo lista=listaDAO.consultaListaCorreo(Integer.parseInt(request.getParameter("id")));
        
        lf.setEmail(lista.getEmail());
        lf.setSactivo(String.valueOf(lista.isActivo()));
        
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
        ListaCorreoForm lf=(ListaCorreoForm) form;
        ListaCorreoDAO listaDAO=(ListaCorreoDAO) request.getSession().getServletContext().getAttribute("listaDAO");
        ListaCorreo lista=listaDAO.consultaListaCorreo(Integer.parseInt(request.getSession().getAttribute("id").toString()));
                
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("id");
        
        if(!this.isCancelled(request)){
            lista.setEmail(lf.getEmail());
            lista.setActivo(lf.isActivo());
            
            listaDAO.editaListaCorreo(lista);
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ListaCorreoDAO listaDAO=(ListaCorreoDAO) request.getSession().getServletContext().getAttribute("listaDAO");
        ListaCorreo lista=listaDAO.consultaListaCorreo(Integer.parseInt(request.getParameter("id")));
        
        listaDAO.eliminaListaCorreo(lista);
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on generar_email button click
     */
    public ActionForward generar_email(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        CKEditorConfig settings = new CKEditorConfig();
        settings.addConfigValue("height","500");
        settings.addConfigValue("toolbar","Full");
                
        request.getSession().setAttribute("settings", settings);
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on generar_email button click
     */
    public ActionForward enviar_email(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ListaCorreoDAO listaDAO=(ListaCorreoDAO) request.getSession().getServletContext().getAttribute("listaDAO");
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource");  
        Locale locale=request.getLocale(); 
        
        Correo mail=new Correo();
        mail.setFrom(cfg.getEmailSistema());
        String para="";
        for(Object o: listaDAO.devuelveListaCorreos()){
            ListaCorreo m=(ListaCorreo) o;
            if(m.isActivo()){
                if(para.isEmpty())
                    para+=m.getEmail();
                else
                    para+=";" + m.getEmail();
            }
        }
        mail.setTo(para);
        mail.setSubject(msg.getMessage(locale, "titulo.news"));
        mail.setText(request.getParameter("texto_email"));

        mail.send();
        
        return mapping.findForward(SUCCESS);
    }
}
