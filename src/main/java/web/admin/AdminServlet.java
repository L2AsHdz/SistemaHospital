package web.admin;

import datos.CRUD;
import datos.admin.AdminDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.usuario.Admin;

/**
 * @date 1/10/2020
 * @time 23:42:00
 * @author asael
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    
    CRUD<Admin> adminDAO = AdminDAOImpl.getAdminDAO();

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

        switch (accion) {
            case "logout" -> {
                request.getSession().invalidate();
                response.sendRedirect("index.jsp");
            }
            case "perfil" -> {
                String nombre = request.getParameter("nombre");
                String cui = request.getParameter("cui");
                
                Admin admin = (Admin) request.getSession().getAttribute("user");
                admin.setCUI(cui);
                admin.setNombre(nombre);
                
                adminDAO.update(admin);
                request.getSession().setAttribute("user", admin);
                response.sendRedirect("admin/inicioAdmin.jsp");
            }
        }
    }
}
