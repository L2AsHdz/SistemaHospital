package datos.examen;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
        String sql = "INSERT INTO examen (codigoPaciente, codigoLaboratorista, codigoTipoExamen, "
                + "codigoMedico, orden, fecha, hora, estado, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, examen.getCodigoPaciente());
            ps.setString(2, examen.getCodigoLaboratorista());
            ps.setString(3, examen.getCodigoTipoExamen());
            ps.setString(4, examen.getCodigoMedico());
            ps.setBinaryStream(5, examen.getOrden());
            ps.setString(6, examen.getFecha().toString());
            ps.setString(7, examen.getHora().toString());
            ps.setInt(8, examen.getEstado());
            ps.setFloat(9, examen.getTotal());
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
                    examen.setCodigo(Integer.parseInt(codigo));
                    examen.setCodigoPaciente(rs.getString("codigoPaciente"));
                    examen.setCodigoLaboratorista(rs.getString("codigoLaboratorista"));
                    examen.setCodigoTipoExamen(rs.getString("codigoTipoExamen"));
                    examen.setCodigoMedico(rs.getString("codigoMedico"));
                    examen.setOrden(rs.getBinaryStream("orden"));
                    examen.setFecha(LocalDate.parse(rs.getString("fecha")));
                    examen.setHora(LocalTime.parse(rs.getString("hora")));
                    examen.setEstado(rs.getInt("estado"));
                    examen.setTotal(rs.getFloat("total"));
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
        String sql = "SELECT codigo FROM examen ORDER BY codigo DESC LIMIT 1";
        int codigo = -1;
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
        String sql = "ALTER TABLE examen AUTO_INCREMENT = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
}
