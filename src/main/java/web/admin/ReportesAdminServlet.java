package web.admin;

import datos.medico.MedicoDAO;
import datos.medico.MedicoDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.usuario.Medico;

/**
 * @date 6/10/2020
 * @time 11:54:19
 * @author asael
 */
@WebServlet("/ReportesAdminServlet")
public class ReportesAdminServlet extends HttpServlet {

    private final MedicoDAO medicoDAO = MedicoDAOImpl.getMedicoDAO();

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

        switch (accion) {
            case "reporte1" -> {

                if (!fechaFinal.trim().isEmpty() && !fechaInicial.trim().isEmpty()) {
                    medicos = medicoDAO.getMedicosConMasinformes(fechaInicial, fechaFinal, 1);
                } else {
                    medicos = medicoDAO.getMedicosConMasinformes(fechaInicial, fechaFinal, 2);
                }
                request.setAttribute("fechaInicial", fechaInicial);
                request.setAttribute("fechaFinal", fechaFinal);
                request.setAttribute("medicosInformes", medicos);
                request.setAttribute("buscado", true);
                request.getRequestDispatcher("admin/medicosConMasInformes.jsp").forward(request, response);
            }
            case "reporte2" -> {
                if (!fechaFinal.trim().isEmpty() && !fechaInicial.trim().isEmpty()) {
                    medicos = medicoDAO.getIngresosDeMedicos(fechaInicial, fechaFinal, 1);
                } else {
                    medicos = medicoDAO.getIngresosDeMedicos(fechaInicial, fechaFinal, 2);
                }
                request.setAttribute("fechaInicial", fechaInicial);
                request.setAttribute("fechaFinal", fechaFinal);
                request.setAttribute("medicosIngresos", medicos);
                request.setAttribute("buscado", true);
                request.getRequestDispatcher("admin/ingresosPorMedico.jsp").forward(request, response);
            }
        }
    }
}
