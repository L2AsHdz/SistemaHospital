package web.lecturaArchivo;

import datos.CRUD;
import datos.admin.AdminDAOImpl;
import model.usuario.Admin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import otros.FileInputException;
import static otros.Validaciones.validarAdmin;

/**
 *
 * @author asael
 */
public class LecturaAdmin {

    private static final CRUD<Admin> adminDAO = AdminDAOImpl.getAdminDAO();

    public static void leerAdmin(Document doc) throws FileInputException {
        //nodos admin
        NodeList admins = doc.getElementsByTagName("admin");

        for (int i = 0; i < admins.getLength(); i++) {
            Node adminNode = admins.item(i);

            if (adminNode.getNodeType() == Node.ELEMENT_NODE) {
                Element admin = (Element) adminNode;
                String codigo = getTextNode(admin, "CODIGO");
                String cui = getTextNode(admin, "DPI");
                String nombre = getTextNode(admin, "NOMBRE");
                String password = getTextNode(admin, "PASSWORD");

                validarAdmin(codigo, nombre, cui, password, i);
                adminDAO.create(new Admin(codigo, nombre, cui, password));
            }
        }
    }

    private static String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
