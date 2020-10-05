<%-- 
    Document   : historialMedico
    Created on : 4/10/2020, 18:53:17
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_GT" />
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
        <jsp:include page="/WEB-INF/medico/navBarMedico.jsp"/>
        
        <div class="container-fluid">
            <div class="row mt-3 mb-3 ml-1">
                <div class="col-xl-10">
                    <h3>His</h3>
                </div>
            </div>

            <!-- Filtros de busqueda -->
            <div class="row">
                <div class="col-xl-10 text-">
                    <form class="form-inline ml-3" action="${pageContext.request.contextPath}/ConsultaServlet?accion=buscar" method="POST"> 
                        <label class="mr-sm-4 font-weight-bold">
                            <h5>Buscar medicos por:</h5>
                        </label>
                        <label for="nombre" class="mr-sm-2">Nombre:</label>
                        <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Nombre del medico" name="nombre" value="${nombre}" autofocus>
                        <label for="especialidad" class="mr-sm-2">Especialidad:</label>
                        <label for="hora" class="mr-sm-2">Hora:</label>
                        <input type="time" class="form-control mb-2 mr-sm-2" name="hora" value="${hora}">
                        <button type="submit" class="btn btn-primary mb-2 ml-2">Buscar</button>
                    </form>
                </div>
            </div>
        </div>
        
        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </body>
</html>
