package web.lecturaArchivo;

import datos.*;
import datos.admin.*;
import datos.consulta.*;
import datos.especialidad.*;
import datos.examen.*;
import datos.laboratorista.*;
import datos.medico.*;
import datos.paciente.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.consulta.*;
import model.especialidad.*;
import model.examen.*;
import model.usuario.*;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import otros.FileInputException;
import static otros.Validaciones.*;
import static web.lecturaArchivo.lecturaAdmin.leerAdmin;
import static web.lecturaArchivo.LecturaPaciente.leerPaciente;
import static web.lecturaArchivo.LecturaEspecialidad.leerEspecialidad;
import static web.lecturaArchivo.LecturaMedico.leerMedico;
import static web.lecturaArchivo.LecturaTipoExamen.leerTipoExamen;
import static web.lecturaArchivo.LecturaLaboratorista.leerLaboratorista;
import static web.lecturaArchivo.LecturaResultado.leerResultado;
import static web.lecturaArchivo.LecturaConsulta.leerConsulta;
import static web.lecturaArchivo.LecturaInforme.leerInforme;

/**
 *
 * @author asael
 */
@WebServlet("/LecturaArchivo")
@MultipartConfig
public class LecturaArchivoServlet extends HttpServlet {
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("archivoEntrada");
        String nombreArchivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        //Guardar archivo en servidor
        guardarArchivo(filePart, nombreArchivo);

        //Leer datos del archivo XML
        leerArchivoXML(nombreArchivo);
        
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void guardarArchivo(Part filePart, String nombreArchivo) {
        File ruta = new File("/home/asael/uploads/datosEntrada");
        File file = new File(ruta, nombreArchivo);

        try ( InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void leerArchivoXML(String nombreArchivo) {
        try {
            //DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Crear documento
            File xml = new File("/home/asael/uploads/datosEntrada/"+nombreArchivo);
            Document doc = builder.parse(xml);

            //Leer etiqueta admin
            leerAdmin(doc);

            //Leer etiqueta paciente
            leerPaciente(doc);

            //Leer etiqueta consulta (especialidad)
            leerEspecialidad(doc);

            //Leer etiqueta doctor (medico)
            leerMedico(doc);

            //Leer etiqueta examen (Tipo Examen)
            leerTipoExamen(doc);

            //Leer etiqueta laboratorista
            leerLaboratorista(doc);

            //Leer etiqueta resultado (Examen y Resultado)
            leerResultado(doc);

            //Leer etiqueta cita (consulta)
            leerConsulta(doc);

            //Leer etiqueta reporte (informe)
            leerInforme(doc);

        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            System.out.println(System.out);
        }
    }
    
    private String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }

}
