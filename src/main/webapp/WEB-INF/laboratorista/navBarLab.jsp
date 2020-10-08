<div>
    <nav class="navbar navbar-expand-xl fixed-top bg-dark navbar-dark">
        <a href="${pageContext.request.contextPath}/laboratorista/inicioLabjsp" class="navbar-brand">
            <i class="fas fa-hospital-symbol"></i>
            Sistema Hospital
        </a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/CulminarExamenServlet?accion=listar">Examenes del dia</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        Reportes
                    </a>

                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ReportesLabServlet?accion=reporte1">Examenes realizados en el dia</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/laboratorista/cantExamenesPorDia.jsp">Cantidad de examenes realizados por dia</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ReportesLabServlet?accion=reporte3">Las 10 fechas con mas examenes realizados</a>
                    </div>
                </li>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                        ${user.nombre}
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#modalPerfil">Perfil</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/LaboratoristaServlet?accion=logout">Cerrar sesion</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="py-4 mb-2"></div>

<div class="modal" id="modalPerfil">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modificar perfil</h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/LaboratoristaServlet?accion=perfil" method="POST">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">*Nombre</label>
                        <input type="text" class="form-control" name="nombre" value="${user.nombre}">

                    </div>
                    <div class="form-group">
                        <label for="cui">*CUI</label>
                        <input type="text" class="form-control" name="cui" value="${user.CUI}">
                    </div>
                    <div class="form-group">
                        <label for="telefono">*Telefono</label>
                        <input type="text" class="form-control" name="telefono" value="${user.telefono}">
                    </div>
                    <div class="form-group">
                        <label for="correo">*Correo</label>
                        <input type="text" class="form-control" name="correo" value="${user.correo}">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Actualizar datos</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
                </div>
            </form>

        </div>
    </div>
</div>