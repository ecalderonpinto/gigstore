<%-- 
    Document   : menu_gestion
    Created on : 06/03/2012, 16:57:15
    Author     : Manel Márquez Bonilla
    Descripcion: Menu para la gestion de la tienda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex,nofollow">
        <title><bean:message key="titulo.administrar" /></title>
    </head>
    <body>
        <ctrl:cabecera />
        <ctrl:menu_admin />
        <div id="cuerpo">
            <div id="contenido_g" class="admin">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li><bean:message key="ruta.menu_admin" /></li>
                    </ul>
                </div>
                <h2><bean:message key="titulo.administrar" /></h2>  
                <div class="cuadro_menu">
                    <div class="entrada">
                        <h3><bean:message key="texto.accesos" /></h3>
                        <ul class="menu">
                            <div class="col">
                                <li>
                                    <div class="icono">
                                        <a class="productos" href="${pageContext.request.contextPath}/administrar/productos.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_productos" /></a>
                                    </div>
                                </li>
                                <li>
                                    <div class="icono">
                                        <a class="pedidos" href="${pageContext.request.contextPath}/administrar/pedidos.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_pedidos" /></a>
                                    </div>
                                </li>
                                <li>
                                    <div class="icono">
                                        <a class="usuarios" href="${pageContext.request.contextPath}/administrar/usuarios.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_usuarios" /></a>
                                    </div>
                                </li>
                            </div>
                            <div class="col col2">
                                <li>
                                    <div class="icono">
                                        <a class="estadisticas" href="${pageContext.request.contextPath}/administrar/estadisticas.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_estadisticas" /></a>
                                    </div>
                                </li>
                                <li>
                                    <div class="icono">
                                        <a class="listanews" href="${pageContext.request.contextPath}/administrar/listacorreo.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_news" /></a>
                                    </div>
                                </li>
                                <li>
                                    <div class="icono">
                                        <a class="config" href="${pageContext.request.contextPath}/administrar/tienda.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_tienda" /></a>
                                    </div>
                                </li>
                            </div>
                        </ul>  
                    </div>
                </div>
                <div class="cuadro_ped">
                    <div class="entrada">
                        <h3><bean:message key="texto.pedidos" /></h3>
                        <table class="minitabla">
                            <tr>
                                <th><bean:message key="texto.fecha" /></th><th><bean:message key="texto.user" /></th><th><bean:message key="texto.estado" /></th>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach var="p" items="${pednuevos}">
                                <tr <c:if test="${i%2==0}">class="par"</c:if> >
                                    <td>
                                        <p><f:formatDate value="${p.fecha}" pattern="dd MMMM yyyy HH:mm:ss" ></f:formatDate> </p>
                                        <p>
                                            <bean:message key="texto.total" />: <c:if test="${cfg.moneda=='USD'}">&#36;</c:if><c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                            <f:formatNumber pattern="#.##" value="${p.total}"></f:formatNumber><c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>                                             
                                        </p>
                                    </td>
                                    <td>${p.usuario.nombre} ${p.usuario.apellidos}</td>
                                    <td>${p.estado.estado}</td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="cuadro_resumen">
                    <div class="entrada">
                        <h3><bean:message key="texto.resumen" /></h3>
                        <div class="lista1">
                            <h4><bean:message key="texto.contenido" /></h4>
                            <p><bean:message key="texto.productos" />: <span class="valor">${nprods}</span></p>
                            <p><bean:message key="texto.enstock" />: <span class="valor">${ndisp}</span></p>
                            <p><bean:message key="texto.categorias" />: <span class="valor">${ncats}</span></p>
                            <p><bean:message key="texto.subcategorias" />: <span class="valor">${nsubc}</span></p>
                            <p><bean:message key="texto.tags" />: <span class="valor">${ntags}</span></p>
                        </div>
                        <div class="lista2">
                            <h4><bean:message key="texto.pedidos" /></h4>
                            <p>${pedest1}: <span class="valor">${npedest1}</span></p>
                            <p>${pedest2}: <span class="valor">${npedest2}</span></p>
                            <p>${pedest3}: <span class="valor">${npedest3}</span></p>
                            <p>${pedest4}: <span class="valor">${npedest4}</span></p>
                            <p>${pedest5}: <span class="valor">${npedest5}</span></p>
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                </div>
                <div class="cuadro_stock">
                    <div class="entrada">
                        <h3><bean:message key="texto.stockbajo" /></h3>
                        <table class="minitabla">
                            <tr>
                                <th><bean:message key="texto.productos" /></th><th><bean:message key="texto.est_referencia" /></th><th><bean:message key="texto.stock" /></th>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach var="p" items="${stockbajo}">
                                <tr <c:if test="${i%2==0}">class="par"</c:if> >
                                    <td>${p.nombre}</td>
                                    <td>${p.referencia}</td>
                                    <td>${p.stock}</td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                        </table>
                    </div>
                </div>                
                <div class="cuadro_est">
                    <div class="entrada">
                        <h3><bean:message key="texto.estadisticas" /></h3>
                        <a class="estadisticas" href="${pageContext.request.contextPath}/administrar/estadisticas.do?metodo=mostrar_listado" ><img src="${pageContext.request.contextPath}/chart?tipo=ventas&ancho=285&alto=200" alt="<bean:message key="texto.estadisticas" />" title="<bean:message key="texto.estadisticas" />" /></a>
                        <img src="${pageContext.request.contextPath}/chart" />
                    </div>
                </div>
            </div>
        </div>        
        <ctrl:pie />
    </body>
</html>
