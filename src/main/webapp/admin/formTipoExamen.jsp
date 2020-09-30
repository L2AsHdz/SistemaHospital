<%-- 
    Document   : agregarTipoExamen
    Created on : 29/09/2020, 18:09:12
    Author     : asael
--%>

<%@page import="model.examen.TipoExamen"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <c:choose>
            <c:when test="${tipoExamen != null}">
                <form id="form-tipoExamen" action="${pageContext.request.contextPath}/TipoExamenServlet?accion=modificar" method="POST">
                </c:when>
                <c:otherwise>
                    <form id="form-tipoExamen" action="${pageContext.request.contextPath}/TipoExamenServlet?accion=agregar" method="POST">
                    </c:otherwise>
                </c:choose>

                <!-- Botones -->
                <div class="container-fluid py-3 mb-4 bg-secondary">
                    <div class="row">
                        <div class="col-xl-2">
                            <a href="${pageContext.request.contextPath}/TipoExamenServlet" class="btn btn-light btn-block">
                                <i class="fas fa-arrow-left"></i> Regresar
                            </a>
                        </div>
                        <div class="col-xl-2">
                            <button type="submit" class="btn btn-success btn-block">
                                <i class="fas fa-check"></i> Guardar tipo de examen
                            </button>
                        </div>
                        <div class="col-xl-2">
                            <button type="reset" class="btn btn-dark btn-block">
                                <i class="fas fa-backspace"></i> Limpiar campos
                            </button>
                        </div>
                    </div>
                </div>


                <div class="container-fluid mb-5">
                    <div class="row">
                        <div class="col-xl-6">
                            <c:choose>
                                <c:when test="${tipoExamen != null}">
                                    <h3>Modificar tipo de Examen</h3>
                                </c:when>
                                <c:otherwise>
                                    <h3>Agregar tipo de Examen</h3>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group">
                                <label for="codigo">*Codigo</label>
                                <input type="text" class="form-control" name="codigo" value="${tipoExamen.codigo}">
                            </div>
                            <div class="form-group">
                                <label for="nombre">*Nombre</label>
                                <input type="text" class="form-control" name="nombre" value="${tipoExamen.nombre}">
                            </div>
                            <div class="form-group">
                                <label for="requiereOrden">*Requiere orden</label>
                                <select class="form-control" name="requiereOrden">
                                    <c:choose>
                                        <c:when test="${tipoExamen.requiereOrden == 1}">
                                            <option value="TRUE">Si</option>
                                            <option value="FALSE">No</option>
                                        </c:when>
                                        <c:when test="${tipoExamen.requiereOrden == 0}">
                                            <option value="FALSE">No</option>
                                            <option value="TRUE">Si</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="">Seleccione...</option>
                                            <option value="TRUE">Si</option>
                                            <option value="FALSE">No</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="descripcion">*Descripcion</label>
                                <textarea type="textarea" rows="3" class="form-control" name="descripcion">${tipoExamen.descripcion}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="costo">*Costo</label>
                                <input type="text" class="form-control" name="costo" value="${tipoExamen.costo}">
                            </div>
                            <div class="form-group">
                                <label for="tipoInforme">*Tipo Informe</label>
                                <select class="form-control" name="tipoInforme">
                                    <%TipoExamen tipo = (TipoExamen) request.getAttribute("tipoExamen");
                                        if (tipo != null && tipo.getTipoInforme().equals("PDF")) {%>
                                    <option value="PDF">PDF</option>
                                    <option value="IMG">IMG</option>
                                    <%} else if (tipo != null && tipo.getTipoInforme().equals("IMG")) {%>
                                    <option value="IMG">IMG</option>
                                    <option value="PDF">PDF</option>
                                    <%} else {%>
                                    <option value="">Seleccione...</option>
                                    <option value="PDF">PDF</option>
                                    <option value="IMG">IMG</option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <!--JS--> 
            <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

            <!-- JQuery Validation -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/validaciones/validarTipoExamen.js"></script>
    </body>
</html>