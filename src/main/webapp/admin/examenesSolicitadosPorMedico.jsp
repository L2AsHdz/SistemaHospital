<%-- 
    Document   : examenesSolicitadosPorMedico
    Created on : 6/10/2020, 11:41:30
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
        <jsp:include page="/WEB-INF/admin/navBarAdmin.jsp"/>

        <div class="container-fluid mt-5 pl-4">
            <div class="row">
                <div class="col-xl-10">
                    <div class="mb-4">
                        <h4>Medicos con mayor cantidad de examenes solicitados</h4>
                    </div>
                    <form class="form-inline ml-3" action="${pageContext.request.contextPath}/ReportesAdminServlet?accion=reporte5" method="POST"> 
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
                        <button type="submit" class="btn btn-primary mb-2 ml-2">Ver medicos</button>
                    </form>
                </div>
            </div>

            <div class="row pt-4">
                <div class="col-xl-8">

                    <!-- Medicos con mayor cantidad de examenes solicitados -->
                    <c:choose>
                        <c:when test="${!empty(medicosExamenes)}">
                            <div class="card">
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Codigo</th>
                                                <th>nombre</th>
                                                <th>Solicitados</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="medico" items="${medicosExamenes}">
                                                <tr>
                                                    <td>${medico.codigo}</td>
                                                    <td>${medico.nombre}</td>
                                                    <td>${medico.examenes}</td>
                                                    <td>
                                                        <a class="btn btn-info" data-toggle="modal" data-target="#te${medico.codigo}">
                                                            <i class="fas fa-eye"></i> Ver examenes solicitados
                                                        </a>

                                                        <div class="modal" id="te${medico.codigo}">
                                                            <div class="modal-dialog modal-dialog-centered">
                                                                <div class="modal-content">
                                                                    <div class="modal-body">
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                        <c:forEach var="examen" items="${medico.examenesSolicitados}">
                                                                            <p>${examen.demandas} - ${examen.nombre}</p>
                                                                        </c:forEach>
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
                        </c:when>
                        <c:otherwise>
                            <c:if test="${buscado}">
                                <h4>No hay examenes solicitados en el intervalo de tiempo dado</h4>
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
