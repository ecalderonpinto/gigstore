<%-- 
    Document   : admin_news_lista
    Created on : 29/08/2012, 10:32:27
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="jmesa" uri="/WEB-INF/tlds/jmesa.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/jmesa.css" rel="stylesheet" type="text/css">
        <title><bean:message key="titulo.admin_news" /></title>
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
                        <li><bean:message key="ruta.lista_news" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.admin_news" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.tabla_news" /></span></p>
                    <div class="entrada">                            
                        <form name="listadoNewsForm" action="${pageContext.request.contextPath}/administrar/listacorreo.do">
                            <input type="hidden" name="metodo" value="mostrar_listado">
                            ${listado_news}
                        </form>
                    </div>
                    <p class="links"><a class="alta" href="${pageContext.request.contextPath}/administrar/listacorreo/generar_boletin.do?metodo=generar_email"><bean:message key="enlace.generar_news" /></a></p>                    
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
                location.href = '${pageContext.request.contextPath}/administrar/listacorreo.do?metodo=mostrar_listado&' + parameterString;
            }
        </script>
    </body>
</html>

