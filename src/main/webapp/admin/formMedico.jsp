<%-- 
    Document   : formMedico
    Created on : 30/09/2020, 11:22:56
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
            <c:when test="${medico != null}">
                <form id="form-medico" action="${pageContext.request.contextPath}/MedicoServlet?accion=modificar" method="POST">
                </c:when>
                <c:otherwise>
                    <form id="form-medico" action="${pageContext.request.contextPath}/MedicoServlet?accion=agregar" method="POST">
                    </c:otherwise>
                </c:choose>

                <!-- Botones -->
                <div class="container-fluid py-3 mb-4 bg-secondary">
                    <div class="row">
                        <div class="col-xl-2">
                            <a href="${pageContext.request.contextPath}/MedicoServlet" class="btn btn-light btn-block">
                                <i class="fas fa-arrow-left"></i> Regresar
                            </a>
                        </div>
                        <div class="col-xl-2">
                            <button type="submit" class="btn btn-success btn-block">
                                <i class="fas fa-check"></i> Guardar medico
                            </button>
                        </div>
                        <c:if test="${medico == null}">
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
                                <c:when test="${medico != null}">
                                    <h3>Modificar medico</h3>
                                </c:when>
                                <c:otherwise>
                                    <h3>Agregar medico</h3>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${medico != null}">
                                    <div class="form-group">
                                        <label for="codigo">*Codigo</label>
                                        <input type="text" class="form-control" name="codigo" value="${medico.codigo}" readonly>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <label for="codigo">*Codigo</label>
                                        <input type="text" class="form-control" name="codigo" value="${medico.codigo}" autofocus>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group">
                                <label for="nombre">*Nombre</label>
                                <input type="text" class="form-control" name="nombre" value="${medico.nombre}">
                            </div>
                            <div class="form-group">
                                <label for="noColegiado">*No. Colegiado</label>
                                <input type="text" class="form-control" name="noColegiado" value="${medico.noColegiado}">
                            </div>
                            <div class="form-group">
                                <label for="cui">*CUI</label>
                                <input type="text" class="form-control" name="cui" value="${medico.CUI}">
                            </div>
                            <div class="form-group">
                                <label for="telefono">*Telefono</label>
                                <input type="text" class="form-control" name="telefono" value="${medico.telefono}">
                            </div>
                            <div class="form-group">
                                <label for="correo">*Correo</label>
                                <input type="text" class="form-control" name="correo" value="${medico.correo}">
                            </div>
                            <div class="form-group">
                                <label for="horaInicio">*Hora Entrada</label>
                                <input type="time" class="form-control" name="horaInicio" id="horaInicio" value="${medico.horaInicio}">
                            </div>
                            <div class="form-group">
                                <label for="horaFinal">*Hora Salida</label>
                                <input type="time" class="form-control" name="horaFinal" id="horaFinal" value="${medico.horaFinal}">
                            </div>
                            <div class="form-group">
                                <label for="fechaLabores">*Fecha inicio labores</label>
                                <input type="date" class="form-control" name="fechaLabores" value="${medico.fechaInicioLabores}">
                            </div>
                            <div class="form-group">
                                <label for="password">*Contraseña</label>
                                <input type="password" class="form-control" name="password">
                            </div>
                            <div class="form-group esp">
                                <label for="checkEsp">*Especialidades</label>
                                <c:forEach var="especialidad" items="${especialidades}">
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <input type="checkbox" class="form-check-input" name="checkEsp" value="${especialidad.id}">
                                            ${especialidad.id}. ${especialidad.nombre}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <!--JS--> 
            <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

            <!-- JQuery Validate -->
            <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/validaciones/validarMedico.js"></script>
    </body>
</html>
