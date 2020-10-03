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
        String sql = "INSERT INTO consulta (codigoPaciente, codigoMedico, idEspecialidad, fecha, hora, estado, total) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, consulta.getCodigoPaciente());
            ps.setString(2, consulta.getCodigoMedico());
            ps.setInt(3, consulta.getIdEspecialidad());
            ps.setString(4, consulta.getFecha().toString());
            ps.setString(5, consulta.getHora().toString());
            ps.setInt(6, consulta.getEstado());
            ps.setFloat(7, consulta.getTotal());
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
        String sql = "SELECT codigo FROM consulta WHERE codigo = ?";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codigo));
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

    @Override
    public int getLastCodigo() {
        String sql = "SELECT codigo FROM consulta ORDER BY codigo DESC LIMIT 1";
        int codigo = 1;
        try (PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                codigo = rs.getInt("codigo") + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return codigo;
    }

    @Override
    public void setNextCodigo(int codigo) {
        String sql = "ALTER TABLE consulta AUTO_INCREMENT = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public boolean isAvailable(String codMedico, String hora, String fecha) {
        String sql = "SELECT codigo FROM consulta WHERE codigoMedico = ? AND hora = ? AND fecha = ?";
        boolean flag = true;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codMedico);
            ps.setString(2, hora);
            ps.setString(3, fecha);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    flag = false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return flag;
    }
    
}
