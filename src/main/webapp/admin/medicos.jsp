<%-- 
    Document   : medicos
    Created on : 30/09/2020, 03:29:14
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">w
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
                    <a href="formTipoExamen.jsp" class="btn btn-primary btn-block">
                        <i class="fas fa-plus"></i> Agregar medico
                    </a>
                </div>
            </div>
        </div>

        <!--Listado de medicos-->
        <div class="container-fluid mb-5">
            <div class="row">
                <div class="col-xl-12">
                    <div class="card">
                        <div class="card-header">
                            <h4>Listado de medicos</h4>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>No Colegiado</th>
                                        <th>CUI</th>
                                        <th>Telefono</th>
                                        <th>Correo</th>
                                        <th>Hora entrada</th>
                                        <th>Hora salida</th>
                                        <th>Empezo a laborar</th>
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
                                            <td>${medico.telefono}</td>
                                            <td>${medico.correo}</td>
                                            <td>${medico.horaInicio}</td>
                                            <td>${medico.horaFinal}</td>
                                            <td>${medico.fechaInicioLabores}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/MedicoServlet?accion=editar&codigo=${medico.codigo}" 
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
