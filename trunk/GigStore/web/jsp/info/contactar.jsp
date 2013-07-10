<%-- 
    Document   : contactar
    Created on : 02/05/2012, 12:27:43
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="titulo.contactar" /></title>
    </head>
    <body>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li><bean:message key="titulo.contactar" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.contactar" /></h1>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"></span></p>
                    <div class="entrada">                        
                        <span>${txt.secciones.textoContactar}</span>
                    </div>
                    <p class="links"></p>
                </div>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.contactar" /></span></p>
                    <div class="entrada">                        
                        <p id="erroremail">${error_msg}</p>
                        <form method="post" action="${pageContext.request.contextPath}/usuario/contactar/enviar.do" >
                            <p class="etiqueta"><bean:message key="texto.nombre" /></p> <input type="text" class="cajatexto" name="nombre" value="${nombre_c}" /><br />
                            <p class="etiqueta"><bean:message key="texto.email" /></p> <input type="text" class="cajatexto" name="email" value="${email_c}" /><br />
                            <p class="etiqueta"><bean:message key="texto.asunto" /></p> <input type="text" class="cajatexto" name="asunto" value="${asunto_c}" /><br />
                            <p class="etiqueta"><bean:message key="texto.mensaje" /></p> <textarea name="mensaje" rows="10" cols="80">${msn_c}</textarea><br />        
                            <p class="etiqueta"><bean:message key="texto.codigo" /></p> <img class="captcha" alt="Captcha" src="${pageContext.request.contextPath}/CaptchaServlet?num=${num}" /> <br />
                            <input type="text" class="cajatexto" name="codigo" /> <br />
                            <input type="hidden" name="metodo" value="contactar_enviar" />
                            <input type="submit" value="<bean:message key="boton.enviar" />" class="boton" />
                        </form>
                    </div>
                    <p class="links"></p>
                </div>
            </div>
        </div>
        <ctrl:pie />
    </body>
</html>
