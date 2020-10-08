package web.lecturaArchivo;

import datos.CRUD;
import datos.admin.AdminDAOImpl;
import java.util.ArrayList;
import java.util.List;
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

    public static List<String> leerAdmin(Document doc) {
        //nodos admin
        List<String> errores = new ArrayList<>();
        NodeList admins = doc.getElementsByTagName("admin");

        for (int i = 0; i < admins.getLength(); i++) {
            Node adminNode = admins.item(i);

            if (adminNode.getNodeType() == Node.ELEMENT_NODE) {
                Element admin = (Element) adminNode;
                String codigo = getTextNode(admin, "CODIGO");
                String cui = getTextNode(admin, "DPI");
                String nombre = getTextNode(admin, "NOMBRE");
                String password = getTextNode(admin, "PASSWORD");

                try {
                    validarAdmin(codigo, nombre, cui, password, i);
                    adminDAO.create(new Admin(codigo, nombre, cui, password));
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
