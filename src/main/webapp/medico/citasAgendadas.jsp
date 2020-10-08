<%-- 
    Document   : citasAgendadas
    Created on : 5/10/2020, 01:09:07
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <div class="container-fluid mt-5 pl-4">
            <div class="row">
                <div class="col-xl-10">
                    <div class="mb-4">
                        <h4>Consultas agendadas en un intervalo de tiempo</h4>
                    </div>
                    <form class="form-inline ml-3" action="${pageContext.request.contextPath}/ReportesMedicoServlet?accion=reporte1" method="POST"> 
                        <label class="mr-sm-4 font-weight-bold">
                            <h5>Intervalo de fecha:</h5>
                        </label>
                        <label for="fechaInicial" class="mr-sm-2">Fecha inicial</label>
                        <input type="date" class="form-control mb-2 mr-sm-2"  name="fechaInicial" id="fechaInicial" value="${fechaInicial}" autofocus>
                        <label for="fechaFinal" class="mr-sm-2">Fecha final:</label>
                        <input type="date" class="form-control mb-2 mr-sm-2" name="fechaFinal" value="${fechaFinal}">
                        <button type="reset" class="btn btn-secondary mb-2 ml-2" onclick="$('#fechaInicial').focus()">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                        <button type="submit" class="btn btn-primary mb-2 ml-2">Ver citas</button>
                    </form>
                </div>
            </div>

            <div class="row pt-4">
                <div class="col-xl-7">

                    <!-- Citas agendadas en un intervalo de tiempo -->
                    <c:choose>
                        <c:when test="${!empty(consultas)}">
                            <div class="card">
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>#</th>
                                                <th>Paciente</th>
                                                <th>Especialidad</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="consulta" items="${consultas}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td>${consulta.nombrePaciente}</td>
                                                    <td>${consulta.nombreEspecialidad}</td>
                                                    <td>${consulta.fecha}</td>
                                                    <td>${consulta.hora}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${buscado}">
                                <h4>No hay consultas entre las fechas ingresadas</h4>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </body>
</html>
