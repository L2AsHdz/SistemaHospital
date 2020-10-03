<%-- 
    Document   : agendarExamen
    Created on : 3/10/2020, 01:29:29
    Author     : asael
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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


        <c:if test="${tiposExamen != null}">
            <div class="container-fluid">
                <div class="row my-3 pl-5">
                    <div class="col-xl-10">
                        <h3>Agendar examen</h3>
                    </div>
                </div>
            </div>

            <!--Listado de tipos de examenes-->
            <div class="container-fluid my-4 pl-5">
                <div class="row">
                    <div class="col-xl-10">
                        <div class="card">
                            <div class="card-header">
                                <h4>Listado de tipos de examenes</h4>
                            </div>
                            <div class="card-body">
                                <table class="table table-striped">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>Codigo</th>
                                            <th>Nombre</th>
                                            <th>Requiere Orden</th>
                                            <th>Costo</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <!--Iteramos los elementos de la lista de clientes-->
                                        <c:forEach var="tipoExamen" items="${tiposExamen}">
                                            <tr>
                                                <td>${tipoExamen.codigo}</td>
                                                <td>${tipoExamen.nombre}</td>
                                                <td>${tipoExamen.requiereOrdenS}</td>
                                                <td><fmt:formatNumber value="${tipoExamen.costo}" type="currency" /></td>
                                                <td>
                                                    <a class="btn btn-info" data-toggle="modal" data-target="#t${tipoExamen.codigo}">
                                                        <i class="fas fa-eye"></i> Ver descripcion
                                                    </a>

                                                    <div class="modal" id="t${tipoExamen.codigo}">
                                                        <div class="modal-dialog modal-dialog-centered">
                                                            <div class="modal-content">
                                                                <div class="modal-body">
                                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    <h5>Descripcion:</h5>
                                                                    ${tipoExamen.descripcion}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/ExamenServlet?accion=preAgendar&codigo=${tipoExamen.codigo}" 
                                                       class="btn btn-secondary">
                                                        <i class="fas fa-angle-double-right"></i> Agendar examen
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

        <c:if test="${agendar}">
            <div class="container-fluid mt-4 mb-5">
                <div class="row d-flex justify-content-center">
                    <div class="col-xl-4">
                        <div class="card">
                            <div class="card-header">
                                <h5>Agendar examen</h5>
                            </div>
                            <div class="card-body">
                                <form id="form-consulta" action="${pageContext.request.contextPath}/ExamenServlet?accion=agendar" method="POST" enctype="multipart/form-data">
                                    <div class="form-group d-none">
                                        <label for="codigo">Codigo examen:</label>
                                        <input type="text" class="form-control" name="codigo" value="${tipoExamen.codigo}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre">Examen:</label>
                                        <input type="text" class="form-control" name="nombre" value="${tipoExamen.nombre}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="costo">Costo:</label>
                                        <input type="text" class="form-control" name="costo" value="${tipoExamen.costo}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for="medico">Medico:</label>
                                        <c:choose>
                                            <c:when test="${tipoExamen.requiereOrden == 1}">
                                                <select class="form-control" name="medicoO">
                                                </c:when>
                                                <c:otherwise>
                                                    <select class="form-control" name="medico">
                                                    </c:otherwise>
                                                </c:choose>
                                                <option value="">Seleccione...</option>
                                                <c:forEach var="medico" items="${medicos}">
                                                    <option value="${medico.codigo}">${medico.nombre}</option>
                                                </c:forEach>
                                            </select>
                                    </div>
                                    <c:if test="${tipoExamen.requiereOrden == 1}">
                                        <div class="form-group">
                                            <label for="orden">Seleccione la orden del medico:</label>
                                            <input type="file" class="form-control-file border" name="orden" id="orden" accept=".pdf">
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label for="hora">*Hora:</label>
                                        <input type="time" class="form-control" name="hora" id="hora" value="${hora}">
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
        <script src="${pageContext.request.contextPath}/js/validaciones/validarExamen.js"></script>
    </body>
</html>
