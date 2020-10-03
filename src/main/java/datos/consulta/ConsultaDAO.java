package datos.consulta;

import datos.CRUD;
import model.consulta.Consulta;

/**
 *
 * @author asael
 */
public interface ConsultaDAO extends CRUD<Consulta> {
    int getIdConsulta();
    void setNextCodigo(int codigo);
}
