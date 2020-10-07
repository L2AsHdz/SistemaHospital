<%-- 
    Document   : fechasConMasExamenes
    Created on : 7/10/2020, 11:44:40
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

        <div class="container-fluid mt-5 pl-4">

            <div class="row">
                <div class="col-xl-6">
                    <c:choose>
                        <c:when test="${!empty(cantResultados)}">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Las 10 fechas con mas examenes realizados</h4>
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>#</th>
                                                <th>Fecha</th>
                                                <th>Cant. Realizados</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="resultado" items="${cantResultados}" varStatus="status">
                                                <tr>
                                                    <td>${status.count}</td>
                                                    <td>${resultado.fecha}</td>
                                                    <td>${resultado.cantRealizados}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${buscado}">
                                <h4>No hay examenes realizados</h4>
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
