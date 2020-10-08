package datos.examen;

import datos.CRUD;
import java.util.List;
import model.examen.Examen;

/**
 *
 * @author asael
 */
public interface ExamenDAO extends CRUD<Examen> {
    void create2(Examen examen);
    int getLastCodigo();
    void setNextCodigo(int codigo);
    void setEstado(String codExamen, int estado);
    byte[] getOrdenByCodExamen(String codigoExamen);
    List<Examen> getExamenesPendientes(String codPaciente);
    List<Examen> getExamenesPendientesToday(String codTipoExamen);
}
