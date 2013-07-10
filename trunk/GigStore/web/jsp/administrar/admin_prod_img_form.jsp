<%-- 
    Document   : admin_prod_img_form
    Created on : 10/05/2012, 10:24:13
    Author     : Manel Márquez Bonilla
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
                <bean:message key="titulo.alta_imagen" />
            </c:if>
            <c:if test="${accion=='editar'}">
                <bean:message key="titulo.modificar_imagen" />
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
                        <li><a href="${pageContext.request.contextPath}/administrar/productos.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_prod" /></a> &gt </li>
                        <c:if test="${obj_tipo=='producto'}"><li><a href="${pageContext.request.contextPath}/administrar/productos/detalle.do?metodo=mostrar_detalle&id=${obj_id}" ><bean:message key="ruta.detalle_prod" /></a> &gt </li></c:if>
                        <c:if test="${obj_tipo=='categoria'}"><li><a href="${pageContext.request.contextPath}/administrar/productos/categorias.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_cat" /></a> &gt </li><li><a href="${pageContext.request.contextPath}/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=${obj_id}" ><bean:message key="ruta.detalle_cat" /></a> &gt </li></c:if>
                        <li><bean:message key="ruta.editar_prod_img" /></li>
                    </ul>
                </div>
                <h1>
                    <c:if test="${accion=='alta'}">
                        <bean:message key="titulo.alta_imagen" />
                    </c:if>
                    <c:if test="${accion=='editar'}">
                        <bean:message key="titulo.modificar_imagen" />
                    </c:if>
                </h1>    
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.modificar_imagen" /></span></p>
                    <h3>${obj_nombre}</h3>
                    <img src="${pageContext.request.contextPath}/imagen?id=${img_id}" class="adminimg2" alt="${img_alt}" title="${img_alt}" />
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/productos/imagenes/alta" method="post" enctype="multipart/form-data" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.alt" /></p>
                                    <p class="etiqueta"><bean:message key="texto.principal" /></p>
                                    <c:if test="${accion=='alta'}">
                                        <p class="etiqueta"><bean:message key="texto.eligeimagen" /></p> 
                                    </c:if>
                                </div>
                                <div class="valores">
                                    <html:text property="alt" />
                                    <html:checkbox property="sprincipal" styleClass="check"></html:checkbox>
                                    <c:if test="${accion=='alta'}">
                                        <html:file property="imagen"/>
                                    </c:if>
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
