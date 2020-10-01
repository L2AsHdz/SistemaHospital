package datos.laboratorista;

import datos.CRUD;
import java.util.List;
import model.usuario.Turno;

/**
 *
 * @author asael
 */
public interface TurnoDAO extends CRUD<Turno> {
    List<String> getTurnosByCodLab(String codLab);
}
