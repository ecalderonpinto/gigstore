<%-- 
    Document   : script_confirmacion
    Created on : 04/04/2012, 10:21:00
    Author     : Manel Márquez Bonilla
--%>

<%@tag description="script de confirmacion para acciones" pageEncoding="UTF-8"%>

<SCRIPT>
    function confirmar(mensaje){
        if(!confirm(mensaje)){
            return false; //no se borra
        }else{
            return true;//si se borra
        }
    }
</SCRIPT> 