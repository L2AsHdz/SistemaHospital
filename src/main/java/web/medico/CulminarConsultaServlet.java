package web.medico;

import datos.CRUD;
import datos.consulta.ConsultaDAO;
import datos.consulta.ConsultaDAOImpl;
import datos.consulta.InformeDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.consulta.Consulta;
import model.consulta.Informe;
import model.usuario.Medico;

/**
 * @date 4/10/2020
 * @time 08:40:40
 * @author asael
 */
@WebServlet("/CulminarConsultaServlet")
public class CulminarConsultaServlet extends HttpServlet {
    
    private final ConsultaDAO consultaDAO = ConsultaDAOImpl.getconsultaDAO();
    private final CRUD<Informe> informeDAO = InformeDAOImpl.getInformeDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "finalizar" -> {
                String codigoConsulta = request.getParameter("codConsulta");
                String contenidoInforme = request.getParameter("informe");
                String fecha = request.getParameter("fecha");
                String hora = request.getParameter("hora");
                
                Informe informe = new Informe(codigoConsulta, contenidoInforme, fecha, hora);
                
                informeDAO.create(informe);
                consultaDAO.setEstado(codigoConsulta, 1);
                response.sendRedirect(request.getContextPath()+"/CulminarConsultaServlet?accion=listar");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
        Medico medico = (Medico) sesion.getAttribute("user");
        switch (accion) {
            case "listar" -> {
                List<Consulta> consultasToday = consultaDAO.getConsultasPendientesToday(medico.getCodigo());
                sesion.setAttribute("consultasToday", consultasToday);
                response.sendRedirect("medico/culminarConsulta.jsp");
            }
        }
    }
}
