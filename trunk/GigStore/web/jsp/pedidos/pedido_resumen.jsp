<%-- 
    Document   : pedido_confirmacion2
    Created on : 04/10/2012, 13:18:51
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
        <meta name="robots" content="noindex,nofollow">  
        <title><bean:message key="titulo.detalle_ped" /></title>
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
                <h1><bean:message key="titulo.confirma2" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"></span></p>
                    <div class="entrada">
                        <h3><bean:message key="texto.datosusr" />: </h3>
                        <div id="detalle">
                            <table class="resumen">
                                <tr><td class="tit"><bean:message key="texto.nif" />:</td><td class="valor">${usrSesion.nif}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.nombre" />:</td><td class="valor">${usrSesion.nombre} ${usrSesion.apellidos}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.telf" />:</td><td class="valor">${usrSesion.telefono}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.email" />:</td><td class="valor">${usrSesion.email}</td></tr>
                            </table>                    
                        </div>
                        <br />  
                        <h3><bean:message key="texto.datosped" />: </h3>
                        <div id="detalle">
                            <table class="resumen">
                                <tr><td class="tit"><bean:message key="texto.elijadirenv" />:</td><td class="valor">${pedido.direccionEnvio}</td></tr>
                                <c:if test="${cfg.usarDirFactura}">
                                    <tr><td class="tit"><bean:message key="texto.elijadirfact" />:</td><td class="valor">${pedido.direccionFactura}</td></tr>
                                </c:if>         
                                <c:if test="${pedido.formaPago=='pp'}">
                                    <tr><td class="tit"><bean:message key="texto.formapago" />:</td><td class="valor"><bean:message key="texto.paypal" /></td></tr>
                                    <tr><td class="tit"></td><td class="valor"><bean:message key="texto.paypal_exp" /></td></tr>
                                </c:if>         
                                <c:if test="${pedido.formaPago=='tr'}">
                                    <tr><td class="tit"><bean:message key="texto.formapago" />:</td><td class="valor"><bean:message key="texto.transferencia" /></td></tr>
                                    <tr><td class="tit"></td><td class="valor"><bean:message key="texto.transferencia_exp" arg0="${cfg.numCuenta}" /></td></tr>
                                </c:if>         
                                <c:if test="${pedido.formaPago=='cr'}">
                                    <tr><td class="tit"><bean:message key="texto.formapago" />:</td><td class="valor"><bean:message key="texto.creembolso" /></td></tr>
                                    <tr><td class="tit"></td><td class="valor"><bean:message key="texto.creembolso_exp" /></td></tr>
                                </c:if>       
                                <c:if test="${pedido.formaPago=='ef'}">
                                    <tr><td class="tit"><bean:message key="texto.formapago" />:</td><td class="valor"><bean:message key="texto.efectivo" /></td></tr>
                                    <tr><td class="tit"></td><td class="valor"><bean:message key="texto.efectivo_exp" /></td></tr>
                                </c:if>
                            </table>                    
                        </div>
                        <br />
                        <table id="carro">
                            <tr>
                                <th><bean:message key="texto.referencia" /></th>
                                <th><bean:message key="texto.nombre" /></th>
                                <th></th>
                                <th><bean:message key="texto.unidades" /></th>
                                <th><bean:message key="texto.precio" /></th>
                                <th><bean:message key="texto.subtotal" /></th>
                            </tr>
                            <c:set var="subtotal" value="${0}" />
                            <c:forEach var="lin" items="${carrito}">
                                <tr class="linea">
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
                                    <td>${lin.cantidad}</td>
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
                            <tr class="total">
                                <td class="dcha" colspan="5"><bean:message key="texto.subtotal" /></td>
                                <td class="dcha">
                                    <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                    <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                    <f:formatNumber pattern="0.00">${subtotal}</f:formatNumber>
                                    <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                </td> 
                            </tr>  
                            <c:if test="${!cfg.mostrarIVAIncluido}">
                                <tr class="total">
                                    <td class="dcha" colspan="5"><bean:message key="texto.totaliva" /></td>
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
                                    <td class="dcha" colspan="5"><bean:message key="texto.ivainc" /></td>
                                    <td class="dcha"></td> 
                                </tr>
                            </c:if>
                            <tr class="total">
                                <td class="dcha" colspan="5"><bean:message key="texto.tipoenvio" />: ${pedido.tipoenv}</td>
                                <td class="dcha">
                                    <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                    <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                    <f:formatNumber pattern="0.00">${pedido.tipoenv.precio}</f:formatNumber>
                                    <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                </td> 
                            </tr>
                            <tr class="total">
                                <td class="dcha" colspan="5"><bean:message key="texto.costesfp" />: </td>
                                <td class="dcha">
                                    <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                    <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                    <f:formatNumber pattern="0.00">${pedido.precioFP}</f:formatNumber>
                                    <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                </td> 
                            </tr>
                            <tr class="total">
                                <td class="dcha" colspan="5"><bean:message key="texto.total" /></td>
                                <td class="dcha">
                                    <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                    <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                    <f:formatNumber pattern="0.00">${pedido.total}</f:formatNumber>
                                    <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                </td> 
                            </tr>
                        </table>
                    </div>
                    <p class="links">
                        <a href="${pageContext.request.contextPath}/pedido/confirmar/paso-3/">        
                            <c:if test="${pedido.formaPago=='pp'}">
                                <img src="https://www.paypal.com/es_XC/i/btn/btn_xpressCheckout.gif" />
                            </c:if>
                            <c:if test="${pedido.formaPago!='pp'}">
                                <bean:message key="enlace.confirmar" />
                            </c:if>   
                        </a>
                    </p>
                </div>
            </div>
        </div>
        <ctrl:pie />
    </body>
</html>
