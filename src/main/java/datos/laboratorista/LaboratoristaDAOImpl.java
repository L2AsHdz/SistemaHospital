package datos.laboratorista;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.usuario.Laboratorista;

/**
 *
 * @author asael
 */
public class LaboratoristaDAOImpl implements LaboratoristaDAO {
    
    private static LaboratoristaDAOImpl laboratoristaDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private LaboratoristaDAOImpl() {
    }
    
    public static LaboratoristaDAOImpl getLaboratoristaDAO() {
        if (laboratoristaDAO == null) {
            laboratoristaDAO = new LaboratoristaDAOImpl();
        }
        return laboratoristaDAO;
    }

    @Override
    public List<Laboratorista> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Laboratorista laboratorista) {
        String sql = "INSERT INTO laboratorista VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, laboratorista.getCodigo());
            ps.setString(2, laboratorista.getNombre());
            ps.setString(3, laboratorista.getRegistroSalud());
            ps.setString(4, laboratorista.getCUI());
            ps.setString(5, laboratorista.getTelefono());
            ps.setString(6, laboratorista.getCorreo());
            ps.setString(7, laboratorista.getFechaInicioLabores().toString());
            ps.setString(8, laboratorista.getCodigoTipoExamen());
            ps.setString(9, laboratorista.getEncryptPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Laboratorista getObject(String codigo) {
        String sql = "SELECT * FROM laboratorista WHERE codigo = ?";

        Laboratorista laboratorista = null;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    laboratorista = new Laboratorista();
                    laboratorista.setCodigo(codigo);
                    laboratorista.setNombre(rs.getString("nombre"));
                    laboratorista.setRegistroSalud(rs.getString("registroSalud"));
                    laboratorista.setCUI(rs.getString("cui"));
                    laboratorista.setTelefono(rs.getString("telefono"));
                    laboratorista.setCorreo(rs.getString("correo"));
                    laboratorista.setFechaInicioLabores(LocalDate.parse(rs.getString("fechaInicioLabores")));
                    laboratorista.setCodigoTipoExamen(rs.getString("codigoTipoExamen"));
                    laboratorista.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return laboratorista;
    }

    @Override
    public void update(Laboratorista t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigo FROM laboratorista where codigo = ?";
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
