package datos.laboratorista;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.usuario.Turno;

/**
 *
 * @author asael
 */
public class TurnoDAOImpl implements TurnoDAO {
    
    private static TurnoDAOImpl turnoDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private TurnoDAOImpl() {
    }
    
    public static TurnoDAOImpl getTurnoDAO() {
        if (turnoDAO == null) {
            turnoDAO = new TurnoDAOImpl();
        }
        return turnoDAO;
    }

    @Override
    public List<Turno> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Turno turno) {
        String sql = "INSERT INTO turno VALUES (?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, turno.getCodigoLaboratorista());
            ps.setString(2, turno.getDia().name());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Turno getObject(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Turno t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getTurnosByCodLab(String codLab) {
        String sql = "SELECT dia FROM turno WHERE codigoLaboratorista = ?";
        List<String> turnos = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codLab);
            try ( ResultSet rs = ps.executeQuery()) {
                turnos = new ArrayList();

                while (rs.next()) {
                    String turno = rs.getString("dia");
                    switch (turno) {
                        case "SUNDAY" -> {turnos.add("Domingo");}
                        case "MONDAY" -> {turnos.add("Lunes");}
                        case "TUESDAY" -> {turnos.add("Martes");}
                        case "WEDNESDAY" -> {turnos.add("Miercoles");}
                        case "THURSDAY" -> {turnos.add("Jueves");}
                        case "FRIDAY" -> {turnos.add("Viernes");}
                        case "SATURDAY" -> {turnos.add("Sabado");}
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return turnos;
    }
    
}
