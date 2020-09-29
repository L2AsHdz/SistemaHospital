package web.lecturaArchivo;

import datos.CRUD;
import datos.consulta.ConsultaDAOImpl;
import datos.especialidad.EspecialidadDAO;
import datos.especialidad.EspecialidadDAOImpl;
import model.consulta.Consulta;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import otros.FileInputException;
import static otros.Validaciones.validarConsulta;

/**
 *
 * @author asael
 */
public class LecturaConsulta {

    private static final CRUD<Consulta> consultaDAO = ConsultaDAOImpl.getconsultaDAO();
    private static final EspecialidadDAO especialidadDAO = EspecialidadDAOImpl.getEspecialidadDAO();

    public static void leerConsulta(Document doc) {
        NodeList consultas = doc.getElementsByTagName("cita");

        for (int i = 0; i < consultas.getLength(); i++) {
            Node consultaNode = consultas.item(i);

            if (consultaNode.getNodeType() == Node.ELEMENT_NODE) {
                Element consulta = (Element) consultaNode;

                String codigo = getTextNode(consulta, "CODIGO");
                String paciente = getTextNode(consulta, "PACIENTE");
                String medico = getTextNode(consulta, "MEDICO");
                String especialidad = getTextNode(consulta, "ESPECIALIDAD");
                String fecha = getTextNode(consulta, "FECHA");
                String hora = getTextNode(consulta, "HORA");

                try {
                    validarConsulta(codigo, paciente, medico, especialidad, fecha, hora, i);
                    int idEspecialidad = especialidadDAO.getIdEspecialidad(especialidad);
                    consultaDAO.create(new Consulta(codigo, medico, paciente, idEspecialidad,
                            fecha, hora, 0, especialidadDAO.getCosto(idEspecialidad)));
                } catch (FileInputException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }
    
    private static String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}