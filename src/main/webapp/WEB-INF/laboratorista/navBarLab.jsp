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
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ReportesLabServlet?accion=reporte2">Cantidad de examenes realizados por dia</a>
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
                        <a class="dropdown-item" href="#">Ver Perfil</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/LaboratoristaServlet?accion=logout">Cerrar sesion</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="py-4 mb-2"></div>