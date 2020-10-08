<%-- 
    Document   : paginaInicio
    Created on : 25/09/2020, 17:48:55
    Author     : asael
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Iniciar sesion</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/navBar.jsp"/>

        <div class="container mt-5">
            <div class="row">
                <div class="col-xl-3"></div>
                <div class="col-xl-6">
                    <h3 class="text-center mt-5">Iniciar sesion</h3>

                    <form id="form-login" action="${pageContext.request.contextPath}/LoginServlet" method="POST">
                        <div class="form-group">
                            <label for="codigo">Codigo</label>
                            <input type="text" class="form-control" name="codigo" placeholder="Ingrese codigo" autofocus>
                        </div>
                        <div class="form-group">
                            <label for="password">Contraseña</label>
                            <input type="password" class="form-control" name="password" placeholder="Password">
                        </div>
                        <div class="text-center">
                            <button type="reset" class="btn btn-secondary mr-2">Limpiar</button>
                            <button type="submit" class="btn btn-success">Iniciar sesion</button>
                        </div>
                        <div class="text-center mt-2">
                            <a href="${pageContext.request.contextPath}/paciente/registro.jsp">Registrase como paciente</a>
                        </div>
                        <c:if test="${errorLogin  != null}" >
                            <div class="alert alert-danger alert-dismissible mt-2">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                Codigo y/o contraseña incorrecta
                            </div>
                        </c:if>
                    </form>
                </div>
                <div class="col-xl-3"></div>
            </div>
        </div>

        <c:if test="${!empty(errores)}">
            <div class="modal" id="modal-errores">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4>Ocurrieron los siguientes errores:</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <c:forEach var="error" items="${errores}">
                                ${error}<br>
                            </c:forEach>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-info" data-dismiss="modal">Aceptar</button>
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
        <script src="${pageContext.request.contextPath}/js/validaciones/validarLogin.js"></script>

        <script>
                                $(document).ready(function () {
                                    $('#modal-errores').modal('toggle');
                                });
        </script>
    </body>
</html>
