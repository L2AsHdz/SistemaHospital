package web.examen;

import datos.CRUD;
import datos.examen.ExamenDAO;
import datos.examen.ExamenDAOImpl;
import datos.examen.TipoExamenDAOImpl;
import datos.medico.MedicoDAOImpl;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.examen.Examen;
import model.examen.TipoExamen;
import model.usuario.Medico;
import model.usuario.Paciente;

/**
 * @date 3/10/2020
 * @time 01:39:45
 * @author asael
 */
@WebServlet("/ExamenServlet")
@MultipartConfig
public class ExamenServlet extends HttpServlet {

    private final ExamenDAO examenDAO = ExamenDAOImpl.getExamenDAO();
    private final CRUD<TipoExamen> tipoExamenDAO = TipoExamenDAOImpl.getTipoExamenDAO();
    private final CRUD<Medico> medicoDAO = MedicoDAOImpl.getMedicoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar" -> {
                List<TipoExamen> tiposExamen = tipoExamenDAO.getListado();
                request.setAttribute("tiposExamen", tiposExamen);
                request.getRequestDispatcher("paciente/agendarExamen.jsp").forward(request, response);
            }
            case "preAgendar" -> {
                String codigo = request.getParameter("codigo");
                TipoExamen tipoExamen = tipoExamenDAO.getObject(codigo);
                List<Medico> medicos = medicoDAO.getListado();
                request.setAttribute("tipoExamen", tipoExamen);
                request.setAttribute("medicos", medicos);
                request.setAttribute("agendar", true);
                request.getRequestDispatcher("paciente/agendarExamen.jsp").forward(request, response);
            }
            case "agendar" -> {
                Paciente paciente = (Paciente) request.getSession().getAttribute("user");
                String codPaciente = paciente.getCodigo();
                String codMedico1 = request.getParameter("medico");
                String codMedico2 = request.getParameter("medicoO");
                String codigoMedico = codMedico1 == null ? codMedico2 : codMedico1;
                String codigoExamen = request.getParameter("codigo");
                String fecha = request.getParameter("fecha");
                String hora = request.getParameter("hora");
                float costo = Float.parseFloat(request.getParameter("costo"));
                InputStream orden = null;
                try {
                orden = request.getPart("orden").getInputStream();
                } catch (IOException | ServletException | NullPointerException e) {
                }
                
                Examen examen = new Examen(codPaciente, codigoExamen, codigoMedico, orden, fecha, hora, 0, costo);
                
                examenDAO.create(examen);
                request.setAttribute("success", true);
                request.getRequestDispatcher("paciente/inicioPaciente.jsp").forward(request, response);
            }
        }
    }

}
