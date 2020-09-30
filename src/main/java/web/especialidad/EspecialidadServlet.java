package web.especialidad;

import datos.especialidad.EspecialidadDAO;
import datos.especialidad.EspecialidadDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.especialidad.Especialidad;

/**
 *
 * @author asael
 */
@WebServlet("/EspecialidadServlet")
public class EspecialidadServlet extends HttpServlet {

    private final EspecialidadDAO especialidadDAO = EspecialidadDAOImpl.getEspecialidadDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String costo = request.getParameter("costo");

        switch (accion) {
            case "agregar" -> {
                if (!especialidadDAO.exists(nombre)) {
                    especialidadDAO.create(new Especialidad(nombre, costo));
                    redirect(request, response);
                    //mostrar mensaje de exito
                } else {
                    //Mostrar error por entidad repetida
                }
            }
            case "modificar" -> {
                Especialidad especialidad = new Especialidad(nombre, costo);
                especialidad.setId(Integer.parseInt(id));
                especialidadDAO.update(especialidad);
                redirect(request, response);
                //mostrar mensaje de exito
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null && accion.equals("editar")) {
            String nombre = request.getParameter("nombre");
            Especialidad especialidad = especialidadDAO.getObject(nombre);
            request.setAttribute("especialidad", especialidad);
            request.getRequestDispatcher("admin/formEspecialidad.jsp").forward(request, response);
        } else {
            redirect(request, response);
        }
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Especialidad> especialidades = especialidadDAO.getListado();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("especialidades", especialidades);
        response.sendRedirect("admin/especialidades.jsp");
    }

}
