<%-- 
    Document   : formLaboratorista
    Created on : 1/10/2020, 09:45:17
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

        <!-- Formulario -->
        <c:choose>
            <c:when test="${laboratorista != null}">
                <form id="form-laboratorista" action="${pageContext.request.contextPath}/LaboratoristaServlet?accion=modificar" method="POST">
                </c:when>
                <c:otherwise>
                    <form id="form-laboratorista" action="${pageContext.request.contextPath}/LaboratoristaServlet?accion=agregar" method="POST">
                    </c:otherwise>
                </c:choose>

                <!-- Botones -->
                <div class="container-fluid py-3 mb-4 bg-secondary">
                    <div class="row">
                        <div class="col-xl-2">
                            <a href="${pageContext.request.contextPath}/LaboratoristaServlet" class="btn btn-light btn-block">
                                <i class="fas fa-arrow-left"></i> Regresar
                            </a>
                        </div>
                        <div class="col-xl-2">
                            <button type="submit" class="btn btn-success btn-block">
                                <i class="fas fa-check"></i> Guardar laboratorista
                            </button>
                        </div>
                        <c:if test="${laboratorista == null}">
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
                            <c:choose>
                                <c:when test="${laboratorista != null}">
                                    <h3>Modificar laboratorista</h3>
                                </c:when>
                                <c:otherwise>
                                    <h3>Agregar laboratorista</h3>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group">
                                <label for="codigo">*Codigo</label>
                                <input type="text" class="form-control" name="codigo" value="${laboratorista.codigo}" autofocus>
                            </div>
                            <div class="form-group">
                                <label for="nombre">*Nombre</label>
                                <input type="text" class="form-control" name="nombre" value="${laboratorista.nombre}">
                            </div>
                            <div class="form-group">
                                <label for="registroSalud">*Registro de salud</label>
                                <input type="text" class="form-control" name="registroSalud" value="${laboratorista.registroSalud}">
                            </div>
                            <div class="form-group">
                                <label for="cui">*CUI</label>
                                <input type="text" class="form-control" name="cui" value="${laboratorista.CUI}">
                            </div>
                            <div class="form-group">
                                <label for="telefono">*Telefono</label>
                                <input type="text" class="form-control" name="telefono" value="${laboratorista.telefono}">
                            </div>
                            <div class="form-group">
                                <label for="correo">*Correo</label>
                                <input type="text" class="form-control" name="correo" value="${laboratorista.correo}">
                            </div>
                            <div class="form-group">
                                <label for="tipoExamen">*Tipo examen</label>
                                <select class="form-control" name="tipoExamen">
                                    <option value="">Seleccione...</option>
                                    <c:forEach var="tipoExamen" items="${tiposExamen}">
                                        <option value="${tipoExamen.codigo}">${tipoExamen.nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group esp">
                                <label for="turnos">*Turnos:</label>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="turnos">
                                        Lunes
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="turnos">
                                        Martes
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="turnos">
                                        Miercoles
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="turnos">
                                        Jueves
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="turnos">
                                        Viernes
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="turnos">
                                        Sabado
                                    </label>
                                </div>
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="checkbox" class="form-check-input" name="turnos">
                                        Domingo
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fechaLabores">*Fecha inicio labores</label>
                                <input type="date" class="form-control" name="fechaLabores" value="${laboratorista.fechaInicioLabores}">
                            </div>
                            <div class="form-group">
                                <label for="password">*Contraseña</label>
                                <input type="password" class="form-control" name="password" value="${laboratorista.password}">
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <!--JS--> 
            <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

            <!-- JQuery Validate -->
            <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/validaciones/validarLaboratorista.js"></script>
    </body>
</html>
