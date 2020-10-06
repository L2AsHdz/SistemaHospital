package web.admin;

import datos.examen.TipoExamenDAO;
import datos.examen.TipoExamenDAOImpl;
import datos.medico.MedicoDAO;
import datos.medico.MedicoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.examen.TipoExamen;
import model.usuario.Medico;

/**
 * @date 6/10/2020
 * @time 11:54:19
 * @author asael
 */
@WebServlet("/ReportesAdminServlet")
public class ReportesAdminServlet extends HttpServlet {

    private final MedicoDAO medicoDAO = MedicoDAOImpl.getMedicoDAO();
    private final TipoExamenDAO tipoExamenDAO = TipoExamenDAOImpl.getTipoExamenDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");
        List<Medico> medicos;
        List<TipoExamen> tiposExamen;

        switch (accion) {
            case "reporte1" -> {

                if (!fechaFinal.trim().isEmpty() && !fechaInicial.trim().isEmpty()) {
                    medicos = medicoDAO.getMedicosConMasinformes(fechaInicial, fechaFinal, 1);
                } else {
                    medicos = medicoDAO.getMedicosConMasinformes(fechaInicial, fechaFinal, 2);
                }
                sendDatosReportesMedicos(fechaInicial, fechaFinal, "medicosInformes", medicos,
                        "admin/medicosConMasInformes.jsp", request, response);
            }
            case "reporte2" -> {
                if (!fechaFinal.trim().isEmpty() && !fechaInicial.trim().isEmpty()) {
                    medicos = medicoDAO.getIngresosDeMedicos(fechaInicial, fechaFinal, 1);
                } else {
                    medicos = medicoDAO.getIngresosDeMedicos(fechaInicial, fechaFinal, 2);
                }
                sendDatosReportesMedicos(fechaInicial, fechaFinal, "medicosIngresos", medicos,
                        "admin/ingresosPorMedico.jsp", request, response);
            }
            case "reporte3" -> {
                if (!fechaFinal.trim().isEmpty() && !fechaInicial.trim().isEmpty()) {
                    medicos = medicoDAO.getMedicosConMenosConsultas(fechaInicial, fechaFinal, 1);
                } else {
                    medicos = medicoDAO.getMedicosConMenosConsultas(fechaInicial, fechaFinal, 2);
                }
                sendDatosReportesMedicos(fechaInicial, fechaFinal, "medicosConsultas", medicos,
                        "admin/medicosConMenosConsultas.jsp", request, response);
            }
            case "reporte4" -> {
                if (!fechaFinal.trim().isEmpty() && !fechaInicial.trim().isEmpty()) {
                    tiposExamen = tipoExamenDAO.getExamenesDemandados(fechaInicial, fechaFinal, 1);
                } else {
                    tiposExamen = tipoExamenDAO.getExamenesDemandados(fechaInicial, fechaFinal, 2);
                }
                request.setAttribute("fechaInicial", fechaInicial);
                request.setAttribute("fechaFinal", fechaFinal);
                request.setAttribute("examenesDemandados", tiposExamen);
                request.setAttribute("buscado", true);
                request.getRequestDispatcher("admin/examenesMasDemandados.jsp").forward(request, response);
            }
            case "reporte5" -> {
                if (!fechaFinal.trim().isEmpty() && !fechaInicial.trim().isEmpty()) {
                    medicos = medicoDAO.getMedicosConMasSolicitudes(fechaInicial, fechaFinal, 1);
                } else {
                    medicos = medicoDAO.getMedicosConMasSolicitudes(fechaInicial, fechaFinal, 2);
                }
                sendDatosReportesMedicos(fechaInicial, fechaFinal, "medicosExamenes", medicos, 
                        "admin/examenesSolicitadosPorMedico.jsp", request, response);
            }
        }
    }

    private void sendDatosReportesMedicos(String fechaInicial, String fechaFinal, String nombreLista,
            List<Medico> medicos, String rutaJSP, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("fechaInicial", fechaInicial);
        request.setAttribute("fechaFinal", fechaFinal);
        request.setAttribute(nombreLista, medicos);
        request.setAttribute("buscado", true);
        request.getRequestDispatcher(rutaJSP).forward(request, response);
    }
}
