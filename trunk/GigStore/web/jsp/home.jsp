<%-- 
    Document   : home
    Created on : 29/02/2012, 10:30:17
    Author     : Manel Márquez Bonilla
    Descripcion: Pagina de inicio de la aplicación
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
<!--        Eliminar la siguiente línea en producción              -->
        <meta name="robots" content="noindex,nofollow">
        <meta name="keywords" content="${txt.mtHome}">
        <meta name="description" content="${txt.mdHome}">
        
        <title>${txt.tituloTienda} | ${txt.subtituloTienda}</title>
    </head>
    <body>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <h1>${txt.subtituloTienda}</h1>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"></span></p>
                    <div class="entrada" id="txt_home">
                        ${txt.secciones.textoBienvenidaHome}
                        <c:if test="${txt.banners.bannerHomeActivo && txt.banners.bannerHome!=''}">
                            <img src="${pageContext.request.contextPath}/banner?tipo=home" alt="${txt.banners.bannerHomeAlt}" title="${txt.banners.bannerHomeAlt}" />
                        </c:if>
                    </div>
                </div>  
                <div id="bigbanner">
                    <listado:MostrarDestacadosHandler id="slider" coleccion="destacados" />
                </div>                
                <listado:MostrarDestacadosHandler id="slider2" coleccion="vendidos" /> 
                <listado:MostrarDestacadosHandler id="slider3" coleccion="nuevos" />
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
                    numeric: true
//                    ,
//                    controlsBefore:'<p id="controls">',
//                    controlsAfter:'</p>',		
//                    prevText:'',
//                    nextText:'',
//                    prevId: 'prevBtn',
//                    nextId: 'nextBtn'                  
                });
                $("#slider2").easySlider({
                    continuous:	true, 
                    controlsBefore:'<p id="controls2">',
                    controlsAfter:'</p>',		
                    prevText:'',
                    nextText:'',		
                    prevId: 'prevBtn2',
                    nextId: 'nextBtn2'  
                });
                $("#slider3").easySlider({
                    continuous:	true, 
                    controlsBefore:'<p id="controls3">',
                    controlsAfter:'</p>',		
                    prevText:'',
                    nextText:'',		
                    prevId: 'prevBtn3',
                    nextId: 'nextBtn3'  	
                });
            });
        </script>
    </body>
</html>
