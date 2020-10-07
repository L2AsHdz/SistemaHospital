package web.laboratorista;

import datos.examen.ExamenDAO;
import datos.examen.ExamenDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.examen.Examen;
import model.usuario.Laboratorista;

/**
 * @date 6/10/2020
 * @time 21:50:57
 * @author asael
 */
@WebServlet("/CulminarExamenServlet")
public class CulminarExamenServlet extends HttpServlet {
    
    private final ExamenDAO examenDAO = ExamenDAOImpl.getExamenDAO();

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
        HttpSession sesion = request.getSession();
        Laboratorista lab =  (Laboratorista) sesion.getAttribute("user");
        
        switch (accion) {
            case "listar" -> {
                List<Examen> examenes = examenDAO.getExamenesPendientesToday(lab.getCodigoTipoExamen());
                request.setAttribute("examenesToday", examenes);
                request.getRequestDispatcher("laboratorista/examenesDelDia.jsp").forward(request, response);
            }
        }
    }
}
