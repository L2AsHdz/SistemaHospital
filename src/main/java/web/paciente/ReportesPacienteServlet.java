package web.paciente;

import datos.CRUD;
import datos.consulta.InformeDAO;
import datos.consulta.InformeDAOImpl;
import datos.examen.ResultadoDAO;
import datos.examen.ResultadoDAOImpl;
import datos.examen.TipoExamenDAOImpl;
import datos.medico.MedicoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.consulta.Informe;
import model.examen.Resultado;
import model.examen.TipoExamen;
import model.usuario.Paciente;

/**
 * @date 5/10/2020
 * @time 12:09:01
 * @author asael
 */
@WebServlet("/ReportesPacienteServlet")
public class ReportesPacienteServlet extends HttpServlet {

    private final ResultadoDAO resultadoDAO = ResultadoDAOImpl.getResultadoDAO();
    private final CRUD<TipoExamen> tipoExamenDAO = TipoExamenDAOImpl.getTipoExamenDAO();
    private final InformeDAO informeDAO = InformeDAOImpl.getInformeDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Paciente paciente = (Paciente) sesion.getAttribute("user");
        String fechaInicial = request.getParameter("fechaInicial");
        String fechaFinal = request.getParameter("fechaFinal");

        switch (accion) {
            case "reporte2" -> {
                String codTipoExamen = request.getParameter("codTipoExamen");

                List<TipoExamen> tiposExamen = tipoExamenDAO.getListado();
                List<Resultado> resultados;

                if (!fechaInicial.trim().isEmpty() && !fechaFinal.trim().isEmpty()) {
                    resultados = resultadoDAO.getResultadosbyTipoExamen(paciente.getCodigo(), codTipoExamen, fechaInicial, fechaFinal, 1);
                } else {
                    resultados = resultadoDAO.getResultadosbyTipoExamen(paciente.getCodigo(), codTipoExamen, fechaInicial, fechaFinal, 2);
                }

                request.setAttribute("tiposExamen", tiposExamen);
                request.setAttribute("fechaInicial", fechaInicial);
                request.setAttribute("fechaFinal", fechaFinal);
                request.setAttribute("resultadosByTipo", resultados);
                request.setAttribute("buscado", true);
                request.getRequestDispatcher("paciente/examenesPorTipo.jsp").forward(request, response);
            }
            case "reporte4" -> {
                String codMedico = request.getParameter("codMedico");

                if (MedicoDAOImpl.getMedicoDAO().exists(codMedico)) {
                    List<Informe> informes;
                    if (!fechaInicial.trim().isEmpty() && !fechaFinal.trim().isEmpty()) {
                        informes = informeDAO.getInformesByMedico(paciente.getCodigo(), codMedico, fechaInicial, fechaFinal, 1);
                    } else {
                        informes = informeDAO.getInformesByMedico(paciente.getCodigo(), codMedico, fechaInicial, fechaFinal, 2);
                    }

                    request.setAttribute("codMedico", codMedico);
                    request.setAttribute("fechaInicial", fechaInicial);
                    request.setAttribute("fechaFinal", fechaFinal);
                    request.setAttribute("consultasByMedico", informes);
                    request.setAttribute("buscado", true);
                    request.getRequestDispatcher("paciente/consultasPorMedico.jsp").forward(request, response);

                } else {
                    request.setAttribute("error", "No existe un medico con el codigo ingresado");
                    request.getRequestDispatcher("paciente/consultasPorMedico.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Paciente paciente = (Paciente) sesion.getAttribute("user");

        switch (accion) {
            case "reporte1" -> {
                List<Resultado> resultados = resultadoDAO.getLastFiveResultados(paciente.getCodigo());
                sesion.setAttribute("lastResultados", resultados);
                response.sendRedirect("paciente/ultimosExamenes.jsp");
            }
            case "listarTipos" -> {
                List<TipoExamen> tiposExamen = tipoExamenDAO.getListado();
                request.setAttribute("tiposExamen", tiposExamen);
                request.getRequestDispatcher("paciente/examenesPorTipo.jsp").forward(request, response);
            }
            case "reporte3" -> {
                List<Informe> informes = informeDAO.getLastFiveInformes(paciente.getCodigo());
                sesion.setAttribute("lastConsultas", informes);
                response.sendRedirect("paciente/ultimasConsultas.jsp");
            }
        }
    }

}
