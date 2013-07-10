<%-- 
    Document   : admin_news_form
    Created on : 29/08/2012, 10:32:56
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="titulo.modificar_news" /></title>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/listacorreo.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_news" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_news" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.modificar_news" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.editar_news" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/listacorreo/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.email" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activo" /></p>                                    
                                </div>
                                <div class="valores">
                                    <html:text property="email" />
                                    <html:checkbox property="sactivo" styleClass="check"></html:checkbox>                                    
                                </div>
                                <div id="botones">
                                    <html:hidden property="metodo" value="editar_ejecutar" />
                                    <html:submit styleClass="boton"><bean:message key="boton.modificar" /></html:submit>                            
                                    <html:reset styleClass="boton" />
                                    <html:cancel styleClass="boton" />
                                </div>
                            </html:form>
                        </div>
                    </div>
                    <p class="links"></p>
                </div>                
            </div>
        </div>        
        <ctrl:pie />
    </body>
</html>

