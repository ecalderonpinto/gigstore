<%-- 
    Document   : newjspadmin_prod_opt_form
    Created on : 29/10/2012, 12:31:09
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="titulo.modificar_prod_opt" /></title>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/productos.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_productos" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/productos/detalle.do?metodo=mostrar_detalle&id=${id_prod}" /><bean:message key="ruta.detalle_prod" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_opt" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.modificar_prod_opt" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.editar_opt" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/productos/opciones/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.opcion" /></p>
                                    <p class="etiqueta"><bean:message key="texto.precio" /></p>
                                    <p class="etiqueta"><bean:message key="texto.stock" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activo" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="opcion" />
                                    <html:text property="sprecio" />
                                    <html:text property="sstock" />
                                    <html:checkbox property="sactiva" />
                                </div>
                                <div id="botones">
                                    <html:hidden property="metodo" value="editar_ejecutar" />
                                    <html:submit styleClass="boton"><bean:message key="boton.modificar" /></html:submit>
                                    <html:reset styleClass="boton" />
                                    <html:cancel styleClass="boton" />
                                </div>
                            </html:form>
                        </div>
                    </div>
                    <p class="links"></p>
                </div>                
            </div>
        </div>        
        <ctrl:pie />
    </body>
</html>