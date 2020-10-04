package web.paciente;

import datos.consulta.InformeDAO;
import datos.consulta.InformeDAOImpl;
import datos.examen.ResultadoDAO;
import datos.examen.ResultadoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.consulta.Informe;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "historial" -> {
                HttpSession sesion = request.getSession();
                Paciente paciente = (Paciente) sesion.getAttribute("user");
                String codPaciente = paciente.getCodigo();
                List<Informe> informes = informeDAO.getListInformeByPaciente(codPaciente);
                List<Resultado> resultados = resultadoDAO.getListResultadoByPaciente(codPaciente);
                sesion.setAttribute("informes", informes);
                sesion.setAttribute("resultados", resultados);
                response.sendRedirect("paciente/historialMedico.jsp");
            }
        }
    }

    
}
