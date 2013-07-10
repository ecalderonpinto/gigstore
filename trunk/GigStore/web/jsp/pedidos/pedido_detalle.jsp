<%-- 
    Document   : vista_pedido
    Created on : 06/03/2012, 16:52:23
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="listado" uri="/WEB-INF/tlds/gigStore.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
        <meta name="robots" content="noindex,nofollow">
        <title><bean:message key="titulo.detalle_ped" /></title>
        <ctrl:script_confirmacion />
    </head>
    <body>
        <div id="fb-root"></div>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li><bean:message key="titulo.detalle_ped" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.detalle_ped" /></h1>                      
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"></span></p>
                    
                    <div class="entrada"> 
                        <table id="carro">
                            <tr>
                                <th><bean:message key="texto.eliminar" /></th>
                                <th><bean:message key="texto.referencia" /></th>
                                <th><bean:message key="texto.nombre" /></th>
                                <th></th>
                                <th></th>
                                <th><bean:message key="texto.unidades" /></th>
                                <th></th>
                                <th><bean:message key="texto.precio" /></th>
                                <th><bean:message key="texto.subtotal" /></th>
                            </tr>
                            <c:set var="subtotal" value="${0}" />
                            <c:set var="totaliva" value="${0}" />
                            <c:forEach var="lin" items="${carrito}">
                                <tr class="linea">
                                    <td class="eliminar"><a href="${pageContext.request.contextPath}/pedido/eliminar.do?metodo=eliminar&lin=${lin.nlinea}" onclick="javascript:return confirmar('<bean:message key="texto.confirmar" />')"><img src="${pageContext.request.contextPath}/images/tienda/Trash.png" alt="<bean:message key="texto.eliminar" />"></a></td>
                                    <td>${lin.producto.referencia}</td>
                                    <c:set var="nombre" value="" />
                                    <c:forEach var="desc" items="${lin.producto.descripciones}" >
                                        <c:if test="${desc.idioma==pageContext.request.locale.language}"><c:set var="nombre" value="${desc.nombre}" /></c:if>
                                    </c:forEach>
                                    <c:if test="${empty(nombre)}">
                                        <c:set var="nombre" value="${lin.producto.nombre}" />
                                    </c:if>
                                    <c:set var="opt" value="" />
                                    <c:forEach var="s" items="${lin.opcion.traducciones}" >
                                        <c:set var="parts" value="${fn:split(s, ';')}" />
                                        <c:if test="${parts[0]==pageContext.request.locale.language}"><c:set var="opt" value="${parts[1]}" /></c:if>
                                    </c:forEach>
                                    <c:if test="${empty(opt)}">
                                        <c:set var="opt" value="${lin.opcion.opcion}" />
                                    </c:if>
                                    <td><a href="${pageContext.request.contextPath}${lin.producto.url}">${nombre} ${opt}</a></td>
                                    <td>
                                        <c:if test="${empty(lin.producto.imagenes)}">
                                            <img src="${pageContext.request.contextPath}/images/tienda/Gift.png" data-title="<bean:message key="texto.imgnodisponible" />" data-description="<bean:message key="texto.imgnodisponible" />" >
                                        </c:if>
                                        <c:forEach var="imagen" items="${lin.producto.imagenes}">
                                            <c:if test="${imagen.principal}">
                                                <img src="${pageContext.request.contextPath}/imagen?id=${imagen.id}" data-title="${imagen.alt}" data-description="${imagen.alt}" > 
                                            </c:if>
                                        </c:forEach> 
                                    </td>
                                    <td class="dcha" ><a href="${pageContext.request.contextPath}/pedido/modificar.do?metodo=restar&lin=${lin.nlinea}" class="menos"><img src="${pageContext.request.contextPath}/images/tienda/menos.png" alt=""></a></td>
                                    <td>${lin.cantidad}</td>
                                    <td><a href="${pageContext.request.contextPath}/pedido/modificar.do?metodo=sumar&lin=${lin.nlinea}" class="mas"><img src="${pageContext.request.contextPath}/images/tienda/mas.png" alt=""></a></td>
                                    <td class="dcha">
                                        <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                        <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                        <f:formatNumber pattern="0.00">${lin.precio}</f:formatNumber>
                                        <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                    </td>  
                                    <td class="dcha">
                                        <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                        <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                        <f:formatNumber pattern="0.00">${lin.subtotal}</f:formatNumber>
                                        <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                    </td> 
                                </tr>
                                <c:set var="subtotal" value="${subtotal+lin.subtotal}" />
                                <c:if test="${!cfg.mostrarIVAIncluido}"><c:set var="totaliva" value="${totaliva+(lin.subtotal*(lin.iva/100))}" /></c:if>
                            </c:forEach>   
                            <c:if test="${!cfg.mostrarIVAIncluido}">
                                <tr class="total">
                                    <td class="dcha" colspan="8"><bean:message key="texto.totaliva" /></td>
                                    <td class="dcha">
                                        <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                        <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                        <f:formatNumber pattern="0.00">${totaliva}</f:formatNumber>
                                        <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                    </td> 
                                </tr>
                            </c:if>  
                            <c:if test="${cfg.mostrarIVAIncluido}">
                                <tr class="total">
                                    <td class="dcha" colspan="8"><bean:message key="texto.ivainc" /></td>
                                    <td class="dcha"></td> 
                                </tr>
                            </c:if>
                            <tr class="total">
                                <td class="dcha" colspan="8"><bean:message key="texto.total" /> (<bean:message key="texto.gastosnoinc" />)</td>
                                <td class="dcha">
                                    <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                    <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                    <f:formatNumber pattern="0.00">${subtotal+totaliva}</f:formatNumber>
                                    <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                </td> 
                            </tr>
                        </table>
                    </div>
                    <p class="links"><a href="${pageContext.request.contextPath}/pedido/confirmar/paso-1/"><bean:message key="enlace.confirmar" /></a></p>
                </div>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.otrosdestacados" /></span></p>
                    <div class="entrada">
                        <listado:MostrarDestacadosHandler id="slider" coleccion="destacados" />
                    </div>
                    <p class="links"></p>
                </div>   
            </div>
        </div>
        <ctrl:pie />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easySlider1.7.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){	
                $("#slider").easySlider({
                    continuous:	true, 
                    controlsBefore:'<p id="controls">',
                    controlsAfter:'</p>',		
                    prevText:'',
                    nextText:'',
                    prevId: 'prevBtn',
                    nextId: 'nextBtn'                  
                });
            });
        </script>
    </body>
</html>