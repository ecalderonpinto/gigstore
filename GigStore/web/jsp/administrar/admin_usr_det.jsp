<%-- 
    Document   : admin_det_user
    Created on : 11/04/2012, 11:17:16
    Author     : Manel Márquez Bonilla
    Descripcion: Datos del usuario a administrar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="jmesa" uri="/WEB-INF/tlds/jmesa.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/jmesa.css" rel="stylesheet" type="text/css">
        <title><bean:message key="titulo.admin_usuarios_datos" /></title>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/usuarios.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_usr" /></a> &gt </li>
                        <li><bean:message key="ruta.detalle_usr" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.admin_usuarios_datos" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.admin_usuarios_datos" /></span></p>
                    <div class="entrada">
                        <div id="detalle">
                            <table class="resumen">
                                <tr><td class="tit"><bean:message key="texto.nif" />:</td><td class="valor">${usuario.nif}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.nombre" />:</td><td class="valor">${usuario.nombre}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.apellidos" />:</td><td class="valor">${usuario.apellidos}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.fechanac" />:</td><td class="valor"><f:formatDate pattern="dd/MM/yyyy" value="${usuario.fechaNacimiento}"></f:formatDate> </td></tr>
                                <tr><td class="tit"><bean:message key="texto.telf" />:</td><td class="valor">${usuario.telefono}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.email" />:</td><td class="valor">${usuario.email}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.user" />:</td><td class="valor">${usuario.usuario}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.rol" />:</td><td class="valor">${usuario.rol}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.estado" />:</td><td class="valor">${usuario.estado}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.fehaalta" />:</td><td class="valor"><f:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${usuario.fechaAlta}"></f:formatDate></td></tr>
                            </table>                    
                        </div>
                    </div>
                    <p class="links"><a class="editar" href="${pageContext.request.contextPath}/administrar/usuarios/editar.do?metodo=editar_inicio&id=${usuario.id}"><bean:message key="enlace.editar_usuario" /></a></p>
                </div>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.admin_direcciones" /></span></p>
                    <div class="entrada">
                        <div id="tabla_dirs">
                            <form name="listadoDireccionesUsr" action="${pageContext.request.contextPath}/administrar/usuarios/detalle.do">
                                <input type="hidden" name="metodo" value="mostrar_detalle">
                                <input type="hidden" name="id" value="${usuario.id}">
                                ${listado_direcciones_usr}
                            </form> 
                        </div>
                    </div>
                    <p class="links"><a class="alta" href="${pageContext.request.contextPath}/administrar/usuarios/direcciones/editar.do?metodo=alta_inicio&usr_id=${usuario.id}"><bean:message key="enlace.alta_direccion" /></a>                  </p>
                </div>
            </div>
        </div>        
        <ctrl:pie />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.3.min.js"></script>
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
                location.href = '${pageContext.request.contextPath}/administrar/usuarios/detalle.do?metodo=mostrar_detalle&id=${usuario.id}&' + parameterString;
            }
        </script>
    </body>
</html>