<%-- 
    Document   : admin_opt_det
    Created on : 28/05/2013, 21:04:40
    Author     : Manel Márquez Bonilla
    Descripcion: Datos de la opción
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="jmesa" uri="/WEB-INF/tlds/jmesa.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/jmesa.css" rel="stylesheet" type="text/css">
        <title><bean:message key="titulo.admin_opt_datos" /></title>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/productos.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_prod" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/opciones.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_opts" /></a> &gt </li>
                        <li><bean:message key="ruta.detalle_opt" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.admin_opt_datos" />: ${opcion.opcion}</h1>        
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.admin_opt_datos" /></span></p>
                    <div class="entrada">
                        <div id="detalle">
                            <table class="resumen">
                                <tr><td class="tit"><bean:message key="texto.opcion" />:</td><td class="valor">${opcion.opcion}</td></tr>
                                <tr><td class="tit"><bean:message key="texto.descripcion" />:</td><td class="valor">${opcion.descripcion}</td></tr>
                            </table>                    
                        </div>
                    </div>
                    <p class="links"><a class="editar" href="${pageContext.request.contextPath}/administrar/opciones/editar.do?metodo=editar_inicio&id=${opcion.id}"><bean:message key="enlace.editar_opcion" /></a></p>
                </div>
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.valores" /></span></p>
                    <div class="entrada">                        
                        <form name="listadoValoresForm" action="${pageContext.request.contextPath}/administrar/opciones/detalle.do">
                            <input type="hidden" name="metodo" value="mostrar_detalle">
                            <input type="hidden" name="id" value="${opcion.id}">
                            ${listado_valores}
                        </form>
                    </div>
                    <p class="links"><a href="${pageContext.request.contextPath}/administrar/opciones/valores/editar.do?metodo=alta_inicio&id=${opcion.id}"><bean:message key="enlace.agregar_opt_val" /></a></p>
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
                location.href = '${pageContext.request.contextPath}/administrar/opciones/detalle.do?metodo=mostrar_detalle&id=${producto.id}&' + parameterString;
            }
        </script>
    </body>
</html>