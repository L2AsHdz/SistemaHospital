package datos.especialidad;

import datos.CRUD;
import model.especialidad.Especialidad;

/**
 *
 * @author asael
 */
public interface EspecialidadDAO extends CRUD<Especialidad>{
    int getIdEspecialidad(String nombre);
    float getCosto(int id);
}
