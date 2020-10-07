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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        Laboratorista lab = (Laboratorista) request.getSession().getAttribute("user");
        List<Resultado> resultados;
        
        switch (accion) {
            case "reporte1" -> {
                resultados = resultadoDAO.getResultadosRealizadosHoy(lab.getCodigo());
                request.setAttribute("resultadosRealizados", resultados);
                request.getRequestDispatcher("laboratorista/examenesRealizadosHoy.jsp").forward(request, response);
            }
            case "reporte2" -> {
                String fechaInicial = request.getParameter("fechaInicial");
                String fechaFinal = request.getParameter("fechaFinal");
                
                if (!fechaFinal.trim().isEmpty() && !fechaInicial.trim().isEmpty()) {
                    resultados = resultadoDAO.getResultadosRealizadosPorDia(lab.getCodigo(), fechaInicial, fechaFinal, 1);
                } else {
                    resultados = resultadoDAO.getResultadosRealizadosPorDia(lab.getCodigo(), fechaInicial, fechaFinal, 2);
                }
                
                request.setAttribute("fechaInicial", fechaInicial);
                request.setAttribute("fechaFinal", fechaFinal);
                request.setAttribute("cantResultados", resultados);
                request.setAttribute("buscado", true);
                request.getRequestDispatcher("laboratorista/cantExamenesPorDia.jsp").forward(request, response);
            }
            case "reporte3" -> {
                resultados = resultadoDAO.getResultadosRealizadosPorDia(lab.getCodigo());
                request.setAttribute("cantResultados", resultados);
                request.getRequestDispatcher("laboratorista/fechasConMasExamenes.jsp").forward(request, response);
            }
        }
    }

}
