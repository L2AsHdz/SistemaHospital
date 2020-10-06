package datos.examen;

import datos.CRUD;
import java.util.List;
import model.examen.TipoExamen;

/**
 *
 * @author asael
 */
public interface TipoExamenDAO extends CRUD<TipoExamen>{
    String getCodigoByNombre(String nombre);
    float getCosto(String codigo);
    List<TipoExamen> getExamenesDemandados(String fechaInicial, String fechaFinal, int opcion);
    List<TipoExamen> getExamenesSolicitadosByMedico(String codMedico, String fechaInicial, String fechaFinal, int opcion);
}
