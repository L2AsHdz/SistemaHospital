package datos.paciente;

import datos.CRUD;
import java.util.List;
import model.usuario.Paciente;

/**
 *
 * @author asael
 */
public interface PacienteDAO extends CRUD<Paciente> {
    List<Paciente> getPacientesConMasInformes(String fechaInicial, String fechaFinal, int opcion);
    List<Paciente> getIngresosConsultasPorPaciete(String fechaInicial, String fechaFinal, int opcion);
    List<Paciente> getIngresosExamenesPorPaciete(String fechaInicial, String fechaFinal, int opcion);
    String getCodigo();
}
