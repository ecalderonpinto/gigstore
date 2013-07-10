<%-- 
    Document   : confirmacion_pedido
    Created on : 06/03/2012, 16:52:40
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <meta name="robots" content="noindex,nofollow">   
        <title><bean:message key="titulo.confirma1" /></title>
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
                        <li><bean:message key="titulo.confirma1" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.confirma1" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"></span></p>
                    <div class="entrada">     
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/pedido/confirmar/paso2" acceptCharset="ISO-8859-1" >
                                <h3><bean:message key="texto.elijadirenv" />: </h3> 
                                <html:select property="direccionEnvio">
                                    <c:if test="${empty(usrSesion.direcciones)}"><html:option value="0"><bean:message key="texto.eligeuno" /></html:option></c:if>
                                    <c:forEach var="dir" items="${usrSesion.direcciones}">
                                        <html:option value="${dir.id}">${dir}</html:option>
                                    </c:forEach>
                                </html:select>
                                <br />
                                <c:if test="${cfg.usarDirFactura}">
                                    <p class="etiqueta"><bean:message key="texto.elijadirfact" /></p>
                                    <html:select property="direccionFactura">
                                        <c:if test="${empty(usrSesion.direcciones)}"><html:option value="0"><bean:message key="texto.eligeuno" /></html:option></c:if>
                                        <c:forEach var="dir" items="${usrSesion.direcciones}">
                                            <html:option value="${dir.id}">${dir}</html:option>
                                        </c:forEach>
                                    </html:select>
                                    <br />
                                </c:if>
                                <br />
                                <a class="alta" href="${pageContext.request.contextPath}/usuario/direcciones/editar.do?metodo=alta_inicio"><bean:message key="enlace.alta_direccion" /></a>
                                <br /><br /><br />
                                
                                <h3><bean:message key="texto.formapago" />: </h3> 
                                <c:if test="${cfg.activarTransferencia}">
                                    <html:radio property="formaPago" value="tr"><bean:message key="texto.transferencia" /> - 
                                        <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                        <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                        <f:formatNumber pattern="0.00">${cfg.precioTransferencia}</f:formatNumber>
                                        <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                    </html:radio><br />
                                </c:if>
                                <c:if test="${cfg.activarPayPal}">
                                    <html:radio property="formaPago" value="pp"><img src="https://www.paypal.com/es_XC/i/logo/PayPal_mark_37x23.gif" style="margin-right:7px;" /><bean:message key="texto.paypal_opt" /> - 
                                        <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                        <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                        <f:formatNumber pattern="0.00">${cfg.precioPayPal}</f:formatNumber>
                                        <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                    </html:radio><br />
                                </c:if>
                                <c:if test="${cfg.activarReembolso}">
                                    <html:radio property="formaPago" value="cr"><bean:message key="texto.creembolso" /> - 
                                        <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                        <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                        <f:formatNumber pattern="0.00">${cfg.precioReembolso}</f:formatNumber>
                                        <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                    </html:radio><br />
                                </c:if>
                                <c:if test="${cfg.activarEfectivo}">
                                    <html:radio property="formaPago" value="ef"><bean:message key="texto.efectivo" /> - 
                                        <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                        <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                        <f:formatNumber pattern="0.00">${cfg.precioEfectivo}</f:formatNumber>
                                        <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                    </html:radio><br />
                                </c:if>
                                <br />
                                
                                <h3><bean:message key="texto.tipoenvio" />: </h3> 
                                <c:forEach var="env" items="${envios}">
                                    <c:if test="${env.activo}">
                                        <html:radio property="stipoenv" value="${env.id}">
                                            ${env.tipo} - 
                                            <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                            <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                            <f:formatNumber pattern="0.00">${env.precio}</f:formatNumber>
                                            <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                             - ${env.descripcion}                                             
                                        </html:radio><br />
                                    </c:if>
                                </c:forEach>
                                <!--
                                <html:select property="stipoenv">
                                    <html:option value="0"><bean:message key="texto.eligeuno" /></html:option>
                                    <c:forEach var="env" items="${envios}">
                                        <html:option value="${env.id}">
                                            ${env.tipo} - 
                                            <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                            <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                            <f:formatNumber pattern="0.00">${env.precio}</f:formatNumber>
                                            <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                        </html:option>
                                    </c:forEach>
                                </html:select>
                                -->
                                <br />
                                
                                <html:hidden property="metodo" value="configurar_pedido" />
                                <html:submit styleClass="boton"><bean:message key="boton.continuar" /></html:submit>
                            </html:form>
                        </div>
                    </div>
                    
                </div>  
            </div>
        </div>
        <ctrl:pie />
    </body>
</html>