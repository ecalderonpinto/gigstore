/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import dominio.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarTextosTiendaAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";

    /** Provides the mapping from resource key to method name.
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.mostrar_listado", "mostrar_listado");
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
        TextosTiendaDAO txtDAO=(TextosTiendaDAO) request.getSession().getServletContext().getAttribute("txtDAO");
        
        request.setAttribute("listado_textos_tienda", GeneradorTablas.tablaTextosTienda(txtDAO.devuelveTextosTienda(), request, response));
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_inicio button click
     */
    public ActionForward alta_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        request.getSession().setAttribute("idiomaSel", "0");
        request.getSession().setAttribute("accion", "alta");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        AdministrarTextosTiendaForm tf=(AdministrarTextosTiendaForm) form;
        TextosTiendaDAO txtDAO=(TextosTiendaDAO) request.getSession().getServletContext().getAttribute("txtDAO");
        TextosTienda txt=new TextosTienda();
        BannersTienda banners=new BannersTienda();
        TextosSeccionesTienda secciones=new TextosSeccionesTienda();
                  
        request.getSession().removeAttribute("idiomaSel");
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            txt.setIdioma(tf.getIdioma().trim());
            txt.setTituloTienda(tf.getTituloTienda().trim());
            txt.setSubtituloTienda(tf.getSubtituloTienda().trim());
            txt.setTextoBienvenidaUsr(tf.getTextoBienvenidaUsr().trim());
            txt.setMtHome(tf.getMtHome().trim());
            txt.setMtCategorias(tf.getMtCategorias().trim());
            txt.setMtProductos(tf.getMtProductos().trim());
            txt.setMdHome(tf.getMdHome().trim());
            txt.setMdCategorias(tf.getMdCategorias().trim());
            txt.setMdProductos(tf.getMdProductos().trim());
            txt.setEmailAlta(tf.getEmailAlta());
            txt.setEmailModificar(tf.getEmailModificar());
            txt.setEmailNews(tf.getEmailNews());
            txt.setEmailPassword(tf.getEmailPassword());
            txt.setEmailPedido(tf.getEmailPedido());
            
            secciones.setTextoBienvenidaHome(tf.getTextoBienvenidaHome().trim());
            secciones.setTextoConocenos(tf.getTextoConocenos().trim());
            secciones.setTextoComprar(tf.getTextoComprar().trim());
            secciones.setTextoContactar(tf.getTextoContactar().trim());
            secciones.setContactarDias(tf.getContactarDias().trim());
            secciones.setContactarHorario(tf.getContactarHorario().trim());
            secciones.setContactarTelefono(tf.getContactarTelefono().trim());
            secciones.setTextoPrivacidad(tf.getTextoPrivacidad().trim());
            secciones.setTextoVenta(tf.getTextoVenta().trim());
            secciones.setTextoCostes(tf.getTextoCostes().trim());
            secciones.setTextos(txt);
            
            banners.setBannerFichaActivo(tf.isBannerFichaActivo());
            banners.setBannerHomeActivo(tf.isBannerHomeActivo());
            banners.setBannerLateral1Activo(tf.isBannerLateral1Activo());
            banners.setBannerLateral2Activo(tf.isBannerLateral2Activo());
            banners.setBannerFichaAlt(tf.getBannerFichaAlt());
            banners.setBannerHomeAlt(tf.getBannerHomeAlt());
            banners.setBannerLateral1Alt(tf.getBannerLateral1Alt());
            banners.setBannerLateral2Alt(tf.getBannerLateral2Alt());
            
            if(tf.getFbannerFicha().getFileSize()>0){
                FormFile imagen = tf.getFbannerFicha();
                banners.setBannerFicha(imagen.getFileData());
                banners.setBannerFichaTipo(imagen.getContentType());
            }
            
            if(tf.getFbannerHome().getFileSize()>0){
                FormFile imagen = tf.getFbannerHome();
                banners.setBannerHome(imagen.getFileData());
                banners.setBannerHomeTipo(imagen.getContentType());
            }
            
            if(tf.getFbannerLateral1().getFileSize()>0){
                FormFile imagen = tf.getFbannerLateral1();
                banners.setBannerLateral1(imagen.getFileData());
                banners.setBannerLateral1Tipo(imagen.getContentType());
            }
            
            if(tf.getFbannerLateral2().getFileSize()>0){
                FormFile imagen = tf.getFbannerLateral2();
                banners.setBannerLateral2(imagen.getFileData());
                banners.setBannerLateral2Tipo(imagen.getContentType());
            }
            banners.setTextos(txt);
            
            txt.setSecciones(secciones);
            txt.setBanners(banners);
            txtDAO.altaTextosTienda(txt);         
            
            if(txt.getIdioma().equalsIgnoreCase(request.getLocale().getLanguage()))
                request.getSession().setAttribute("txt", txt);
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        AdministrarTextosTiendaForm tf=(AdministrarTextosTiendaForm) form;
        TextosTiendaDAO txtDAO=(TextosTiendaDAO) request.getSession().getServletContext().getAttribute("txtDAO");
        TextosTienda txt=txtDAO.consultaTextosTienda(Integer.parseInt(request.getParameter("id")));
        
        tf.setTituloTienda(txt.getTituloTienda());
        tf.setSubtituloTienda(txt.getSubtituloTienda());
        tf.setTextoBienvenidaUsr(txt.getTextoBienvenidaUsr());
        tf.setTextoBienvenidaHome(txt.getSecciones().getTextoBienvenidaHome());
        tf.setTextoConocenos(txt.getSecciones().getTextoConocenos());
        tf.setTextoComprar(txt.getSecciones().getTextoComprar());
        tf.setTextoContactar(txt.getSecciones().getTextoContactar());
        tf.setContactarDias(txt.getSecciones().getContactarDias());
        tf.setContactarHorario(txt.getSecciones().getContactarHorario());
        tf.setContactarTelefono(txt.getSecciones().getContactarTelefono());
        tf.setTextoPrivacidad(txt.getSecciones().getTextoPrivacidad());
        tf.setTextoVenta(txt.getSecciones().getTextoVenta());
        tf.setTextoCostes(txt.getSecciones().getTextoCostes());
        tf.setMtHome(txt.getMtHome());
        tf.setMtCategorias(txt.getMtCategorias());
        tf.setMtProductos(txt.getMtProductos());
        tf.setMdHome(txt.getMdHome());
        tf.setMdCategorias(txt.getMdCategorias());
        tf.setMdProductos(txt.getMdProductos());
        tf.setSbannerFichaActivo(String.valueOf(txt.getBanners().isBannerFichaActivo()));
        tf.setSbannerHomeActivo(String.valueOf(txt.getBanners().isBannerHomeActivo()));
        tf.setSbannerLateral1Activo(String.valueOf(txt.getBanners().isBannerLateral1Activo()));
        tf.setSbannerLateral2Activo(String.valueOf(txt.getBanners().isBannerLateral2Activo()));
        tf.setBannerFichaAlt(txt.getBanners().getBannerFichaAlt());
        tf.setBannerHomeAlt(txt.getBanners().getBannerHomeAlt());
        tf.setBannerLateral1Alt(txt.getBanners().getBannerLateral1Alt());
        tf.setBannerLateral2Alt(txt.getBanners().getBannerLateral2Alt());
        tf.setEmailAlta(txt.getEmailAlta());
        tf.setEmailModificar(txt.getEmailModificar());
        tf.setEmailNews(txt.getEmailNews());
        tf.setEmailPassword(txt.getEmailPassword());
        tf.setEmailPedido(txt.getEmailPedido());
        
        request.getSession().setAttribute("idiomaSel", txt.getIdioma());
        request.getSession().setAttribute("id", request.getParameter("id"));
        request.getSession().setAttribute("accion", "editar");
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {   
        AdministrarTextosTiendaForm tf=(AdministrarTextosTiendaForm) form;
        TextosTiendaDAO txtDAO=(TextosTiendaDAO) request.getSession().getServletContext().getAttribute("txtDAO");
        TextosTienda txt=txtDAO.consultaTextosTienda(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        BannersTienda banners=txt.getBanners();
        TextosSeccionesTienda secciones=txt.getSecciones();
        
        request.getSession().removeAttribute("accion");
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("idiomaSel");
                
        if(!this.isCancelled(request)){
            txt.setIdioma(tf.getIdioma().trim());
            txt.setTituloTienda(tf.getTituloTienda().trim());
            txt.setSubtituloTienda(tf.getSubtituloTienda().trim());
            txt.setTextoBienvenidaUsr(tf.getTextoBienvenidaUsr().trim());
            txt.setMtHome(tf.getMtHome().trim());
            txt.setMtCategorias(tf.getMtCategorias().trim());
            txt.setMtProductos(tf.getMtProductos().trim());
            txt.setMdHome(tf.getMdHome().trim());
            txt.setMdCategorias(tf.getMdCategorias().trim());
            txt.setMdProductos(tf.getMdProductos().trim());
            txt.setEmailAlta(tf.getEmailAlta());
            txt.setEmailModificar(tf.getEmailModificar());
            txt.setEmailNews(tf.getEmailNews());
            txt.setEmailPassword(tf.getEmailPassword());
            txt.setEmailPedido(tf.getEmailPedido());
            
            secciones.setTextoBienvenidaHome(tf.getTextoBienvenidaHome().trim());
            secciones.setTextoConocenos(tf.getTextoConocenos().trim());
            secciones.setTextoComprar(tf.getTextoComprar().trim());
            secciones.setTextoContactar(tf.getTextoContactar().trim());
            secciones.setContactarDias(tf.getContactarDias().trim());
            secciones.setContactarHorario(tf.getContactarHorario().trim());
            secciones.setContactarTelefono(tf.getContactarTelefono().trim());
            secciones.setTextoPrivacidad(tf.getTextoPrivacidad().trim());
            secciones.setTextoVenta(tf.getTextoVenta().trim());
            secciones.setTextoCostes(tf.getTextoCostes().trim());
            secciones.setTextos(txt);
            
            banners.setBannerFichaActivo(tf.isBannerFichaActivo());
            banners.setBannerHomeActivo(tf.isBannerHomeActivo());
            banners.setBannerLateral1Activo(tf.isBannerLateral1Activo());
            banners.setBannerLateral2Activo(tf.isBannerLateral2Activo());
            banners.setBannerFichaAlt(tf.getBannerFichaAlt());
            banners.setBannerHomeAlt(tf.getBannerHomeAlt());
            banners.setBannerLateral1Alt(tf.getBannerLateral1Alt());
            banners.setBannerLateral2Alt(tf.getBannerLateral2Alt());
            
            if(tf.getFbannerFicha().getFileSize()>0){
                FormFile imagen = tf.getFbannerFicha();
                banners.setBannerFicha(imagen.getFileData());
                banners.setBannerFichaTipo(imagen.getContentType());
            }
            
            if(tf.getFbannerHome().getFileSize()>0){
                FormFile imagen = tf.getFbannerHome();
                banners.setBannerHome(imagen.getFileData());
                banners.setBannerHomeTipo(imagen.getContentType());
            }
            
            if(tf.getFbannerLateral1().getFileSize()>0){
                FormFile imagen = tf.getFbannerLateral1();
                banners.setBannerLateral1(imagen.getFileData());
                banners.setBannerLateral1Tipo(imagen.getContentType());
            }
            
            if(tf.getFbannerLateral2().getFileSize()>0){
                FormFile imagen = tf.getFbannerLateral2();
                banners.setBannerLateral2(imagen.getFileData());
                banners.setBannerLateral2Tipo(imagen.getContentType());
            }
            banners.setTextos(txt);
            
            txt.setSecciones(secciones);
            txt.setBanners(banners);
            txtDAO.editaTextosTienda(txt);            
            
            TextosTienda aux=(TextosTienda) request.getSession().getAttribute("txt");
            if(txt.getIdioma().equalsIgnoreCase(aux.getIdioma()))
                request.getSession().setAttribute("txt", txt);
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {       
        TextosTiendaDAO txtDAO=(TextosTiendaDAO) request.getSession().getServletContext().getAttribute("txtDAO");
        TextosTienda txt=txtDAO.consultaTextosTienda(Integer.parseInt(request.getParameter("id")));                
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        
        if(!txt.getIdioma().equalsIgnoreCase(cfg.getIdioma()))
            txtDAO.eliminaTextosTienda(txt);
        
        return mapping.findForward(SUCCESS);
    }
}
