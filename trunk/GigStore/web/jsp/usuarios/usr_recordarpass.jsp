<%-- 
    Document   : usr_recordarpass
    Created on : 26/12/2012, 19:41:11
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario recordatorio de contraseña
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex,nofollow">
        <title><bean:message key="titulo.recordatorio" /></title>
    </head>
    <body>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li><bean:message key="titulo.recordatorio" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.recordatorio" /></h1> 
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.recordatorio" /></span></p>
                    <div class="entrada">
                        <p><bean:message key="texto.recordarpass" /></p><br />
                        <p id="errorlogin">${error_recordar}</p>
                        <form method="post" action="${pageContext.request.contextPath}/usuario/recordatorio/enviar/" >
                            <p class="etiqueta"><bean:message key="texto.email" /></p> <input type="text" class="cajatexto" name="email" /><br />
                            <input type="hidden" name="metodo" value="enviar_recordatorio" />
                            <input type="submit" value="<bean:message key="boton.enviar" />" class="boton" onclick="javascript:return confirmar('<bean:message key="texto.enviar_news" />')" /><input type="reset" class="boton" />
                        </form>
                    </div>
                    <p class="links"></p>
                </div> 
            </div>
        </div>
        <ctrl:pie />
    </body>
</html>

