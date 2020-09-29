package datos.medico;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.usuario.Medico;

/**
 *
 * @author asael
 */
public class MedicoDAOImpl implements MedicoDAO {
    
    private static MedicoDAOImpl medicoDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private MedicoDAOImpl() {
    }
    
    public static MedicoDAOImpl getMedicoDAO(){
        if (medicoDAO == null) {
            medicoDAO = new MedicoDAOImpl();
        }
        return medicoDAO;
    }

    @Override
    public List<Medico> getListado() {
        String sql = "SELECT * FROM medico";
        List<Medico> medicos = null;

        try ( PreparedStatement declaracion = conexion.prepareStatement(sql);  
                ResultSet rs = declaracion.executeQuery()) {
            medicos = new ArrayList();

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setCodigo(rs.getString("codigo"));
                medico.setNombre(rs.getString("nombre"));
                medico.setCUI(rs.getString("cui"));
                medico.setPassword(rs.getString("password"));
                //faltan agregar los demas datos
                medicos.add(medico);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return medicos;
    }

    @Override
    public void create(Medico medico) {
        String sql = "INSERT INTO medico VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, medico.getCodigo());
            ps.setString(2, medico.getNombre());
            ps.setString(3, medico.getNoColegiado());
            ps.setString(4, medico.getCUI());
            ps.setString(5, medico.getTelefono());
            ps.setString(6, medico.getCorreo());
            ps.setString(7, medico.getHoraInicio().toString());
            ps.setString(8, medico.getHoraFinal().toString());
            ps.setString(9, medico.getFechaInicioLabores().toString());
            ps.setString(10, medico.getEncryptPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Medico getObject(String codigo) {
        String sql = "SELECT * FROM medico WHERE codigo = ?";

        Medico medico = null;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    medico = new Medico();
                    medico.setCodigo(codigo);
                    medico.setNombre(rs.getString("nombre"));
                    medico.setNoColegiado(rs.getString("noColegiado"));
                    medico.setCUI(rs.getString("cui"));
                    medico.setTelefono(rs.getString("telefono"));
                    medico.setCorreo(rs.getString("correo"));
                    medico.setHoraInicio(LocalTime.parse(rs.getString("horaInicio")));
                    medico.setHoraFinal(LocalTime.parse(rs.getString("horaFinal")));
                    medico.setFechaInicioLabores(LocalDate.parse(rs.getString("fechaInicioLabores")));
                    medico.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return medico;
    }

    @Override
    public void update(Medico t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigo FROM medico where codigo = ?";
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
