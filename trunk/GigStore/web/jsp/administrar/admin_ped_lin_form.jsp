<%-- 
    Document   : admin_ped_lin_form
    Created on : 07/11/2012, 18:27:28
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario alta/modificacion de las líneas del pedido
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<!DOCTYPE html>
<html:html xhtml="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <c:if test="${accion=='alta'}">
                <bean:message key="titulo.alta_linped" />
            </c:if>
            <c:if test="${accion=='editar'}">
                <bean:message key="titulo.editar_linped" />
            </c:if>
        </title>
        <script language="JavaScript">
            function filtrarOpciones(idProd){
                var listaopts=document.forms['administrarPedidoLineaForm'].elements[1];
               
                for(var i=1;i<listaopts.length;i++){
                    var idopt=listaopts.options[i].value.split(";")[1];
                    if(idopt!=idProd){
                        listaopts.options[i].style.display="none";
                        listaopts.options[0].selected=true;
                    }else{
                        listaopts.options[i].style.display="list-item";
                    }
                }
            }
        </script>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/pedidos.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_ped" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/pedidos/detalle.do?metodo=mostrar_detalle&id=${id}" ><bean:message key="ruta.detalle_ped" /></a> &gt </li>
                        <li><bean:message key="ruta.editar_linped" /></li>
                    </ul>
                </div>
                <h1>
                    <c:if test="${accion=='alta'}">
                        <bean:message key="titulo.alta_linped" />
                    </c:if>
                    <c:if test="${accion=='editar'}">
                        <bean:message key="titulo.editar_linped" />
                    </c:if>
                </h1>    
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="ruta.editar_linped" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            <html:form action="/administrar/pedidos/lienas/alta" acceptCharset="ISO-8859-1">
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.referencia" /> </p>                     
                                    <p class="etiqueta"><bean:message key="titulo.opciones" /></p>
                                    <p class="etiqueta"><bean:message key="texto.unidades" /></p>
                                </div>
                                <div class="valores">
                                    <div class="styled-select">
                                        <html:select property="sproducto" value="${productoSel}" onchange="filtrarOpciones(this.value)">
                                            <html:option value="0"><bean:message key="texto.eligeuno" /></html:option>
                                            <c:forEach var="p" items="${productos}">
                                                <html:option value="${p.id}">${p.referencia} - ${p.nombre}</html:option>
                                            </c:forEach>
                                        </html:select>    
                                    </div>
                                    <div class="styled-select">
                                        <html:select property="sopcion" value="${opcionSel}">
                                            <html:option value="0;0"><bean:message key="texto.eligeuno" /></html:option>
                                            <c:forEach var="o" items="${opciones}">
                                                <html:option value="${o.id};${o.producto.id}">${o.opcion}</html:option>
                                            </c:forEach>
                                        </html:select>
                                    </div>
                                    <html:text property="scantidad" /><br />            
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
</html:html>