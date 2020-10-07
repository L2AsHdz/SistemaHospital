package web.examen;

import datos.examen.ExamenDAO;
import datos.examen.ExamenDAOImpl;
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
@WebServlet("/pdf")
public class PDF extends HttpServlet {

    private final ExamenDAO examenDAO = ExamenDAOImpl.getExamenDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigoExamen = request.getParameter("codExamen");
        response.setContentType("application/pdf");

        byte[] orden = examenDAO.getOrdenByCodExamen(codigoExamen);
        response.getOutputStream().write(orden);
    }

}
