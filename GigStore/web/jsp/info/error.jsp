<%-- 
    Document   : error
    Created on : 08/03/2012, 18:35:49
    Author     : Manel Márquez Bonilla
    Descripcion: Pagina de error
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="listado" uri="/WEB-INF/tlds/gigStore.tld" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="titulo.error" /></title>
    </head>
    <body>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li><bean:message key="titulo.error" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.error" /></h1>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"></span></p>
                    <div class="entrada">
                        <c:if test="${requestScope['javax.servlet.error.status_code']==404}"><h3><bean:message key="texto.error_404" /></c:if>
                        <c:if test="${requestScope['javax.servlet.error.status_code']!=404}"><h3><bean:message key="texto.error_pag" /></c:if>
                    </div>
                </div>    
                <listado:MostrarDestacadosHandler id="slider" coleccion="destacados" />
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
