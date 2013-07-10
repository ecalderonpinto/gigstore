<%-- 
    Document   : admin_tienda_formapago
    Created on : 18/06/2013, 10:26:23
    Author     : Manel Márquez Bonilla
    Descripcion: Configuración general de la tienda on-line
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib uri="/WEB-INF/tlds/jmesa.tld" prefix="jmesa" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/jmesa.css" rel="stylesheet" type="text/css">
        <title><bean:message key="titulo.admin_tienda" /></title>
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
                        <li><bean:message key="ruta.admin_tienda_pago" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.admin_tienda_pago" /></h1>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.admin_tienda_pago" /></span></p>
                    <div class="entrada">
                        <div id="detalle">
                            <table class="resumen">
                                <tr>
                                    <td class="tit"><bean:message key="texto.activarPayPal" />:</td>
                                    <td class="valor">
                                        <c:if test="${cfg.activarPayPal}">
                                            <bean:message key="texto.verdadero" />
                                        </c:if>
                                        <c:if test="${!cfg.activarPayPal}">
                                            <bean:message key="texto.falso" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr><td class="tit"><bean:message key="texto.precioPayPal" />:</td><td class="valor">${cfg.precioPayPal}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.correoPayPal" />:</td><td class="valor">${cfg.correoPayPal}</td></tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.activarReembolso" />:</td>
                                    <td class="valor">
                                        <c:if test="${cfg.activarReembolso}">
                                            <bean:message key="texto.verdadero" />
                                        </c:if>
                                        <c:if test="${!cfg.activarReembolso}">
                                            <bean:message key="texto.falso" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr><td class="tit"><bean:message key="texto.precioReembolso" />:</td><td class="valor">${cfg.precioReembolso}</td></tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.activarTransferencia" />:</td>
                                    <td class="valor">
                                        <c:if test="${cfg.activarTransferencia}">
                                            <bean:message key="texto.verdadero" />
                                        </c:if>
                                        <c:if test="${!cfg.activarTransferencia}">
                                            <bean:message key="texto.falso" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr><td class="tit"><bean:message key="texto.precioTransferencia" />:</td><td class="valor">${cfg.precioTransferencia}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.numCuenta" />:</td><td class="valor">${cfg.numCuenta}</td></tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.activarEfectivo" />:</td>
                                    <td class="valor">
                                        <c:if test="${cfg.activarEfectivo}">
                                            <bean:message key="texto.verdadero" />
                                        </c:if>
                                        <c:if test="${!cfg.activarEfectivo}">
                                            <bean:message key="texto.falso" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr><td class="tit"><bean:message key="texto.precioEfectivo" />:</td><td class="valor">${cfg.precioEfectivo}</td></tr>
                            </table>                    
                        </div>
                    </div>
                    <p class="links"><a class="editar" href="${pageContext.request.contextPath}/administrar/tienda/formapago/editar.do?metodo=editar_inicio&id=1"><bean:message key="enlace.editar_formapago" /></a></p>
                </div>
            </div>             
        </div>
        <ctrl:pie />
    </body>
</html>
