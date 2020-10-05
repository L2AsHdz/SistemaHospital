<div>
    <nav class="navbar navbar-expand-xl fixed-top bg-dark navbar-dark">
        <a href="${pageContext.request.contextPath}/paciente/inicioPaciente.jsp" class="navbar-brand">
            <i class="fas fa-hospital-symbol"></i>
            Sistema Hospital
        </a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ConsultaServlet?accion=add">Agendar Consulta</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ExamenServlet?accion=listar">Agendar Examen</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/HistorialMedicoServlet?accion=historial">Historial medico</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/HistorialMedicoServlet?accion=pendientes">Pendientes</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        Reportes
                    </a>

                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ReportesPacienteServlet?accion=reporte1">Ultimos 5 examenes realizados</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ReportesPacienteServlet?accion=listarTipos">Examenes realizados por tipo</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ReportesPacienteServlet?accion=reporte3">Ultimas 5 consultas realizadas</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/paciente/consultasPorMedico.jsp">Consultas realizados con un medico</a>
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
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/PacienteServlet?accion=logout">Cerrar sesion</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="py-4 mb-2"></div>