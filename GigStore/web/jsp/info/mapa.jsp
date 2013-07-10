<%-- 
    Document   : mapa
    Created on : 20/03/2013, 17:11:58
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="cats" uri="/WEB-INF/tlds/gigStore.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="titulo.mapa" /></title>
    </head>
    <body>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li><bean:message key="titulo.mapa" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.mapa" /></h1>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"></span></p>
                    <div class="entrada">                       
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/" ><bean:message key="enlace.home" /></a></li>
                            <ul>
                                <li><bean:message key="texto.recomendaciones" /></li>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/recomendaciones/destacados/" ><bean:message key="enlace.destacados" /></a></li>
                                    <li><a href="${pageContext.request.contextPath}/recomendaciones/vendidos/" /><bean:message key="enlace.vendidos" /></a></li>
                                    <li><a href="${pageContext.request.contextPath}/recomendaciones/nuevos/" ><bean:message key="enlace.nuevos" /></a></li>
                                </ul>
                                <li><a href="${pageContext.request.contextPath}/categorias/" ><bean:message key="titulo.lateral" /></a></li> 
                                <ul><cats:LateralHandler /></ul>
                                <li><bean:message key="texto.op_usuario" /></li>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/usuario/acceder/" ><bean:message key="enlace.acceso" /></a></li> 
                                    <li><a href="${pageContext.request.contextPath}/usuario/datos-personales/" ><bean:message key="enlace.sucuenta" /></a></li> 
                                </ul>
                                <li><bean:message key="titulo.informacion" /></li>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/info/conocenos/" ><bean:message key="enlace.conocenos" /></a></li>
                                    <li><a href="${pageContext.request.contextPath}/info/comprar/" ><bean:message key="enlace.comprar" /></a></li>
                                    <li><a href="${pageContext.request.contextPath}/info/contactar/" ><bean:message key="enlace.contacto" /></a></li> 
                                </ul>
                                <li><bean:message key="titulo.legal" /></li>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/info/privacidad-de-datos/" ><bean:message key="enlace.privacidad" /></a></li>
                                    <li><a href="${pageContext.request.contextPath}/info/condiciones-de-venta/" ><bean:message key="enlace.condiciones" /></a></li>
                                    <li><a href="${pageContext.request.contextPath}/info/costes-del-envio/" ><bean:message key="enlace.costes" /></a></li>
                                </ul>
                            </ul>
                        </ul>
                    </div>
                    <p class="links"></p>
                </div>
            </div>
        </div>
        <ctrl:pie />
    </body>
</html>
