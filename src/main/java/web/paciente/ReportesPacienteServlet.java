package web.paciente;

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
import model.examen.Resultado;
import model.usuario.Paciente;

/**
 * @date 5/10/2020
 * @time 12:09:01
 * @author asael
 */
@WebServlet("/ReportesPacienteServlet")
public class ReportesPacienteServlet extends HttpServlet {
    
    private final ResultadoDAO resultadoDAO = ResultadoDAOImpl.getResultadoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Paciente paciente = (Paciente) sesion.getAttribute("user");
        
        switch (accion) {
            case "reporte1" -> {
                List<Resultado> resultados = resultadoDAO.getLastFiveResultados(paciente.getCodigo());
                sesion.setAttribute("lastResultados", resultados);
                response.sendRedirect("paciente/ultimosExamenes.jsp");
            }
        }
    }

    
}
