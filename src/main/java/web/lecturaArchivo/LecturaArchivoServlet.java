package web.lecturaArchivo;

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
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
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
        List<String> errores = leerArchivoXML(inputFileName);

        if (errores.isEmpty()) {
            request.setAttribute("nice", "La lectura del archivo finalizo sin ningun error");
        } else {
            request.setAttribute("errores", errores);
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void guardarArchivo(Part filePart, String nombreArchivo) {
        File ruta = new File("/home/asael/uploads/datosEntrada");
        File file = new File(ruta, nombreArchivo);

        try ( InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo " + nombreArchivo + " guardado");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private List<String> leerArchivoXML(String nombreArchivo) {

        //Errores en la lectura del archivo
        List<String> errores = new ArrayList<>();
        try {

            //DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Crear documento
            File xml = new File("/home/asael/uploads/datosEntrada/" + nombreArchivo);
            Document doc = builder.parse(xml);

            //Leer etiqueta admin
            errores.addAll(leerAdmin(doc));

            //Leer etiqueta paciente
            errores.addAll(leerPaciente(doc));

            //Leer etiqueta consulta (especialidad)
            errores.addAll(leerEspecialidad(doc));

            //Leer etiqueta doctor (medico)
            errores.addAll(leerMedico(doc));

            //Leer etiqueta examen (Tipo Examen)
            errores.addAll(leerTipoExamen(doc));

            //Leer etiqueta laboratorista
            errores.addAll(leerLaboratorista(doc));

            //Leer etiqueta resultado (Examen y Resultado)
            errores.addAll(leerResultado(doc));

            //Leer etiqueta cita (consulta)
            errores.addAll(leerConsulta(doc));

            //Leer etiqueta reporte (informe)
            errores.addAll(leerInforme(doc));

        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            ex.printStackTrace(System.out);
        }
        return errores;
    }
}
