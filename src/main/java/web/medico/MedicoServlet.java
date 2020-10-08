package web.medico;

import datos.CRUD;
import datos.especialidad.AsignacionEspecialidadDAOImpl;
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
import model.especialidad.AsignacionEspecialidad;
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
    private final CRUD<AsignacionEspecialidad> asignacionDAO = AsignacionEspecialidadDAOImpl.getAsignacionEsDAO();

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
        String[] checkEsp = request.getParameterValues("checkEsp");

        Medico medico = new Medico(noColegiado, telefono, correo, horaInicio, horaFinal, fechaInicioLabores, codigo, nombre, cui, password);

        switch (accion) {
            case "agregar" -> {
                if (!medicoDAO.exists(codigo)) {
                    medicoDAO.create(medico);

                    for (String e : checkEsp) {
                        asignacionDAO.create(new AsignacionEspecialidad(codigo, Integer.parseInt(e)));
                    }
                    redirect(request, response);
                    //mostrar mensaje de exito
                } else {
                    request.setAttribute("error", "Ya existe un medico registrado con ese codigo");
                    request.getRequestDispatcher("admin/formMedico.jsp").forward(request, response);
                }
            }
            case "modificar" -> {
                medicoDAO.update(medico);
                redirect(request, response);
                //mostrar mensaje de exito
            }
            case "perfil" -> {
                medico = (Medico) request.getSession().getAttribute("user");
                medico.setNombre(nombre);
                medico.setCUI(cui);
                medico.setCorreo(correo);
                medico.setTelefono(telefono);
                
                medicoDAO.update(medico);
                request.getSession().setAttribute("user", medico);
                response.sendRedirect("medico/inicioMedico.jsp");
                
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        if (accion != null && accion.equals("editar")) {
            String codigo = request.getParameter("codigo");
            List<Especialidad> especialidades = especialidadDAO.getListado();
            Medico medico = medicoDAO.getObject(codigo);
            request.setAttribute("especialidades", especialidades);
            request.setAttribute("medico", medico);
            request.getRequestDispatcher("admin/formMedico.jsp").forward(request, response);
        
        } else if (accion != null && accion.equals("add")) {
            List<Especialidad> especialidades = especialidadDAO.getListado();
            request.setAttribute("especialidades", especialidades);
            request.getRequestDispatcher("admin/formMedico.jsp").forward(request, response);
        
        } else if (accion != null && accion.equals("logout")) {
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");
        
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
