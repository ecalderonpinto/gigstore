<%-- 
    Document   : menu_admin
    Created on : 28/03/2013, 21:24:13
    Author     : Manel Márquez Bonilla
    Descripcion: Cabecera de la aplicacion
--%>

<%@tag description="Menu de secciones de administracion" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>


<div id="header">
    <ul class="mi-menu">
        <li><span><bean:message key="enlace.ges_productos" /></span>
            <ul>
                <li><a href="${pageContext.request.contextPath}/administrar/productos.do?metodo=mostrar_listado"><bean:message key="titulo.tabla_productos" /></a></li>
                <li><a href="${pageContext.request.contextPath}/administrar/productos/categorias.do?metodo=mostrar_listado"><bean:message key="titulo.tabla_categorias" /></a></li>
                <li><a href="${pageContext.request.contextPath}/administrar/productos/estados.do?metodo=mostrar_listado"><bean:message key="titulo.estados" /></a></li>
                <li><a href="${pageContext.request.contextPath}/administrar/opciones.do?metodo=mostrar_listado"><bean:message key="titulo.opciones" /></a></li>
            </ul>
        </li>
        <li><span><bean:message key="enlace.ges_pedidos" /></span>
            <ul>
                <li><a href="${pageContext.request.contextPath}/administrar/pedidos.do?metodo=mostrar_listado"><bean:message key="titulo.tabla_pedidos" /></a></li>
                <li><a href="${pageContext.request.contextPath}/administrar/pedidos/estados.do?metodo=mostrar_listado"><bean:message key="titulo.estados" /></a></li>
                <li><a href="${pageContext.request.contextPath}/administrar/pedidos/costeenvio.do?metodo=mostrar_listado"><bean:message key="titulo.tabla_costeenv" /></a></li>
            </ul>
        </li>
        <li><span><bean:message key="enlace.ges_usuarios" /></span>
            <ul>
                <li><a href="${pageContext.request.contextPath}/administrar/usuarios.do?metodo=mostrar_listado"><bean:message key="ruta.lista_usr" /></a></li>
                <li><a href="${pageContext.request.contextPath}/administrar/usuarios/estados.do?metodo=mostrar_listado"><bean:message key="titulo.estados" /></a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/administrar/estadisticas.do?metodo=mostrar_listado"><bean:message key="enlace.ges_estadisticas" /></a></li>
        <li><a href="${pageContext.request.contextPath}/administrar/listacorreo.do?metodo=mostrar_listado"><bean:message key="enlace.ges_news" /></a></li>
        <li><span><bean:message key="enlace.ges_tienda" /></span>
            <ul>
                <li><a href="${pageContext.request.contextPath}/administrar/tienda.do?metodo=mostrar_listado"><bean:message key="titulo.variables_tienda" /></a></li>
                <li><a href="${pageContext.request.contextPath}/administrar/tienda/textos.do?metodo=mostrar_listado"><bean:message key="titulo.textos_tienda" /></a></li>
                <li><a href="${pageContext.request.contextPath}/administrar/tienda/formapago.do?metodo=mostrar_listado"><bean:message key="ruta.admin_tienda_pago" /></a></li>
                <li><a href="${pageContext.request.contextPath}/administrar/tienda/tiposiva.do?metodo=mostrar_listado"><bean:message key="titulo.tabla_tipoiva" /></a></li>
            </ul>
        </li>
    </ul>    
</div>