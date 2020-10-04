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
 * @date 4/10/2020
 * @time 08:40:40
 * @author asael
 */
@WebServlet("/CulminarConsultaServlet")
public class CulminarConsultaServlet extends HttpServlet {
    
    private final ConsultaDAO consultaDAO = ConsultaDAOImpl.getconsultaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "finalizar" -> {
                System.out.println("holi: " + request.getParameter("codConsulta"));
                response.sendRedirect("medico/inicioMedico.jsp");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Medico medico = (Medico) sesion.getAttribute("user");
        switch (accion) {
            case "listar" -> {
                List<Consulta> consultasToday = consultaDAO.getConsultasPendientesToday(medico.getCodigo());
                sesion.setAttribute("consultasToday", consultasToday);
                response.sendRedirect("medico/culminarConsulta.jsp");
            }
        }
    }
}
