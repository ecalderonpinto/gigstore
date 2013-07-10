<%-- 
    Document   : admin_est_detalle
    Created on : 05/12/2012, 18:53:46
    Author     : Manel Márquez Bonilla
    Descripcion: Detalle de estadísticas de la web
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="ruta.est_detalle" /></title>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/estadisticas.do?metodo=mostrar_listado" ><bean:message key="ruta.admin_estadisticas" /></a> &gt </li>
                        <li><bean:message key="ruta.est_detalle" /></li>
                    </ul>
                </div>
                <h1><bean:message key="ruta.est_detalle" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.est_detalle" /></span></p>
                    <div class="entrada">
                        <form method="post" action="${pageContext.request.contextPath}/administrar/estadisticas/detalle.do" >
                            <div class="styled-select">
                                <select name="mes">
                                    <c:forEach var="f" items="${fechas}">
                                        <option value="${f}">${f}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="hidden" name="metodo" value="${met}" />
                            <input type="submit" class="boton" value="<bean:message key="boton.mostrar" />" />
                        </form>
                        <img src="${pageContext.request.contextPath}/chart" alt="<bean:message key="texto.estadisticas" />" title="<bean:message key="texto.estadisticas" />" />
                    </div>
                    <p class="links"></p>
                </div>
            </div>
        </div>        
        <ctrl:pie />
    </body>
</html>