/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarTextosTiendaForm extends org.apache.struts.action.ActionForm {
    private String idioma;
    private String tituloTienda;
    private String subtituloTienda;
    private String textoBienvenidaUsr;
    private String textoBienvenidaHome;
    private String textoConocenos;
    private String textoComprar;
    private String textoContactar;
    private String contactarDias;
    private String contactarHorario;
    private String contactarTelefono;
    private String textoPrivacidad;
    private String textoVenta;
    private String textoCostes;
    private String mtHome;
    private String mtCategorias;
    private String mtProductos;
    private String mdHome;
    private String mdCategorias;
    private String mdProductos;
    private boolean bannerHomeActivo;
    private boolean bannerFichaActivo;
    private boolean bannerLateral1Activo;
    private boolean bannerLateral2Activo;
    private String sbannerHomeActivo;
    private String sbannerFichaActivo;
    private String sbannerLateral1Activo;
    private String sbannerLateral2Activo;
    private FormFile fbannerFicha;
    private FormFile fbannerHome;
    private FormFile fbannerLateral1;
    private FormFile fbannerLateral2;
    private String bannerHomeAlt;
    private String bannerFichaAlt;
    private String bannerLateral1Alt;
    private String bannerLateral2Alt;
    private String emailNews;
    private String emailAlta;
    private String emailModificar;
    private String emailPedido;
    private String emailPassword;

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getContactarDias() {
        return contactarDias;
    }

    public void setContactarDias(String contactarDias) {
        this.contactarDias = contactarDias;
    }

    public String getContactarHorario() {
        return contactarHorario;
    }

    public void setContactarHorario(String contactarHorario) {
        this.contactarHorario = contactarHorario;
    }

    public String getContactarTelefono() {
        return contactarTelefono;
    }

    public void setContactarTelefono(String contactarTelefono) {
        this.contactarTelefono = contactarTelefono;
    }

    public String getSubtituloTienda() {
        return subtituloTienda;
    }

    public void setSubtituloTienda(String subtituloTienda) {
        this.subtituloTienda = subtituloTienda;
    }

    public String getTextoBienvenidaHome() {
        return textoBienvenidaHome;
    }

    public void setTextoBienvenidaHome(String textoBienvenidaHome) {
        this.textoBienvenidaHome = textoBienvenidaHome;
    }

    public String getTextoBienvenidaUsr() {
        return textoBienvenidaUsr;
    }

    public void setTextoBienvenidaUsr(String textoBienvenidaUsr) {
        this.textoBienvenidaUsr = textoBienvenidaUsr;
    }

    public String getTextoComprar() {
        return textoComprar;
    }

    public void setTextoComprar(String textoComprar) {
        this.textoComprar = textoComprar;
    }

    public String getTextoConocenos() {
        return textoConocenos;
    }

    public void setTextoConocenos(String textoConocenos) {
        this.textoConocenos = textoConocenos;
    }

    public String getTextoContactar() {
        return textoContactar;
    }

    public void setTextoContactar(String textoContactar) {
        this.textoContactar = textoContactar;
    }

    public String getTextoCostes() {
        return textoCostes;
    }

    public void setTextoCostes(String textoCostes) {
        this.textoCostes = textoCostes;
    }

    public String getTextoPrivacidad() {
        return textoPrivacidad;
    }

    public void setTextoPrivacidad(String textoPrivacidad) {
        this.textoPrivacidad = textoPrivacidad;
    }

    public String getTextoVenta() {
        return textoVenta;
    }

    public void setTextoVenta(String textoVenta) {
        this.textoVenta = textoVenta;
    }

    public String getTituloTienda() {
        return tituloTienda;
    }

    public void setTituloTienda(String tituloTienda) {
        this.tituloTienda = tituloTienda;
    }

    public String getMdCategorias() {
        return mdCategorias;
    }

    public void setMdCategorias(String mdCategorias) {
        this.mdCategorias = mdCategorias;
    }

    public String getMdHome() {
        return mdHome;
    }

    public void setMdHome(String mdHome) {
        this.mdHome = mdHome;
    }

    public String getMdProductos() {
        return mdProductos;
    }

    public void setMdProductos(String mdProductos) {
        this.mdProductos = mdProductos;
    }

    public String getMtCategorias() {
        return mtCategorias;
    }

    public void setMtCategorias(String mtCategorias) {
        this.mtCategorias = mtCategorias;
    }

    public String getMtHome() {
        return mtHome;
    }

    public void setMtHome(String mtHome) {
        this.mtHome = mtHome;
    }

    public String getMtProductos() {
        return mtProductos;
    }

    public void setMtProductos(String mtProductos) {
        this.mtProductos = mtProductos;
    }

    public boolean isBannerFichaActivo() {
        return bannerFichaActivo;
    }

    public void setBannerFichaActivo(boolean bannerFichaActivo) {
        this.bannerFichaActivo = bannerFichaActivo;
    }

    public String getBannerFichaAlt() {
        return bannerFichaAlt;
    }

    public void setBannerFichaAlt(String bannerFichaAlt) {
        this.bannerFichaAlt = bannerFichaAlt;
    }

    public boolean isBannerHomeActivo() {
        return bannerHomeActivo;
    }

    public void setBannerHomeActivo(boolean bannerHomeActivo) {
        this.bannerHomeActivo = bannerHomeActivo;
    }

    public String getBannerHomeAlt() {
        return bannerHomeAlt;
    }

    public void setBannerHomeAlt(String bannerHomeAlt) {
        this.bannerHomeAlt = bannerHomeAlt;
    }

    public boolean isBannerLateral1Activo() {
        return bannerLateral1Activo;
    }

    public void setBannerLateral1Activo(boolean bannerLateral1Activo) {
        this.bannerLateral1Activo = bannerLateral1Activo;
    }

    public String getBannerLateral1Alt() {
        return bannerLateral1Alt;
    }

    public void setBannerLateral1Alt(String bannerLateral1Alt) {
        this.bannerLateral1Alt = bannerLateral1Alt;
    }

    public boolean isBannerLateral2Activo() {
        return bannerLateral2Activo;
    }

    public void setBannerLateral2Activo(boolean bannerLateral2Activo) {
        this.bannerLateral2Activo = bannerLateral2Activo;
    }

    public String getBannerLateral2Alt() {
        return bannerLateral2Alt;
    }

    public void setBannerLateral2Alt(String bannerLateral2Alt) {
        this.bannerLateral2Alt = bannerLateral2Alt;
    }

    public FormFile getFbannerFicha() {
        return fbannerFicha;
    }

    public void setFbannerFicha(FormFile fbannerFicha) {
        this.fbannerFicha = fbannerFicha;
    }

    public FormFile getFbannerHome() {
        return fbannerHome;
    }

    public void setFbannerHome(FormFile fbannerHome) {
        this.fbannerHome = fbannerHome;
    }

    public FormFile getFbannerLateral1() {
        return fbannerLateral1;
    }

    public void setFbannerLateral1(FormFile fbannerLateral1) {
        this.fbannerLateral1 = fbannerLateral1;
    }

    public FormFile getFbannerLateral2() {
        return fbannerLateral2;
    }

    public void setFbannerLateral2(FormFile fbannerLateral2) {
        this.fbannerLateral2 = fbannerLateral2;
    }

    public String getSbannerFichaActivo() {
        return sbannerFichaActivo;
    }

    public void setSbannerFichaActivo(String sbannerFichaActivo) {
        this.sbannerFichaActivo = sbannerFichaActivo;
    }

    public String getSbannerHomeActivo() {
        return sbannerHomeActivo;
    }

    public void setSbannerHomeActivo(String sbannerHomeActivo) {
        this.sbannerHomeActivo = sbannerHomeActivo;
    }

    public String getSbannerLateral1Activo() {
        return sbannerLateral1Activo;
    }

    public void setSbannerLateral1Activo(String sbannerLateral1Activo) {
        this.sbannerLateral1Activo = sbannerLateral1Activo;
    }

    public String getSbannerLateral2Activo() {
        return sbannerLateral2Activo;
    }

    public void setSbannerLateral2Activo(String sbannerLateral2Activo) {
        this.sbannerLateral2Activo = sbannerLateral2Activo;
    }

    public String getEmailAlta() {
        return emailAlta;
    }

    public void setEmailAlta(String emailAlta) {
        this.emailAlta = emailAlta;
    }

    public String getEmailModificar() {
        return emailModificar;
    }

    public void setEmailModificar(String emailModificar) {
        this.emailModificar = emailModificar;
    }

    public String getEmailNews() {
        return emailNews;
    }

    public void setEmailNews(String emailNews) {
        this.emailNews = emailNews;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getEmailPedido() {
        return emailPedido;
    }

    public void setEmailPedido(String emailPedido) {
        this.emailPedido = emailPedido;
    }
    
    /**
     *
     */
    public AdministrarTextosTiendaForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        MessageResources messages = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
        if (getIdioma() == null || getIdioma().length() < 1 || getIdioma().equalsIgnoreCase("0")) {
            errors.add("idioma", new ActionMessage("errors.required", messages.getMessage(locale, "texto.idioma")));
        }
        
        if (getTituloTienda() == null || getTituloTienda().length() < 1) {
            errors.add("tituloTienda", new ActionMessage("errors.required", messages.getMessage(locale, "texto.titulo")));
        }
        
        if (getSubtituloTienda() == null || getSubtituloTienda().length() < 1) {
            errors.add("subtituloTienda", new ActionMessage("errors.required", messages.getMessage(locale, "texto.subtitulo")));
        }
        
        if (getTextoBienvenidaUsr() == null || getTextoBienvenidaUsr().length() < 1) {
            errors.add("textoBienvenida", new ActionMessage("errors.required", messages.getMessage(locale, "texto.bienvenida")));
        }
        
        if (getTextoConocenos()== null || getTextoConocenos().length() < 1) {
            errors.add("textoConocenos", new ActionMessage("errors.required", messages.getMessage(locale, "texto.conocenos")));
        }
        
        if (getTextoComprar()== null || getTextoComprar().length() < 1) {
            errors.add("textoComprar", new ActionMessage("errors.required", messages.getMessage(locale, "texto.comprar")));
        }
        
        if (getTextoContactar()== null || getTextoContactar().length() < 1) {
            errors.add("textoContactar", new ActionMessage("errors.required", messages.getMessage(locale, "texto.contactar")));
        }
        
        if (getContactarDias()== null || getContactarDias().length() < 1) {
            errors.add("contactarDias", new ActionMessage("errors.required", messages.getMessage(locale, "texto.contactardias")));
        }
        
        if (getContactarHorario()== null || getContactarHorario().length() < 1) {
            errors.add("contactarHorario", new ActionMessage("errors.required", messages.getMessage(locale, "texto.contactarhorario")));
        }
        
        if (getContactarTelefono()== null || getContactarTelefono().length() < 1) {
            errors.add("contactarTelefono", new ActionMessage("errors.required", messages.getMessage(locale, "texto.contactartlf")));
        }
        
        if (getTextoPrivacidad()== null || getTextoPrivacidad().length() < 1) {
            errors.add("textoPrivacidad", new ActionMessage("errors.required", messages.getMessage(locale, "texto.privacidad")));
        }
        
        if (getTextoCostes()== null || getTextoCostes().length() < 1) {
            errors.add("textoCostes", new ActionMessage("errors.required", messages.getMessage(locale, "texto.costes")));
        }
        
        if (getTextoVenta()== null || getTextoVenta().length() < 1) {
            errors.add("textoVenta", new ActionMessage("errors.required", messages.getMessage(locale, "texto.venta")));
        }
        
        if (getMtCategorias()== null || getMtCategorias().length() < 1) {
            errors.add("MtCategorias", new ActionMessage("errors.required", messages.getMessage(locale, "texto.mtcategorias")));
        }
        
        if (getMtHome()== null || getMtHome().length() < 1) {
            errors.add("MtHome", new ActionMessage("errors.required", messages.getMessage(locale, "texto.mthome")));
        }
        
        if (getMtProductos()== null || getMtProductos().length() < 1) {
            errors.add("MtProductos", new ActionMessage("errors.required", messages.getMessage(locale, "texto.mtproductos")));
        }
        
        if (getMdCategorias()== null || getMdCategorias().length() < 1) {
            errors.add("MdCategorias", new ActionMessage("errors.required", messages.getMessage(locale, "texto.mdcategorias")));
        }
        
        if (getMdHome()== null || getMdHome().length() < 1) {
            errors.add("MdHome", new ActionMessage("errors.required", messages.getMessage(locale, "texto.mdhome")));
        }
        
        if (getMdProductos()== null || getMdProductos().length() < 1) {
            errors.add("MdProductos", new ActionMessage("errors.required", messages.getMessage(locale, "texto.mdproductos")));
        }
        
        if(getEmailAlta()==null || getEmailAlta().length()<1){
            errors.add("EmailAlta", new ActionMessage("errors.required", messages.getMessage(locale, "texto.email_altausr")));
        }
        
        if(getEmailModificar()==null || getEmailModificar().length()<1){
            errors.add("EmailModificar", new ActionMessage("errors.required", messages.getMessage(locale, "texto.email_modifusr")));
        }
        
        if(getEmailNews()==null || getEmailNews().length()<1){
            errors.add("EmailNews", new ActionMessage("errors.required", messages.getMessage(locale, "texto.email_news")));
        }
        
        if(getEmailPassword()==null || getEmailPassword().length()<1){
            errors.add("EmailPassword", new ActionMessage("errors.required", messages.getMessage(locale, "texto.email_recordarpass")));
        }
        
        if(getEmailPedido()==null || getEmailPedido().length()<1){
            errors.add("EmailPedido", new ActionMessage("errors.required", messages.getMessage(locale, "texto.email_pedido")));
        }
        
        try{
            if(getSbannerFichaActivo() == null)
                bannerFichaActivo = false;
            else
                bannerFichaActivo = true;
        }catch(Exception Ex){
            errors.add("bannerFichaActivo", new ActionMessage("errors.required", messages.getMessage(locale, "texto.principal")));
        }
        
        try{
            if(getSbannerHomeActivo() == null)
                bannerHomeActivo = false;
            else
                bannerHomeActivo = true;
        }catch(Exception Ex){
            errors.add("bannerHomeActivo", new ActionMessage("errors.required", messages.getMessage(locale, "texto.principal")));
        }
        
        try{
            if(getSbannerLateral1Activo() == null)
                bannerLateral1Activo = false;
            else
                bannerLateral1Activo = true;
        }catch(Exception Ex){
            errors.add("bannerLateral1Activo", new ActionMessage("errors.required", messages.getMessage(locale, "texto.principal")));
        }
        
        try{
            if(getSbannerLateral2Activo() == null)
                bannerLateral2Activo = false;
            else
                bannerLateral2Activo = true;
        }catch(Exception Ex){
            errors.add("bannerLateral2Activo", new ActionMessage("errors.required", messages.getMessage(locale, "texto.principal")));
        }
        
        if (!getFbannerFicha().getFileName().isEmpty() && (getFbannerFicha().getFileSize() > 65535 || !(getFbannerFicha().getContentType().equalsIgnoreCase("image/gif") || getFbannerFicha().getContentType().equalsIgnoreCase("image/jpeg") || getFbannerFicha().getContentType().equalsIgnoreCase("image/png")))) {
            errors.add("imagen", new ActionMessage("errors.imagen", messages.getMessage(locale, "texto.imagen")));
        }
        
        if (!getFbannerHome().getFileName().isEmpty() && (getFbannerHome().getFileSize() > 65535 || !(getFbannerHome().getContentType().equalsIgnoreCase("image/gif") || getFbannerHome().getContentType().equalsIgnoreCase("image/jpeg") || getFbannerHome().getContentType().equalsIgnoreCase("image/png")))) {
            errors.add("imagen", new ActionMessage("errors.imagen", messages.getMessage(locale, "texto.imagen")));
        }
        
        if (!getFbannerLateral1().getFileName().isEmpty() && (getFbannerLateral1().getFileSize() > 65535 || !(getFbannerLateral1().getContentType().equalsIgnoreCase("image/gif") || getFbannerLateral1().getContentType().equalsIgnoreCase("image/jpeg") || getFbannerLateral1().getContentType().equalsIgnoreCase("image/png")))) {
            errors.add("imagen", new ActionMessage("errors.imagen", messages.getMessage(locale, "texto.imagen")));
        }
        
        if (!getFbannerLateral2().getFileName().isEmpty() && (getFbannerLateral2().getFileSize() > 65535 || !(getFbannerLateral2().getContentType().equalsIgnoreCase("image/gif") || getFbannerLateral2().getContentType().equalsIgnoreCase("image/jpeg") || getFbannerLateral2().getContentType().equalsIgnoreCase("image/png")))) {
            errors.add("imagen", new ActionMessage("errors.imagen", messages.getMessage(locale, "texto.imagen")));
        }
        
        return errors;
    }
}
