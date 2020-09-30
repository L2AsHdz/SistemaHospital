package datos.examen;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.examen.TipoExamen;

/**
 *
 * @author asael
 */
public class TipoExamenDAOImpl implements TipoExamenDAO {
    
    private static TipoExamenDAOImpl tipoExamenDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private TipoExamenDAOImpl() {
    }
    
    public static TipoExamenDAOImpl getTipoExamenDAO() {
        if (tipoExamenDAO == null) {
            tipoExamenDAO = new TipoExamenDAOImpl();
        }
        return tipoExamenDAO;
    }

    @Override
    public List<TipoExamen> getListado() {
        String sql = "SELECT * FROM tipoExamen";
        List<TipoExamen> tiposExamen = null;

        try ( PreparedStatement declaracion = conexion.prepareStatement(sql);  
                ResultSet rs = declaracion.executeQuery()) {
            tiposExamen = new ArrayList();

            while (rs.next()) {
                TipoExamen tipoExamen = new TipoExamen();
                tipoExamen.setCodigo(rs.getString("codigo"));
                tipoExamen.setNombre(rs.getString("nombre"));
                tipoExamen.setRequiereOrden(rs.getInt("requiereOrden"));
                tipoExamen.setDescripcion(rs.getString("descripcion"));
                tipoExamen.setCosto(rs.getFloat("costo"));
                tipoExamen.setTipoInforme(rs.getString("tipoInforme"));
                tiposExamen.add(tipoExamen);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return tiposExamen;
    }

    @Override
    public void create(TipoExamen tipoExamen) {
        String sql = "INSERT INTO tipoExamen VALUES (?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tipoExamen.getCodigo());
            ps.setString(2, tipoExamen.getNombre());
            ps.setInt(3, tipoExamen.getRequiereOrden());
            ps.setString(4, tipoExamen.getDescripcion());
            ps.setFloat(5, tipoExamen.getCosto());
            ps.setString(6, tipoExamen.getTipoInforme());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public TipoExamen getObject(String codigo) {
        String sql = "SELECT * FROM tipoExamen WHERE codigo = ?";

        TipoExamen tipoExamen = null;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tipoExamen = new TipoExamen();
                    tipoExamen.setCodigo(codigo);
                    tipoExamen.setNombre(rs.getString("nombre"));
                    tipoExamen.setRequiereOrden(rs.getInt("requiereOrden"));
                    tipoExamen.setDescripcion(rs.getString("descripcion"));
                    tipoExamen.setCosto(rs.getFloat("costo"));
                    tipoExamen.setTipoInforme(rs.getString("tipoInforme"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return tipoExamen;
    }

    @Override
    public void update(TipoExamen tipoExamen) {
        String sql = "UPDATE tipoExamen SET nombre = ?, requiereOrden = ?, descripcion = ?,"
                + "costo = ?, tipoInforme = ? WHERE codigo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tipoExamen.getNombre());
            ps.setInt(2, tipoExamen.getRequiereOrden());
            ps.setString(3, tipoExamen.getDescripcion());
            ps.setFloat(4, tipoExamen.getCosto());
            ps.setString(5, tipoExamen.getTipoInforme());
            ps.setString(6, tipoExamen.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCodigoByNombre(String nombre) {
        String sql = "SELECT codigo FROM tipoExamen where nombre = ?";
        String codigo = "";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    codigo = rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return codigo;
    }

    @Override
    public boolean exists(String id) {
        String sql = "SELECT codigo FROM tipoExamen where codigo = ? OR nombre = ?";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, id);
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
    public float getCosto(String codigo) {
        String sql = "SELECT costo FROM tipoExamen where codigo = ?";
        float costo = -1;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    costo = rs.getFloat("costo");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return costo;
    }
    
}
