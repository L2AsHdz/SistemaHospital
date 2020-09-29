<nav class="navbar navbar-expand-sm bg-primary navbar-dark fixed-top">
    <a href="${pageContext.request.contextPath}/admin/inicioAdmin.jsp" class="navbar-brand">
        <i class="fas fa-hospital"></i>
        Sistema Hospital
    </a>

    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/tiposExamenes.jsp">Tipos de examenes</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Especialidades</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Medicos</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Laboratoristas</a>
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