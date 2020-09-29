package datos.consulta;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.consulta.Informe;

/**
 *
 * @author asael
 */
public class InformeDAOImpl implements InformeDAO {
    
    private static InformeDAOImpl informeDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private InformeDAOImpl() {
    }
    
    public static InformeDAOImpl getInformeDAO() {
        if (informeDAO == null) {
            informeDAO = new InformeDAOImpl();
        }
        return informeDAO;
    }

    @Override
    public List<Informe> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Informe informe) {
        String sql = "INSERT INTO informe VALUES (?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, informe.getCodigoConsulta());
            ps.setString(2, informe.getInforme());
            ps.setString(3, informe.getFecha().toString());
            ps.setString(4, informe.getHora().toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Informe getObject(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Informe t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigoConsulta FROM informe where codigoConsulta = ?";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return flag;
    }
    
}
