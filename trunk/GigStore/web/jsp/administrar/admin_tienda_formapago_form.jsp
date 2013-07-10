<%-- 
    Document   : admin_tienda_formapago_form
    Created on : 18/06/2013, 10:26:44
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario para configuración de formas de pago de la tienda on-line
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
        <title><bean:message key="titulo.modificar_cfg" /></title>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/tienda/formapago.do?metodo=mostrar_listado" ><bean:message key="ruta.admin_tienda_pago" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_tienda_pago" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.modificar_formapago" /></h1>                
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.modificar_formapago" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/tienda/formapago/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.activarPayPal" /></p>
                                    <p class="etiqueta"><bean:message key="texto.precioPayPal" /></p>
                                    <p class="etiqueta"><bean:message key="texto.correoPayPal" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activarReembolso" /></p> 
                                    <p class="etiqueta"><bean:message key="texto.precioReembolso" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activarTransferencia" /></p>
                                    <p class="etiqueta"><bean:message key="texto.precioTransferencia" /></p>
                                    <p class="etiqueta"><bean:message key="texto.numCuenta" /></p>
                                    <p class="etiqueta"><bean:message key="texto.activarEfectivo" /></p>
                                    <p class="etiqueta"><bean:message key="texto.precioEfectivo" /></p>
                                </div>
                                <div class="valores">
                                    <html:checkbox property="sactivarPayPal" styleClass="check"></html:checkbox>
                                    <html:text property="sprecioPayPal" maxlength="10" />
                                    <html:text property="correoPayPal" maxlength="150" />
                                    <html:checkbox property="sactivarReembolso" styleClass="check"></html:checkbox>
                                    <html:text property="sprecioReembolso" maxlength="10" />
                                    <html:checkbox property="sactivarTransferencia" styleClass="check"></html:checkbox> 
                                    <html:text property="sprecioTransferencia" maxlength="10" />
                                    <html:text property="numCuenta"  maxlength="25" />     
                                    <html:checkbox property="sactivarEfectivo" styleClass="check"></html:checkbox>      
                                    <html:text property="sprecioEfectivo" maxlength="10" />            
                                </div>
                                <div id="botones">
                                    <html:hidden property="id" value="${id}" />                    
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

