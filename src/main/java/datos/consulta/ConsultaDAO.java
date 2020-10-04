package datos.consulta;

import datos.CRUD;
import java.util.List;
import model.consulta.Consulta;

/**
 *
 * @author asael
 */
public interface ConsultaDAO extends CRUD<Consulta> {
    int getLastCodigo();
    void setNextCodigo(int codigo);
    boolean isAvailable(String codMedico, String hora, String fecha);
    List<Consulta> getConsultasPendientes(String codPaciente);
    List<Consulta> getConsultasPendientesToday(String codMedico);
}
