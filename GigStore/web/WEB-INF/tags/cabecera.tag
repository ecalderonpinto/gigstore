<%-- 
    Document   : cabecera
    Created on : 06/03/2012, 17:27:25
    Author     : Manel Márquez Bonilla
    Descripcion: Cabecera de la aplicacion
--%>

<%@tag description="Cabecera común para todas las páginas" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<link rel="icon" href="${pageContext.request.contextPath}/images/tienda/favicon.ico" type="image/png" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/tienda/favicon.ico" type="image/png" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gigStore.css" /> <style type="text/css"></style>
<link href='http://fonts.googleapis.com/css?family=Sofia' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.min.js"></script>

<div id="cabecera">  
    <div id="logo">
        <p id="titulo"><a href="${pageContext.request.contextPath}/" >${txt.tituloTienda}</a></p>
        <p id="subtitulo">${txt.subtituloTienda}</p>
    </div>
    <c:if test="${usrSesion!=null}" > 
        <div id="bienvenida">        
            <span>${txt.textoBienvenidaUsr}</span>&nbsp;<span>${usrSesion.nombre} ${usrSesion.apellidos}</span>
            <a href="${pageContext.request.contextPath}/usuario/salir.do?metodo=salir" ><bean:message key="enlace.salir" /></a>
        </div>
    </c:if>
    <div id="enlaces">
        <ul>
            <li><a href="${pageContext.request.contextPath}/" ><bean:message key="enlace.home" /></a></li>
            <li><a href="${pageContext.request.contextPath}/info/conocenos/" ><bean:message key="enlace.conocenos" /></a></li>
            <li><a href="${pageContext.request.contextPath}/info/comprar/" ><bean:message key="enlace.comprar" /></a></li>
            <li><a href="${pageContext.request.contextPath}/info/contactar/" ><bean:message key="enlace.contacto" /></a></li> 
            <c:if test="${usrSesion==null}" >
                <li><a href="${pageContext.request.contextPath}/usuario/acceder/" ><bean:message key="enlace.acceso" /></a></li> 
            </c:if>
            <c:if test="${usrSesion.rol=='usuario'}" >
                <li><a href="${pageContext.request.contextPath}/usuario/datos-personales/" ><bean:message key="enlace.sucuenta" /></a></li> 
            </c:if>
            <c:if test="${usrSesion.rol=='administrador'}" >
                <li><a href="${pageContext.request.contextPath}/administrar/" ><bean:message key="enlace.administrar" /></a></li> 
            </c:if> 
        </ul>
    </div>  
</div> 