package web.examen;

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
        String accion = request.getParameter("accion");
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String requiereOrden = request.getParameter("requiereOrden");
        String descripcion = request.getParameter("descripcion");
        String costo = request.getParameter("costo");
        String tipoInforme = request.getParameter("tipoInforme");

        switch (accion) {
            case "agregar" -> {
                if (!tipoExamenDAO.exists(codigo)) {
                    tipoExamenDAO.create(new TipoExamen(codigo, nombre, requiereOrden, descripcion, costo, tipoInforme));
                    redirect(request, response);
                    //mostrar mensaje de exito
                } else {
                    //Mostrar error por entidad repetida
                }
            }
            case "modificar" -> {
                tipoExamenDAO.update(new TipoExamen(codigo, nombre, requiereOrden, descripcion, costo, tipoInforme));
                redirect(request, response);
                    //mostrar mensaje de exito
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("editar")) {
            String codigo = request.getParameter("codigo");
            TipoExamen tipoExamen = tipoExamenDAO.getObject(codigo);
            request.setAttribute("tipoExamen", tipoExamen);
            request.getRequestDispatcher("admin/formTipoExamen.jsp").forward(request, response);
        } else {
            redirect(request, response);
        }
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TipoExamen> tiposExamen = tipoExamenDAO.getListado();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("tiposExamen", tiposExamen);
        response.sendRedirect("admin/tiposExamenes.jsp");
    }
}
