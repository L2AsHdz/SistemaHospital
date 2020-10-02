package web.consulta;

import datos.CRUD;
import datos.especialidad.EspecialidadDAOImpl;
import datos.medico.MedicoDAO;
import datos.medico.MedicoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.especialidad.Especialidad;
import model.usuario.Medico;

/**
 * @date 1/10/2020
 * @time 17:04:44
 * @author asael
 */
@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {
    
    private final CRUD<Especialidad> especialDAO = EspecialidadDAOImpl.getEspecialidadDAO();
    private final MedicoDAO medicoDAO = MedicoDAOImpl.getMedicoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "buscar" -> {
                String nombre = request.getParameter("nombre");
                String idEspecialidad = request.getParameter("especialidad");
                String hora = request.getParameter("hora");
                
                List<Especialidad> especialidades = especialDAO.getListado();
                List<Medico> medicos;
                
                if (!nombre.trim().isEmpty() && !idEspecialidad.isEmpty() && !hora.isEmpty()) {
                    medicos = medicoDAO.getFilteredList(nombre, idEspecialidad, hora, 1);
                } else if (!idEspecialidad.isEmpty() && !hora.isEmpty()) {
                    medicos = medicoDAO.getFilteredList(nombre, idEspecialidad, hora, 2);
                } else if (!nombre.trim().isEmpty() && !idEspecialidad.isEmpty()) {
                    medicos = medicoDAO.getFilteredList(nombre, idEspecialidad, hora, 3);
                } else if (!nombre.trim().isEmpty() && !hora.isEmpty()) {
                    medicos = medicoDAO.getFilteredList(nombre, idEspecialidad, hora, 4);
                } else if (!nombre.trim().isEmpty()) {
                    medicos = medicoDAO.getFilteredList(nombre, idEspecialidad, hora, 5);
                } else if (!idEspecialidad.isEmpty()) {
                    medicos = medicoDAO.getFilteredList(nombre, idEspecialidad, hora, 6);
                } else if (!hora.isEmpty()) {
                    medicos = medicoDAO.getFilteredList(nombre, idEspecialidad, hora, 7);
                } else {
                    medicos = medicoDAO.getListado();
                }
                
                request.setAttribute("especialidades", especialidades);
                request.setAttribute("medicos", medicos);
                request.getRequestDispatcher("paciente/agendarConsulta.jsp").forward(request, response);
                
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "add" -> {
                List<Especialidad> especialidades = especialDAO.getListado();
                request.setAttribute("especialidades", especialidades);
                request.getRequestDispatcher("paciente/agendarConsulta.jsp").forward(request, response);
            }
            case "buscar" -> {
            }
        }
    }

    
}
