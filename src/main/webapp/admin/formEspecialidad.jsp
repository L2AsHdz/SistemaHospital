<%-- 
    Document   : formEspecialidad
    Created on : 30/09/2020, 02:05:45
    Author     : asael
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <!--Barra de navegacion-->
        <jsp:include page="/WEB-INF/admin/navBarAdmin.jsp" />

        <c:choose>
            <c:when test="${especialidad != null}">
                <form id="form-especialidad" action="${pageContext.request.contextPath}/EspecialidadServlet?accion=modificar" method="POST">
                </c:when>
                <c:otherwise>
                    <form id="form-especialidad" action="${pageContext.request.contextPath}/EspecialidadServlet?accion=agregar" method="POST">
                    </c:otherwise>
                </c:choose>

                <!-- Botones -->
                <div class="container-fluid py-3 mb-4 bg-secondary">
                    <div class="row">
                        <div class="col-xl-2">
                            <a href="${pageContext.request.contextPath}/EspecialidadServlet" class="btn btn-light btn-block">
                                <i class="fas fa-arrow-left"></i> Regresar
                            </a>
                        </div>
                        <div class="col-xl-2">
                            <button type="submit" class="btn btn-success btn-block">
                                <i class="fas fa-check"></i> Guardar especialidad
                            </button>
                        </div>
                        <c:if test="${especialidad == null}">
                            <div class="col-xl-2">
                                <button type="reset" class="btn btn-dark btn-block">
                                    <i class="fas fa-backspace"></i> Limpiar campos
                                </button>
                            </div>
                        </c:if>
                    </div>
                </div>


                <div class="container-fluid mb-5">
                    <div class="row">
                        <div class="col-xl-6">
                            <c:if test="${error  != null}" >
                                <div class="alert alert-danger alert-dismissible mt-2">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${error}
                                </div>
                            </c:if>
                            <c:choose>
                                <c:when test="${especialidad != null}">
                                    <h3>Modificar especialidad</h3>
                                </c:when>
                                <c:otherwise>
                                    <h3>Agregar especialidad</h3>
                                </c:otherwise>
                            </c:choose>

                            <c:if test="${especialidad != null}">
                                <div class="form-group">
                                    <label for="id">*Nombre</label>
                                    <input type="text" class="form-control" name="id" value="${especialidad.id}" readonly>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="nombre">*Nombre</label>
                                <input type="text" class="form-control" name="nombre" value="${especialidad.nombre}" autofocus>
                            </div>
                            <div class="form-group">
                                <label for="costo">*Costo</label>
                                <input type="text" class="form-control" name="costo" value="${especialidad.costo}">
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <!--JS--> 
            <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

            <!-- JQuery Validate -->
            <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/validaciones/validarEspecialidad.js"></script>
    </body>
</html>
