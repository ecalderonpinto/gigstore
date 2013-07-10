<%-- 
    Document   : lateral
    Created on : 06/03/2012, 17:39:28
    Author     : Manel Márquez Bonilla
--%>

<%@tag description="Panel lateral común para todas las páginas" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="cats" uri="/WEB-INF/tlds/gigStore.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>

<script type="text/javascript">
  $(document).ready(function(){ // Script del Navegador
    $("ul.subnavegador").not('.selected').hide();
    $("a.desplegable").mouseover(function(e){
      var desplegable = $(this).parent().find("ul.subnavegador");
      $('.desplegable').parent().find("ul.subnavegador").not(desplegable).slideUp('slow');
      desplegable.slideToggle('slow');
      e.preventDefault();
    })
 });
</script>

<ctrl:script_confirmacion />
<div id="lateral">
    <ul>
        <c:if test="${txt.banners.bannerLateral1Activo && txt.banners.bannerLateral1!=''}">
            <li>
                <img src="${pageContext.request.contextPath}/banner?tipo=lateral1" alt="${txt.banners.bannerLateral1Alt}" title="${txt.banners.bannerLateral1Alt}" />
            </li>
        </c:if>
        <c:if test="${!empty(carrito)}">
            <li>
                <h3><bean:message key="titulo.carrito" /></h3>
                <table id="minicarrito">
                    <tr>
                        <th><bean:message key="texto.unidades" /></th>
                        <th><bean:message key="texto.nombre" /></th>
                        <th><bean:message key="texto.precio" /></th>
                    </tr>
                    <c:set var="subtotal" value="${0}" />
                    <c:forEach var="lin" items="${carrito}">
                        <tr class="linea">
                            <td>${lin.cantidad} x </td>
                            <c:set var="nom" value="" />
                            <c:forEach var="desc" items="${lin.producto.descripciones}" >
                                <c:if test="${desc.idioma==pageContext.request.locale.language}"><c:set var="nom" value="${desc.nombre}" /></c:if>
                            </c:forEach>
                            <c:if test="${empty(nom)}">
                                <c:set var="nom" value="${lin.producto.nombre}" />
                            </c:if>
                            <td>${nom}</td>
                            <td class="dcha">
                                <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                <f:formatNumber pattern="0.00">${lin.precio}</f:formatNumber>
                                <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                            </td> 
                        </tr>
                        <c:set var="subtotal" value="${subtotal+lin.subtotal}" />
                    </c:forEach>   
                    <tr class="total">
                        <td class="dcha" colspan="2"><bean:message key="texto.subtotal" /></td>
                        <td class="dcha">
                            <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                            <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                            <f:formatNumber pattern="0.00">${subtotal}</f:formatNumber>
                            <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                        </td> 
                    </tr>
                </table>
                <div id="btncarrito">
                    <a href="${pageContext.request.contextPath}/pedido/carro-de-la-compra/"><bean:message key="enlace.vercarrito" /></a>
                </div> 
                <div style="clear: both;">&nbsp;</div>
            </li>
        </c:if>
        <li>
            <h3><bean:message key="titulo.buscador" /></h3>
            <p><bean:message key="texto.buscador" /></p>
            <div class="miniform" >
                <form method="post" action="${pageContext.request.contextPath}/buscar/">
                    <div>
                        <input type="text" name="busqueda" class="miniform-text" value="${busqueda}" />
                        <input type="submit" class="miniform-submit" value="<bean:message key="boton.buscar" />" />
                    </div>
                </form>
            </div>
            <div style="clear: both;">&nbsp;</div>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/categorias/">
                <h3><bean:message key="titulo.lateral" /></h3>
            </a>
            <p><bean:message key="texto.lateral" /></p>
            <ul class="navegador">
                <cats:LateralHandler />
            </ul>
        </li>
        <li>
            <h3><bean:message key="texto.marcas" /></h3>
            <ul class="navegador">
                <c:forEach var="marca" items="${marcas}">
                    <li><a href="${pageContext.request.contextPath}/productos/buscar.do?metodo=buscar&accion=buscar&busqueda=${marca}">${marca}</a></li>
                </c:forEach>
            </ul>
        </li>
        <li>
            <h3><bean:message key="titulo.news" /></h3>
            <p><bean:message key="texto.news" /></p>
            <div class="miniform" >
                <form method="post" action="${pageContext.request.contextPath}/administrar/listacorreo/agregar.do">
                    <div>
                        <input type="text" name="email" class="miniform-text" value="" />
                        <input type="hidden" name="metodo" value="alta_ejecutar" />
                        <input type="submit" class="miniform-submit" value="<bean:message key="boton.subscribirse" />" onclick="javascript:return confirmar('<bean:message key="texto.subscribirse" />')" />
                    </div>
                </form>
            </div>
            <p id="erroremail">${error_email}</p>
            <div style="clear: both;">&nbsp;</div>
        </li>
        <c:if test="${txt.banners.bannerLateral2Activo && txt.banners.bannerLateral2!=''}">
            <li>
                <img src="${pageContext.request.contextPath}/banner?tipo=lateral2" alt="${txt.banners.bannerLateral2Alt}" title="${txt.banners.bannerLateral2Alt}" />
            </li>
        </c:if>
    </ul>
</div>