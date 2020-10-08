package web.lecturaArchivo;

import datos.CRUD;
import datos.examen.TipoExamenDAOImpl;
import java.util.ArrayList;
import java.util.List;
import model.examen.TipoExamen;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import otros.FileInputException;
import static otros.Validaciones.validarTipoExamen;

/**
 *
 * @author asael
 */
public class LecturaTipoExamen {

    private static final CRUD<TipoExamen> tipoExamenDAO = TipoExamenDAOImpl.getTipoExamenDAO();

    public static List<String> leerTipoExamen(Document doc) {
        List<String> errores = new ArrayList<>();
        NodeList tiposExamenes = doc.getElementsByTagName("examen");

        for (int i = 0; i < tiposExamenes.getLength(); i++) {
            Node tipoExamenNode = tiposExamenes.item(i);

            if (tipoExamenNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tipoExamen = (Element) tipoExamenNode;

                String codigo = getTextNode(tipoExamen, "CODIGO");
                String nombre = getTextNode(tipoExamen, "NOMBRE");
                String orden = getTextNode(tipoExamen, "ORDEN");
                String descripcion = getTextNode(tipoExamen, "DESCRIPCION");
                String costo = getTextNode(tipoExamen, "COSTO");
                String informe = getTextNode(tipoExamen, "INFORME");

                try {
                    validarTipoExamen(codigo, nombre, orden, descripcion, costo, informe, i);
                    tipoExamenDAO.create(new TipoExamen(codigo, nombre, orden, descripcion, costo, informe));
                } catch (FileInputException e) {
                    errores.add(e.getMessage());
                }
            }
        }
        return errores;
    }

    private static String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
