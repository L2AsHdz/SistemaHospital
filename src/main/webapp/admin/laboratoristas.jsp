<%-- 
    Document   : laboratoristas
    Created on : 30/09/2020, 23:48:49
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

        <!--Barra de navegacion-->
        <jsp:include page="/WEB-INF/admin/navBarAdmin.jsp" />

        <!--Boton agregar un medico-->
        <div class="container-fluid py-3 mb-4 bg-secondary">
            <div class="row">
                <div class="col-xl-3">
                    <a href="formLaboratorista.jsp" class="btn btn-primary btn-block">
                        <i class="fas fa-plus"></i> Agregar laboratorista
                    </a>
                </div>
            </div>
        </div>

        <!--Listado de laboratoristas-->
        <div class="container-fluid mb-5">
            <div class="row">
                <div class="col-xl-12">
                    <div class="card">
                        <div class="card-header">
                            <h4>Listado de laboratoristas</h4>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Registro salud</th>
                                        <th>CUI</th>
                                        <th>Telefono</th>
                                        <th>Correo</th>
                                        <th>Empezo a laborar</th>
                                        <th>Tipo examen</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="laboratorista" items="${laboratoristas}">
                                        <tr>
                                            <td>${laboratorista.codigo}</td>
                                            <td>${laboratorista.nombre}</td>
                                            <td>${laboratorista.registroSalud}</td>
                                            <td>${laboratorista.CUI}</td>
                                            <td>${laboratorista.telefono}</td>
                                            <td>${laboratorista.correo}</td>
                                            <td>${laboratorista.fechaInicioLabores}</td>
                                            <td>${medico.tipoExamen}</td>
                                            <td>
                                                <a class="btn btn-info" data-toggle="modal" data-target="#m${laboratorista.codigo}">
                                                    <i class="fas fa-eye"></i> Ver turnos
                                                </a>

                                                <div class="modal" id="m${laboratorista.codigo}">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-body">
                                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                <h5>Turnos:</h5>
                                                                <c:forEach var="turno" items="${laboratorista.turnos}" varStatus="status">
                                                                    ${status.count}. ${turno}<br>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/LaboratoristaServlet?accion=editar&codigo=${laboratorista.codigo}" 
                                                   class="btn btn-secondary">
                                                    <i class="fas fa-angle-double-right"></i> Editar
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

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>           
    </body>
</html>
