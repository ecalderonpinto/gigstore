<%-- 
    Document   : admin_prod_cat_det
    Created on : 18/05/2012, 19:21:43
    Author     : Manel Márquez Bonilla
    Descripcion: Datos de la categoria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="jmesa" uri="/WEB-INF/tlds/jmesa.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/jmesa.css" rel="stylesheet" type="text/css">
        <title><bean:message key="titulo.admin_categoria_datos" /></title>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/productos.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_prod" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/productos/categorias.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_cat" /></a> &gt </li>
                        <li><bean:message key="ruta.detalle_cat" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.admin_categoria_datos" />: ${categoria.nombre}</h1>        
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.admin_categoria_datos" /></span></p>
                    <div class="entrada">
                        <div id="detalle">
                            <table class="resumen">
                                <tr><td class="tit"><bean:message key="texto.nombre" />:</td><td class="valor">${categoria.nombre}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.catmadre" />:</td><td class="valor">${categoriamadre}</td></tr>                               
                                <tr><td class="tit"><bean:message key="texto.descuento" />:</td><td class="valor"><f:formatNumber pattern="#.##" value="${categoria.descuento}"></f:formatNumber></td></tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.descporcentaje" />:</td>
                                    <td class="valor">
                                        <c:if test="${categoria.descporcentaje}">
                                            <bean:message key="texto.porcentaje" />
                                        </c:if>
                                        <c:if test="${!categoria.descporcentaje}">
                                            <bean:message key="texto.directo" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.activo" />:</td>
                                    <td class="valor">
                                        <c:if test="${categoria.activo}">
                                            <bean:message key="texto.verdadero" />
                                        </c:if>
                                        <c:if test="${!categoria.activo}">
                                            <bean:message key="texto.falso" />
                                        </c:if>
                                    </td>
                                </tr>
                            </table>                    
                        </div>
                    </div>
                    <p class="links"><a class="editar" href="${pageContext.request.contextPath}/administrar/productos/categorias/editar.do?metodo=editar_inicio&id=${categoria.id}"><bean:message key="enlace.editar_categoria" /></a></p>
                </div>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.descripciones" /></span></p>
                    <div class="entrada">                        
                        <form name="listadoCatDescForm" action="${pageContext.request.contextPath}/administrar/productos/categorias/detalle.do">
                            <input type="hidden" name="metodo" value="mostrar_detalle">
                            <input type="hidden" name="id" value="${categoria.id}">
                            ${listado_cat_desc}
                        </form>
                    </div>
                    <p class="links"><a href="${pageContext.request.contextPath}/administrar/productos/categorias/descripciones/editar.do?metodo=alta_inicio&id=${categoria.id}"><bean:message key="enlace.agregar_cat_desc" /></a></p>
                </div>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.imagenes" /></span></p>
                    <div class="entrada">
                        <div class="bloquefoto">
                            <c:if test="${categoria.imagen.id!=null}">
                                <img src="${pageContext.request.contextPath}/imagen?id=${categoria.imagen.id}" class="adminimg" alt="${categoria.imagen.alt}" title="${categoria.imagen.alt}" />
                                <c:if test="${categoria.imagen.principal}">
                                    <p class="principal"><bean:message key="texto.principal" /></p>
                                </c:if>
                                <a class="editar_img" href="${pageContext.request.contextPath}/administrar/productos/categorias/imagenes/editar.do?metodo=editar_inicio&obj_tipo=categoria&id=${categoria.imagen.id}&obj_id=${categoria.id}"><bean:message key="enlace.editar_img" /></a>
                                <a class="eliminar_img" href="${pageContext.request.contextPath}/administrar/productos/categorias/imagenes/eliminar.do?metodo=eliminar&obj_tipo=categoria&id=${categoria.imagen.id}&obj_id=${categoria.id}" onclick="javascript:return confirmar('<bean:message key="texto.confirmar" />')"><bean:message key="enlace.eliminar_img" /></a>
                            </c:if>
                        </div>
                    </div>
                    <p class="links"><a href="${pageContext.request.contextPath}/administrar/productos/categorias/imagenes/editar.do?metodo=alta_inicio&obj_tipo=categoria&id=${categoria.id}"><bean:message key="enlace.agregar_img" /></a></p>
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
                location.href = '${pageContext.request.contextPath}/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=${producto.id}&' + parameterString;
            }
        </script>
    </body>
</html>

