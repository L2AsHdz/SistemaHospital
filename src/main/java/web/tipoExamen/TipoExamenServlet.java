package web.tipoExamen;

import datos.CRUD;
import datos.examen.TipoExamenDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.examen.TipoExamen;

/**
 *
 * @author asael
 */
@WebServlet("/TipoExamenServlet")
public class TipoExamenServlet extends HttpServlet {

    private final CRUD<TipoExamen> tipoExamenDAO = TipoExamenDAOImpl.getTipoExamenDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TipoExamen> tiposExamen = tipoExamenDAO.getListado();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("tiposExamen", tiposExamen);
        response.sendRedirect("admin/tiposExamenes.jsp");
    }
    
    
}
