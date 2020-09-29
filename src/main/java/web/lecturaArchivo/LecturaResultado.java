package web.lecturaArchivo;

import datos.CRUD;
import datos.examen.ExamenDAOImpl;
import datos.examen.ResultadoDAOImpl;
import datos.examen.TipoExamenDAO;
import datos.examen.TipoExamenDAOImpl;
import model.examen.Examen;
import model.examen.Resultado;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import otros.FileInputException;
import static otros.Validaciones.validarResultado;

/**
 *
 * @author asael
 */
public class LecturaResultado {

    private static final TipoExamenDAO tipoExamenDAO = TipoExamenDAOImpl.getTipoExamenDAO();
    private static final CRUD<Examen> examenDAO = ExamenDAOImpl.getExamenDAO();
    private static final CRUD<Resultado> resultadoDAO = ResultadoDAOImpl.getResultadoDAO();

    public static void leerResultado(Document doc) throws FileInputException {
        NodeList resultados = doc.getElementsByTagName("resultado");

        for (int i = 0; i < resultados.getLength(); i++) {
            Node resultadoNode = resultados.item(i);

            if (resultadoNode.getNodeType() == Node.ELEMENT_NODE) {
                Element resultadoE = (Element) resultadoNode;

                String codigo = getTextNode(resultadoE, "CODIGO");
                String paciente = getTextNode(resultadoE, "PACIENTE");
                String examen = getTextNode(resultadoE, "EXAMEN");
                String laboratorista = getTextNode(resultadoE, "LABORATORISTA");
                String medico = getTextNode(resultadoE, "MEDICO");
                String orden = getTextNode(resultadoE, "ORDEN");
                String resultado = getTextNode(resultadoE, "INFORME");
                String fecha = getTextNode(resultadoE, "FECHA");
                String hora = getTextNode(resultadoE, "HORA");

                validarResultado(codigo, paciente, examen, medico, laboratorista,
                        orden, resultado, fecha, hora, i);
                examenDAO.create(new Examen(codigo, paciente, laboratorista, examen,
                        medico, orden, fecha, hora, 1, tipoExamenDAO.getCosto(examen)));
                resultadoDAO.create(new Resultado(codigo, resultado, fecha, hora));
            }
        }
    }

    private static String getTextNode(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
