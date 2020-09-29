package datos.examen;

import datos.CRUD;
import model.examen.TipoExamen;

/**
 *
 * @author asael
 */
public interface TipoExamenDAO extends CRUD<TipoExamen>{
    String getCodigoByNombre(String nombre);
    float getCosto(String codigo);
}
