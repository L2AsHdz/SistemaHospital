<%-- 
    Document   : ingresosPorMedico
    Created on : 6/10/2020, 11:39:38
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
        <jsp:include page="/WEB-INF/admin/navBarAdmin.jsp"/>

        <div class="container-fluid mt-5 pl-4">
            <div class="row">
                <div class="col-xl-10">
                    <div class="mb-4">
                        <h4>Ingresos por medico</h4>
                    </div>
                    <form class="form-inline ml-3" action="${pageContext.request.contextPath}/ReportesAdminServlet?accion=reporte2" method="POST"> 
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
                        <button type="submit" class="btn btn-primary mb-2 ml-2">Ver ingresos</button>
                    </form>
                </div>
            </div>

            <div class="row pt-4">
                <div class="col-xl-8">

                    <!-- Ingresos por medico -->
                    <c:choose>
                        <c:when test="${!empty(medicosIngresos)}">
                            <div class="card">
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Codigo</th>
                                                <th>nombre</th>
                                                <th>No. Colegiado</th>
                                                <th>CUI</th>
                                                <th>Ingresos totales</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach var="medico" items="${medicosIngresos}">
                                                <tr>
                                                    <td>${medico.codigo}</td>
                                                    <td>${medico.nombre}</td>
                                                    <td>${medico.noColegiado}</td>
                                                    <td>${medico.CUI}</td>
                                                    <td><fmt:formatNumber value="${medico.ingresos}" type="currency" /></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>	
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${buscado}">
                                <h4>No hay ingresos en el intervalo dado</h4>
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
