<%-- 
    Document   : admin_ped_detalle
    Created on : 15/07/2012, 13:04:44
    Author     : Manel Márquez Bonilla
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
                        <li><a href="${pageContext.request.contextPath}/administrar/pedidos.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_ped" /></a> &gt </li>
                        <li><bean:message key="ruta.detalle_ped" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.admin_pedido_datos" /></h1>        
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.detalle_ped" /></span></p>
                    <div class="entrada">
                        <div id="detalle">
                            <table class="resumen">
                                <tr><td class="tit"><bean:message key="texto.pedidoid" />:</td><td class="valor">${pedido.id}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.fecha" />:</td><td class="valor"><f:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${pedido.fecha}"></f:formatDate></td></tr>
                                <tr><td class="tit"><bean:message key="texto.estado" />:</td><td class="valor">${pedido.estado.estado}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.tipoenvio" />:</td><td class="valor">${pedido.tipoenv.tipo}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.formapago" />:</td><td class="valor">   
                                <c:if test="${pedido.formaPago=='pp'}">
                                    <bean:message key="texto.paypal" />
                                </c:if>         
                                <c:if test="${pedido.formaPago=='tr'}">
                                    <bean:message key="texto.transferencia" />
                                </c:if>         
                                <c:if test="${pedido.formaPago=='cr'}">
                                    <bean:message key="texto.creembolso" />
                                </c:if>         
                                <c:if test="${pedido.formaPago=='ef'}">
                                    <bean:message key="texto.efectivo" />
                                </c:if></td></tr>
                                <tr><td class="tit"><bean:message key="texto.user" />:</td><td class="valor">${pedido.usuario.usuario}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.elijadirenv" />:</td><td class="valor">${pedido.direccionEnvio}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.elijadirfact" />:</td><td class="valor">${pedido.direccionFactura}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.subtotal" />:</td><td class="valor"><c:if test="${cfg.moneda=='USD'}">&#36;</c:if><c:if test="${cfg.moneda=='GBP'}">&#163;</c:if><f:formatNumber pattern="#.##" value="${subtotal}"></f:formatNumber><c:if test="${cfg.moneda=='EUR'}">&euro;</c:if></td></tr>
                                <tr><td class="tit"><bean:message key="texto.tipoenvio" />:</td><td class="valor"><c:if test="${cfg.moneda=='USD'}">&#36;</c:if><c:if test="${cfg.moneda=='GBP'}">&#163;</c:if><f:formatNumber pattern="#.##" value="${pedido.envio}"></f:formatNumber><c:if test="${cfg.moneda=='EUR'}">&euro;</c:if></td></tr>
                                <tr><td class="tit"><bean:message key="texto.costesfp" />:</td><td class="valor"><c:if test="${cfg.moneda=='USD'}">&#36;</c:if><c:if test="${cfg.moneda=='GBP'}">&#163;</c:if><f:formatNumber pattern="#.##" value="${pedido.precioFP}"></f:formatNumber><c:if test="${cfg.moneda=='EUR'}">&euro;</c:if></td></tr>
                                <tr><td class="tit"><bean:message key="texto.total" />:</td><td class="valor"><c:if test="${cfg.moneda=='USD'}">&#36;</c:if><c:if test="${cfg.moneda=='GBP'}">&#163;</c:if><f:formatNumber pattern="#.##" value="${pedido.total}"></f:formatNumber><c:if test="${cfg.moneda=='EUR'}">&euro;</c:if></td></tr>
                            </table>                    
                        </div>
                        <br />    
                        <form name="listadoLineasPed" action="${pageContext.request.contextPath}/administrar/pedidos/detalle.do">
                            <input type="hidden" name="metodo" value="mostrar_detalle">
                            <input type="hidden" name="id" value="${pedido.id}">
                            ${listado_lineas}
                        </form>
                    </div>
                    <p class="links"><a class="editar" href="${pageContext.request.contextPath}/administrar/pedidos/editar.do?metodo=editar_inicio&id=${pedido.id}"><bean:message key="enlace.editar_pedido" /></a> <a class="editar" href="${pageContext.request.contextPath}/administrar/pedidos/lineas/editar.do?metodo=alta_inicio&id=${pedido.id}"><bean:message key="enlace.agregar_prod_ped" /></a></p>
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
                location.href = '${pageContext.request.contextPath}/administrar/pedidos/detalle.do?metodo=mostrar_detalle&id=${pedido.id}&' + parameterString;
            }
        </script>
    </body>
</html>

