package datos.examen;

import datos.Conexion;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.examen.Examen;

/**
 *
 * @author asael
 */
public class ExamenDAOImpl implements ExamenDAO {
    
    private static ExamenDAOImpl examenDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private ExamenDAOImpl() {
    }
    
    public static ExamenDAOImpl getExamenDAO() {
        if (examenDAO == null) {
            examenDAO = new ExamenDAOImpl();
        }
        return examenDAO;
    }

    @Override
    public List<Examen> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Examen examen) {
        String sql = "INSERT INTO examen VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, examen.getCodigo());
            ps.setString(2, examen.getCodigoPaciente());
            ps.setString(3, examen.getCodigoLaboratorista());
            ps.setString(4, examen.getCodigoTipoExamen());
            ps.setString(5, examen.getCodigoMedico());
            ps.setBinaryStream(6, examen.getOrden());
            ps.setString(7, examen.getFecha().toString());
            ps.setString(8, examen.getHora().toString());
            ps.setInt(9, examen.getEstado());
            ps.setFloat(10, examen.getTotal());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Examen getObject(String codigo) {
        String sql = "SELECT * FROM tipoExamen WHERE codigo = ?";

        Examen examen = null;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    examen = new Examen();
                    examen.setCodigo(codigo);
                    InputStream is = rs.getBinaryStream("orden");
                    //faltan los demas atributos
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return examen;
    }

    @Override
    public void update(Examen t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigo FROM examen where codigo = ?";
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
