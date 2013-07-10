<%-- 
    Document   : admin_prod_cat_form
    Created on : 20/04/2012, 10:56:45
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario editar categorias
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
                <bean:message key="titulo.alta_categoria" />
            </c:if>
            <c:if test="${accion=='editar'}">
                <bean:message key="titulo.modificar_categoria" />
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
                        <li><a href="${pageContext.request.contextPath}/administrar/productos/categorias.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_cat" /></a> &gt </li>
                        <c:if test="${accion=='editar'}">
                            <li><a href="${pageContext.request.contextPath}/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=${id}" ><bean:message key="ruta.detalle_cat" /></a> &gt </li>
                        </c:if>
                        <li><bean:message key="ruta.editar_prod_cat" /></li>
                    </ul>
                </div>
                <h1>
                    <c:if test="${accion=='alta'}">
                        <bean:message key="titulo.alta_categoria" />
                    </c:if>
                    <c:if test="${accion=='editar'}">
                        <bean:message key="titulo.modificar_categoria" />
                    </c:if>
                </h1>    
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.modificar_categoria" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/productos/categorias/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.nombre" /></p>
                                    <p class="etiqueta"><bean:message key="texto.catmadre" /></p>
                                    <p class="etiqueta"><bean:message key="texto.descuento" /></p>
                                    <p class="etiqueta"><bean:message key="texto.descporcentaje" /></p>
                                    <p class="etiqueta"><bean:message key="texto.estado" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="nombre" />
                                    <div class="styled-select">
                                        <html:select property="scategoriaMadre" value="${catSel}">
                                            <html:option value="0"><bean:message key="texto.eligeuno" /></html:option>
                                            <c:forEach var="c" items="${lista_categorias}">
                                                <html:option value="${c.id}">
                                                    <c:if test="${c.nodo>0}">
                                                        '
                                                        <c:forEach begin="0" end="${c.nodo-1}" step="1">
                                                        --
                                                        </c:forEach>
                                                        &gt;
                                                    </c:if>
                                                    ${c.nombre}
                                                </html:option>
                                            </c:forEach>
                                        </html:select>
                                    </div>                
                                    <html:text property="sdescuento" />
                                    <div class="styled-select">
                                        <html:select property="sdescporcentaje" value="${tipoDescSel}">
                                            <html:option value="0"><bean:message key="texto.directo" /></html:option>
                                            <html:option value="1"><bean:message key="texto.porcentaje" /></html:option>
                                        </html:select>
                                    </div>
                                    <div class="styled-select">
                                        <html:select property="sactivo" value="${estadoSel}">
                                            <html:option value="-1"><bean:message key="texto.eligeuno" /></html:option>
                                            <html:option value="1"><bean:message key="texto.activo" /></html:option>
                                            <html:option value="0"><bean:message key="texto.inactivo" /></html:option>
                                        </html:select>
                                    </div>    
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
                                </div                                
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
