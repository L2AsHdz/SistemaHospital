<%-- 
    Document   : inicioAdmin
    Created on : 29/09/2020, 03:19:46
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${user.codigo} - ${user.nombre}</title>
        
        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>
        <!--Barra de navegacion-->
        <jsp:include page="/WEB-INF/admin/navBarAdmin.jsp" />
        
        
        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </body>
</html>
