<%-- 
    Document   : buscarMedico
    Created on : 1/10/2020, 16:12:20
    Author     : asael
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <div class="container-fluid">
            <div class="row mt-3 mb-3 ml-1">
                <div class="col-xl-10">
                    <h3>Agendar nueva consulta</h3>
                </div>
            </div>

            <!-- Filtros de busqueda -->
            <div class="row">
                <div class="col-xl-10">
                    <form class="form-inline ml-3" action="${pageContext.request.contextPath}/ConsultaServlet?accion=buscar" method="POST"> 
                        <label class="mr-sm-4 font-weight-bold">
                            <h5>Buscar medicos por:</h5>
                        </label>
                        <label for="nombre" class="mr-sm-2">Nombre:</label>
                        <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Nombre del medico" name="nombre" value="${nombre}" autofocus>
                        <label for="especialidad" class="mr-sm-2">Especialidad:</label>
                        <select class="form-control mb-2 mr-sm-2" name="especialidad">
                            <option value="">Seleccione...</option>
                            <c:forEach var="esp" items="${especialidades}">
                                <option value="${esp.id}">${esp.nombre}</option>
                            </c:forEach>
                        </select>
                        <label for="hora" class="mr-sm-2">Hora:</label>
                        <input type="time" class="form-control mb-2 mr-sm-2" name="hora" value="${hora}">
                        <button type="submit" class="btn btn-primary mb-2 ml-2">Buscar</button>
                    </form>
                </div>
            </div>
            <c:if test="${success}" >
                <div class="row">
                    <div class="col-xl-6">
                        <div class="alert alert-success alert-dismissible mt-2">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            La consulta se agendo correctamente
                        </div>
                    </div>
                </div>
            </c:if>
        </div>

        <!-- Listado de medicos -->
        <c:if test="${medicos != null}">
            <div class="container-fluid mb-5 mt-3">
                <div class="row">
                    <div class="col-xl-12">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Codigo</th>
                                    <th>Nombre</th>
                                    <th>No Colegiado</th>
                                    <th>CUI</th>
                                    <th>Correo</th>
                                    <th>Hora entrada</th>
                                    <th>Hora salida</th>
                                    <th>Empezo a laborar</th>
                                    <th>Especialidades</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="medico" items="${medicos}">
                                <form action="${pageContext.request.contextPath}/ConsultaServlet?accion=preAgendar&codMedico=${medico.codigo}" method="POST">
                                    <tr>
                                        <td>${medico.codigo}</td>
                                        <td>${medico.nombre}</td>
                                        <td>${medico.noColegiado}</td>
                                        <td>${medico.CUI}</td>
                                        <td>${medico.correo}</td>
                                        <td>${medico.horaInicio}</td>
                                        <td>${medico.horaFinal}</td>
                                        <td>${medico.fechaInicioLabores}</td>
                                        <td>
                                            <select class="form-control" name="tipoConsulta">
                                                <c:forEach var="e" items="${medico.especialidades}">
                                                    <option value="${e}">${e}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <button type="submit" class="btn btn-secondary">
                                                <i class="fas fa-angle-double-right"></i> Agendar consulta
                                            </button>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${agendar}">
            <div class="container-fluid mt-4">
                <div class="row d-flex justify-content-center">
                    <div class="col-xl-4">
                        <c:if test="${error != null}">
                            <div class="alert alert-danger alert-dismissible mt-2">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                ${error}
                            </div>
                        </c:if>
                        <div class="card">
                            <div class="card-header">
                                <h5>Agendar</h5>
                            </div>
                            <div class="card-body">
                                <form id="form-consulta" action="${pageContext.request.contextPath}/ConsultaServlet?accion=agendar" method="POST">
                                    <div class="form-group d-none">
                                        <label for="codigoMedico">Codigo medico:</label>
                                        <input type="text" class="form-control" name="codigoMedico" value="${medico.codigo}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre">Medico:</label>
                                        <input type="text" class="form-control" name="nombre" value="${medico.nombre}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="horario">Horario:</label>
                                        <input type="text" class="form-control" name="horario" value="${medico.horaInicio} - ${medico.horaFinal}" readonly>
                                        <input type="text" class="form-control d-none" id="horaEntrada" value="${medico.horaInicio}"  readonly>
                                        <input type="text" class="form-control d-none" id="horaSalida" value="${medico.horaFinal}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="especialidad">Especialidad:</label>
                                        <input type="text" class="form-control d-none" name="idEspecialidad" value="${especialidad.id}" readonly>
                                        <input type="text" class="form-control" name="especialidad" value="${especialidad.nombre}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="costo">Costo:</label>
                                        <input type="text" class="form-control" name="costo" value="${especialidad.costo}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="hora">*Hora:</label>
                                        <input type="time" class="form-control" name="hora" id="hora" value="${horaC}">
                                    </div>
                                    <div class="form-group">
                                        <label for="fecha">*Fecha:</label>
                                        <input type="date" class="form-control" name="fecha" value="${fecha}">
                                    </div>
                                    <div>
                                        <button type="submit" class="btn btn-primary btn-block">Agendar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarConsulta.js"></script>
    </body>
</html>
