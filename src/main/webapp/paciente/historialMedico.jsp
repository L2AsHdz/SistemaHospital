<%-- 
    Document   : historialMedico
    Created on : 3/10/2020, 18:51:49
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

        <!-- Historial medico -->
        <!-- Consultas realiadas -->
        <c:if test="${!empty(informes)}">
            <div class="container-fluid my-5 pl-4">
                <div class="row">
                    <div class="col-xl-10">
                        <div class="card">
                            <div class="card-header">
                                <h4>Consultas realizadas</h4>
                            </div>
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

                                        <c:forEach var="informe" items="${informes}" varStatus="status">
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${informe.medico}</td>
                                                <td>${informe.especialidad}</td>
                                                <td>${informe.fecha}</td>
                                                <td>${informe.hora}</td>
                                                <td><fmt:formatNumber value="${informe.costo}" type="currency" /></td>
                                                <td>
                                                    <a class="btn btn-info" data-toggle="modal" data-target="#d${informe.codigoConsulta}">
                                                        <i class="fas fa-eye"></i> Ver informe
                                                    </a>

                                                    <div class="modal" id="d${informe.codigoConsulta}">
                                                        <div class="modal-dialog modal-dialog-centered">
                                                            <div class="modal-content">
                                                                <div class="modal-body">
                                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    <h5>Informe:</h5>
                                                                    ${informe.informe}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>	
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <!-- examenes realizados -->
        <c:if test="${!empty(resultados)}">
            <div class="container-fluid my-5 pl-4">
                <div class="row">
                    <div class="col-xl-10">
                        <div class="card">
                            <div class="card-header">
                                <h4>Examenes realizados</h4>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>#</th>
                                            <th>Medico</th>
                                            <th>Laboratorista</th>
                                            <th>Examen</th>
                                            <th>Fecha</th>
                                            <th>Hora</th>
                                            <th>Costo</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="resultado" items="${resultados}" varStatus="status">
                                            <tr>
                                                <td>${status.count}</td>
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
                                                <td><fmt:formatNumber value="${resultado.costo}" type="currency" /></td>
                                                <td>
                                                    <a class="btn btn-info" data-toggle="modal" data-target="#d${resultado.codigoExamen}">
                                                        <i class="fas fa-eye"></i> Ver resultado
                                                    </a>

                                                    <div class="modal" id="d${resultado.codigoExamen}">
                                                        <div class="modal-dialog modal-dialog-centered">
                                                            <div class="modal-content">
                                                                <div class="modal-body">
                                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    <h5>Resultado:</h5>
                                                                    Aqui ira la imagen
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>	
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </body>
</html>
