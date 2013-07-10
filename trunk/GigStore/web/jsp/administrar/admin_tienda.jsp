<%-- 
    Document   : admin_tienda
    Created on : 04/05/2012, 12:18:06
    Author     : Manel Márquez Bonilla
    Descripcion: Configuración general de la tienda on-line
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib uri="/WEB-INF/tlds/jmesa.tld" prefix="jmesa" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/jmesa.css" rel="stylesheet" type="text/css">
        <title><bean:message key="titulo.admin_tienda" /></title>
        <ctrl:script_confirmacion />
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
                        <li><bean:message key="ruta.admin_tienda" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.admin_tienda" /></h1>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.variables_tienda" /></span></p>
                    <div class="entrada">
                        <div id="detalle">
                            <table class="resumen">
                                <tr><td class="tit"><bean:message key="texto.nombreempresa" />:</td><td class="valor">${cfg.nombreEmpresa}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.direccionempresa" />:</td><td class="valor">${cfg.direccionEmpresa}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.telefonoempresa" />:</td><td class="valor">${cfg.telefonoEmpresa}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.emailsistema" />:</td><td class="valor">${cfg.emailSistema}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.idioma" />:</td><td class="valor">${cfg.idioma}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.nregs" />:</td><td class="valor">${cfg.nRegsPag}</td></tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.monedasistema" />:</td>
                                    <td class="valor">
                                        <c:if test="${cfg.moneda=='EUR'}">
                                            <bean:message key="texto.euro" />
                                        </c:if>
                                        <c:if test="${cfg.moneda=='GBP'}">
                                            <bean:message key="texto.libra" />
                                        </c:if>
                                        <c:if test="${cfg.moneda=='USD'}">
                                            <bean:message key="texto.dolar" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.ocultarProducto" />:</td>
                                    <td class="valor">
                                        <c:if test="${cfg.ocultarProducto}">
                                            <bean:message key="texto.verdadero" />
                                        </c:if>
                                        <c:if test="${!cfg.ocultarProducto}">
                                            <bean:message key="texto.falso" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.ocultarBotonCompra" />:</td>
                                    <td class="valor">
                                        <c:if test="${cfg.ocultarBotonCompra}">
                                            <bean:message key="texto.verdadero" />
                                        </c:if>
                                        <c:if test="${!cfg.ocultarBotonCompra}">
                                            <bean:message key="texto.falso" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr><td class="tit"><bean:message key="texto.stockMin" />:</td><td class="valor">${cfg.stockMin}</td></tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.mostrarIVAIncluido" />:</td>
                                    <td class="valor">
                                        <c:if test="${cfg.mostrarIVAIncluido}">
                                            <bean:message key="texto.verdadero" />
                                        </c:if>
                                        <c:if test="${!cfg.mostrarIVAIncluido}">
                                            <bean:message key="texto.falso" />
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="tit"><bean:message key="texto.usarDirFactura" />:</td>
                                    <td class="valor">
                                        <c:if test="${cfg.usarDirFactura}">
                                            <bean:message key="texto.verdadero" />
                                        </c:if>
                                        <c:if test="${!cfg.usarDirFactura}">
                                            <bean:message key="texto.falso" />
                                        </c:if>
                                    </td>
                                </tr>
                            </table>                    
                        </div>
                    </div>
                    <p class="links"><a class="editar" href="${pageContext.request.contextPath}/administrar/tienda/configuracion/editar.do?metodo=editar_inicio&id=1"><bean:message key="enlace.editar_cfg" /></a></p>
                </div>
            </div>             
        </div>
        <ctrl:pie />
    </body>
</html>
