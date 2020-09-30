package datos.especialidad;

import datos.CRUD;
import java.util.List;
import model.especialidad.AsignacionEspecialidad;

/**
 *
 * @author asael
 */
public interface AsignacionEspecialidadDAO extends CRUD<AsignacionEspecialidad> {
    boolean verificarAsignacion(String codigoMedico, int idEspecialidad);
    int getIdFirstAsinacion(String codigoMedico);
    List<String> getEspecialidadesByCodMed(String codMedico);
}
