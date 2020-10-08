package web.examen;

import datos.examen.ResultadoDAO;
import datos.examen.ResultadoDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date 7/10/2020
 * @time 02:04:20
 * @author asael
 */
@WebServlet("/pdfR")
public class PDFR extends HttpServlet {

    private final ResultadoDAO resultadoDAO = ResultadoDAOImpl.getResultadoDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigoExamen = request.getParameter("codExamen");
        response.setContentType("application/pdf");

        byte[] orden = resultadoDAO.getResultadoByCodExamen(codigoExamen);
        if (orden != null) {
            response.getOutputStream().write(orden);
        }
    }

}
