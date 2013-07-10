<%-- 
    Document   : pedido_confirmacion3
    Created on : 04/10/2012, 13:40:00
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="listado" uri="/WEB-INF/tlds/gigStore.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
        <meta name="robots" content="noindex,nofollow">  
        <title><bean:message key="titulo.finalizado" /></title>
    </head>
    <body>
        <div id="fb-root"></div>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <h1><bean:message key="titulo.finalizado" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"></span></p>
                    <div class="entrada">
                        <h3><bean:message key="texto.finalizado1" /> <a href="${pageContext.request.contextPath}/usuario/datos-personales/" ><bean:message key="enlace.sucuenta" /></a>.</h3>
                        <h3><bean:message key="texto.finalizado2" /></h3>
                    </div>
                </div>    
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"></span></p>
                    <div class="entrada">
                        <listado:MostrarDestacadosHandler id="slider" coleccion="destacados" />
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

