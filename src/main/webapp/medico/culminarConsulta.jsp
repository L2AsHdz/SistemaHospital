<%-- 
    Document   : culminarConsulta
    Created on : 4/10/2020, 08:24:41
    Author     : asael
--%>

<%@page import="model.consulta.Consulta"%>
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

        <!-- Consultas pendientes -->
        <c:if test="${!empty(consultasToday)}">
            <div class="container-fluid my-5 pl-4">
                <div class="row">
                    <div class="col-xl-10">
                        <div class="card">
                            <div class="card-header">
                                <h4>Consultas pendientes</h4>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>#</th>
                                            <th>Paciente</th>
                                            <th>Especialidad</th>
                                            <th>Fecha</th>
                                            <th>Hora</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="consulta" items="${consultasToday}" varStatus="status">
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${consulta.nombrePaciente}</td>
                                                <td>${consulta.nombreEspecialidad}</td>
                                                <td>${consulta.fecha}</td>
                                                <td>${consulta.hora}</td>
                                                <td>
                                                    <a class="btn btn-secondary" data-toggle="modal" data-target="#modal-informe" onclick="$('#codConsulta').val(${consulta.codigo})">
                                                        <i class="fas fa-eye"></i> Agregar informe
                                                    </a>
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

        <div class="modal" id="modal-informe">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-body">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h5>Agregar informe:</h5>
                        <form action="${pageContext.request.contextPath}/CulminarConsultaServlet?accion=finalizar"
                              id="form-informe"  method="POST">
                            <input type="text" class="form-control d-none" name="codConsulta" id="codConsulta">
                            <div class="form-group">
                                <label for="informe">*Informe</label>
                                <textarea type="textarea" rows="5" class="form-control" name="informe"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="hora">*Hora:</label>
                                <input type="time" class="form-control" name="hora" id="hora">
                            </div>
                            <div class="form-group">
                                <label for="fecha">*Fecha:</label>
                                <input type="date" class="form-control" name="fecha">
                            </div>
                            <div>
                                <button id="boton" type="submit" class="btn btn-primary btn-block">Finalizar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarInforme.js"></script>
    </body>
</html>
