package web.laboratorista;

import datos.CRUD;
import datos.examen.ExamenDAO;
import datos.examen.ExamenDAOImpl;
import datos.examen.ResultadoDAOImpl;
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
import model.examen.Resultado;
import model.usuario.Laboratorista;

/**
 * @date 6/10/2020
 * @time 21:50:57
 * @author asael
 */
@WebServlet("/CulminarExamenServlet")
@MultipartConfig
public class CulminarExamenServlet extends HttpServlet {
    
    private final ExamenDAO examenDAO = ExamenDAOImpl.getExamenDAO();
    private final CRUD<Resultado> resultadoDAO = ResultadoDAOImpl.getResultadoDAO();

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
        HttpSession sesion = request.getSession();
        Laboratorista lab =  (Laboratorista) sesion.getAttribute("user");
        
        switch (accion) {
            case "listar" -> {
                List<Examen> examenes = examenDAO.getExamenesPendientesToday(lab.getCodigoTipoExamen());
                request.setAttribute("examenesToday", examenes);
                request.getRequestDispatcher("laboratorista/examenesDelDia.jsp").forward(request, response);
            }
            case "finalizar" -> {
                String codExamen = request.getParameter("codExamen");
                String fecha = request.getParameter("fecha");
                String hora = request.getParameter("hora");
                InputStream fileResultado = request.getPart("resultado").getInputStream();
                
                Resultado resultado = new Resultado(codExamen, lab.getCodigo(), fileResultado, fecha, hora);
                examenDAO.setEstado(codExamen, 1);
                resultadoDAO.create(resultado);
                response.sendRedirect("laboratorista/inicioLab.jsp");
            }
        }
    }
}
