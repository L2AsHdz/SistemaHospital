package web.medico;

import datos.consulta.ConsultaDAO;
import datos.consulta.ConsultaDAOImpl;
import datos.paciente.PacienteDAO;
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
import model.usuario.Medico;
import model.usuario.Paciente;

/**
 * @date 5/10/2020
 * @time 01:04:04
 * @author asael
 */
@WebServlet("/ReportesMedicoServlet")
public class ReportesMedicoServlet extends HttpServlet {

    private final ConsultaDAO consultaDAO = ConsultaDAOImpl.getconsultaDAO();
    private final PacienteDAO pacienteDAO = PacienteDAOImpl.getPacienteDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Medico medico = (Medico) sesion.getAttribute("user");
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        switch (accion) {
            case "reporte1" -> {

                List<Consulta> consultas;

                if (!fechaFinal.trim().isEmpty() && !fechaInicial.trim().isEmpty()) {
                    consultas = consultaDAO.getConsultasAgendadasByIntevalo(medico.getCodigo(), fechaInicial, fechaFinal, 1);
                } else {
                    consultas = consultaDAO.getConsultasAgendadasByIntevalo(medico.getCodigo(), fechaInicial, fechaFinal, 2);
                }
                request.setAttribute("fechaInicial", fechaInicial);
                request.setAttribute("fechaFinal", fechaFinal);
                request.setAttribute("consultas", consultas);
                request.setAttribute("buscado", true);
                request.getRequestDispatcher("medico/citasAgendadas.jsp").forward(request, response);
            }
            case "reporte2" -> {

                List<Paciente> pacientes;
                
                if (!fechaInicial.trim().isEmpty() && !fechaFinal.trim().isEmpty()) {
                    pacientes = pacienteDAO.getPacientesConMasInformes(fechaInicial, fechaFinal, 1);
                    System.out.println("con filtro");
                } else {
                    pacientes = pacienteDAO.getPacientesConMasInformes(fechaInicial, fechaFinal, 2);
                }
                request.setAttribute("fechaInicial", fechaInicial);
                request.setAttribute("fechaFinal", fechaFinal);
                request.setAttribute("pacientes", pacientes);
                request.setAttribute("buscado", true);
                request.getRequestDispatcher("medico/pacientesInformes.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
