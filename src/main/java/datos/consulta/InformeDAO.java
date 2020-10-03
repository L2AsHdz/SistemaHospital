package datos.consulta;

import datos.CRUD;
import java.util.List;
import model.consulta.Informe;

/**
 *
 * @author asael
 */
public interface InformeDAO extends CRUD<Informe> {
    List<Informe> getListInformeByPaciente(String codPaciente);
}
