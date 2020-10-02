package web.lecturaArchivo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import otros.FileInputException;
import static web.lecturaArchivo.LecturaAdmin.leerAdmin;
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
        String inputFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        List<Part> fileParts = (List<Part>) request.getParts();
        
        fileParts.forEach(part -> {
            String name = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            guardarArchivo(part, name);
        });
        
        //Guardar archivo de entrada en servidor
        guardarArchivo(filePart, inputFileName);

        //Leer datos del archivo XML
        leerArchivoXML(inputFileName);

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
            System.out.println("Archivo "+nombreArchivo + " guardado");
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
            File xml = new File("/home/asael/uploads/datosEntrada/" + nombreArchivo);
            Document doc = builder.parse(xml);

            //Leer etiqueta admin
            try {
                leerAdmin(doc);
            } catch (FileInputException e) {
                //mostrar error en pagina web
            }

            //Leer etiqueta paciente
            try {
                leerPaciente(doc);
            } catch (FileInputException ex) {
            }

            //Leer etiqueta consulta (especialidad)
            try {
                leerEspecialidad(doc);
            } catch (FileInputException ex) {
            }

            //Leer etiqueta doctor (medico)
            try {
                leerMedico(doc);
            } catch (FileInputException ex) {
            }

            //Leer etiqueta examen (Tipo Examen)
            try {
                leerTipoExamen(doc);
            } catch (FileInputException ex) {
            }

            //Leer etiqueta laboratorista
            try {
                leerLaboratorista(doc);
            } catch (FileInputException ex) {
            }

            //Leer etiqueta resultado (Examen y Resultado)
            try {
                leerResultado(doc);
            } catch (FileInputException ex) {
            }

            //Leer etiqueta cita (consulta)
            try {
                leerConsulta(doc);
            } catch (FileInputException ex) {
            }

            //Leer etiqueta reporte (informe)
            try {
                leerInforme(doc);
            } catch (FileInputException ex) {
            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }

}
