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
import model.consulta.Consulta;

/**
 *
 * @author asael
 */
public class ConsultaDAOImpl implements ConsultaDAO {

    private static ConsultaDAOImpl consultaDAO = null;
    private Connection conexion = Conexion.getConexion();

    private ConsultaDAOImpl() {
    }

    public static ConsultaDAOImpl getconsultaDAO() {
        if (consultaDAO == null) {
            consultaDAO = new ConsultaDAOImpl();
        }
        return consultaDAO;
    }

    @Override
    public List<Consulta> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Consulta consulta) {
        String sql = "INSERT INTO consulta (codigoPaciente, codigoMedico, idEspecialidad, fecha, hora, estado, total) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, consulta.getCodigoPaciente());
            ps.setString(2, consulta.getCodigoMedico());
            ps.setInt(3, consulta.getIdEspecialidad());
            ps.setString(4, consulta.getFecha().toString());
            ps.setString(5, consulta.getHora().toString());
            ps.setInt(6, consulta.getEstado());
            ps.setFloat(7, consulta.getTotal());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Consulta getObject(String codigo) {
        String sql = "SELECT * FROM consulta WHERE codigo = ?";

        Consulta consulta = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codigo));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    consulta = new Consulta();
                    consulta.setCodigo(rs.getInt("codigo"));
                    consulta.setCodigoMedico(rs.getString("codigoMedico"));
                    consulta.setCodigoPaciente(rs.getString("codigoPaciente"));
                    consulta.setIdEspecialidad(rs.getInt("idEspecialidad"));
                    consulta.setFecha(LocalDate.parse(rs.getString("fecha")));
                    consulta.setHora(LocalTime.parse(rs.getString("hora")));
                    consulta.setEstado(rs.getInt("estado"));
                    consulta.setTotal(rs.getFloat("total"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return consulta;
    }

    @Override
    public void update(Consulta t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigo FROM consulta WHERE codigo = ?";
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
        String sql = "SELECT codigo FROM consulta ORDER BY codigo DESC LIMIT 1";
        int codigo = 1;
        try ( PreparedStatement ps = conexion.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

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
        String sql = "ALTER TABLE consulta AUTO_INCREMENT = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public boolean isAvailable(String codMedico, String hora, String fecha) {
        String sql = "SELECT codigo FROM consulta WHERE codigoMedico = ? AND hora = ? AND fecha = ?";
        boolean flag = true;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codMedico);
            ps.setString(2, hora);
            ps.setString(3, fecha);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    flag = false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return flag;
    }

    @Override
    public List<Consulta> getConsultasPendientes(String codPaciente) {
        String sql = "SELECT c.codigo, m.nombre medico, e.nombre especialidad, c.fecha, "
                + "c.hora, c.total FROM consulta c INNER JOIN medico m ON c.codigoMedico=m.codigo "
                + "INNER JOIN especialidad e ON c.idEspecialidad=e.id WHERE c.codigoPaciente = ? AND "
                + "c.estado = 0 ORDER BY c.fecha";
        List<Consulta> consultas = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codPaciente);
            try ( ResultSet rs = ps.executeQuery()) {
                consultas = new ArrayList();

                while (rs.next()) {
                    Consulta consulta = new Consulta();
                    consulta.setCodigo(rs.getInt("codigo"));
                    consulta.setNombreMedico(rs.getString("medico"));
                    consulta.setNombreEspecialidad(rs.getString("especialidad"));
                    consulta.setFecha(LocalDate.parse(rs.getString("fecha")));
                    consulta.setHora(LocalTime.parse(rs.getString("hora")));
                    consulta.setTotal(rs.getFloat("total"));
                    consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return consultas;
    }

    @Override
    public List<Consulta> getConsultasPendientesToday(String codMedico) {
        String sql = "SELECT c.codigo, c.codigoPaciente, c.idEspecialidad, p.nombre paciente, e.nombre especialidad, c.fecha, "
                + "c.hora FROM consulta c INNER JOIN paciente p ON c.codigoPaciente=p.codigo "
                + "INNER JOIN especialidad e ON c.idEspecialidad=e.id WHERE c.codigoMedico = ? "
                + "AND c.estado = 0 AND c.fecha = CAST(NOW() AS DATE) ORDER BY c.hora";
        List<Consulta> consultas = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codMedico);
            try ( ResultSet rs = ps.executeQuery()) {
                consultas = new ArrayList();

                while (rs.next()) {
                    Consulta consulta = new Consulta();
                    consulta.setCodigo(rs.getInt("codigo"));
                    consulta.setCodigoPaciente(rs.getString("codigoPaciente"));
                    consulta.setNombrePaciente(rs.getString("paciente"));
                    consulta.setIdEspecialidad(rs.getInt("idEspecialidad"));
                    consulta.setNombreEspecialidad(rs.getString("especialidad"));
                    consulta.setFecha(LocalDate.parse(rs.getString("fecha")));
                    consulta.setHora(LocalTime.parse(rs.getString("hora")));
                    consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return consultas;
    }

    @Override
    public void setEstado(String codConsulta, int estado) {
        String sql = "UPDATE consulta SET estado = ? WHERE codigo = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, estado);
            ps.setInt(2, Integer.parseInt(codConsulta));
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public List<Consulta> getConsultasAgendadasByIntevalo(String codMedico, String fechaInicial, String fechaFinal, int opcion) {
        String sql = "SELECT c.codigo, p.nombre paciente, e.nombre especialidad, c.fecha, c.hora FROM consulta c "
                + "INNER JOIN paciente p ON c.codigoPaciente=p.codigo INNER JOIN especialidad e "
                + "ON c.idEspecialidad=e.id WHERE c.codigoMedico = ? AND c.estado = 0 ";
        String interavalo = "AND (c.fecha BETWEEN ? AND ?) ";
        String order = "ORDER BY c.fecha, c.hora";
        List<Consulta> consultas = null;
        PreparedStatement ps = null;

        try {
            switch (opcion) {
                case 1 -> {
                    ps = conexion.prepareStatement(sql + interavalo + order);
                    ps.setString(1, codMedico);
                    ps.setString(2, fechaInicial);
                    ps.setString(3, fechaFinal);
                }
                case 2 -> {
                    ps = conexion.prepareStatement(sql + order);
                    ps.setString(1, codMedico);
                }
            }
            try ( ResultSet rs = ps.executeQuery()) {
                consultas = new ArrayList();

                while (rs.next()) {
                    Consulta consulta = new Consulta();
                    consulta.setCodigo(rs.getInt("codigo"));
                    consulta.setNombrePaciente(rs.getString("paciente"));
                    consulta.setNombreEspecialidad(rs.getString("especialidad"));
                    consulta.setFecha(LocalDate.parse(rs.getString("fecha")));
                    consulta.setHora(LocalTime.parse(rs.getString("hora")));
                    consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return consultas;
    }

}
