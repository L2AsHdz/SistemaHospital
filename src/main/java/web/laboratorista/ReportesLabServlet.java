package web.laboratorista;

import datos.examen.ResultadoDAO;
import datos.examen.ResultadoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.examen.Resultado;
import model.usuario.Laboratorista;

/**
 * @date 7/10/2020
 * @time 10:19:53
 * @author asael
 */
@WebServlet("/ReportesLabServlet")
public class ReportesLabServlet extends HttpServlet {
    
    private final ResultadoDAO resultadoDAO = ResultadoDAOImpl.getResultadoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        Laboratorista lab = (Laboratorista) request.getSession().getAttribute("user");
        
        switch (accion) {
            case "reporte1" -> {
                List<Resultado> resultados = resultadoDAO.getResultadosRealizadosHoy(lab.getCodigo());
                request.setAttribute("resultadosRealizados", resultados);
                request.getRequestDispatcher("laboratorista/examenesRealizadosHoy.jsp").forward(request, response);
            }
        }
    }

    
}
