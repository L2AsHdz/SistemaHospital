<div>
    <nav class="navbar navbar-expand-xl fixed-top bg-dark navbar-dark">
        <a href="${pageContext.request.contextPath}/medico/inicioMedico.jsp" class="navbar-brand">
            <i class="fas fa-hospital-symbol"></i>
            Sistema Hospital
        </a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/CulminarConsultaServlet?accion=listar">Consultas del dia</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/medico/historialMedico.jsp">Buscar Historial Medico</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        Reportes
                    </a>

                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/medico/citasAgendadas.jsp">Citas agendadas en un intervalo de tiempo</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/">Pacientes con mayor cantidad de informes</a>
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
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/MedicoServlet?accion=logout">Cerrar sesion</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="py-4 mb-2"></div>