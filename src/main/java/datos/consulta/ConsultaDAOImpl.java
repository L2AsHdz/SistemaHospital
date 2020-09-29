package datos.consulta;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.consulta.Consulta;

/**
 *
 * @author asael
 */
public class ConsultaDAOImpl implements ConsultaDAO {
    
    private static ConsultaDAOImpl consultaDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private ConsultaDAOImpl() {
    }
    
    public static ConsultaDAOImpl getconsultaDAO() {
        if (consultaDAO == null) {
            consultaDAO = new ConsultaDAOImpl();
        }
        return consultaDAO;
    }

    @Override
    public List<Consulta> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Consulta consulta) {
        String sql = "INSERT INTO consulta VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, consulta.getCodigo());
            ps.setString(2, consulta.getCodigoPaciente());
            ps.setString(3, consulta.getCodigoMedico());
            ps.setInt(4, consulta.getIdEspecialidad());
            ps.setString(5, consulta.getFecha().toString());
            ps.setString(6, consulta.getHora().toString());
            ps.setInt(7, consulta.getEstado());
            ps.setFloat(8, consulta.getTotal());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Consulta getObject(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Consulta t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigo FROM consulta where codigo = ?";
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
