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
        <title>Seleccion de archivos</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>

        <header class="py-2 bg-dark text-white">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-6">
                        <h1>
                            <i class="fas fa-hospital ml-3"></i> Sistema Hospital
                        </h1>
                    </div>
                </div>
            </div>
        </header>



        <div class="container-fluid py-2 bg-light">
            <div class="row">
                <div class="col-xl-3">
                    <h3 class="mt-2 ml-3">Seleccion de archivos</h3>
                </div>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/LecturaArchivo" method="POST" id="form-archivo" enctype="multipart/form-data">
            <div class="containter-fluid pl-5">
                <div class="row">
                    <div class="col-xl-6">
                        <div class="my-2">
                            <label for="archivoEntrada" class="font-weight-bold">Seleccione el archivo de entrada:</label>
                            <input type="file" class="form-control-file border" name="archivoEntrada" accept=".xml">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-6">
                        <div class="mt-4 mb-2">
                            <label for="archivos" class="font-weight-bold">Seleccione los informes y resultados:</label>
                            <input type="file" class="form-control-file border" name="archivos" accept="image/*,.pdf" multiple    >
                        </div>                        
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-4"></div>
                    <div class="col-xl-2">
                        <button type="submit" class="btn btn-primary btn-block mt-2">Continuar</button>
                    </div>
                </div>
            </div>
        </form>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones/validarArchivoEntrada.js"></script>
    </body>
</html>
