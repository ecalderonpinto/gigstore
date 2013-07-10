<%-- 
    Document   : admin_prod_desc
    Created on : 18/05/2012, 19:22:26
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario edicion descripción productos
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
                <bean:message key="titulo.alta_prod_desc" />
            </c:if>
            <c:if test="${accion=='editar'}">
                <bean:message key="titulo.modificar_prod_desc" />
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
                        <li><a href="${pageContext.request.contextPath}/administrar/productos/detalle.do?metodo=mostrar_detalle&id=${id_prod}" /><bean:message key="ruta.detalle_prod" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_desc" /></li>
                    </ul>
                </div>
                <h1>
                    <c:if test="${accion=='alta'}">
                        <bean:message key="titulo.alta_prod_desc" />
                    </c:if>
                    <c:if test="${accion=='editar'}">
                        <bean:message key="titulo.modificar_prod_desc" />
                    </c:if>
                </h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.modificar_prod_desc" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/productos/descripciones/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.idioma" /></p>
                                    <p class="etiqueta"><bean:message key="texto.nombre" /></p>
                                    <p class="etiqueta"><bean:message key="texto.formato" /></p>
                                    <p class="etiqueta"><bean:message key="texto.dimensiones" /></p>
                                    <p class="etiqueta"><bean:message key="texto.mtproductos" /></p> 
                                    <p class="etiqueta"><bean:message key="texto.mdproductos" /></p>
                                    <p class="etiqueta"><bean:message key="texto.etiquetas" /></p>
                                    <p class="etiqueta"><bean:message key="texto.descripcion" /></p>
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
                                    <html:text property="nombre" />
                                    <html:text property="formato" />
                                    <html:text property="dimensiones" />
                                    <html:text property="mtProductos" maxlength="255" />
                                    <html:text property="mdProductos" maxlength="255" />
                                    <html:text property="etiquetas" styleClass="fin" /><p class="acl">*<bean:message key="texto.etiquetas_acl" /></p>
                                </div>
                                <div id="txtdesc">
                                    <html:textarea property="descripcion" />
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
        <ckeditor:replaceAll basePath="${pageContext.request.contextPath}/ckeditor/" />     
    </body>
</html>
