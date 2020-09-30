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
        <header class="py-2 bg-dark text-white">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-6">
                        <h1 class="ml-3">
                            <i class="fas fa-hospital"></i>
                            Sistema Hospital
                        </h1>
                    </div>
                </div>
            </div>
        </header>

        <div class="container">
            <div class="row">
                <div class="col-xl-3"></div>
                <div class="col-xl-6">
                    <h3 class="text-center mt-5">Iniciar sesion</h3>

                    <form id="form-login" action="${pageContext.request.contextPath}/LoginServlet" method="POST">
                        <div class="form-group">
                            <label for="codigo">Codigo</label>
                            <input type="text" class="form-control" name="codigo" placeholder="Ingrese codigo">
                        </div>
                        <div class="form-group">
                            <label for="password">Contraseña</label>
                            <input type="password" class="form-control" name="password" placeholder="Password">
                        </div>
                        <div class="text-center">
                            <button type="reset" class="btn btn-primary mr-2">Limpiar</button>
                            <button type="submit" class="btn btn-primary">Iniciar sesion</button>
                        </div>
                        <c:if test="${errorLogin  != null}" >
                            <div class="alert alert-danger alert-dismissible mt-2">
                                Codigo y/o contraseña incorrecta
                            </div>
                        </c:if>
                    </form>
                </div>
                <div class="col-xl-3"></div>
            </div>
        </div>


        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarLogin.js"></script>
    </body>
</html>
