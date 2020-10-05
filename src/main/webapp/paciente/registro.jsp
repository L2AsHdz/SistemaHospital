<%-- 
    Document   : registro.jsp
    Created on : 1/10/2020, 13:14:05
    Author     : asael
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Resgistro paciente</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>

        <jsp:include page="/WEB-INF/navBar.jsp"/>

        <form id="form-paciente" action="${pageContext.request.contextPath}/PacienteServlet?accion=agregar" method="POST">
            <div class="container-fluid mb-5">

                <div class="row">
                    <div class="col-xl-6">
                        <c:if test="${error  != null}" >
                            <div class="alert alert-danger alert-dismissible mt-2">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                ${error}
                            </div>
                        </c:if>
                        <h3 class="mt-2">Registro de pacientes</h3>
                        <div class="form-group">
                            <label for="codigo">*Codigo</label>
                            <input type="text" class="form-control" name="codigo" value="${paciente.codigo}" autofocus>
                        </div>
                        <div class="form-group">
                            <label for="nombre">*Nombre</label>
                            <input type="text" class="form-control" name="nombre" value="${paciente.nombre}">
                        </div>
                        <div class="form-group">
                            <label for="sexo">*Sexo</label>
                            <select class="form-control" name="sexo">
                                <option value="">Seleccione...</option>
                                <option>Hombre</option>
                                <option>Mujer</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="cui">*CUI</label>
                            <input type="text" class="form-control" name="cui" value="${paciente.CUI}">
                        </div>
                        <div class="form-group">
                            <label for="telefono">*Telefono</label>
                            <input type="text" class="form-control" name="telefono" value="${paciente.telefono}">
                        </div>
                        <div class="form-group">
                            <label for="correo">*Correo</label>
                            <input type="text" class="form-control" name="correo" value="${paciente.correo}">
                        </div>
                        <div class="form-group">
                            <label for="birth">*Fecha de nacimiento</label>
                            <input type="date" class="form-control" name="birth" value="${paciente.birth}">
                        </div>
                        <div class="form-group">
                            <label for="peso">*Peso(kg)</label>
                            <input type="text" class="form-control" name="peso" value="${paciente.peso}">
                        </div>
                        <div class="form-group">
                            <label for="sangre">*Tipo de sangre</label>
                            <select class="form-control" name="sangre">
                                <option value="">Seleccione...</option>
                                <option>A+</option>
                                <option>B+</option>
                                <option>AB+</option>
                                <option>O+</option>
                                <option>A-</option>
                                <option>B-</option>
                                <option>AB-</option>
                                <option>O-</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="password">*Contraseña</label>
                            <input type="password" class="form-control" name="password">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-3">
                        <button type="reset" class="btn btn-secondary btn-block">Limpiar</button>
                    </div>
                    <div class="col-xl-3">
                        <button type="submit" class="btn btn-success btn-block">Registrar</button>
                    </div>
                </div>
            </div>
        </form>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarPaciente.js"></script>
    </body>
</html>
