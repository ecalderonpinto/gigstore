<%-- 
    Document   : admin_tienda_form
    Created on : 08/05/2012, 18:23:23
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario para configuración general de la tienda on-line
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
                        <li><a href="${pageContext.request.contextPath}/administrar/tienda.do?metodo=mostrar_listado" ><bean:message key="ruta.admin_tienda" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_cfg_tienda" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.modificar_cfg" /></h1>                
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.variables_tienda" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/tienda/configuracion/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.nombreempresa" /></p>
                                    <p class="etiqueta"><bean:message key="texto.direccionempresa" /></p>
                                    <p class="etiqueta"><bean:message key="texto.telefonoempresa" /></p> 
                                    <p class="etiqueta"><bean:message key="texto.emailsistema" /></p>
                                    <p class="etiqueta"><bean:message key="texto.idioma" /></p>
                                    <p class="etiqueta"><bean:message key="texto.nregs" /></p>
                                    <p class="etiqueta"><bean:message key="texto.monedasistema" /></p>
                                    <p class="etiqueta"><bean:message key="texto.ocultarProducto" /></p> 
                                    <p class="etiqueta"><bean:message key="texto.ocultarBotonCompra" /></p> 
                                    <p class="etiqueta"><bean:message key="texto.stockMin" /></p>
                                    <p class="etiqueta"><bean:message key="texto.mostrarIVAIncluido" /></p>
                                    <p class="etiqueta"><bean:message key="texto.usarDirFactura" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="nombreEmpresa" />
                                    <html:text property="direccionEmpresa"  maxlength="250" />
                                    <html:text property="telefonoEmpresa" maxlength="15" />
                                    <html:text property="emailSistema" maxlength="150" />

                                    <div class="styled-select">
                                        <html:select property="idioma" value="${idiomaSel}">
                                            <html:option value="0"><bean:message key="texto.eligeuno" /></html:option>
                                            <c:forEach var="entry" items="${locales}">
                                                <html:option value="${entry.key}">${entry.value}</html:option>
                                            </c:forEach>
                                        </html:select>
                                    </div>    
                                    <div class="styled-select">
                                        <html:select property="snRegsPag" value="${nRegsSel}">
                                            <html:option value="0"><bean:message key="texto.eligeuno" /></html:option>
                                            <html:option value="9">9</html:option>
                                            <html:option value="12">12</html:option>
                                            <html:option value="18">18</html:option>
                                            <html:option value="24">24</html:option>
                                        </html:select>   
                                    </div>    
                                    <div class="styled-select">
                                        <html:select property="moneda" value="${monedaSel}">
                                            <html:option value="0"><bean:message key="texto.eligeuno" /></html:option>
                                            <html:option value="EUR"><bean:message key="texto.euro" /></html:option>
                                            <html:option value="GBP"><bean:message key="texto.libra" /></html:option>
                                            <html:option value="USD"><bean:message key="texto.dolar" /></html:option>
                                        </html:select>             
                                    </div>    
                                    <html:checkbox property="socultarProducto" styleClass="check"></html:checkbox>
                                    <html:checkbox property="socultarBotonCompra" styleClass="check"></html:checkbox>
                                    <html:text property="sstockMin"  maxlength="2" />
                                    <html:checkbox property="smostrarIVAIncluido" styleClass="check"></html:checkbox>
                                    <html:checkbox property="susarDirFactura" styleClass="check"></html:checkbox>   
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
