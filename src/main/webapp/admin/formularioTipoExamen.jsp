<%-- 
    Document   : agregarTipoExamen
    Created on : 29/09/2020, 18:09:12
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <form id="form-tipoExamen" action="${pageContext.request.contextPath}/TipoExamenServlet?accion=agregar" 
              method="POST">

            <!--Boton regresar-->
            <div class="container-fluid py-3 mb-4 bg-secondary">
                <div class="row">
                    <div class="col-xl-2">
                        <a href="${pageContext.request.contextPath}/TipoExamenServlet" class="btn btn-light btn-block">
                            <i class="fas fa-arrow-left"></i> Regresar
                        </a>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-6">
                        <h3>Agregar Tipo de Examen</h3>
                        <div class="form-group">
                            <label for="codigo">*Codigo</label>
                            <input type="text" class="form-control" name="codigo">

                        </div>
                        <div class="form-group">
                            <label for="nombre">*Nombre</label>
                            <input type="text" class="form-control" name="nombre">
                        </div>
                        <div class="form-group">
                            <label for="requiereOrden">*Requiere orden</label>
                            <select class="form-control" name="requiereOrden">
                                <option value="">Seleccione...</option>
                                <option value="1">Si</option>
                                <option value="0">No</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="descripcion">*Descripcion</label>
                            <textarea type="textarea" rows="3" class="form-control" name="descripcion"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="costo">*Costo</label>
                            <input type="text" class="form-control" name="costo">
                        </div>
                        <div class="form-group">
                            <label for="tipoInforme">*Tipo Informe</label>
                            <select class="form-control" name="tipoInforme">
                                <option>PDF</option>
                                <option>IMG</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row mb-5">
                    <div class="col-xl-1"></div>
                    <div class="col-xl-2">
                        <button type="submit" class="btn btn-primary btn-block">Agregar</button>
                    </div>
                    <div class="col-xl-2">
                        <button type="reset" class="btn btn-info btn-block">Limpiar</button>
                    </div>
                </div>
            </div>
        </form>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validation -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
        <script src="../js/validaciones/validarTipoExamen.js"></script>
    </body>
</html>