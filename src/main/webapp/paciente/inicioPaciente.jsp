<%-- 
    Document   : inicioPaciente
    Created on : 29/09/2020, 03:20:57
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
        <!-- Barra de navegacion -->
        <jsp:include page="/WEB-INF/paciente/navBarPaciente.jsp"/>
        
        <h4>Su codigo de paciente es: ${user.codigo}</h4>
        
        <c:if test="${success}" >
            <div class="container py-3">
                <div class="row">
                    <div class="col-xl-6">
                        <div class="alert alert-success alert-dismissible mt-2">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            El examen se agendo correctamente
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </body>
</html>