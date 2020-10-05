package datos.examen;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.examen.Resultado;
import model.examen.ResultadoDTO;

/**
 *
 * @author asael
 */
public class ResultadoDAOImpl implements ResultadoDAO {
    
    private static ResultadoDAOImpl resultadoDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private ResultadoDAOImpl() {
    }
    
    public static ResultadoDAOImpl getResultadoDAO() {
        if (resultadoDAO == null) {
            resultadoDAO = new ResultadoDAOImpl();
        }
        return resultadoDAO;
    }

    @Override
    public List<Resultado> getListado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Resultado resultado) {
        String sql = "INSERT INTO resultado VALUES (?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, resultado.getCodigoExamen());
            ps.setString(2, resultado.getCodigoLaboratorista());
            ps.setBinaryStream(3, resultado.getResultado());
            ps.setString(4, resultado.getFecha().toString());
            ps.setString(5, resultado.getHora().toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Resultado getObject(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Resultado t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigoExamen FROM resultado where codigoExamen = ?";
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
    public List<Resultado> getListResultadoByPaciente(String codPaciente) {
        String sql = "SELECT e.codigo, p.nombre paciente, m.nombre medico, l.nombre laboratorista, te.nombre tipoExamen, "
                + "e.fecha, e.hora, r.resultado, e.total FROM resultado r INNER JOIN examen e "
                + "ON r.codigoExamen=e.codigo LEFT JOIN medico m ON e.codigoMedico=m.codigo "
                + "INNER JOIN paciente p ON e.codigoPaciente=p.codigo INNER JOIN laboratorista l ON r.codigoLaboratorista=l.codigo INNER JOIN "
                + "tipoExamen te ON e.codigoTipoExamen=te.codigo WHERE e.codigoPaciente = ? "
                + "ORDER BY e.fecha";
        List<Resultado> resultados = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codPaciente);
            try ( ResultSet rs = ps.executeQuery()) {
                resultados = new ArrayList();

                while (rs.next()) {
                    ResultadoDTO resultado = new ResultadoDTO();
                    resultado.setCodigoExamen(rs.getInt("codigo"));
                    resultado.setPaciente(rs.getString("paciente"));
                    resultado.setMedico(rs.getString("medico"));
                    resultado.setLaboratorista(rs.getString("laboratorista"));
                    resultado.setTipoExamen(rs.getString("tipoExamen"));
                    resultado.setFecha(LocalDate.parse(rs.getString("fecha")));
                    resultado.setHora(LocalTime.parse(rs.getString("hora")));
                    resultado.setResultado(rs.getBinaryStream("resultado"));
                    resultado.setCosto(rs.getFloat("total"));
                    resultados.add(resultado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return resultados;
    }

    @Override
    public List<Resultado> getLastFiveResultados(String codPaciente) {
        String sql = "SELECT e.codigo, m.nombre medico, l.nombre laboratorista, te.nombre tipoExamen, "
                + "e.fecha, e.hora, r.resultado, e.total FROM resultado r INNER JOIN examen e ON "
                + "r.codigoExamen=e.codigo LEFT JOIN medico m ON e.codigoMedico=m.codigo INNER JOIN "
                + "laboratorista l ON r.codigoLaboratorista=l.codigo INNER JOIN tipoExamen te ON "
                + "e.codigoTipoExamen=te.codigo WHERE e.codigoPaciente = ? ORDER BY e.fecha, e.hora DESC LIMIT 5";
        List<Resultado> resultados = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codPaciente);
            try ( ResultSet rs = ps.executeQuery()) {
                resultados = new ArrayList();

                while (rs.next()) {
                    ResultadoDTO resultado = new ResultadoDTO();
                    resultado.setCodigoExamen(rs.getInt("codigo"));
                    resultado.setMedico(rs.getString("medico"));
                    resultado.setLaboratorista(rs.getString("laboratorista"));
                    resultado.setTipoExamen(rs.getString("tipoExamen"));
                    resultado.setFecha(LocalDate.parse(rs.getString("fecha")));
                    resultado.setHora(LocalTime.parse(rs.getString("hora")));
                    resultado.setResultado(rs.getBinaryStream("resultado"));
                    resultado.setCosto(rs.getFloat("total"));
                    resultados.add(resultado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return resultados;
    }
    
}
