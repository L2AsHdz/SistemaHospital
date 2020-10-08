package web.lecturaArchivo;

import datos.consulta.ConsultaDAO;
import datos.consulta.ConsultaDAOImpl;
import datos.especialidad.EspecialidadDAO;
import datos.especialidad.EspecialidadDAOImpl;
import java.util.ArrayList;
import java.util.List;
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

    private static final ConsultaDAO consultaDAO = ConsultaDAOImpl.getconsultaDAO();
    private static final EspecialidadDAO especialidadDAO = EspecialidadDAOImpl.getEspecialidadDAO();

    public static List<String> leerConsulta(Document doc) {
        List<String> errores = new ArrayList<>();
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
                    errores.add(e.getMessage());
                }
            }
        }
        
        consultaDAO.setNextCodigo(consultaDAO.getLastCodigo());
        
        return errores;
    }

    private static String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
