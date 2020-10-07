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
import javax.servlet.http.HttpServletResponse;
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

    @Override
    public List<Resultado> getResultadosbyTipoExamen(String codPaciente, String codTipoExamen,
            String fechaInicial, String fechaFinal, int opcion) {
        String sql = "SELECT e.codigo, m.nombre medico, l.nombre laboratorista, te.nombre tipoExamen, "
                + "e.fecha, e.hora, r.resultado, e.total FROM resultado r INNER JOIN examen e ON "
                + "r.codigoExamen=e.codigo LEFT JOIN medico m ON e.codigoMedico=m.codigo INNER JOIN "
                + "laboratorista l ON r.codigoLaboratorista=l.codigo INNER JOIN tipoExamen te ON "
                + "e.codigoTipoExamen=te.codigo WHERE e.codigoPaciente = ? AND e.codigoTipoExamen = ? ";
        String interavalo = "AND (e.fecha BETWEEN ? AND ?)";
        String order = "ORDER BY e.fecha, e.hora";
        List<Resultado> resultados = null;
        PreparedStatement ps = null;

        try {
            switch (opcion) {
                case 1 -> {
                    ps = conexion.prepareStatement(sql + interavalo + order);
                    ps.setString(1, codPaciente);
                    ps.setString(2, codTipoExamen);
                    ps.setString(3, fechaInicial);
                    ps.setString(4, fechaFinal);
                }
                case 2 -> {
                    ps = conexion.prepareStatement(sql + order);
                    ps.setString(1, codPaciente);
                    ps.setString(2, codTipoExamen);
                }
            }
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
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return resultados;
    }

    @Override
    public void getFileResultado(int codExamen, HttpServletResponse response) {
        String sql = "SELECT resultado FROM resultado WHERE codigoExamen = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setInt(1, codExamen);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    byte[] input = rs.getBytes("resultado");
                    response.setContentType("image/*");
                    response.setContentLength(input.length);
                    response.getOutputStream().write(input);
                } else {
                    System.out.println("error");
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public List<Resultado> getResultadosRealizadosHoy(String codLaboratorista) {
        String sql = "SELECT e.codigo, p.nombre paciente, m.nombre medico, te.nombre tipoExamen, "
                + "r.fecha, r.hora, r.resultado, e.total FROM resultado r INNER JOIN examen e "
                + "ON r.codigoExamen=e.codigo LEFT JOIN medico m ON e.codigoMedico=m.codigo "
                + "INNER JOIN paciente p ON e.codigoPaciente=p.codigo INNER JOIN laboratorista l ON "
                + "r.codigoLaboratorista=l.codigo INNER JOIN tipoExamen te ON e.codigoTipoExamen=te.codigo "
                + "WHERE r.codigoLaboratorista = ? AND r.fecha = CAST(NOW() AS DATE) ORDER BY r.fecha, r.hora";
        List<Resultado> resultados = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codLaboratorista);
            try ( ResultSet rs = ps.executeQuery()) {
                resultados = new ArrayList();

                while (rs.next()) {
                    ResultadoDTO resultado = new ResultadoDTO();
                    resultado.setCodigoExamen(rs.getInt("codigo"));
                    resultado.setPaciente(rs.getString("paciente"));
                    resultado.setMedico(rs.getString("medico"));
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
    public List<Resultado> getResultadosRealizadosPorDia(String codLaboratorista, String fechaInicial, String fechaFinal, int opcion) {
        String sql = "SELECT r.fecha, COUNT(r.codigoExamen) cantRealizados FROM resultado r "
                + "WHERE r.codigoLaboratorista = ? ";
        String intervalo = "AND (r.fecha BETWEEN ? AND ?)";
        String group = "GROUP BY r.fecha";
        List<Resultado> resultados = null;
        PreparedStatement ps = null;

        try {
            switch (opcion) {
                case 1 -> {
                    ps = conexion.prepareStatement(sql + intervalo + group);
                    ps.setString(1, codLaboratorista);
                    ps.setString(2, fechaInicial);
                    ps.setString(2, fechaFinal);
                }
                case 2 -> {
                    ps = conexion.prepareStatement(sql + group);
                    ps.setString(1, codLaboratorista);
                }
            }
            try ( ResultSet rs = ps.executeQuery()) {
                resultados = new ArrayList();

                while (rs.next()) {
                    ResultadoDTO resultado = new ResultadoDTO();
                    resultado.setFecha(LocalDate.parse(rs.getString("fecha")));
                    resultado.setCantRealizados(rs.getInt("cantRealizados"));
                    resultados.add(resultado);
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
        return resultados;
    }

}
