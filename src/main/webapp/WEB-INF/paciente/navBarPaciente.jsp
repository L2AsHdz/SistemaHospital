<div>
    <nav class="navbar navbar-expand-xl fixed-top bg-dark navbar-dark">
        <a href="${pageContext.request.contextPath}/paciente/inicioPaciente.jsp" class="navbar-brand">
            <i class="fas fa-hospital"></i>
            Sistema Hospital
        </a>

        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/ConsultaServlet?accion=add">Agendar Consulta</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#">Agendar Examen</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#">Historial medico</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#">Pendientes</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    Reportes
                </a>

                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Reporte 1</a>
                    <a class="dropdown-item" href="#">Reporte 2</a>
                </div>
            </li>
        </ul>
    </nav>
</div>
<div class="py-4 mb-2"></div>