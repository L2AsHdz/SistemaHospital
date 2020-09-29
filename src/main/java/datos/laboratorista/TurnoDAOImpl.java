package datos.laboratorista;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.usuario.Turno;

/**
 *
 * @author asael
 */
public class TurnoDAOImpl implements TurnoDAO {
    
    private static TurnoDAOImpl turnoDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private TurnoDAOImpl() {
    }
    
    public static TurnoDAOImpl getTurnoDAO() {
        if (turnoDAO == null) {
            turnoDAO = new TurnoDAOImpl();
        }
        return turnoDAO;
    }

    @Override
    public List<Turno> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Turno laboratorista) {
        String sql = "INSERT INTO turno VALUES (?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, laboratorista.getCodigoLaboratorista());
            ps.setString(2, laboratorista.getDia().name());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Turno getObject(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Turno t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
