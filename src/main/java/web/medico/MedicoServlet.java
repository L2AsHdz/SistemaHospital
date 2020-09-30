package web.medico;

import datos.CRUD;
import datos.especialidad.EspecialidadDAOImpl;
import datos.medico.MedicoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.especialidad.Especialidad;
import model.usuario.Medico;

/**
 * @date 30/09/2020
 * @time 03:39:07
 * @author asael
 */

@WebServlet("/MedicoServlet")
public class MedicoServlet extends HttpServlet {
    
    private final CRUD<Medico> medicoDAO = MedicoDAOImpl.getMedicoDAO();
    private final CRUD<Especialidad> especialidadDAO = EspecialidadDAOImpl.getEspecialidadDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String noColegiado = request.getParameter("noColegiado");
        String cui = request.getParameter("cui");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String horaInicio = request.getParameter("horaInicio");
        String horaFinal = request.getParameter("horaFinal");
        String fechaInicioLabores = request.getParameter("fechaLabores");
        String password = request.getParameter("password");
    
        switch (accion) {
            case "agregar" -> {
                if (!medicoDAO.exists(codigo)) {
                    medicoDAO.create(new Medico(noColegiado, telefono, correo, horaInicio, horaFinal, fechaInicioLabores, codigo, nombre, cui, password));
                    redirect(request, response);
                    //mostrar mensaje de exito
                } else {
                    //Mostrar error por entidad repetida
                }
            }
            case "modificar" -> {
                medicoDAO.update(new Medico(noColegiado, telefono, correo, horaInicio, horaFinal, fechaInicioLabores, codigo, nombre, cui, password));
                redirect(request, response);
                    //mostrar mensaje de exito
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("editar")) {
            String codigo = request.getParameter("codigo");
            Medico medico = medicoDAO.getObject(codigo);
            request.setAttribute("medico", medico);
            request.getRequestDispatcher("admin/formMedico.jsp").forward(request, response);
        } else if (accion != null && accion.equals("add")) {
            List<Especialidad> especialidades = especialidadDAO.getListado();
            request.setAttribute("especialidades", especialidades);
            request.getRequestDispatcher("admin/formMedico.jsp").forward(request, response);
        } else {
            redirect(request, response);
        }
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Medico> medicos = medicoDAO.getListado();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("medicos", medicos);
        response.sendRedirect("admin/medicos.jsp");
    }
}
