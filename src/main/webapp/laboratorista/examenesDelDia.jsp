<%-- 
    Document   : examenesDelDia
    Created on : 6/10/2020, 20:47:56
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
        <jsp:include page="/WEB-INF/laboratorista/navBarLab.jsp"/>

        <!-- Examenes pendientes -->
        <div class="container-fluid my-5 pl-4">
            <div class="row">
                <div class="col-xl-10">
                    <c:choose>
                        <c:when test="${!empty(examenesToday)}">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Examenes pendientes</h4>
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>#</th>
                                                <th>Paciente</th>
                                                <th>Medico</th>
                                                <th>Examen</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="examen" items="${examenesToday}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td>${examen.nombrePaciente}</td>
                                                    <td>${examen.nombreMedico}</td>
                                                    <td>${examen.nombreTipoExamen}</td>
                                                    <td>${examen.fecha}</td>
                                                    <td>${examen.hora}</td>
                                                    <td>
                                                        <c:if test="${!empty(examen.orden)}">
                                                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/pdf?codExamen=${examen.codigo}" target="_blank">
                                                                <i class="fas fa-file-pdf"></i> Ver orden
                                                            </a>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <a class="btn btn-primary" data-toggle="modal" data-target="#modal-resultado" 
                                                           data-controls-modal="modal-resultado" data-backdrop="static" data-keyboard="false"
                                                           onclick="$('#codExamen').val(${examen.codigo});$('#resultado').attr('accept', '${examen.tipoInforme}')">
                                                            <i class="fas fa-arrow-alt-circle-up"></i> Agregar resultado
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>

                            <div class="modal" id="modal-resultado">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <form action="${pageContext.request.contextPath}/CulminarExamenServlet?accion=finalizar"
                                              id="form-resultado"  method="POST" enctype="multipart/form-data">
                                            <div class="modal-body">
                                                <button type="button" form="form-informe" class="close" data-dismiss="modal" onclick="$('#limpiar').click()">&times;</button>
                                                <h5>Agregar resultado:</h5>
                                                <input type="text" class="form-control d-none" name="codExamen" id="codExamen">
                                                <div class="form-group">
                                                    <label for="resultado">*Resultado</label>
                                                    <input type="file" class="form-control-file border" name="resultado" id="resultado">
                                                </div>
                                                <div class="form-group">
                                                    <label for="hora">*Hora:</label>
                                                    <input type="time" class="form-control" name="hora">
                                                </div>
                                                <div class="form-group">
                                                    <label for="fecha">*Fecha:</label>
                                                    <input type="date" class="form-control" name="fecha">
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-primary btn-block">Finalizar</button>
                                                    <button id="limpiar" type="reset" class="btn btn-primary d-none">Limpiar</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <h4>No hay examenes agendados para el dia de hoy</h4>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarResultado.js"></script>
    </body>
</html>
