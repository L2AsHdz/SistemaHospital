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
    List<Resultado> getLastFiveResultados(String codPaciente);
    List<Resultado> getResultadosbyTipoExamen(String codPaciente, String codTipoExamen, String fechaInicial, String fechaFinal, int opcion);
}
