package web;

import datos.CRUD;
import datos.admin.AdminDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.usuario.Admin;

/**
 *
 * @author asael
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {

    private CRUD<Admin> adminDAO = AdminDAOImpl.getAdminDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Admin> admins = adminDAO.getListado();
        
        if (admins.isEmpty()) {
            response.sendRedirect("admin/lecturaArchivo.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    
    
}
