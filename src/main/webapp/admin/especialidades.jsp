<%-- 
    Document   : especialidades
    Created on : 30/09/2020, 02:03:06
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
        <!--Barra de navegacion-->
        <jsp:include page="/WEB-INF/admin/navBarAdmin.jsp" />

        <!--Boton agregar especialidad-->
        <div class="container-fluid py-3 mb-4 bg-secondary">
            <div class="row">
                <div class="col-xl-3">
                    <a href="formEspecialidad.jsp" class="btn btn-primary btn-block">
                        <i class="fas fa-plus"></i> Agregar especialidad
                    </a>
                </div>
            </div>
        </div>

        <!--Listado de tipos de especialidades-->
        <div class="container-fluid mb-5">
            <div class="row">
                <div class="col-xl-6">
                    <div class="card">
                        <div class="card-header">
                            <h4>Listado de especialidades</h4>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Costo</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="especialidad" items="${especialidades}">
                                        <tr>
                                            <td>${especialidad.id}</td>
                                            <td>${especialidad.nombre}</td>
                                            <td><fmt:formatNumber value="${especialidad.costo}" type="currency" /></td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/EspecialidadServlet?accion=editar&nombre=${especialidad.nombre}" 
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
