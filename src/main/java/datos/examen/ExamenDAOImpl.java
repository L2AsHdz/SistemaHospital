package datos.examen;

import datos.Conexion;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
        String sql = "INSERT INTO examen (codigoPaciente, codigoTipoExamen, codigoMedico, "
                + "orden, fecha, hora, estado, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, examen.getCodigoPaciente());
            ps.setString(2, examen.getCodigoTipoExamen());
            ps.setString(3, examen.getCodigoMedico());
            ps.setBinaryStream(4, examen.getOrden());
            ps.setString(5, examen.getFecha().toString());
            ps.setString(6, examen.getHora().toString());
            ps.setInt(7, examen.getEstado());
            ps.setFloat(8, examen.getTotal());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void create2(Examen examen) {
        String sql = "INSERT INTO examen VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, examen.getCodigo());
            ps.setString(2, examen.getCodigoPaciente());
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
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    examen = new Examen();
                    examen.setCodigo(Integer.parseInt(codigo));
                    examen.setCodigoPaciente(rs.getString("codigoPaciente"));
                    examen.setCodigoTipoExamen(rs.getString("codigoTipoExamen"));
                    examen.setCodigoMedico(rs.getString("codigoMedico"));
                    examen.setOrden(rs.getBinaryStream("orden"));
                    examen.setFecha(LocalDate.parse(rs.getString("fecha")));
                    examen.setHora(LocalTime.parse(rs.getString("hora")));
                    examen.setEstado(rs.getInt("estado"));
                    examen.setTotal(rs.getFloat("total"));
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
        String sql = "ALTER TABLE examen AUTO_INCREMENT = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public List<Examen> getExamenesPendientes(String codPaciente) {
        String sql = "SELECT e.codigo, m.nombre medico, te.nombre tipoExamen, e.fecha, e.hora, "
                + "e.total FROM examen e LEFT JOIN medico m ON e.codigoMedico=m.codigo INNER JOIN "
                + "tipoExamen te ON e.codigoTipoExamen=te.codigo WHERE e.codigoPaciente = ? AND e.estado = 0 "
                + "ORDER BY e.fecha";
        List<Examen> examenes = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codPaciente);
            try ( ResultSet rs = ps.executeQuery()) {
                examenes = new ArrayList();

                while (rs.next()) {
                    Examen examen = new Examen();
                    examen.setCodigo(rs.getInt("codigo"));
                    examen.setNombreMedico(rs.getString("medico"));
                    examen.setNombreTipoExamen(rs.getString("tipoExamen"));
                    examen.setFecha(LocalDate.parse(rs.getString("fecha")));
                    examen.setHora(LocalTime.parse(rs.getString("hora")));
                    examen.setTotal(rs.getFloat("total"));
                    examenes.add(examen);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return examenes;
    }

    @Override
    public List<Examen> getExamenesPendientesToday(String codTipoExamen) {
        String sql = "SELECT e.codigo, p.nombre paciente, m.nombre medico, te.nombre tipoExamen, te.tipoInforme, e.fecha, e.hora, e.orden,"
                + "e.total FROM examen e LEFT JOIN medico m ON e.codigoMedico=m.codigo INNER JOIN paciente p ON e.codigoPaciente=p.codigo "
                + "INNER JOIN tipoExamen te ON e.codigoTipoExamen=te.codigo WHERE e.codigoTipoExamen = ? AND e.estado = 0 "
                + "AND e.fecha = CAST(NOW() AS DATE) ORDER BY e.fecha";
        List<Examen> examenes = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codTipoExamen);
            try ( ResultSet rs = ps.executeQuery()) {
                examenes = new ArrayList();

                while (rs.next()) {
                    Examen examen = new Examen();
                    examen.setCodigo(rs.getInt("codigo"));
                    examen.setNombrePaciente(rs.getString("paciente"));
                    examen.setNombreMedico(rs.getString("medico"));
                    examen.setNombreTipoExamen(rs.getString("tipoExamen"));
                    if (rs.getString("tipoInforme").equals("PDF")) {
                        examen.setTipoInforme(".pdf");
                    } else {
                        examen.setTipoInforme("image/*");
                    }
                    examen.setFecha(LocalDate.parse(rs.getString("fecha")));
                    examen.setHora(LocalTime.parse(rs.getString("hora")));
                    examen.setOrden(rs.getBinaryStream("orden"));
                    examen.setTotal(rs.getFloat("total"));
                    examenes.add(examen);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return examenes;
    }

    @Override
    public byte[] getOrdenByCodExamen(String codigoExamen) {
        String sql = "SELECT orden FROM examen WHERE codigo = ?";
        InputStream orden = null;
        byte[] datosPDF = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigoExamen);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orden = rs.getBinaryStream("orden");

                    int tamanoInput = orden.available();
                    datosPDF = new byte[tamanoInput];
                    orden.read(datosPDF, 0, tamanoInput);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return datosPDF;
    }

    @Override
    public void setEstado(String codExamen, int estado) {
        String sql = "UPDATE examen SET estado = ? WHERE codigo = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, estado);
            ps.setInt(2, Integer.parseInt(codExamen));
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
