<%-- 
    Document   : admin_txt_tienda_form
    Created on : 10/06/2012, 17:48:39
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario para configuración general de los textos de la tienda on-line
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <c:if test="${accion=='alta'}">
                <bean:message key="titulo.alta_texto" />
            </c:if>
            <c:if test="${accion=='editar'}">
                <bean:message key="titulo.modificar_texto" />
            </c:if>
        </title>
    </head>
    <body>
        <ctrl:cabecera />
        <ctrl:menu_admin />
        <div id="cuerpo">
            <div id="contenido_g">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/" ><bean:message key="ruta.menu_admin" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/tienda.do?metodo=mostrar_listado" ><bean:message key="ruta.admin_tienda" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/tienda/textos.do?metodo=mostrar_listado" ><bean:message key="ruta.admin_txt" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_txt" /></li>
                    </ul>
                </div>
                <c:if test="${accion=='alta'}">
                    <h1><bean:message key="titulo.alta_texto" /></h1>
                </c:if>
                <c:if test="${accion=='editar'}">
                    <h1><bean:message key="titulo.modificar_texto" /></h1>
                </c:if>
                <div id="formulario">
                    <html:errors />
                    <html:form action="/administrar/tienda/textos/alta" acceptCharset="ISO-8859-1" enctype="multipart/form-data" >
                        <div class="cuadro">
                            <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.editar_txt" /></span></p>
                            <div class="entrada">
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.idioma" /></p>
                                    <p class="etiqueta"><bean:message key="texto.titulo" /></p> 
                                    <p class="etiqueta"><bean:message key="texto.subtitulo" /></p>
                                    <p class="etiqueta"><bean:message key="texto.bienvenidausr" /></p>
                                    <p class="etiqueta"><bean:message key="texto.contactardias" /></p> 
                                    <p class="etiqueta"><bean:message key="texto.contactarhorario" /></p>
                                    <p class="etiqueta"><bean:message key="texto.contactartlf" /></p>
                                </div>
                                <div class="valores">
                                    <div class="styled-select">
                                        <html:select property="idioma" value="${idiomaSel}">
                                            <html:option value="0"><bean:message key="texto.eligeuno" /></html:option>
                                            <c:forEach var="entry" items="${locales}">
                                                <html:option value="${entry.key}">${entry.value}</html:option>
                                            </c:forEach>
                                        </html:select>
                                    </div>    
                                    <html:text property="tituloTienda" maxlength="150" />
                                    <html:text property="subtituloTienda" maxlength="255" />
                                    <html:text property="textoBienvenidaUsr" maxlength="255" />
                                    <html:text property="contactarDias" maxlength="50" />
                                    <html:text property="contactarHorario" maxlength="50" />
                                    <html:text property="contactarTelefono" maxlength="50" />
                                </div>
                            </div>
                            <p class="links"></p>
                        </div>
                        <div class="cuadro">
                            <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.seo_tienda" /></span></p>
                            <div class="entrada">
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.mthome" /></p>
                                    <p class="etiqueta"><bean:message key="texto.mdhome" /></p> <br />
                                    <p class="etiqueta"><bean:message key="texto.mtcategorias" /></p>
                                    <p class="etiqueta"><bean:message key="texto.mdcategorias" /></p> <br /> 
                                    <p class="etiqueta"><bean:message key="texto.mtproductos" /></p> 
                                    <p class="etiqueta"><bean:message key="texto.mdproductos" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="mtHome" maxlength="255" />
                                    <html:text property="mdHome" maxlength="255" /><br />
                                    <html:text property="mtCategorias" maxlength="255" />
                                    <html:text property="mdCategorias" maxlength="255" /><br />
                                    <html:text property="mtProductos" maxlength="255" />
                                    <html:text property="mdProductos" maxlength="255" />
                                </div>
                            </div>
                            <p class="links"></p>
                        </div>
                        <div class="cuadro">
                            <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.txt_tienda" /></span></p>
                            <div class="entrada">
                                <p class="etiqueta"><bean:message key="texto.bienvenidahome" /></p> <html:textarea property="textoBienvenidaHome" /><br />
                                <p class="etiqueta"><bean:message key="texto.conocenos" /></p> <html:textarea property="textoConocenos" /><br />
                                <p class="etiqueta"><bean:message key="texto.comprar" /></p> <html:textarea property="textoComprar" /><br />
                                <p class="etiqueta"><bean:message key="texto.contactar" /></p> <html:textarea property="textoContactar" /><br />
                                <p class="etiqueta"><bean:message key="texto.privacidad" /></p> <html:textarea property="textoPrivacidad" /><br />
                                <p class="etiqueta"><bean:message key="texto.venta" /></p> <html:textarea property="textoVenta" /><br />
                                <p class="etiqueta"><bean:message key="texto.costes" /></p> <html:textarea property="textoCostes" /><br />
                            </div>
                            <p class="links"></p>
                        </div>
                        <div class="cuadro">
                            <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="texto.email_notificaciones" /></span></p>
                            <div class="entrada">
                                <p class="etiqueta"><bean:message key="texto.email_news" /></p> <html:textarea property="emailNews" /><br />
                                <p class="etiqueta"><bean:message key="texto.email_altausr" /></p> <html:textarea property="emailAlta" /><br />
                                <p class="etiqueta"><bean:message key="texto.email_modifusr" /></p> <html:textarea property="emailModificar" /><br />
                                <p class="etiqueta"><bean:message key="texto.email_pedido" /></p> <html:textarea property="emailPedido" /><br />
                                <p class="etiqueta"><bean:message key="texto.email_recordarpass" /></p> <html:textarea property="emailPassword" /><br />
                            </div>
                            <p class="links"></p>
                        </div>
                        <div class="cuadro">
                            <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.banners_tienda" /></span></p>
                            <div class="entrada">
                                <c:if test="${accion=='editar'}">
                                    <img src="${pageContext.request.contextPath}/adminbanner?idioma=${idiomaSel}&tipo=home" alt="${txt.banners.bannerHomeAlt}" title="${txt.banners.bannerHomeAlt}" />
                                </c:if>
                                
                                <p class="etiqueta"><bean:message key="texto.banner_home" /></p>
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.alt" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activo" /></p>
                                    <p class="etiqueta"><bean:message key="texto.eligeimagen" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="bannerHomeAlt" />
                                    <html:checkbox property="sbannerHomeActivo" styleClass="check"></html:checkbox>
                                    <html:file property="fbannerHome"/>
                                </div>
                                                        
                                <br /><br />
                                <c:if test="${accion=='editar'}">
                                    <img src="${pageContext.request.contextPath}/adminbanner?idioma=${idiomaSel}&tipo=ficha" alt="${txt.banners.bannerFichaAlt}" title="${txt.banners.bannerFichaAlt}" />
                                </c:if>
                                
                                <p class="etiqueta"><bean:message key="texto.banner_ficha" /></p>
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.alt" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activo" /></p>
                                    <p class="etiqueta"><bean:message key="texto.eligeimagen" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="bannerFichaAlt" />
                                    <html:checkbox property="sbannerFichaActivo" styleClass="check"></html:checkbox>
                                    <html:file property="fbannerFicha"/>
                                </div>
                                
                                <br /><br />
                                <c:if test="${accion=='editar'}">
                                    <img src="${pageContext.request.contextPath}/adminbanner?idioma=${idiomaSel}&tipo=lateral1" alt="${txt.banners.bannerLateral1Alt}" title="${txt.banners.bannerLateral1Alt}" />
                                </c:if>
                                
                                <p class="etiqueta"><bean:message key="texto.banner_lat1" /></p>
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.alt" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activo" /></p>
                                    <p class="etiqueta"><bean:message key="texto.eligeimagen" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="bannerLateral1Alt" />
                                    <html:checkbox property="sbannerLateral1Activo" styleClass="check"></html:checkbox>
                                    <html:file property="fbannerLateral1"/>
                                </div>
                                
                                <br /><br />
                                <c:if test="${accion=='editar'}">
                                    <img src="${pageContext.request.contextPath}/adminbanner?idioma=${idiomaSel}&tipo=lateral2" alt="${txt.banners.bannerLateral2Alt}" title="${txt.banners.bannerLateral2Alt}" />
                                </c:if>
                                
                                <p class="etiqueta"><bean:message key="texto.banner_lat2" /></p>
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.alt" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activo" /></p>
                                    <p class="etiqueta"><bean:message key="texto.eligeimagen" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="bannerLateral2Alt" />
                                    <html:checkbox property="sbannerLateral2Activo" styleClass="check"></html:checkbox>
                                    <html:file property="fbannerLateral2"/>
                                </div>
                                <div id="botones">
                                    <c:if test="${accion=='alta'}">
                                        <html:hidden property="metodo" value="alta_ejecutar" />
                                        <html:submit styleClass="boton"><bean:message key="boton.alta" /></html:submit>
                                    </c:if>
                                    <c:if test="${accion=='editar'}">
                                        <html:hidden property="metodo" value="editar_ejecutar" />
                                        <html:submit styleClass="boton"><bean:message key="boton.modificar" /></html:submit>
                                    </c:if>
                                    <html:reset styleClass="boton" />
                                    <html:cancel styleClass="boton" />
                                </div>
                            </div>
                            <p class="links"></p>
                        </div>
                    </html:form>
                </div>                    
            </div>
        </div>        
        <ctrl:pie />
        <ckeditor:replaceAll basePath="${pageContext.request.contextPath}/ckeditor/" />
    </body>
</html>
