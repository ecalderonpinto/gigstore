<%-- 
    Document   : admin_prod_cat_det
    Created on : 20/04/2012, 10:09:41
    Author     : Manel Márquez Bonilla
    Descripcion: Datos de la producto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="jmesa" uri="/WEB-INF/tlds/jmesa.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/jmesa.css" rel="stylesheet" type="text/css">
        <title><bean:message key="titulo.admin_producto_datos" /></title>
        <ctrl:script_confirmacion />
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
                        <li><a href="${pageContext.request.contextPath}/administrar/productos.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_productos" /></a> &gt </li>
                        <li><bean:message key="ruta.detalle_prod" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.admin_producto_datos" />: ${producto.nombre}</h1>        
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.admin_producto_datos" /></span></p>
                    <div class="entrada">
                        <div id="detalle">
                            <table class="resumen">
                                <tr><td class="tit"><bean:message key="texto.referencia" />:</td><td class="valor">${producto.referencia}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.nombre" />:</td><td class="valor">${producto.nombre}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.categoria" />:</td><td class="valor">${producto.categoria}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.marca" />:</td><td class="valor">${producto.marca}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.precio" />:</td><td class="valor"><c:if test="${cfg.moneda=='USD'}">&#36;</c:if><c:if test="${cfg.moneda=='GBP'}">&#163;</c:if><f:formatNumber pattern="#.##" value="${producto.precio}"></f:formatNumber><c:if test="${cfg.moneda=='EUR'}">&euro;</c:if></td></tr>
                                <tr><td class="tit"><bean:message key="texto.iva" />:</td><td class="valor">${producto.iva.descripcion}</td></tr>                                
                                <tr><td class="tit"><bean:message key="texto.descuento" />:</td><td class="valor"><f:formatNumber pattern="#.##" value="${producto.descuento}"></f:formatNumber></td></tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.descporcentaje" />:</td>
                                    <td class="valor">
                                        <c:if test="${producto.descporcentaje}">
                                            <bean:message key="texto.porcentaje" />
                                        </c:if>
                                        <c:if test="${!producto.descporcentaje}">
                                            <bean:message key="texto.directo" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr><td class="tit"><bean:message key="texto.stock" />:</td><td class="valor">${producto.stock}</td></tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.destacado" />:</td>
                                    <td class="valor">
                                        <c:if test="${producto.destacado}">
                                            <bean:message key="texto.verdadero" />
                                        </c:if>
                                        <c:if test="${!producto.destacado}">
                                            <bean:message key="texto.falso" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr><td class="tit"><bean:message key="texto.estado" />:</td><td class="valor">${producto.estado.estado}</td></tr>
                            </table>                    
                        </div>
                    </div>
                    <p class="links"><a class="editar" href="${pageContext.request.contextPath}/administrar/productos/editar.do?metodo=editar_inicio&id=${producto.id}"><bean:message key="enlace.editar_producto" /></a></p>
                    <p class="links"><a class="ver" href="${pageContext.request.contextPath}${producto.url}"><bean:message key="enlace.ficha_producto" /></a></p>
                </div>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.descripciones" /></span></p>
                    <div class="entrada">                           
                        <form name="listadoProdDescForm" action="${pageContext.request.contextPath}/administrar/productos/detalle.do">
                            <input type="hidden" name="metodo" value="mostrar_detalle">
                            <input type="hidden" name="id" value="${producto.id}">
                            ${listado_prod_desc}
                        </form>
                    </div>
                    <p class="links"><a href="${pageContext.request.contextPath}/administrar/productos/descripciones/editar.do?metodo=alta_inicio&id=${producto.id}"><bean:message key="enlace.agregar_prod_desc" /></a></p>
                </div>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.opciones" /></span></p>
                    <div class="entrada">                           
                        <form name="listadoProdOptForm" action="${pageContext.request.contextPath}/administrar/productos/detalle.do">
                            <input type="hidden" name="metodo" value="mostrar_detalle">
                            <input type="hidden" name="id" value="${producto.id}">
                            ${listado_prod_opt}
                        </form>
                    </div>
                    <p class="links"><a href="${pageContext.request.contextPath}/administrar/productos/opciones/combinar/editar.do?metodo=alta_inicio&id=${producto.id}"><bean:message key="enlace.agregar_prod_opt" /></a></p>
                </div>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.imagenes" /></span></p>
                    <div class="entrada">
                        <c:forEach var="img" items="${producto.imagenes}">
                            <div class="bloquefoto">
                                <img src="${pageContext.request.contextPath}/imagen?id=${img.id}" class="adminimg" alt="${img.alt}" title="${img.alt}" />
                                <c:if test="${img.principal}">
                                    <p class="principal"><bean:message key="texto.principal" /></p>
                                </c:if>
                                <a class="editar_img" href="${pageContext.request.contextPath}/administrar/productos/imagenes/editar.do?metodo=editar_inicio&obj_tipo=producto&id=${img.id}&obj_id=${producto.id}"><bean:message key="enlace.editar_img" /></a>
                                <a class="eliminar_img" href="${pageContext.request.contextPath}/administrar/productos/imagenes/eliminar.do?metodo=eliminar&obj_tipo=producto&id=${img.id}&obj_id=${producto.id}" onclick="javascript:return confirmar('<bean:message key="texto.confirmar" />')"><bean:message key="enlace.eliminar_img" /></a>
                            </div>
                        </c:forEach>
                    </div>
                    <p class="links"><a href="${pageContext.request.contextPath}/administrar/productos/imagenes/editar.do?metodo=alta_inicio&obj_tipo=producto&id=${producto.id}"><bean:message key="enlace.agregar_img" /></a></p>
                </div>
            </div>
        </div>        
        <ctrl:pie />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jmesa.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
            function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                location.href = '${pageContext.request.contextPath}/administrar/productos/detalle.do?metodo=mostrar_detalle&id=${producto.id}&' + parameterString;
            }
        </script>
    </body>
</html>
