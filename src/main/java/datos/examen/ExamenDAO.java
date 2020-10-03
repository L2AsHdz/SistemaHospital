package datos.examen;

import datos.CRUD;
import model.examen.Examen;

/**
 *
 * @author asael
 */
public interface ExamenDAO extends CRUD<Examen> {
    int getLastCodigo();
    void setNextCodigo(int codigo);
}
