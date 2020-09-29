package web.lecturaArchivo;

import datos.CRUD;
import datos.paciente.PacienteDAOImpl;
import model.usuario.Paciente;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import otros.FileInputException;
import static otros.Validaciones.validarPaciente;

/**
 *
 * @author asael
 */
public class LecturaPaciente {

    private static final CRUD<Paciente> pacienteDAO = PacienteDAOImpl.getPacienteDAO();

    public static void leerPaciente(Document doc) throws FileInputException {
        NodeList pacientes = doc.getElementsByTagName("paciente");

        for (int i = 0; i < pacientes.getLength(); i++) {
            Node pacienteNode = pacientes.item(i);

            if (pacienteNode.getNodeType() == Node.ELEMENT_NODE) {
                Element paciente = (Element) pacienteNode;

                String codigo = getTextNode(paciente, "CODIGO");
                String nombre = getTextNode(paciente, "NOMBRE");
                String sexo = getTextNode(paciente, "SEXO");
                String birth = getTextNode(paciente, "BIRTH");
                String cui = getTextNode(paciente, "DPI");
                String telefono = getTextNode(paciente, "TELEFONO");
                String peso = getTextNode(paciente, "PESO");
                String sangre = getTextNode(paciente, "SANGRE");
                String correo = getTextNode(paciente, "CORREO");
                String password = getTextNode(paciente, "PASSWORD");

                validarPaciente(codigo, nombre, sexo, birth, cui, telefono, peso, sangre, correo, password, i);
                pacienteDAO.create(new Paciente(sexo, birth, telefono, peso, sangre, correo, codigo, nombre, cui, password));
            }
        }
    }

    private static String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
