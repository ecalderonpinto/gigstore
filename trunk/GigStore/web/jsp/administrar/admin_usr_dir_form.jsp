<%-- 
    Document   : dir_form
    Created on : 30/03/2012, 19:00:05
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario alta/modificacion de direcciones de los usuarios
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
        <title>
            <c:if test="${accion=='alta'}">
                <bean:message key="titulo.alta_direccion" />
            </c:if>
            <c:if test="${accion=='editar'}">
                <bean:message key="titulo.modificar_direccion" />
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
                        <li><a href="${pageContext.request.contextPath}/administrar/usuarios.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_usr" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/usuarios/detalle.do?metodo=mostrar_detalle&id=${usr_id}" ><bean:message key="ruta.detalle_usr" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_usr_dir" /></li>
                    </ul>
                </div>
                <h1>
                    <c:if test="${accion=='alta'}">
                        <bean:message key="titulo.alta_direccion" />
                    </c:if>
                    <c:if test="${accion=='editar'}">
                        <bean:message key="titulo.modificar_direccion" />
                    </c:if>
                </h1>    
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.modificar_direccion" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/usuarios/direcciones/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.direcciona" /></p>
                                    <p class="etiqueta"><bean:message key="texto.direccionb" /></p>
                                    <p class="etiqueta"><bean:message key="texto.cp" /></p>
                                    <p class="etiqueta"><bean:message key="texto.poblacion" /></p>
                                    <p class="etiqueta"><bean:message key="texto.provincia" /></p>
                                    <p class="etiqueta"><bean:message key="texto.pais" /></p>
                                    <p class="etiqueta"><bean:message key="texto.observaciones" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="direcciona" />
                                    <html:text property="direccionb" />
                                    <html:text property="cp" />
                                    <html:text property="poblacion" />
                                    <html:text property="provincia" />
                                    <html:text property="pais" />
                                    <html:textarea property="observaciones" />
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
