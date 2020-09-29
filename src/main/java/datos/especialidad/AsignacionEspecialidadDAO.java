package datos.especialidad;

import datos.CRUD;
import model.especialidad.AsignacionEspecialidad;

/**
 *
 * @author asael
 */
public interface AsignacionEspecialidadDAO extends CRUD<AsignacionEspecialidad> {
    boolean verificarAsignacion(String codigoMedico, int idEspecialidad);
    int getIdFirstAsinacion(String codigoMedico);
}
