package datos.especialidad;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.especialidad.Especialidad;

/**
 *
 * @author asael
 */
public class EspecialidadDAOImpl implements EspecialidadDAO {

    private static EspecialidadDAOImpl especialidadDAO = null;
    private Connection conexion = Conexion.getConexion();

    private EspecialidadDAOImpl() {
    }

    public static EspecialidadDAOImpl getEspecialidadDAO() {
        if (especialidadDAO == null) {
            especialidadDAO = new EspecialidadDAOImpl();
        }
        return especialidadDAO;
    }

    @Override
    public List<Especialidad> getListado() {
        String sql = "SELECT * FROM especialidad";
        List<Especialidad> especialidades = null;

        try ( PreparedStatement declaracion = conexion.prepareStatement(sql);  
                ResultSet rs = declaracion.executeQuery()) {
            especialidades = new ArrayList();

            while (rs.next()) {
                Especialidad especialidad = new Especialidad();
                especialidad.setId(rs.getInt("id"));
                especialidad.setNombre(rs.getString("nombre"));
                especialidad.setCosto(rs.getFloat("costo"));
                especialidades.add(especialidad);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return especialidades;
    }

    @Override
    public void create(Especialidad especialidad) {
        String sql = "INSERT INTO especialidad (nombre, costo) VALUES (?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, especialidad.getNombre());
            ps.setFloat(2, especialidad.getCosto());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Especialidad getObject(String nombre) {
        String sql = "SELECT * FROM especialidad WHERE nombre = ?";

        Especialidad especialidad = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    especialidad = new Especialidad();
                    especialidad.setId(rs.getInt("id"));
                    especialidad.setNombre(nombre);
                    especialidad.setCosto(rs.getFloat("costo"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return especialidad;
    }

    @Override
    public void update(Especialidad especialidad) {
        String sql = "UPDATE especialidad SET nombre = ?, costo = ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, especialidad.getNombre());
            ps.setFloat(2, especialidad.getCosto());
            ps.setInt(3, especialidad.getId());
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
    public int getIdEspecialidad(String nombre) {
        String sql = "SELECT id FROM especialidad where nombre = ?";
        int id = -1;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return id;
    }

    @Override
    public boolean exists(String nombre) {
        String sql = "SELECT id FROM especialidad where nombre = ?";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
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
    public float getCosto(int id) {
        String sql = "SELECT costo FROM especialidad where id = ?";
        float costo = -1;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
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
