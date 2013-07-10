<%-- 
    Document   : usuario_form
    Created on : 06/03/2012, 16:50:45
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario creacion/modificacion usuarios
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex,nofollow">
        <title>
            <c:if test="${accion=='alta'}">
                <bean:message key="titulo.alta_usuario" />
            </c:if>
            <c:if test="${accion=='editar'}">
                <bean:message key="titulo.modificar_usuario" />
            </c:if>
        </title>
    </head>
    <body>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a>  &gt </li>
                        <c:if test="${accion=='alta'}">
                            <li><a href="${pageContext.request.contextPath}/usuario/acceder/" ><bean:message key="titulo.login" /></a> &gt </li>
                        </c:if>
                        <c:if test="${accion=='editar'}">
                            <li><a href="${pageContext.request.contextPath}/usuario/datos-personales/" ><bean:message key="ruta.menu_user" /></a> &gt </li>
                        </c:if>
                        <li><bean:message key="ruta.datos_user" /></li>
                    </ul>
                </div>
                <h1>
                    <c:if test="${accion=='alta'}">
                        <bean:message key="titulo.alta_usuario" />
                    </c:if>
                    <c:if test="${accion=='editar'}">
                        <bean:message key="titulo.modificar_usuario" />
                    </c:if>
                </h1>    
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.datos_user" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/usuario/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.nif" /></p>
                                    <p class="etiqueta"><bean:message key="texto.nombre" /></p>
                                    <p class="etiqueta"><bean:message key="texto.apellidos" /></p>
                                    <p class="etiqueta"><bean:message key="texto.fechanac" /></p>
                                    <p class="etiqueta"><bean:message key="texto.telf" /></p>
                                    <p class="etiqueta"><bean:message key="texto.email" /></p>
                                    <p class="etiqueta"><bean:message key="texto.pass" /></p>
                                    <p class="etiqueta"><bean:message key="texto.pass2" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="nif" />
                                    <html:text property="nombre" />
                                    <html:text property="apellidos" />
                                    <html:text property="sfechaNacimiento" />
                                    <html:text property="telefono" />
                                    <html:text property="email" />
                                    <html:password property="contrasenya1" />
                                    <html:password property="contrasenya2" />
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
