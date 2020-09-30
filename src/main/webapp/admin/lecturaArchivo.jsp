<%-- 
    Document   : index
    Created on : 25/09/2020, 15:19:21
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sistema Hospital</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>

        <header id="main-header" class="py-2 bg-info text-white">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-6">
                        <h1>
                            <i class="fas fa-search"></i>
                            Lectura Archivo    
                        </h1>

                    </div>
                </div>
            </div>
        </header>

        <form action="${pageContext.request.contextPath}/LecturaArchivo" method="POST" id="register-form" enctype="multipart/form-data">
            <div class="container-fluid py-4 bg-light">
                <div class="row">
                    <div class="col-xl-3">
                        <div class="custom-file">
                            <input type="file" class="form-control-file" name="archivoEntrada" accept=".xml">
                        </div>
                    </div>
                    <div class="col-xl-3">
                        <button type="submit" class="btn btn-primary">Continuar</button>
                    </div>
                </div>
            </div>
        </form>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validacionArchivo.js"></script>
    </body>
</html>
