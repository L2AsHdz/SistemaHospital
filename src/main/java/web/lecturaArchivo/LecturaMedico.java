package web.lecturaArchivo;

import datos.CRUD;
import datos.especialidad.AsignacionEspecialidadDAOImpl;
import datos.especialidad.EspecialidadDAO;
import datos.especialidad.EspecialidadDAOImpl;
import datos.medico.MedicoDAOImpl;
import java.util.ArrayList;
import java.util.List;
import model.especialidad.AsignacionEspecialidad;
import model.usuario.Medico;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import otros.FileInputException;
import static otros.Validaciones.validarMedico;

/**
 *
 * @author asael
 */
public class LecturaMedico {

    private static final CRUD<Medico> medicoDAO = MedicoDAOImpl.getMedicoDAO();
    private static final EspecialidadDAO especialidadDAO = EspecialidadDAOImpl.getEspecialidadDAO();
    private static final CRUD<AsignacionEspecialidad> asignacionDAO = AsignacionEspecialidadDAOImpl.getAsignacionEsDAO();

    public static void leerMedico(Document doc) {
        NodeList medicos = doc.getElementsByTagName("doctor");
        List<String> listTitulos = new ArrayList<>();

        for (int i = 0; i < medicos.getLength(); i++) {
            Node medicoNode = medicos.item(i);

            if (medicoNode.getNodeType() == Node.ELEMENT_NODE) {
                Element medico = (Element) medicoNode;

                String codigo = getTextNode(medico, "CODIGO");
                String nombre = getTextNode(medico, "NOMBRE");
                String colegiado = getTextNode(medico, "COLEGIADO");
                String cui = getTextNode(medico, "DPI");
                String telefono = getTextNode(medico, "TELEFONO");
                String correo = getTextNode(medico, "CORREO");
                String fechaInicioLabores = getTextNode(medico, "TRABAJO");
                String password = getTextNode(medico, "PASSWORD");

                Element especialidad = (Element) medico.getElementsByTagName("ESPECIALIDAD").item(0);
                NodeList titulos = especialidad.getElementsByTagName("TITULO");

                listTitulos.clear();
                for (int j = 0; j < titulos.getLength(); j++) {
                    Element titulo = (Element) titulos.item(j);
                    listTitulos.add(titulo.getTextContent());
                }

                Element horario = (Element) medico.getElementsByTagName("HORARIO").item(0);
                String horaInicio = getTextNode(horario, "INICIO");
                String horaFinal = getTextNode(horario, "FIN");

                try {
                    validarMedico(codigo, nombre, colegiado, cui, telefono, correo,
                            horaInicio, horaFinal, fechaInicioLabores, password, listTitulos, i);
                    medicoDAO.create(new Medico(colegiado, telefono, correo, horaInicio,
                            horaFinal, fechaInicioLabores, codigo, nombre, cui, password));

                    listTitulos.forEach(t -> {
                        asignacionDAO.create(new AsignacionEspecialidad(codigo, especialidadDAO.getIdEspecialidad(t)));
                    });

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
