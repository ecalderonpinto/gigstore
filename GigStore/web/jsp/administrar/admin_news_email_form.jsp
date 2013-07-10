<%-- 
    Document   : admin_news_email_form
    Created on : 29/08/2012, 10:33:26
    Author     : Manel Márquez Bonilla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="ctrl" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="titulo.enviar_news" /></title>
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
                        <li><a href="${pageContext.request.contextPath}/administrar/listacorreo.do?metodo=mostrar_listado" ><bean:message key="enlace.ges_news" /></a> &gt </li>
                        <li><bean:message key="titulo.enviar_news" /></li>
                    </ul>
                </div>
                <h1><bean:message key="titulo.enviar_news" /></h1>  
                <div class="cuadro">
                    <p class="meta"><span class="izda"></span><span class="dcha"><bean:message key="titulo.enviar_news" /></span></p>
                    <div class="entrada">
                        <p id="errorenviaremail">${error_enviaremail}</p>
                        <form method="post" action="${pageContext.request.contextPath}/administrar/listacorreo/enviar_boletin.do">
                            <div>
                                <textarea name="texto_email" >
                                    <a href="http://brillantetes.sytes.net" style="text-decoration:none;"><img src="http://www.gignomai.es/ejemplo-tienda/images/atmosphere/tit.jpg" alt="Brillantetes.es" /></a>
                                    <h2 style="color: #3A4648;font-size: 2em;font-family: Arial, Helvetica, sans-serif;">Boletín de noticias de Brillantetes.es</h2>
                                    <div style="width:600px;color: #3A4648;font-size: 1.1em;font-family: Arial, Helvetica, sans-serif;text-align:justify;">
                                        <p>Escriba aquí el texto</p>
                                        <p>Gracias por su confianza,</p><p>El equipo de Brillantetes.es</p>
                                        <a href="http://brillantetes.sytes.net" style="text-decoration:none;color: #8EAAB0;font-family: Arial, Helvetica, sans-serif;">Brillantetes.es</a>
                                        <br/>
                                        <p style="font-size:0.8em;">Si este correo no es solicitado responda a <a href="mailto:brillantetes@gmail.com" style="text-decoration: none;color: #8EAAB0;font-family: Arial, Helvetica, sans-serif;">brillantetes@gmail.com</a> para ser dado de baja de nuestra Base de Datos, o póngase en contacto con nosotros en el tlf. 918542337. </p>
                                    </div>
                                </textarea>
                                <input type="hidden" name="metodo" value="enviar_email" />
                                <input type="submit" class="boton" value="<bean:message key="boton.enviar" />" onclick="javascript:return confirmar('<bean:message key="texto.enviar_news" />')" />
                            </div>
                        </form>
                    </div>
                    <p class="links"></p>
                </div>                
            </div>
        </div>        
        <ctrl:pie />
        <ckeditor:replaceAll basePath="${pageContext.request.contextPath}/ckeditor/" config="${settings}" />     
    </body>
</html>