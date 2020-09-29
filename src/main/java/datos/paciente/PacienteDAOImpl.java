package datos.paciente;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.usuario.Paciente;

/**
 *
 * @author asael
 */
public class PacienteDAOImpl implements PacienteDAO {
    
    private static PacienteDAOImpl pacienteDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private PacienteDAOImpl() {
    }
    
    public static PacienteDAOImpl getPacienteDAO() {
        if (pacienteDAO == null) {
            pacienteDAO = new PacienteDAOImpl();
        }
        return pacienteDAO;
    }

    @Override
    public List<Paciente> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Paciente paciente) {
        String sql = "INSERT INTO paciente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, paciente.getCodigo());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getSexo());
            ps.setString(4, paciente.getBirth().toString());
            ps.setString(5, paciente.getCUI());
            ps.setString(6, paciente.getTelefono());
            ps.setFloat(7, paciente.getPeso());
            ps.setString(8, paciente.getTipoSangre());
            ps.setString(9, paciente.getCorreo());
            ps.setString(10, paciente.getEncryptPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Paciente getObject(String codigo) {
         String sql = "SELECT * FROM paciente WHERE codigo = ?";

        Paciente paciente = null;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    paciente = new Paciente();
                    paciente.setCodigo(codigo);
                    paciente.setNombre(rs.getString("nombre"));
                    paciente.setSexo(rs.getString("sexo"));
                    paciente.setBirth(LocalDate.parse(rs.getString("birth")));
                    paciente.setCUI(rs.getString("cui"));
                    paciente.setTelefono(rs.getString("telefono"));
                    paciente.setPeso(rs.getFloat("peso"));
                    paciente.setTipoSangre(rs.getString("tipoSangre"));
                    paciente.setCorreo(rs.getString("correo"));
                    paciente.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return paciente;
    }

    @Override
    public void update(Paciente t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigo FROM paciente where codigo = ?";
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
