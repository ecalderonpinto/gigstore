<%-- 
    Document   : usr_gestion
    Created on : 13/03/2012, 18:27:58
    Author     : Manel Márquez Bonilla
    Descripcion: Menu con las opciones de gestion del usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="listado" uri="/WEB-INF/tlds/gigStore.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="jmesa" uri="/WEB-INF/tlds/jmesa.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex,nofollow">
        <link href="${pageContext.request.contextPath}/css/jmesa.css" rel="stylesheet" type="text/css">
        <title><bean:message key="titulo.menu_usuario" /></title>
        <ctrl:script_confirmacion />
    </head>
    <body>
        <ctrl:cabecera />
        <div id="cuerpo">
            <ctrl:lateral />
            <div id="contenido">
                <div id="ruta">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/"><bean:message key="ruta.home" /></a> &gt </li>
                        <li><bean:message key="ruta.menu_user" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.menu_usuario" /></h1>  
                
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="texto.datospers" /></span></p>
                    <div class="entrada">
                        <div id="detalle">
                            <table class="resumen">
                                <tr><td class="tit"><bean:message key="texto.nif" />:</td><td class="valor">${usrSesion.nif}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.nombre" />:</td><td class="valor">${usrSesion.nombre}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.apellidos" />:</td><td class="valor">${usrSesion.apellidos}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.fechanac" />:</td><td class="valor"><f:formatDate pattern="dd/MM/yyyy" value="${usrSesion.fechaNacimiento}"></f:formatDate> </td></tr>
                                <tr><td class="tit"><bean:message key="texto.telf" />:</td><td class="valor">${usrSesion.telefono}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.email" />:</td><td class="valor">${usrSesion.email}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.user" />:</td><td class="valor">${usrSesion.usuario}</td></tr>
                            </table>                    
                        </div>
                    </div>
                    <p class="links"><a class="editar" href="${pageContext.request.contextPath}/usuario/datos-personales/editar/"><bean:message key="enlace.editar_usuario" /></a></p>
                </div>
                
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.direcciones_user" /></span></p>
                    <div class="entrada">
                        <div id="tabla_dirs">
                            <form name="listadoDireccionesUsr" action="${pageContext.request.contextPath}/usuario/datos-personales/">
                                <input type="hidden" name="metodo" value="mostrar_detalle">
                                ${listado_direcciones_usr}
                            </form>                       
                        </div>
                    </div>
                    <p class="links"><a class="alta" href="${pageContext.request.contextPath}/usuario/datos-personales/direcciones/alta/"><bean:message key="enlace.alta_direccion" /></a></p>
                </div> 
                
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="texto.pedidosusr" /></span></p>
                    <div class="entrada">
                        <div id="tabla_peds">
                            <form name="listadoPedidosUsr" action="${pageContext.request.contextPath}/usuario/datos-personales/">
                                <input type="hidden" name="metodo" value="mostrar_detalle">
                                ${listado_pedidos_usr}
                            </form>                       
                        </div>
                    </div>
                    <p class="links"></p>
                </div>
                <listado:MostrarDestacadosHandler id="slider" coleccion="seguimiento" /> 
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jmesa.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jmesa.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
            function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                location.href = '${pageContext.request.contextPath}/usuario/datos-personales/' + parameterString;
            }
        </script>
    </body>
</html>
