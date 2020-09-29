package web.lecturaArchivo;

import datos.CRUD;
import datos.consulta.ConsultaDAOImpl;
import datos.consulta.InformeDAOImpl;
import datos.especialidad.AsignacionEspecialidadDAO;
import datos.especialidad.AsignacionEspecialidadDAOImpl;
import datos.especialidad.EspecialidadDAO;
import datos.especialidad.EspecialidadDAOImpl;
import model.consulta.Consulta;
import model.consulta.Informe;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import otros.FileInputException;
import static otros.Validaciones.validarInforme;

/**
 *
 * @author asael
 */
public class LecturaInforme {

    private static final EspecialidadDAO especialidadDAO = EspecialidadDAOImpl.getEspecialidadDAO();
    private static final AsignacionEspecialidadDAO asignacionDAO = AsignacionEspecialidadDAOImpl.getAsignacionEsDAO();
    private static final CRUD<Consulta> consultaDAO = ConsultaDAOImpl.getconsultaDAO();
    private static final CRUD<Informe> informeDAO = InformeDAOImpl.getInformeDAO();

    public static void leerInforme(Document doc) throws FileInputException {
        NodeList informes = doc.getElementsByTagName("reporte");

        for (int i = 0; i < informes.getLength(); i++) {
            Node informeNode = informes.item(i);

            if (informeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element informe = (Element) informeNode;

                String codigo = getTextNode(informe, "CODIGO");
                String paciente = getTextNode(informe, "PACIENTE");
                String medico = getTextNode(informe, "MEDICO");
                String informeText = getTextNode(informe, "INFORME");
                String fecha = getTextNode(informe, "FECHA");
                String hora = getTextNode(informe, "HORA");

                validarInforme(codigo, paciente, medico, informeText, fecha, hora, i);
                if (!consultaDAO.exists(codigo)) {
                    int idEspecialidad = asignacionDAO.getIdFirstAsinacion(medico);
                    consultaDAO.create(new Consulta(codigo, medico, paciente, idEspecialidad,
                            fecha, hora, 1, especialidadDAO.getCosto(idEspecialidad)));
                }
                informeDAO.create(new Informe(codigo, informeText, fecha, hora));
            }
        }
    }

    private static String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
