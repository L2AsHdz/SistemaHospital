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

        <!-- Filtro de busqueda -->
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
                        <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Nombre del medico" name="nombre" autofocus>
                        <label for="especialidad" class="mr-sm-2">Especialidad:</label>
                        <select class="form-control mb-2 mr-sm-2" name="especialidad">
                            <option value="">Seleccione...</option>
                            <c:forEach var="esp" items="${especialidades}">
                                <option value="${esp.id}">${esp.nombre}</option>
                            </c:forEach>
                        </select>
                        <label for="hora" class="mr-sm-2">Hora:</label>
                        <input type="time" class="form-control mb-2 mr-sm-2" name="hora">
                        <button type="submit" class="btn btn-primary mb-2 ml-2">Buscar</button>
                    </form>
                </div>
            </div>
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
                                                <option value="">Seleccione...</option>
                                                <c:forEach var="e" items="${medico.especialidades}">
                                                    <option value="${e}">${e}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/ConsultaServlet?accion=editar&codigo=${medico.codigo}" 
                                               class="btn btn-secondary">
                                                <i class="fas fa-angle-double-right"></i> Agendar consulta
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </body>
</html>
