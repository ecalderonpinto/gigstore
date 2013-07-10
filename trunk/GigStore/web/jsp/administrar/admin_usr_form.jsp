<%-- 
    Document   : ficha_gestion
    Created on : 06/03/2012, 17:21:24
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario editar usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="titulo.admin_usuarios_datos" /></title>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/usuarios.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_usr" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/usuarios/detalle.do?metodo=mostrar_detalle&id=${id}" ><bean:message key="ruta.detalle_usr" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_usr" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.admin_usuarios_datos" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.admin_usuarios_datos" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/usuarios/modificar" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.nif" /></p>
                                    <p class="etiqueta"><bean:message key="texto.nombre" /></p>
                                    <p class="etiqueta"><bean:message key="texto.apellidos" /></p>
                                    <p class="etiqueta"><bean:message key="texto.fechanac" /></p>
                                    <p class="etiqueta"><bean:message key="texto.telf" /></p>
                                    <p class="etiqueta"><bean:message key="texto.email" /></p>
                                    <p class="etiqueta"><bean:message key="texto.user" /></p>
                                    <p class="etiqueta"><bean:message key="texto.pass" /></p>
                                    <p class="etiqueta"><bean:message key="texto.pass2" /></p>
                                    <p class="etiqueta"><bean:message key="texto.rol" />
                                    <p class="etiqueta"><bean:message key="texto.estado" /></p> 
                                    <p class="etiqueta"><bean:message key="texto.fehaalta" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="nif" />
                                    <html:text property="nombre" />
                                    <html:text property="apellidos" />
                                    <html:text property="sfechaNacimiento" />
                                    <html:text property="telefono" />
                                    <html:text property="email" />
                                    <html:text property="usuario" />
                                    <html:password property="contrasenya1" />
                                    <html:password property="contrasenya2" />
                                    <div class="styled-select">
                                        <html:select property="rol" value="${rolSel}">
                                            <html:option value="usuario"><bean:message key="texto.op_usuario" /></html:option>
                                            <html:option value="administrador"><bean:message key="texto.op_admin" /></html:option>
                                        </html:select>
                                    </div>
                                    <div class="styled-select">
                                        <html:select property="sestado" value="${estadoSel}">
                                            <html:option value="0"><bean:message key="texto.eligeuno" /></html:option>
                                            <c:forEach var="e" items="${estados}">
                                                <html:option value="${e.id}">${e.estado}</html:option>
                                            </c:forEach>
                                        </html:select>
                                    </div>    
                                    <html:text property="sfechaAlta" disabled="true" />
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
