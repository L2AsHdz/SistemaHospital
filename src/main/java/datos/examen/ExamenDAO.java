package datos.examen;

import datos.CRUD;
import java.util.List;
import model.examen.Examen;

/**
 *
 * @author asael
 */
public interface ExamenDAO extends CRUD<Examen> {
    int getLastCodigo();
    void setNextCodigo(int codigo);
    byte[] getOrdenByCodExamen(String codigoExamen);
    List<Examen> getExamenesPendientes(String codPaciente);
    List<Examen> getExamenesPendientesToday(String codTipoExamen);
}
