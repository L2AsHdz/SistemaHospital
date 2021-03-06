<%-- 
    Document   : historialMedico
    Created on : 4/10/2020, 18:53:17
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

        <div class="container-fluid">
            <div class="row mt-3 mb-3 ml-1">
                <div class="col-xl-10">
                    <h4>Buscar hitorial medico de un paciente:</h4>
                </div>
            </div>

            <!-- Busqueda del paciente -->
            <div class="row">
                <div class="col-xl-10 text-">
                    <form class="form-inline ml-3" action="${pageContext.request.contextPath}/HistorialMedicoServlet?accion=buscarHistorial" method="POST"> 
                        <label class="mr-sm-4 font-weight-bold">
                        </label>
                        <label for="codPaciente" class="mr-sm-2">Codigo del paciente:</label>
                        <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Codigo del paciente" name="codPaciente" value="${codPaciente}" autofocus>
                        <button type="submit" class="btn btn-primary mb-2 ml-2">Buscar</button>
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

            <!-- Historial medico -->
            <!-- Consultas realizadas -->
            <div class="row my-5 pl-4">
                <div class="col-xl-10">
                    <c:choose>
                        <c:when test="${!empty(informes)}">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Consultas realizadas</h4>
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>#</th>
                                                <th>Paciente</th>
                                                <th>Medico</th>
                                                <th>Especialidad</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="informe" items="${informes}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td>${informe.paciente}</td>
                                                    <td>${informe.medico}</td>
                                                    <td>${informe.especialidad}</td>
                                                    <td>${informe.fecha}</td>
                                                    <td>${informe.hora}</td>
                                                    <td>
                                                        <a class="btn btn-info" data-toggle="modal" data-target="#modal-informe"
                                                           onclick="$('#contenidoInforme').text('${informe.informe}')">
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
                                <h4>El paciente no tiene consultas</h4>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>


            <!-- examenes realizados -->
            <div class="row my-5 pl-4">
                <div class="col-xl-10">
                    <c:choose>
                        <c:when test="${!empty(resultados)}">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Examenes realizados</h4>
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>#</th>
                                                <th>Paciente</th>
                                                <th>Medico</th>
                                                <th>Laboratorista</th>
                                                <th>Examen</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="resultado" items="${resultados}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td>${resultado.paciente}</td>
                                                    <c:choose>
                                                        <c:when test="${empty(resultado.medico)}">
                                                            <td>Sin medico</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>${resultado.medico}</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>${resultado.laboratorista}</td>
                                                    <td>${resultado.tipoExamen}</td>
                                                    <td>${resultado.fecha}</td>
                                                    <td>${resultado.hora}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${resultado.tipoInforme eq 'PDF'}">
                                                                <a class="btn btn-danger" href="${pageContext.request.contextPath}/pdfR?codExamen=${resultado.codigoExamen}" target="_blank">
                                                                    <i class="fas fa-file-pdf"></i> Ver resultado
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a class="btn btn-info" data-toggle="modal" data-target="#modal-resultado"
                                                                   onclick="$('#content-resultado').attr('src', '${pageContext.request.contextPath}/images/${resultado.codigoExamen}')">
                                                                    <i class="fas fa-eye"></i> Ver resultado
                                                                </a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                            <!-- Modal resultado -->
                            <div class="modal" id="modal-resultado">
                                <div class="modal-dialog modal-lg modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h5>Resultado:</h5>
                                            <img id="content-resultado" style="width:100%; height:100%;">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${buscado}">
                                <h4>El paciente no tiene examenes</h4>
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
