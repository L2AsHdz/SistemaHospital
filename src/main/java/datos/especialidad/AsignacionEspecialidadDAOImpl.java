package datos.especialidad;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.especialidad.AsignacionEspecialidad;

/**
 *
 * @author asael
 */
public class AsignacionEspecialidadDAOImpl implements AsignacionEspecialidadDAO {

    private static AsignacionEspecialidadDAOImpl asignacionEDAO = null;
    private Connection conexion = Conexion.getConexion();

    private AsignacionEspecialidadDAOImpl() {
    }

    public static AsignacionEspecialidadDAOImpl getAsignacionEsDAO() {
        if (asignacionEDAO == null) {
            asignacionEDAO = new AsignacionEspecialidadDAOImpl();
        }
        return asignacionEDAO;
    }

    @Override
    public List<AsignacionEspecialidad> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(AsignacionEspecialidad asignacionE) {
        String sql = "INSERT INTO asignacionEspecialidad VALUES (?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, asignacionE.getCodigoMedico());
            ps.setInt(2, asignacionE.getIdEspecialidad());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public AsignacionEspecialidad getObject(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(AsignacionEspecialidad t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean exists(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean verificarAsignacion(String codigoMedico, int idEspecialidad) {
        String sql = "SELECT idEspecialidad FROM asignacionEspecialidad where codigoMedico = ? AND idEspecialidad = ?";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigoMedico);
            ps.setInt(2, idEspecialidad);
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
    public int getIdFirstAsinacion(String codigoMedico) {
        String sql = "SELECT idEspecialidad FROM asignacionEspecialidad WHERE codigoMedico = ? LIMIT 1";
        int id = -1;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigoMedico);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    id = rs.getInt("idEspecialidad");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return id;
    }

    @Override
    public List<String> getEspecialidadesByCodMed(String codMedico) {
        String sql = "SELECT e.nombre FROM asignacionEspecialidad ae INNER JOIN "
                + "especialidad e ON ae.idEspecialidad=e.id WHERE ae.codigoMedico = ?";
        List<String> especialidades = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codMedico);
            try ( ResultSet rs = ps.executeQuery()) {
                especialidades = new ArrayList();

                while (rs.next()) {
                    String especialidad = rs.getString("nombre");
                    especialidades.add(especialidad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return especialidades;
    }

}
