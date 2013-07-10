<%-- 
    Document   : admin_est_listado
    Created on : 05/12/2012, 18:53:05
    Author     : Manel Márquez Bonilla
    Descripcion: Listado de tipos de estadísticas de la web
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="titulo.administrar" /></title>
    </head>
    <body>
        <ctrl:cabecera />
        <ctrl:menu_admin />
        <div id="cuerpo">
            <div id="contenido_c">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/" ><bean:message key="ruta.menu_admin" /></a> &gt </li>
                        <li><bean:message key="ruta.admin_estadisticas" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.estadisticas" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.admin_estadisticas" /></span></p>
                    <div class="entrada">
                        <div class="largo">
                            <a class="estadisticas" href="${pageContext.request.contextPath}/administrar/estadisticas/detalle.do?metodo=est_nvisitas" ><img class="min" src="${pageContext.request.contextPath}/chart?tipo=visitas&ancho=680&alto=200" alt="<bean:message key="texto.estadisticas" />" title="<bean:message key="texto.estadisticas" />" /></a>
                        </div>
                        <div class="largo">
                            <h3><bean:message key="texto.resumen_est" /></h3>
                            <div class="bloque_est">
                                <div class="dato_est"><img src="${pageContext.request.contextPath}/images/tienda/chart_line.png" /> <strong>${totalVisitas}</strong> <bean:message key="texto.visitas_totales" /></div>
                                <div class="dato_est"><img src="${pageContext.request.contextPath}/images/tienda/chart_line.png" /> <strong><f:formatNumber pattern="#.##" value="${paginasVisita}"></f:formatNumber></strong> <bean:message key="texto.paginas_visita" /></div>
                                <div class="dato_est"><img src="${pageContext.request.contextPath}/images/tienda/chart_line.png" /> <strong><f:formatNumber pattern="#.##" value="${visitasVenta}"></f:formatNumber></strong> <bean:message key="texto.ventas_visita" /></div>
                                <div class="dato_est"><img src="${pageContext.request.contextPath}/images/tienda/chart_line.png" /> <strong><f:formatNumber pattern="#.##" value="${abandonos}"></f:formatNumber></strong><bean:message key="texto.abandonos" /></div>
                            </div>
                            <div class="bloque_est">
                                <div class="dato_est dc"><strong>${totalPeticiones}</strong> <bean:message key="texto.peticiones" /> <img src="${pageContext.request.contextPath}/images/tienda/chart_line.png" /></div>
                                <div class="dato_est dc"><strong>${duracion}</strong> <bean:message key="texto.duracion" /> <img src="${pageContext.request.contextPath}/images/tienda/chart_line.png" /></div>
                                <div class="dato_est dc"><strong><f:formatNumber pattern="#.##" value="${nuevasVisitas}"></f:formatNumber></strong><bean:message key="texto.visitas_nuevas" /> <img src="${pageContext.request.contextPath}/images/tienda/chart_line.png" /></div>
                                <div class="dato_est dc"><strong><f:formatNumber pattern="#.##" value="${nuevasVentas}"></f:formatNumber></strong><bean:message key="texto.ventas_nuevas" /> <img src="${pageContext.request.contextPath}/images/tienda/chart_line.png" /></div>
                            </div>
                        </div>
                        <div class="corto iz">
                            <a class="estadisticas" href="${pageContext.request.contextPath}/administrar/estadisticas/detalle.do?metodo=est_nventas" ><img src="${pageContext.request.contextPath}/chart?tipo=ventas&ancho=285&alto=200" alt="<bean:message key="texto.estadisticas" />" title="<bean:message key="texto.estadisticas" />" /></a>
                        </div>
                        <div class="corto">
                            <a class="estadisticas" href="${pageContext.request.contextPath}/administrar/estadisticas/detalle.do?metodo=est_ventasdestino" ><img src="${pageContext.request.contextPath}/chart?tipo=ubicacion&ancho=285&alto=200" alt="<bean:message key="texto.estadisticas" />" title="<bean:message key="texto.estadisticas" />" /></a>
                        </div>
                        <div class="corto iz">
                            <a class="estadisticas" href="${pageContext.request.contextPath}/administrar/estadisticas/detalle.do?metodo=est_productos" ><img src="${pageContext.request.contextPath}/chart?tipo=origen&ancho=285&alto=200" alt="<bean:message key="texto.estadisticas" />" title="<bean:message key="texto.estadisticas" />" /></a>
                        </div>
                        <div class="corto">
                            <h3><bean:message key="texto.peticiones" /></h3>
                            <table class="minitabla">
                                <tr>
                                    <th>Url</th><th>&#160;#&#160;</th><th>&#160;%&#160;</th>
                                </tr>
                                <c:set var="i" value="1" />
                                <c:forEach var="u" items="${urlVisitas}">
                                    <tr <c:if test="${i%2==0}">class="par"</c:if> >
                                        <td class="url">${u[0]}</td>
                                        <td>${u[1]}</td>
                                        <td><f:formatNumber pattern="#" value="${u[2]}"></f:formatNumber>%</td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                    <p class="links"></p>
                </div>
            </div>
        </div>        
        <ctrl:pie />
    </body>
</html>