package datos.paciente;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.usuario.Paciente;

/**
 *
 * @author asael
 */
public class PacienteDAOImpl implements PacienteDAO {

    private static PacienteDAOImpl pacienteDAO = null;
    private Connection conexion = Conexion.getConexion();

    private PacienteDAOImpl() {
    }

    public static PacienteDAOImpl getPacienteDAO() {
        if (pacienteDAO == null) {
            pacienteDAO = new PacienteDAOImpl();
        }
        return pacienteDAO;
    }

    @Override
    public List<Paciente> getListado() {
        String sql = "SELECT * FROM paciente";
        List<Paciente> pacientes = null;

        try ( PreparedStatement declaracion = conexion.prepareStatement(sql);  ResultSet rs = declaracion.executeQuery()) {
            pacientes = new ArrayList();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setCodigo(rs.getString("codigo"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setBirth(LocalDate.parse(rs.getString("birth")));
                paciente.setCUI(rs.getString("cui"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setPeso(rs.getFloat("peso"));
                paciente.setTipoSangre(rs.getString("tipoSangre"));
                paciente.setCorreo(rs.getString("correo"));
                paciente.setPassword(rs.getString("password"));

                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return pacientes;
    }

    @Override
    public void create(Paciente paciente) {
        String sql = "INSERT INTO paciente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, paciente.getCodigo());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getSexo());
            ps.setString(4, paciente.getBirth().toString());
            ps.setString(5, paciente.getCUI());
            ps.setString(6, paciente.getTelefono());
            ps.setFloat(7, paciente.getPeso());
            ps.setString(8, paciente.getTipoSangre());
            ps.setString(9, paciente.getCorreo());
            ps.setString(10, paciente.getEncryptPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Paciente getObject(String codigo) {
        String sql = "SELECT * FROM paciente WHERE codigo = ?";

        Paciente paciente = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    paciente = new Paciente();
                    paciente.setCodigo(codigo);
                    paciente.setNombre(rs.getString("nombre"));
                    paciente.setSexo(rs.getString("sexo"));
                    paciente.setBirth(LocalDate.parse(rs.getString("birth")));
                    paciente.setCUI(rs.getString("cui"));
                    paciente.setTelefono(rs.getString("telefono"));
                    paciente.setPeso(rs.getFloat("peso"));
                    paciente.setTipoSangre(rs.getString("tipoSangre"));
                    paciente.setCorreo(rs.getString("correo"));
                    paciente.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return paciente;
    }

    @Override
    public void update(Paciente paciente) {
        String sql = "UPDATE paciente SET nombre = ?, birth = ?, cui = ?, telefono = ?,"
                + "correo = ?, peso = ? WHERE codigo = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getBirth().toString());
            ps.setString(3, paciente.getCUI());
            ps.setString(4, paciente.getTelefono());
            ps.setString(5, paciente.getCorreo());
            ps.setFloat(6, paciente.getPeso());
            ps.setString(7, paciente.getCodigo());
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
    public boolean exists(String codigo) {
        String sql = "SELECT codigo FROM paciente where codigo = ?";
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

    @Override
    public List<Paciente> getPacientesConMasInformes(String fechaInicial, String fechaFinal, int opcion) {
        String sql = "SELECT p.*, COUNT(i.codigoConsulta) informes FROM paciente p INNER JOIN consulta c "
                + "ON p.codigo=c.codigoPaciente INNER JOIN informe i ON c.codigo=i.codigoConsulta ";
        String interavalo = "WHERE i.fecha BETWEEN ? AND ? ";
        String order = "GROUP BY c.codigoPaciente ORDER BY informes DESC";
        List<Paciente> pacientes = null;
        PreparedStatement ps = null;

        try {
            switch (opcion) {
                case 1 -> {
                    ps = conexion.prepareStatement(sql + interavalo + order);
                    ps.setString(1, fechaInicial);
                    ps.setString(2, fechaFinal);
                }
                case 2 -> {
                    ps = conexion.prepareStatement(sql + order);
                }
            }
            try ( ResultSet rs = ps.executeQuery()) {
                pacientes = new ArrayList();

                while (rs.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setCodigo(rs.getString("codigo"));
                    paciente.setNombre(rs.getString("nombre"));
                    paciente.setSexo(rs.getString("sexo"));
                    paciente.setBirth(LocalDate.parse(rs.getString("birth")));
                    paciente.setCUI(rs.getString("cui"));
                    paciente.setTelefono(rs.getString("telefono"));
                    paciente.setPeso(rs.getFloat("peso"));
                    paciente.setTipoSangre(rs.getString("tipoSangre"));
                    paciente.setCorreo(rs.getString("correo"));
                    paciente.setPassword(rs.getString("password"));
                    paciente.setInformes(rs.getInt("informes"));
                    pacientes.add(paciente);
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
        return pacientes;
    }

    @Override
    public List<Paciente> getIngresosConsultasPorPaciete(String fechaInicial, String fechaFinal, int opcion) {
        String sql = "SELECT p.*, SUM(c.total) ingresosC FROM paciente p INNER JOIN consulta c ON "
                + "p.codigo=c.codigoPaciente ";
        String interavalo = "WHERE c.fecha BETWEEN ? AND ? ";
        String order = "GROUP BY p.codigo ORDER BY ingresosC DESC";
        List<Paciente> pacientes = null;
        PreparedStatement ps = null;

        try {
            switch (opcion) {
                case 1 -> {
                    ps = conexion.prepareStatement(sql + interavalo + order);
                    ps.setString(1, fechaInicial);
                    ps.setString(2, fechaFinal);
                }
                case 2 -> {
                    ps = conexion.prepareStatement(sql + order);
                }
            }
            try ( ResultSet rs = ps.executeQuery()) {
                pacientes = new ArrayList();

                while (rs.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setCodigo(rs.getString("codigo"));
                    paciente.setNombre(rs.getString("nombre"));
                    paciente.setTelefono(rs.getString("telefono"));
                    paciente.setIngresosConsultas(rs.getFloat("ingresosC"));
                    pacientes.add(paciente);
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
        return pacientes;
    }

    @Override
    public List<Paciente> getIngresosExamenesPorPaciete(String fechaInicial, String fechaFinal, int opcion) {
        String sql = "SELECT p.*, SUM(e.total) ingresosE FROM paciente p INNER JOIN examen e ON "
                + "p.codigo=e.codigoPaciente ";
        String interavalo = "WHERE e.fecha BETWEEN ? AND ? ";
        String order = "GROUP BY p.codigo ORDER BY ingresosE DESC";
        List<Paciente> pacientes = null;
        PreparedStatement ps = null;

        try {
            switch (opcion) {
                case 1 -> {
                    ps = conexion.prepareStatement(sql + interavalo + order);
                    ps.setString(1, fechaInicial);
                    ps.setString(2, fechaFinal);
                }
                case 2 -> {
                    ps = conexion.prepareStatement(sql + order);
                }
            }
            try ( ResultSet rs = ps.executeQuery()) {
                pacientes = new ArrayList();

                while (rs.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setCodigo(rs.getString("codigo"));
                    paciente.setNombre(rs.getString("nombre"));
                    paciente.setTelefono(rs.getString("telefono"));
                    paciente.setIngresosExamenes(rs.getFloat("ingresosE"));
                    pacientes.add(paciente);
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
        return pacientes;
    }

    @Override
    public String getCodigo() {
        String sql = "SELECT codigo FROM paciente ORDER BY CAST(codigo AS INT) DESC LIMIT 1";
        Integer codigo = -1;
        try ( PreparedStatement ps = conexion.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                codigo = rs.getInt("codigo") + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return codigo.toString();
    }

}
