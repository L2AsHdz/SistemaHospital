<%-- 
    Document   : pendientes
    Created on : 3/10/2020, 20:37:08
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

        <!-- Consultas pendientes -->
        <div class="container-fluid my-5 pl-4">
            <div class="row">
                <div class="col-xl-10">
                    <c:choose>
                        <c:when test="${!empty(consultasP)}">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Consultas pendientes</h4>
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
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="consulta" items="${consultasP}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td>${consulta.nombreMedico}</td>
                                                    <td>${consulta.nombreEspecialidad}</td>
                                                    <td>${consulta.fecha}</td>
                                                    <td>${consulta.hora}</td>
                                                    <td><fmt:formatNumber value="${consulta.total}" type="currency" /></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h4>No hay consultas pendientes</h4>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!-- Examenes pendientes -->
        <div class="container-fluid my-5 pl-4">
            <div class="row">
                <div class="col-xl-10">
                    <c:choose>
                        <c:when test="${!empty(examenesP)}">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Examenes pendientes</h4>
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>#</th>
                                                <th>Medico</th>
                                                <th>Examen</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                                <th>Costo</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="examen" items="${examenesP}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <c:choose>
                                                        <c:when test="${empty(examen.nombreMedico)}">
                                                            <td>Sin medico</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>${examen.nombreMedico}</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>${examen.nombreTipoExamen}</td>
                                                    <td>${examen.fecha}</td>
                                                    <td>${examen.hora}</td>
                                                    <td><fmt:formatNumber value="${examen.total}" type="currency" /></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h4>No hay examenes pendientes</h4>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </body>
</html>
