package web.laboratorista;

import datos.CRUD;
import datos.examen.TipoExamenDAOImpl;
import datos.laboratorista.LaboratoristaDAOImpl;
import datos.laboratorista.TurnoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.examen.TipoExamen;
import model.usuario.Laboratorista;
import model.usuario.Turno;

/**
 * @date 1/10/2020
 * @time 09:04:55
 * @author asael
 */
@WebServlet("/LaboratoristaServlet")
public class LaboratoristaServlet extends HttpServlet {
    
    private final CRUD<Laboratorista> laboratoristaDAO = LaboratoristaDAOImpl.getLaboratoristaDAO();
    private final CRUD<TipoExamen> tipoExamenDAO = TipoExamenDAOImpl.getTipoExamenDAO();
    private final CRUD<Turno> turnoDAO = TurnoDAOImpl.getTurnoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("editar")) {
            
        } else if (accion != null && accion.equals("add")) {
            List<TipoExamen> tiposExamen = tipoExamenDAO.getListado();
            request.setAttribute("tiposExamen", tiposExamen);
            request.getRequestDispatcher("admin/formLaboratorista.jsp").forward(request, response);
        } else {
            redirect(request, response);
        }
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Laboratorista> laboratoristas = laboratoristaDAO.getListado();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("laboratoristas", laboratoristas);
        response.sendRedirect("admin/laboratoristas.jsp");
    }
    
}
