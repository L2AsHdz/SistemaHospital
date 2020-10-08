package web.paciente;

import datos.paciente.PacienteDAO;
import datos.paciente.PacienteDAOImpl;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.usuario.Paciente;

/**
 * @date 1/10/2020
 * @time 15:11:21
 * @author asael
 */
@WebServlet("/PacienteServlet")
public class PacienteServlet extends HttpServlet {

    private final PacienteDAO pacienteDAO = PacienteDAOImpl.getPacienteDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String codigo = pacienteDAO.getCodigo();
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        String birth = request.getParameter("birth");
        String cui = request.getParameter("cui");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String peso = request.getParameter("peso");
        String sangre = request.getParameter("sangre");
        String password = request.getParameter("password");

        switch (accion) {
            case "agregar" -> {
                Paciente paciente = new Paciente(sexo, birth, telefono, peso, sangre, correo, codigo, nombre, cui, password);
                HttpSession sesion = request.getSession();
                pacienteDAO.create(paciente);
                sesion.setAttribute("user", paciente);
                sesion.setAttribute("tipoUser", 4);
                response.sendRedirect("paciente/inicioPaciente.jsp");
            }
            case "perfil" -> {
                Paciente paciente = (Paciente) request.getSession().getAttribute("user");
                paciente.setNombre(nombre);
                paciente.setCUI(cui);
                paciente.setBirth(LocalDate.parse(birth));
                paciente.setCorreo(correo);
                paciente.setTelefono(telefono);
                paciente.setPeso(Float.parseFloat(peso));

                pacienteDAO.update(paciente);
                request.getSession().setAttribute("user", paciente);
                response.sendRedirect("paciente/inicioPaciente.jsp");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion.equals("logout")) {
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");
        }
    }

}
