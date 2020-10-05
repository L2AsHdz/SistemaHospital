package web.paciente;

import datos.consulta.ConsultaDAO;
import datos.consulta.ConsultaDAOImpl;
import datos.consulta.InformeDAO;
import datos.consulta.InformeDAOImpl;
import datos.examen.ExamenDAO;
import datos.examen.ExamenDAOImpl;
import datos.examen.ResultadoDAO;
import datos.examen.ResultadoDAOImpl;
import datos.paciente.PacienteDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.consulta.Consulta;
import model.consulta.Informe;
import model.examen.Examen;
import model.examen.Resultado;
import model.usuario.Paciente;

/**
 * @date 3/10/2020
 * @time 18:58:41
 * @author asael
 */
@WebServlet("/HistorialMedicoServlet")
public class HistorialMedicoServlet extends HttpServlet {

    private final InformeDAO informeDAO = InformeDAOImpl.getInformeDAO();
    private final ResultadoDAO resultadoDAO = ResultadoDAOImpl.getResultadoDAO();
    private final ConsultaDAO consultaDAO = ConsultaDAOImpl.getconsultaDAO();
    private final ExamenDAO examenDAO = ExamenDAOImpl.getExamenDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "buscarHistorial" -> {
                String codPaciente = request.getParameter("codPaciente");
                if (PacienteDAOImpl.getPacienteDAO().exists(codPaciente)) {
                    List<Informe> informes = informeDAO.getListInformeByPaciente(codPaciente);
                    List<Resultado> resultados = resultadoDAO.getListResultadoByPaciente(codPaciente);
                    request.setAttribute("buscado", true);
                    request.setAttribute("informes", informes);
                    request.setAttribute("resultados", resultados);
                    request.getRequestDispatcher("medico/historialMedico.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "No existe un paciente con el codigo ingresado");
                    request.getRequestDispatcher("medico/historialMedico.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Paciente paciente = (Paciente) sesion.getAttribute("user");
        String codPaciente = paciente.getCodigo();

        switch (accion) {
            case "historial" -> {
                List<Informe> informes = informeDAO.getListInformeByPaciente(codPaciente);
                List<Resultado> resultados = resultadoDAO.getListResultadoByPaciente(codPaciente);
                sesion.setAttribute("informes", informes);
                sesion.setAttribute("resultados", resultados);
                response.sendRedirect("paciente/historialMedico.jsp");
            }
            case "pendientes" -> {
                List<Consulta> consultasP = consultaDAO.getConsultasPendientes(codPaciente);
                List<Examen> examenesP = examenDAO.getExamenesPendientes(codPaciente);
                sesion.setAttribute("consultasP", consultasP);
                sesion.setAttribute("examenesP", examenesP);
                response.sendRedirect("paciente/pendientes.jsp");
            }
        }
    }

}
