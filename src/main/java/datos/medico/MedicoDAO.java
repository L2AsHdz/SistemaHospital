package datos.medico;

import datos.CRUD;
import java.util.List;
import model.usuario.Medico;

/**
 *
 * @author asael
 */
public interface MedicoDAO extends CRUD<Medico> {
    List<Medico> getFilteredList(String nombre, String idEspecialidad, String hora, int opcion);
    List<Medico> getMedicosConMasinformes(String fechaInicial, String fechaFinal, int opcion);
    List<Medico> getIngresosDeMedicos(String fechaInicial, String fechaFinal, int opcion);
    List<Medico> getMedicosConMenosConsultas(String fechaInicial, String fechaFinal, int opcion);
    List<Medico> getMedicosConMasSolicitudes(String fechaInicial, String fechaFinal, int opcion);
}
