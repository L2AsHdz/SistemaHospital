package datos.consulta;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.consulta.Informe;
import model.consulta.InformeDTO;

/**
 *
 * @author asael
 */
public class InformeDAOImpl implements InformeDAO {

    private static InformeDAOImpl informeDAO = null;
    private Connection conexion = Conexion.getConexion();

    private InformeDAOImpl() {
    }

    public static InformeDAOImpl getInformeDAO() {
        if (informeDAO == null) {
            informeDAO = new InformeDAOImpl();
        }
        return informeDAO;
    }

    @Override
    public List<Informe> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Informe informe) {
        String sql = "INSERT INTO informe VALUES (?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, informe.getCodigoConsulta());
            ps.setString(2, informe.getInforme());
            ps.setString(3, informe.getFecha().toString());
            ps.setString(4, informe.getHora().toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Informe getObject(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Informe t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigoConsulta FROM informe where codigoConsulta = ?";
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
    public List<Informe> getListInformeByPaciente(String codPaciente) {
        String sql = "SELECT c.codigo, m.nombre medico, e.nombre especialidad, c.fecha, c.hora, i.informe, "
                + "c.total FROM informe i INNER JOIN consulta c ON i.codigoConsulta=c.codigo "
                + "INNER JOIN medico m ON c.codigoMedico=m.codigo INNER JOIN especialidad e ON "
                + "c.idEspecialidad=e.id WHERE c.codigoPaciente = ? ORDER BY c.fecha";
        List<Informe> informes = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codPaciente);
            try ( ResultSet rs = ps.executeQuery()) {
                informes = new ArrayList();

                while (rs.next()) {
                    InformeDTO informe = new InformeDTO();
                    informe.setCodigoConsulta(rs.getInt("codigo"));
                    informe.setMedico(rs.getString("medico"));
                    informe.setEspecialidad(rs.getString("especialidad"));
                    informe.setFecha(LocalDate.parse(rs.getString("fecha")));
                    informe.setHora(LocalTime.parse(rs.getString("hora")));
                    informe.setInforme(rs.getString("informe"));
                    informe.setCosto(rs.getFloat("total"));
                    informes.add(informe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return informes;
    }

}
