package web.medico;

import datos.consulta.ConsultaDAO;
import datos.consulta.ConsultaDAOImpl;
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

/**
 * @date 5/10/2020
 * @time 01:04:04
 * @author asael
 */
@WebServlet("/ReportesMedicoServlet")
public class ReportesMedicoServlet extends HttpServlet {

    private final ConsultaDAO consultaDAO = ConsultaDAOImpl.getconsultaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Medico medico = (Medico) sesion.getAttribute("user");

        switch (accion) {
            case "reporte1" -> {
                String fechaInicial = request.getParameter("fechaInicial");
                String fechaFinal = request.getParameter("fechaFinal");

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
                System.out.println("que pedo que pedo");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
