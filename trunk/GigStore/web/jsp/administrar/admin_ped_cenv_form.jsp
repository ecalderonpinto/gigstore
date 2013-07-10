<%-- 
    Document   : admin_tienda_cenv_form
    Created on : 08/10/2012, 18:56:54
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario alta/modificacion de los tipos de coste de envío
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <c:if test="${accion=='alta'}">
                <bean:message key="titulo.alta_cenv" />
            </c:if>
            <c:if test="${accion=='editar'}">
                <bean:message key="titulo.modificar_cenv" />
            </c:if>
        </title>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/pedidos.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_pedidos" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/pedidos/costeenvio.do?metodo=mostrar_listado" ><bean:message key="ruta.gestionar_cenv" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_cenv" /></li>
                    </ul>
                </div>
                <h1>
                    <c:if test="${accion=='alta'}">
                        <bean:message key="titulo.alta_cenv" />
                    </c:if>
                    <c:if test="${accion=='editar'}">
                        <bean:message key="titulo.modificar_cenv" />
                    </c:if>
                </h1>    
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.editar_cenv" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/pedidos/costeenvio/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.tipo" /></p>
                                    <p class="etiqueta"><bean:message key="texto.precio" /></p>    
                                    <p class="etiqueta"><bean:message key="texto.activarPayPal" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activarReembolso" /></p> 
                                    <p class="etiqueta"><bean:message key="texto.activarTransferencia" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activarEfectivo" /></p>     
                                    <p class="etiqueta"><bean:message key="texto.activo" /></p>  
                                    <p class="etiqueta"><bean:message key="texto.descripcion" /></p>                           
                                </div>
                                <div class="valores">
                                    <html:text property="tipo" />
                                    <html:text property="sprecio" />
                                    <html:checkbox property="sactivarPayPal" styleClass="check"></html:checkbox>
                                    <html:checkbox property="sactivarReembolso" styleClass="check"></html:checkbox>
                                    <html:checkbox property="sactivarTransferencia" styleClass="check"></html:checkbox> 
                                    <html:checkbox property="sactivarEfectivo" styleClass="check"></html:checkbox>
                                    <html:checkbox property="sactivo" styleClass="check"></html:checkbox>    
                                    <html:textarea property="descripcion" />                                    
                                </div>
                                <div id="botones">
                                    <c:if test="${accion=='alta'}">
                                        <html:hidden property="metodo" value="alta_ejecutar" />
                                        <html:submit styleClass="boton"><bean:message key="boton.alta" /></html:submit>
                                    </c:if>
                                    <c:if test="${accion=='editar'}">
                                        <html:hidden property="metodo" value="editar_ejecutar" />
                                        <html:submit styleClass="boton"><bean:message key="boton.modificar" /></html:submit>
                                    </c:if>

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
