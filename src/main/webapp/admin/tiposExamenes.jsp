<%-- 
    Document   : tiposExamenes
    Created on : 29/09/2020, 16:18:48
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_GT" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${user.codigo} - ${user.nombre}</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>
        <!--Barra de navegacion-->
        <jsp:include page="/WEB-INF/admin/navBarAdmin.jsp" />

        <!--Boton agregar tipo de examen-->
        <div class="container-fluid py-3 mb-4 bg-secondary">
            <div class="row">
                <div class="col-xl-3">
                    <a href="agregarTipoExamen.jsp" class="btn btn-primary btn-block">
                        <i class="fas fa-plus"></i> Agregar tipo de Examen
                    </a>
                </div>
            </div>
        </div>

        <!--Listado de tipos de examenes-->
        <div class="container-fluid">
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
                                        <th>Tipo informe</th>
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
                                        <td>${tipoExamen.tipoInforme}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/TipoExamenServlet?accion=descripcion&codigo=${tipoExamen.codigo}" 
                                               class="btn btn-info">
                                                <i class="fas fa-eye"></i> Ver descripcion
                                            </a>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/TipoExamenServlet?accion=editar&codigo=${tipoExamen.codigo}" 
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
