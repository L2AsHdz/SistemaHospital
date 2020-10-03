package datos.examen;

import datos.CRUD;
import java.util.List;
import model.examen.Resultado;

/**
 *
 * @author asael
 */
public interface ResultadoDAO extends CRUD<Resultado> {
    List<Resultado> getListResultadoByPaciente(String codPaciente);
}
