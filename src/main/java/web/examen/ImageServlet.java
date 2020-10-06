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
 * @date 5/10/2020
 * @time 20:27:27
 * @author asael
 */
@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    private final ResultadoDAO resultadoDAO = ResultadoDAOImpl.getResultadoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codExamen = Integer.parseInt(request.getPathInfo().substring(1));

        resultadoDAO.getFileResultado(codExamen, response);
    }

}
