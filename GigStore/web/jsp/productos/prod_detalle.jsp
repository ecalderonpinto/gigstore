<%-- 
    Document   : prod_detalle
    Created on : 06/03/2012, 16:47:33
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="listado" uri="/WEB-INF/tlds/gigStore.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <meta name="keywords" content="${descripcion.mtProductos} ${descripcion.etiquetas}">
        <meta name="description" content="${descripcion.mdProductos}">
        <link href="${pageContext.request.contextPath}/css/lightbox.css" rel="stylesheet" />      
        <title><bean:message key="titulo.detalle_prod" arg0="${descripcion.nombre}" /></title>
        
        <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lightbox.js"></script>
        <script>
            function cambiarvalor(txt,simbolo,miles,decimal) {
                var dato=txt.split(";");
                if (!isNaN(dato[1])) {
                    document.getElementById("txtprecio").innerHTML=formatMoney(dato[1],2,simbolo,miles,decimal);
                }              
            }
            
            function formatMoney(number, places, symbol, thousand, decimal) {
                number = number || 0;
                places = !isNaN(places = Math.abs(places)) ? places : 2;
                symbol = symbol !== undefined ? symbol : "$";
                thousand = thousand || ",";
                decimal = decimal || ".";
                var negative = number < 0 ? "-" : "",
                    i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
                    j = (j = i.length) > 3 ? j % 3 : 0;
                return negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "") + " " + symbol;
            }
        </script>
    </head>
    <body>
        <div id="fb-root"></div>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <c:if test="${!empty(accion)}">
                            <c:if test="${accion=='destacados'}">
                                <li><a href="${pageContext.request.contextPath}/productos/recomendaciones/destacados/"><bean:message key="enlace.destacados" /></a> &gt </li>
                            </c:if>
                            <c:if test="${accion=='vendidos'}">
                                <li><a href="${pageContext.request.contextPath}/productos/recomendaciones/vendidos/"><bean:message key="enlace.vendidos" /></a> &gt </li>
                            </c:if>
                            <c:if test="${accion=='nuevos'}">
                                <li><a href="${pageContext.request.contextPath}/productos/recomendaciones/nuevos/"><bean:message key="enlace.nuevos" /></a> &gt </li>
                            </c:if>
                            <c:if test="${accion=='buscar'}">
                                <li><a href="${pageContext.request.contextPath}/productos/buscar/"><bean:message key="titulo.buscar" arg0="${busqueda}" /></a> &gt </li>
                            </c:if>
                            <c:if test="${accion=='categorias'}">
                                <li><a href="${pageContext.request.contextPath}/productos/categorias/${categoria_url}/"><bean:message key="titulo.categorias" arg0="${categoria_nombre}" /></a> &gt </li>
                            </c:if>
                            <c:if test="${accion=='seguimiento'}">
                                <li><a href="${pageContext.request.contextPath}/usuario/datos-personales/"><bean:message key="ruta.menu_user" /></a> &gt </li>
                            </c:if>
                        </c:if>
                        <li>${descripcion.nombre}</li>
                    </ul>
                </div>
                <h1>${descripcion.nombre}</h1>                      
                <div class="cuadro">
                    <p class="meta"><span class="izda"><bean:message key="texto.categoria" />: ${producto.categoria.nombre}</span><span class="dcha">${descripcion.nombre}</span></p>
                    <div class="entrada"> 
                        <div id="detalleprod">
                            <div id="imgprod">
                                <c:if test="${empty(producto.imagenes)}">
                                    <div class="visible">
                                        <a href="${pageContext.request.contextPath}/images/tienda/Gift.png" rel="lightbox[${descripcion.nombre}]" title="<bean:message key="texto.imgnodisponible" />"><img src="${pageContext.request.contextPath}/images/tienda/Gift.png" data-title="${descripcion.nombre}" data-description="<bean:message key="texto.imgnodisponible" />"></a> 
                                    </div>
                                </c:if>
                                <c:forEach var="imagen" items="${producto.imagenes}">
                                    <c:if test="${imagen.principal}">
                                        <div class="visible">
                                           <a href="${pageContext.request.contextPath}/imagen?id=${imagen.id}" rel="lightbox[${descripcion.nombre}]" title="${imagen.alt}"><img src="${pageContext.request.contextPath}/imagen?id=${imagen.id}" data-title="${descripcion.nombre}" data-description="${imagen.alt}"></a> 
                                        </div>
                                    </c:if>
                                </c:forEach>     
                                <c:forEach var="imagen" items="${producto.imagenes}">
                                    <c:if test="${!imagen.principal}">
                                        <div class="oculto">
                                           <a href="${pageContext.request.contextPath}/imagen?id=${imagen.id}" rel="lightbox[${descripcion.nombre}]" title="${imagen.alt}"><img src="${pageContext.request.contextPath}/imagen?id=${imagen.id}" data-title="${descripcion.nombre}" data-description="${imagen.alt}"></a> 
                                        </div>
                                    </c:if>
                                </c:forEach>                        
                            </div>
                            <div id="cuadrodatos">                   
                                <div 
                                    <c:if test="${sinopts}">class="resaltado_s"</c:if> 
                                    <c:if test="${!sinopts}">class="resaltado"</c:if> 
                                >
                                    <div id="datos">
                                        <p class="ref">${producto.referencia}</p>
                                        <p class="iva">
                                            <f:formatNumber pattern="0">${producto.iva.iva}</f:formatNumber>%
                                            <c:choose>
                                                <c:when test="${cfg.mostrarIVAIncluido}">
                                                    <bean:message key="texto.ivainc" />
                                                </c:when>
                                                <c:otherwise>
                                                    <bean:message key="texto.ivanoinc" />
                                                </c:otherwise>
                                            </c:choose>                                            
                                        </p>
                                    </div>
                                    <c:if test="${producto.descuento>0 || producto.categoria.descuento>0}">
                                        <div id="preciodesc">
                                            <p><bean:message key="texto.antes" />: 
                                                <strong id="txtpreciodesc">
                                                    <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                                    <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                                    <f:formatNumber pattern="0.00">${producto.precio}</f:formatNumber>
                                                    <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                                </strong>
                                            </p>
                                        </div>
                                    </c:if>
                                    <div id="precio">
                                        <p>
                                            <strong id="txtprecio">
                                                <c:if test="${cfg.moneda=='USD'}">&#36;</c:if>
                                                <c:if test="${cfg.moneda=='GBP'}">&#163;</c:if>
                                                <f:formatNumber pattern="0.00">${precio_final}</f:formatNumber>
                                                <c:if test="${cfg.moneda=='EUR'}">&euro;</c:if>
                                            </strong>
                                        </p>
                                    </div>
                                    <div id="formcarrito">
                                        <c:choose>
                                            <c:when test="${( (cfg.ocultarBotonCompra && producto.estado.id==2) || (cfg.stockMin > 0 && producto.stock < cfg.stockMin) )}">
                                                <p class="nodisp"><bean:message key="texto.prodnodisponible" /></p>
                                            </c:when>   
                                            <c:otherwise>
                                                <form action="${pageContext.request.contextPath}/pedido/agregar.do" method="post">
                                                    <c:if test="${!sinopts}">
                                                        <p class="tit_opt"><bean:message key="titulo.opciones" /></p>
                                                        <div class="styled-select">
                                                            <c:if test="${cfg.moneda=='EUR'}"><select name="opcion" onchange="cambiarvalor(this.value,'&euro;','.',',')"></c:if>
                                                            <c:if test="${cfg.moneda=='USD'}"><select name="opcion" onchange="cambiarvalor(this.value,'&#36;',',','.')"></c:if>
                                                            <c:if test="${cfg.moneda=='GBP'}"><select name="opcion" onchange="cambiarvalor(this.value,'&#163;',',','.')"></c:if>                                                        
                                                                <c:forEach var="opt" items="${opts}" >
                                                                    <c:if test="${opt.precio!=null}">
                                                                        <c:choose>
                                                                            <c:when test="${producto.descuento>0}">
                                                                                <c:choose>
                                                                                    <c:when test="${producto.descporcentaje}">
                                                                                        <c:set var="precio" value="${opt.precio-((opt.precio*producto.descuento)/100)}" />
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <c:set var="precio" value="${opt.precio-producto.descuento}" />
                                                                                    </c:otherwise>
                                                                                </c:choose>  
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <c:set var="precio" value="${opt.precio}" />
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:if>
                                                                    <c:if test="${opt.precio==null}"><c:set var="precio" value="${precio_final}" /></c:if>
                                                                    <c:if test="${producto.categoria.descuento>0}">
                                                                        <c:choose>
                                                                            <c:when test="${producto.categoria.descporcentaje}">
                                                                                <c:set var="precio" value="${precio-((precio*producto.categoria.descuento)/100)}" />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <c:set var="precio" value="${precio-producto.categoria.descuento}" />
                                                                            </c:otherwise>
                                                                        </c:choose>  
                                                                    </c:if>
                                                                    <c:set var="texto" value="" />
                                                                    <c:forEach var="s" items="${opt.traducciones}" >
                                                                        <c:set var="parts" value="${fn:split(s, ';')}" />
                                                                        <c:if test="${parts[0]==pageContext.request.locale.language}"><c:set var="texto" value="${parts[1]}" /></c:if>
                                                                    </c:forEach>
                                                                    <c:if test="${empty(texto)}">
                                                                        <c:set var="texto" value="${opt.opcion}" />
                                                                    </c:if>
                                                                    <option value="${opt.id};${precio}">${texto}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </c:if>
                                                    <bean:message key="texto.unidades" /> <input type="text" name="unidades" class="cajatexto" value="1" />
                                                    <input type="hidden" name="prodId" value="${producto.id}" />
                                                    <input type="hidden" name="accion" value="${accion}" />
                                                    <input type="hidden" name="busqueda" value="${busqueda}" />
                                                    <input type="hidden" name="categoria" value="${categoria}" />
                                                    <input type="hidden" name="categoria_nombre" value="${categoria_nombre}" />
                                                    <input type="submit" name="metodo" value="<bean:message key="boton.acarrito" />" class="boton_linea" />
                                                </form>
                                                <c:if test="${!empty(error)}">
                                                    <div class="visible">
                                                       <p id="error"><bean:message key="errors.integer" /></p>
                                                    </div>
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="compartir">
                                    <!-- AddThis Button BEGIN -->
                                    <div class="addthis_toolbox addthis_default_style addthis_32x32_style">
                                        <a class="addthis_button_preferred_1"></a>
                                        <a class="addthis_button_preferred_2"></a>
                                        <a class="addthis_button_preferred_3"></a>
                                        <a class="addthis_button_preferred_4"></a>
                                        <a class="addthis_button_compact"></a>
                                        <a class="addthis_counter addthis_bubble_style"></a>
                                    </div>
                                    <script type="text/javascript">var addthis_config = {"data_track_addressbar":true};</script>
                                    <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-501513024e94b0c2"></script>
                                    <!-- AddThis Button END -->
                                </div>
                            </div>
                        </div>
                        <div id="descprod">
                            <div id="datosprod">
                                <c:if test="${!empty(producto.marca)}"><p class="formato"><bean:message key="texto.marca" />: ${producto.marca}</p></c:if>
                                <c:if test="${!empty(descripcion.formato)}"><p class="formato"><bean:message key="texto.formato" />: ${descripcion.formato}</p></c:if>
                                <c:if test="${!empty(descripcion.dimensiones)}"><p class="dim"><bean:message key="texto.dimensiones" />: ${descripcion.dimensiones}</p></c:if>
                                <c:if test="${!empty(descripcion.etiquetas)}"><p class="etiquetas"><bean:message key="texto.etiquetas" />: ${descripcion.etiquetas}</p></c:if>
                            </div>
                            <div class="desc">${descripcion.descripcion}</div>
                        </div>
                    </div>
                    <p class="links">
                        <c:if test="${usrSesion.rol=='usuario'}">
                            <c:set var="existe" value="false" />
                            <c:forEach var="pr" items="${usrSesion.seguimiento}">
                                <c:if test="${pr.id==producto.id}">
                                    <a href="${pageContext.request.contextPath}/usuario/seguimiento/eliminar.do?metodo=eliminar_seguimiento&id=${producto.id}"><bean:message key="enlace.eliminar_seguimiento" /></a>
                                    <c:set var="existe" value="${true}" />
                                </c:if>
                            </c:forEach>
                            <c:if test="${(!empty(usrSesion) || usrSesion.id > 0) && !existe }">
                                <a href="${pageContext.request.contextPath}/usuario/seguimiento/agregar.do?metodo=agregar_seguimiento&id=${producto.id}"><bean:message key="enlace.agregar_seguimiento" /></a>
                            </c:if>
                        </c:if>
                        <c:if test="${usrSesion.rol=='administrador'}">
                            <a href="${pageContext.request.contextPath}/administrar/productos/detalle.do?metodo=mostrar_detalle&id=${producto.id}"><bean:message key="enlace.administrar" /></a>
                        </c:if>
                    </p>
                </div>
                <div class="cuadro">
                    <div class="entrada">
                        <listado:MostrarDestacadosHandler id="slider" coleccion="vendidos" />
                        <c:if test="${txt.banners.bannerFichaActivo && txt.banners.bannerFicha!=''}">
                            <img src="${pageContext.request.contextPath}/banner?tipo=ficha" alt="${txt.banners.bannerFichaAlt}" title="${txt.banners.bannerFichaAlt}" />
                        </c:if>
                    </div>
                </div>  
            </div>
        </div>
        <ctrl:pie />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easySlider1.7.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){	
                $("#slider").easySlider({
                    continuous:	true, 
                    auto: true,
                    numeric: false,
                    controlsBefore:'<p id="controls">',
                    controlsAfter:'</p>',		
                    prevText:'',
                    nextText:'',
                    prevId: 'prevBtn',
                    nextId: 'nextBtn'                  
                });
            });
        </script>
    </body>
</html>