<%-- 
    Document   : pie
    Created on : 06/03/2012, 17:39:40
    Author     : Manel Márquez Bonilla
--%>

<%@tag description="Pie común para todas las páginas" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>

<div id="pie">
    <div id="bloques">
        <div class="bloque">
            <h3><bean:message key="titulo.informacion" /></h3>
            <ul>
                <li><a href="${pageContext.request.contextPath}/info/contactar/" ><bean:message key="enlace.contacto" /></a></li>
                <li><a href="${pageContext.request.contextPath}/info/conocenos/" ><bean:message key="enlace.conocenos" /></a></li>
                <li><a href="${pageContext.request.contextPath}/info/mapa-del-web/" ><bean:message key="enlace.mapa" /></a></li>
            </ul>
        </div>
        <div class="bloque">
            <h3><bean:message key="titulo.novedades" /></h3>
            <ul>
                <li><a href="${pageContext.request.contextPath}/recomendaciones/destacados/" ><bean:message key="enlace.destacados" /></a></li>
                <li><a href="${pageContext.request.contextPath}/recomendaciones/vendidos/" ><bean:message key="enlace.vendidos" /></a></li>
                <li><a href="${pageContext.request.contextPath}/recomendaciones/nuevos/" ><bean:message key="enlace.nuevos" /></a></li>
            </ul>
        </div>
        <div class="bloque">
            <h3><bean:message key="titulo.legal" /></h3>
            <ul>
                <li><a href="${pageContext.request.contextPath}/info/privacidad-de-datos/" ><bean:message key="enlace.privacidad" /></a></li>
                <li><a href="${pageContext.request.contextPath}/info/condiciones-de-venta/" ><bean:message key="enlace.condiciones" /></a></li>
                <li><a href="${pageContext.request.contextPath}/info/costes-del-envio/" ><bean:message key="enlace.costes" /></a></li>
            </ul>
        </div>
        <div class="bloque">
            <h3><bean:message key="titulo.contacto" /></h3>
            <ul>
                <li>${txt.secciones.contactarDias}</li>
                <li>${txt.secciones.contactarHorario}</li>
                <li>${txt.secciones.contactarTelefono}</li>
            </ul>
        </div>
    </div>
    <div id="copy">
        <p>Copyright (c) 2012 Brillantetes.com. All rights reserved. Design by <a href="http://www.gignomai.es/" target="_blank">Gignomai CSL</a>.</p>
    </div>    
    <!--script type="text/javascript">
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-41769068-1']);
        _gaq.push(['_trackPageview']);

        (function() {
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
        })();
    </script -->
</div>