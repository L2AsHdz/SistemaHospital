<%-- 
    Document   : consultasPorMedico
    Created on : 5/10/2020, 16:56:45
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
        <jsp:include page="/WEB-INF/paciente/navBarPaciente.jsp"/>

        <!-- Consultas con un medico -->
        <div class="container-fluid mt-4 pl-4">
            <div class="row">
                <div class="col-xl-10 tipo">
                    <div class="mb-4">
                        <h4>Consultas realizadas con un medico</h4>
                    </div>
                    <form id="form-examenesT" class="form-inline ml-3" action="${pageContext.request.contextPath}/ReportesPacienteServlet?accion=reporte4" method="POST"> 
                        <label for="codMedico" class="mr-sm-2">Codigo del medico:</label>
                        <input type="text" class="form-control mb-2 mr-sm-2"  name="codMedico" value="${codMedico}" placeholder="codigo del medico">
                        <label for="fechaInicial" class="mr-sm-2">Fecha inicial:</label>
                        <input type="date" class="form-control mb-2 mr-sm-2"  name="fechaInicial" value="${fechaInicial}">
                        <label for="fechaFinal" class="mr-sm-2">Fecha final:</label>
                        <input type="date" class="form-control mb-2 mr-sm-2" name="fechaFinal" value="${fechaFinal}">
                        <button type="reset" class="btn btn-secondary mb-2 ml-2">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                        <button type="submit" class="btn btn-primary mb-2 ml-2">Ver consultas</button>
                    </form>
                </div>
            </div>

            <c:if test="${!empty(error)}">
                <div class="row mt-2">
                    <div class="col-xl-5">
                        <div class="alert alert-danger alert-dismissible mt-2">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            ${error}
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="row mt-4">
                <div class="col-xl-10">
                    <c:choose>
                        <c:when test="${!empty(consultasByMedico)}">
                            <div class="card">
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>#</th>
                                                <th>Medico</th>
                                                <th>Especialidad</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                                <th>Costo</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="consulta" items="${consultasByMedico}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td>${consulta.medico}</td>
                                                    <td>${consulta.especialidad}</td>
                                                    <td>${consulta.fecha}</td>
                                                    <td>${consulta.hora}</td>
                                                    <td><fmt:formatNumber value="${consulta.costo}" type="currency" /></td>
                                                    <td>
                                                        <a class="btn btn-info" data-toggle="modal" data-target="#modal-informe"
                                                           onclick="$('#contenidoInforme').text('${consulta.informe}')">
                                                            <i class="fas fa-eye"></i> Ver informe
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>

                            <div class="modal" id="modal-informe">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h5>Informe:</h5>
                                            <p id="contenidoInforme"></p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <c:if test="${buscado}">
                                <h4 class="mt-4">No tiene consultas con el medico ingresado</h4>
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
