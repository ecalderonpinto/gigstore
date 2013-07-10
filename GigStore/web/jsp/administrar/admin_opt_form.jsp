<%-- 
    Document   : admin_opt_form
    Created on : 28/05/2013, 21:05:20
    Author     : Manel Márquez Bonilla
    Descripcion: Formulario editar opciones
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <c:if test="${accion=='alta'}">
                <bean:message key="titulo.alta_opcion" />
            </c:if>
            <c:if test="${accion=='editar'}">
                <bean:message key="titulo.modificar_opcion" />
            </c:if>
        </title>
        <script type="text/javascript">
            // Free JavaScript course - coursesweb.net

            // create the object with methods to add and delete <option></option>
            var adOption = new Object();
            // method that check if the option is already in list
            // receives the id of the <select></select> list, and box with the value for <option>
            adOption.checkList = function(list, optval) {
                var re = 0;           // variable that will be returned

                // get the <option> elements
                var opts = document.getElementById(list).getElementsByTagName('option');

                // if the option exists, sets re=1
                for(var i=0; i<opts.length; i++) {
                if(opts[i].value == document.getElementById(optval).value) {
                    re = 1;
                    break;
                }
                }

                return re;         // return the value of re
            };

            // method that adds <option>
            adOption.addOption = function(list, optval) {
                // gets the value for <option>
                var opt_val = document.getElementById(optval).value;

                // check if the user adds a value
                if(opt_val.length > 0) {
                // check if the value not exists (when checkList() returns 0)
                if(this.checkList(list, optval) == 0) {
                    // define the <option> element and adds it into the list
                    var myoption = document.createElement('option');
                    myoption.value = opt_val;
                    myoption.innerHTML = opt_val.split(";")[1];
                    document.getElementById(list).insertBefore(myoption, document.getElementById(list).firstChild);

                    document.getElementById(optval).value = '';           // delete the value added in text box
                }
                else alert('The value "'+opt_val+'" already added');
                }
                else alert('Add a value for option');
            };

            // method that delete the <option>
            adOption.delOption = function(list, optval) {
                // gets the value of <option> that must be deleted
                var opt_val = document.getElementById(optval).value;

                // check if the value exists (when checkList() returns 1)
                if(this.checkList(list, optval) == 1) {
                // gets the <option> elements in the <select> list
                var opts = document.getElementById(list).getElementsByTagName('option');

                // traverse the array with <option> elements, delete the option with the value passed in "optval"
                for(var i=0; i<opts.length; i++) {
                    if(opts[i].value == opt_val) {
                    document.getElementById(list).removeChild(opts[i]);
                    break;
                    }
                }
                }
                else alert('The value "'+opt_val+'" not exist');
            }

            // method that adds the selected <option> in text box
            adOption.selOpt = function(opt, txtbox) { document.getElementById(txtbox).value = opt.split(";")[1]; }

            // method that adds the selected <option> in text box
            adOption.selOptIdioma = function(opt, txtbox) { document.getElementById(txtbox).value = opt + ';'; }
            
            function selectAll(selectBox,selectAll) { 
                // have we been passed an ID 
                if (typeof selectBox == "string") { 
                    selectBox = document.getElementById(selectBox);
                } 
                // is the select box a multiple select box? 
                if (selectBox.type == "select-multiple") { 
                    for (var i = 0; i < selectBox.options.length; i++) { 
                        selectBox.options[i].selected = selectAll; 
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
                        <li><a href="${pageContext.request.contextPath}/administrar/productos.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_prod" /></a> &gt </li>
                        <li><a href="${pageContext.request.contextPath}/administrar/opciones.do?metodo=mostrar_listado" ><bean:message key="ruta.lista_opts" /></a> &gt </li>
                        <c:if test="${accion=='editar'}">
                            <li><a href="${pageContext.request.contextPath}/administrar/opciones/detalle.do?metodo=mostrar_detalle&id=${id}" ><bean:message key="ruta.detalle_opt" /></a> &gt </li>
                        </c:if>
                        <li><bean:message key="ruta.editar_opcion" /></li>
                    </ul>
                </div>
                <h1>
                    <c:if test="${accion=='alta'}">
                        <bean:message key="titulo.alta_opcion" />
                    </c:if>
                    <c:if test="${accion=='editar'}">
                        <bean:message key="titulo.modificar_opcion" />
                    </c:if>
                </h1>    
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.modificar_opcion" /></span></p>
                    <div class="entrada">
                        <div id="formulario">
                            <html:errors />
                            
                            <html:form action="/administrar/opciones/alta" acceptCharset="ISO-8859-1" >
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.opcion" /></p>
                                    <p class="etiqueta"><bean:message key="texto.descripcion" /></p>
                                </div>
                                <div class="valores">
                                    <html:text property="opcion" />
                                    <html:textarea property="descripcion" />
                                </div>
                                    
                                <div class="campos">
                                    <p class="etiqueta"><bean:message key="texto.traduccion" /></p>
                                    <p class="etiqueta"><bean:message key="texto.idioma" /></p>
                                    <p class="etiqueta"><bean:message key="texto.traducciones" /></p>
                                </div>
                                <div class="valores">
                                    <input type="text" name="optval" id="optval" />
                                    <div class="styled-select">
                                        <html:select property="idioma" value="${idiomaSel}" onchange="adOption.selOptIdioma(this.value, 'optval')">
                                            <html:option value="0"><bean:message key="texto.eligeuno" /></html:option>
                                            <c:forEach var="entry" items="${locales}">
                                                <html:option value="${entry.key}">${entry.value}</html:option>
                                            </c:forEach>
                                        </html:select>
                                    </div>    
                                    <html:select property="sel_list" multiple="true" styleId="sel_list" size="12" onchange="adOption.selOpt(this.value, 'optval')">
                                        <c:forEach var="trad" items="${trads}">
                                            <c:set var="parts" value="${fn:split(trad, ';')}" />
                                            <html:option value="${trad}">${parts[1]}</html:option>
                                        </c:forEach>
                                    </html:select>
                                    <input type="button" id="addopt" name="addopt" value="<bean:message key="boton.add_trad" />" onclick="adOption.addOption('sel_list', 'optval');" /> &nbsp;
                                    <input type="button" id="del_opt" name="del_opt" value="<bean:message key="boton.del_trad" />" onclick="adOption.delOption('sel_list', 'optval');" />
                                </div>
                                
                                <div id="botones">
                                    <c:if test="${accion=='alta'}">
                                        <html:hidden property="metodo" value="alta_ejecutar" />
                                        <html:submit styleClass="boton" onclick="selectAll('sel_list',true)"><bean:message key="boton.alta" /></html:submit>
                                    </c:if>
                                    <c:if test="${accion=='editar'}">
                                        <html:hidden property="metodo" value="editar_ejecutar" />
                                        <html:submit styleClass="boton" onclick="selectAll('sel_list',true)"><bean:message key="boton.modificar" /></html:submit>
                                    </c:if>

                                    <html:reset styleClass="boton" />
                                    <html:cancel styleClass="boton" />
                                </div                                
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