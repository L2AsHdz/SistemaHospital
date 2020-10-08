package datos.consulta;

import datos.CRUD;
import java.util.List;
import model.consulta.Consulta;

/**
 *
 * @author asael
 */
public interface ConsultaDAO extends CRUD<Consulta> {
    void create2(Consulta consulta);
    int getLastCodigo();
    void setNextCodigo(int codigo);
    void setEstado(String codConsulta, int estado);
    boolean isAvailable(String codMedico, String hora, String fecha);
    List<Consulta> getConsultasPendientes(String codPaciente);
    List<Consulta> getConsultasPendientesToday(String codMedico);
    List<Consulta> getConsultasAgendadasByIntevalo(String codMedico, String fechaInicial, String fechaFinal, int opcion);
}
