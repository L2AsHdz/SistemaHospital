package web.lecturaArchivo;

import datos.CRUD;
import datos.examen.TipoExamenDAO;
import datos.examen.TipoExamenDAOImpl;
import datos.laboratorista.LaboratoristaDAOImpl;
import datos.laboratorista.TurnoDAOImpl;
import java.util.ArrayList;
import java.util.List;
import model.usuario.Laboratorista;
import model.usuario.Turno;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import otros.FileInputException;
import static otros.Validaciones.validarLaboratorista;

/**
 *
 * @author asael
 */
public class LecturaLaboratorista {

    private static final CRUD<Laboratorista> laboratoristaDAO = LaboratoristaDAOImpl.getLaboratoristaDAO();
    private static final CRUD<Turno> turnoDAO = TurnoDAOImpl.getTurnoDAO();
    private static final TipoExamenDAO tipoExamenDAO = TipoExamenDAOImpl.getTipoExamenDAO();

    public static void leerLaboratorista(Document doc) throws FileInputException {
        NodeList laboratoristas = doc.getElementsByTagName("laboratorista");
        List<String> listDias = new ArrayList<>();

        for (int i = 0; i < laboratoristas.getLength(); i++) {
            Node laboratoristaNode = laboratoristas.item(i);

            if (laboratoristaNode.getNodeType() == Node.ELEMENT_NODE) {
                Element laboratorista = (Element) laboratoristaNode;

                String codigo = getTextNode(laboratorista, "CODIGO");
                String nombre = getTextNode(laboratorista, "NOMBRE");
                String registro = getTextNode(laboratorista, "REGISTRO");
                String cui = getTextNode(laboratorista, "DPI");
                String telefono = getTextNode(laboratorista, "TELEFONO");
                String tipoExamen = getTextNode(laboratorista, "EXAMEN");
                String correo = getTextNode(laboratorista, "CORREO");
                String fechaInicioLabores = getTextNode(laboratorista, "TRABAJOF");
                String password = getTextNode(laboratorista, "PASSWORD");

                Element turno = (Element) laboratorista.getElementsByTagName("TRABAJO").item(0);
                NodeList dias = turno.getElementsByTagName("DIA");

                listDias.clear();
                for (int j = 0; j < dias.getLength(); j++) {
                    Element titulo = (Element) dias.item(j);
                    listDias.add(titulo.getTextContent());
                }

                validarLaboratorista(codigo, nombre, registro, cui, telefono, correo,
                        fechaInicioLabores, tipoExamen, password, listDias, i);
                laboratoristaDAO.create(new Laboratorista(registro, telefono,
                        tipoExamenDAO.getCodigoByNombre(tipoExamen), correo,
                        fechaInicioLabores, codigo, nombre, cui, password));

                listDias.forEach(d -> {
                    turnoDAO.create(new Turno(codigo, d));
                });
            }
        }
    }

    private static String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
