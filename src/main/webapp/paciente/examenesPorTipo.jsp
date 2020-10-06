<%-- 
    Document   : examenesPorTipo
    Created on : 5/10/2020, 14:38:05
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



        <!-- Examenes por tipo -->
        <div class="container-fluid mt-4 pl-4">
            <div class="row">
                <div class="col-xl-10 tipo">
                    <div class="mb-4">
                        <h4>Examenes realizados por tipo</h4>
                    </div>
                    <form id="form-examenesT" class="form-inline ml-3" action="${pageContext.request.contextPath}/ReportesPacienteServlet?accion=reporte2" method="POST"> 
                        <label for="codTipoExamen" class="mr-sm-2">Tipo de examen:</label>
                        <select class="form-control mb-2 mr-sm-2" name="codTipoExamen">
                            <c:forEach var="tipo" items="${tiposExamen}">
                                <option value="${tipo.codigo}">${tipo.nombre}</option>
                            </c:forEach>
                        </select>
                        <label for="fechaInicial" class="mr-sm-2">Fecha inicial:</label>
                        <input type="date" class="form-control mb-2 mr-sm-2"  name="fechaInicial" value="${fechaInicial}">
                        <label for="fechaFinal" class="mr-sm-2">Fecha final:</label>
                        <input type="date" class="form-control mb-2 mr-sm-2" name="fechaFinal" value="${fechaFinal}">
                        <button type="reset" class="btn btn-secondary mb-2 ml-2">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                        <button type="submit" class="btn btn-primary mb-2 ml-2">Ver examenes</button>
                    </form>
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-xl-10">
                    <c:choose>
                        <c:when test="${!empty(resultadosByTipo)}">
                            <div class="card">
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

                                            <c:forEach var="resultado" items="${resultadosByTipo}" varStatus="status">
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
                                                        <a class="btn btn-info" data-toggle="modal" data-target="#modal-resultado"
                                                            onclick="$('#content-resultado').attr('src','${pageContext.request.contextPath}/images/${resultado.codigoExamen}')">
                                                            <i class="fas fa-eye"></i> Ver resultado
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>

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
                                <h4 class="mt-4">No hay examenes que cumplan con los datos ingresados</h4>
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