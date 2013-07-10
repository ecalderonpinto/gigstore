<%-- 
    Document   : login
    Created on : 06/03/2012, 16:48:41
    Author     : Manel Márquez Bonilla
    Descripcion: Pagina de acceso usuarios
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex,nofollow">
        <title><bean:message key="titulo.login" /></title>
    </head>
    <body>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <h1><bean:message key="titulo.login" /></h1>  
                <div id="partido">
                    <div class="cuadro">
                        <h2 class="titulo"><bean:message key="titulo.alta_usr" /></h2>
                        <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="texto.alta_usuario" /></span></p>
                        <div class="entrada"></div>
                        <p class="links"><a href="${pageContext.request.contextPath}/usuario/alta/" /><bean:message key="enlace.alta_usuario" /></a></p>
                    </div> 
                    <div class="cuadro">
                        <h2 class="titulo"><bean:message key="titulo.login_usr" /></h2>
                        <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="texto.login_usr" /></span></p>
                        <div class="entrada">
                            <div id="acceso">
                                <div id="formacceso">
                                    <c:if test="${error!=null}" >
                                        <p id="errorlogin">${error}</p>
                                    </c:if>
                                    <form method="post" action="${pageContext.request.contextPath}/usuario/login.do" >
                                        <p class="etiqueta"><bean:message key="texto.user" /></p> <input type="text" class="cajatexto" name="user" /><br />
                                        <p class="etiqueta"><bean:message key="texto.pass" /></p> <input type="password" class="cajatexto" name="pass" /><br />
                                        <input type="hidden" name="metodo" value="acceso_login" />
                                        <input type="submit" value="<bean:message key="boton.acceso" />" class="boton" /><input type="reset" class="boton" />
                                    </form>
                                </div>
                            </div>
                        </div>
                        <p class="links"><a href="${pageContext.request.contextPath}/usuario/recordatorio/"><bean:message key="enlace.recordar" /></a></p>
                    </div>                   
                </div>
            </div>
        </div>
        <ctrl:pie />
    </body>
</html>
