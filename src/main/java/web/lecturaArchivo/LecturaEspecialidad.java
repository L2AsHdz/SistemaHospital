package web.lecturaArchivo;

import datos.CRUD;
import datos.especialidad.EspecialidadDAOImpl;
import model.especialidad.Especialidad;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import otros.FileInputException;
import static otros.Validaciones.validarEspecialidad;

/**
 *
 * @author asael
 */
public class LecturaEspecialidad {
    
    private static final CRUD<Especialidad> especialidadDAO = EspecialidadDAOImpl.getEspecialidadDAO();

    public static void leerEspecialidad(Document doc) {
        NodeList costosConsulta = doc.getElementsByTagName("consulta");

        for (int i = 0; i < costosConsulta.getLength(); i++) {
            Node costoConsultaNode = costosConsulta.item(i);

            if (costoConsultaNode.getNodeType() == Node.ELEMENT_NODE) {
                Element costoConsulta = (Element) costoConsultaNode;

                String nombre = getTextNode(costoConsulta, "TIPO");
                String costo = getTextNode(costoConsulta, "COSTO");

                try {
                    validarEspecialidad(nombre, costo, i);
                    especialidadDAO.create(new Especialidad(nombre, costo));
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
