<%-- 
    Document   : admin_ped_form
    Created on : 15/07/2012, 13:05:22
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario alta/modificacion de los pedidos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <bean:message key="titulo.admin_pedido_datos" />
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
                        <li><a href="${pageContext.request.contextPath}/administrar/pedidos.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_ped" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/pedidos/detalle.do?metodo=mostrar_detalle&id=${id}" ><bean:message key="ruta.detalle_ped" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_ped" /></li>
                    </ul>
                </div>
                <h1>
                    <bean:message key="titulo.admin_pedido_datos" />
                </h1>    
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.editar_ped" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/pedidos/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.fechapago" /></p>
                                    <p class="etiqueta"><bean:message key="texto.formapago" /></p>
                                    <p class="etiqueta"><bean:message key="texto.tipoenvio" /></p>
                                    <p class="etiqueta"><bean:message key="texto.estado" /></p>
                                    <p class="etiqueta"><bean:message key="texto.elijadirenv" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="sfechaPago" />
                                    <div class="styled-select">
                                        <html:select property="formaPago" value="${formaPagoSel}">
                                            <html:option value="tr"><bean:message key="texto.transferencia" /> - 
                                                <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                                <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                                <f:formatNumber pattern="0.00">${cfg.precioTransferencia}</f:formatNumber>
                                                <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                            </html:option>
                                            <html:option value="pp"><bean:message key="texto.paypal" /> - 
                                                <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                                <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                                <f:formatNumber pattern="0.00">${cfg.precioPayPal}</f:formatNumber>
                                                <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                            </html:option>
                                            <html:option value="cr"><bean:message key="texto.creembolso" /> - 
                                                <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                                <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                                <f:formatNumber pattern="0.00">${cfg.precioReembolso}</f:formatNumber>
                                                <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                            </html:option>
                                            <html:option value="ef"><bean:message key="texto.efectivo" /> - 
                                                <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                                <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                                <f:formatNumber pattern="0.00">${cfg.precioEfectivo}</f:formatNumber>
                                                <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                            </html:option>
                                        </html:select>
                                    </div>
                                    <div class="styled-select">
                                        <html:select property="stipoenv" value="${envioSel}">
                                            <c:forEach var="env" items="${envios}">
                                                <html:option value="${env.id}">
                                                    ${env.tipo} - 
                                                    <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                                    <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if><f:formatNumber pattern="0.00">${env.precio}</f:formatNumber>
                                                    <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                                </html:option>
                                            </c:forEach>
                                        </html:select>
                                    </div>
                                    <div class="styled-select">
                                        <html:select property="sestado" value="${estadoSel}">
                                            <html:option value="0"><bean:message key="texto.eligeuno" /></html:option>
                                            <c:forEach var="e" items="${estados}">
                                                <html:option value="${e.id}">${e.estado}</html:option>
                                            </c:forEach>
                                        </html:select>  
                                    </div>   
                                    <html:textarea property="dirEnvio" />
                                </div>
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.elijadirfact" /></p>
                                </div>
                                <div class="valores">
                                    <html:textarea property="dirFactura" />
                                </div>
                                <div id="botones">
                                    <html:hidden property="metodo" value="editar_ejecutar" />
                                    <html:submit styleClass="boton"><bean:message key="boton.modificar" /></html:submit>
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