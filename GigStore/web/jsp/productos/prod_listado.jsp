<%-- 
    Document   : listado_productos
    Created on : 06/03/2012, 16:47:33
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="listado" uri="/WEB-INF/tlds/gigStore.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="${desc.mtCategorias}">
        <meta name="description" content="${desc.mdCategorias}">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easySlider1.7.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){	
                $("#slider_listado").easySlider({
                    numeric: true
                });
            });
        </script>
        <title>
            <c:if test="${accion=='destacados'}">
                <bean:message key="titulo.destacados" />
            </c:if>
            <c:if test="${accion=='vendidos'}">
                <bean:message key="titulo.vendidos" />
            </c:if>
            <c:if test="${accion=='nuevos'}">
                <bean:message key="titulo.nuevos" />
            </c:if>
            <c:if test="${accion=='buscar'}">
                <bean:message key="titulo.buscar" arg0="${busqueda}" />
            </c:if>
            <c:if test="${accion=='categorias'}">
                <bean:message key="titulo.categorias" arg0="${desc.nombre}" />
            </c:if>
            <c:if test="${accion=='listacats'}">
                <bean:message key="titulo.listacats" />
            </c:if>
        </title>
    </head>
    <body>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li>
                            <c:if test="${accion=='destacados'}">
                                <bean:message key="titulo.destacados" />
                            </c:if>
                            <c:if test="${accion=='vendidos'}">
                                <bean:message key="titulo.vendidos" />
                            </c:if>
                            <c:if test="${accion=='nuevos'}">
                                <bean:message key="titulo.nuevos" />
                            </c:if>
                            <c:if test="${accion=='buscar'}">
                                <bean:message key="titulo.buscar" arg0="${busqueda}" />
                            </c:if>
                            <c:if test="${accion=='categorias'}">
                                <bean:message key="titulo.categorias" arg0="${desc.nombre}" />
                            </c:if>
                            <c:if test="${accion=='listacats'}">
                                <bean:message key="titulo.listacats" />
                            </c:if>
                        </li>
                    </ul>
                </div>
                <h1>
                    <c:if test="${accion=='destacados'}">
                        <bean:message key="titulo.destacados" />
                    </c:if>
                    <c:if test="${accion=='vendidos'}">
                        <bean:message key="titulo.vendidos" />
                    </c:if>
                    <c:if test="${accion=='nuevos'}">
                        <bean:message key="titulo.nuevos" />
                    </c:if>
                    <c:if test="${accion=='buscar'}">
                        <bean:message key="titulo.buscar" arg0="${busqueda}" />
                    </c:if>
                    <c:if test="${accion=='categorias'}">
                        <bean:message key="titulo.categorias" arg0="${desc.nombre}" />
                    </c:if>
                    <c:if test="${accion=='listacats'}">
                        <bean:message key="titulo.listacats" />
                    </c:if>
                </h1>
                    
                <div class="cuadro">
                    <p class="meta">
                        <span class="izda">
                        </span>
                        <span class="dcha">
                        </span>
                    </p>
                    <div class="entrada"> 
                        <c:if test="${accion=='categorias'}">
                            <div class="desc"><p>${desc.descripcion}</p></div> 
                        </c:if>
                        <listado:ListadoProdsHandler id="slider_listado" coleccion="${accion}" />
                    </div>
                    <p class="links"></p>
                </div>
            </div>
        </div>
        <ctrl:pie />
    </body>
</html>