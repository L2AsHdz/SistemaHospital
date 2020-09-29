package web;

import datos.CRUD;
import datos.admin.AdminDAOImpl;
import datos.laboratorista.LaboratoristaDAOImpl;
import datos.medico.MedicoDAOImpl;
import datos.paciente.PacienteDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.usuario.Admin;
import model.usuario.Laboratorista;
import model.usuario.Medico;
import model.usuario.Paciente;
import model.usuario.Usuario;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author asael
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final CRUD<Admin> adminDAO = AdminDAOImpl.getAdminDAO();
    private final CRUD<Medico> medicoDAO = MedicoDAOImpl.getMedicoDAO();
    private final CRUD<Laboratorista> laboratoristaDAO = LaboratoristaDAOImpl.getLaboratoristaDAO();
    private final CRUD<Paciente> pacienteDAO = PacienteDAOImpl.getPacienteDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        String password = request.getParameter("password");

        verificarCredenciales(codigo, DigestUtils.sha1Hex(password), response, request);
    }

    private void verificarCredenciales(String codigo, String encryptPassword, 
            HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        boolean login = false;
        int tipoUser = 0;

        Usuario user = null;
        List<Admin> admins = adminDAO.getListado();
        List<Medico> medicos = medicoDAO.getListado();
        List<Laboratorista> laboratoristas = laboratoristaDAO.getListado();
        List<Paciente> pacientes = pacienteDAO.getListado();

        for (Admin a : admins) {
            if (a.getCodigo().equals(codigo) && a.getPassword().equals(encryptPassword)) {
                login = true;
                user = a;
                tipoUser = 1;
            }
        }

        for (Medico m : medicos) {
            if (m.getCodigo().equals(codigo) && m.getPassword().equals(encryptPassword)) {
                login = true;
                user = m;
                tipoUser = 2;
            }
        }
        
        for (Laboratorista l : laboratoristas) {
            if (l.getCodigo().equals(codigo) && l.getPassword().equals(encryptPassword)) {
                login = true;
                user = l;
                tipoUser = 3;
            }
        }
        
        for (Paciente p : pacientes) {
            if (p.getCodigo().equals(codigo) && p.getPassword().equals(encryptPassword)) {
                login = true;
                user = p;
                tipoUser = 4;
            }
        }
        
        if (login) {
            switch (tipoUser) {
                case 1 -> {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("admin/inicioAdmin.jsp");
                }
                case 2 -> {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("medico/inicioMedico.jsp");
                }
                case 3 -> {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("laboratorista/inicioLab.jsp");
                }
                case 4 -> {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("paciente/inicioPaciente.jsp");
                }
            }
        } else {
            request.setAttribute("errorLogin", true);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
