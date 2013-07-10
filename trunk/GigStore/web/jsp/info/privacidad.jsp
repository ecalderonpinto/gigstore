<%-- 
    Document   : privacidad
    Created on : 10/01/2013, 19:05:57
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="titulo.privacidad" /></title>
    </head>
    <body>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li><bean:message key="titulo.privacidad" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.privacidad" /></h1>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"></span></p>
                    <div class="entrada">                        
                        <span>${txt.secciones.textoPrivacidad}</span>
                    </div>
                    <p class="links"></p>
                </div>
            </div>
        </div>
        <ctrl:pie />
    </body>
</html>
