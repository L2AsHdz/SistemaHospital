package datos.medico;

import datos.Conexion;
import datos.especialidad.AsignacionEspecialidadDAO;
import datos.especialidad.AsignacionEspecialidadDAOImpl;
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

    private final AsignacionEspecialidadDAO asignacionDAO = AsignacionEspecialidadDAOImpl.getAsignacionEsDAO();

    private MedicoDAOImpl() {
    }

    public static MedicoDAOImpl getMedicoDAO() {
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
                medico.setNoColegiado(rs.getString("noColegiado"));
                medico.setCUI(rs.getString("cui"));
                medico.setTelefono(rs.getString("telefono"));
                medico.setCorreo(rs.getString("correo"));
                medico.setHoraInicio(LocalTime.parse(rs.getString("horaInicio")));
                medico.setHoraFinal(LocalTime.parse(rs.getString("horaFinal")));
                medico.setFechaInicioLabores(LocalDate.parse(rs.getString("fechaInicioLabores")));
                medico.setPassword(rs.getString("password"));
                medico.setEspecialidades(asignacionDAO.getEspecialidadesByCodMed(medico.getCodigo()));
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
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try ( ResultSet rs = ps.executeQuery()) {
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
    public void update(Medico medico) {
        String sql = "UPDATE medico SET nombre = ?, noColegiado = ?, cui = ?, telefono = ?,"
                + "correo = ?, horaInicio = ?, horaFinal = ?, fechaInicioLabores = ?, password = ? WHERE codigo = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, medico.getNombre());
            ps.setString(2, medico.getNoColegiado());
            ps.setString(3, medico.getCUI());
            ps.setString(4, medico.getTelefono());
            ps.setString(5, medico.getCorreo());
            ps.setString(6, medico.getHoraInicio().toString());
            ps.setString(7, medico.getHoraFinal().toString());
            ps.setString(8, medico.getFechaInicioLabores().toString());
            ps.setString(9, medico.getEncryptPassword());
            ps.setString(10, medico.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void delete(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
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

    @Override
    public List<Medico> getFilteredList(String nombre, String idEspecialidad, String hora, int opcion) {
        String sql = "SELECT * FROM medico ";
        String sql2 = "SELECT m.* FROM medico m INNER JOIN asignacionEspecialidad ae ON m.codigo=ae.codigoMedico ";
        String filtro;
        PreparedStatement ps = null;
        List<Medico> medicos = null;

        try {
            switch (opcion) {
                case 1 -> {
                    filtro = "WHERE m.nombre LIKE ? AND ae.idEspecialidad = ? AND (m.horaInicio <= ? AND m.horaFinal > ?)";
                    ps = conexion.prepareStatement(sql2 + filtro);
                    ps.setString(1, "%" + nombre + "%");
                    ps.setInt(2, Integer.parseInt(idEspecialidad));
                    ps.setString(3, hora);
                    ps.setString(4, hora);
                }
                case 2 -> {
                    filtro = "WHERE ae.idEspecialidad = ? AND (m.horaInicio <= ? AND m.horaFinal > ?)";
                    ps = conexion.prepareStatement(sql2 + filtro);
                    ps.setInt(1, Integer.parseInt(idEspecialidad));
                    ps.setString(2, hora);
                    ps.setString(3, hora);
                }
                case 3 -> {
                    filtro = "WHERE m.nombre LIKE ? AND ae.idEspecialidad = ?";
                    ps = conexion.prepareStatement(sql2 + filtro);
                    ps.setString(1, "%" + nombre + "%");
                    ps.setInt(2, Integer.parseInt(idEspecialidad));
                }
                case 4 -> {
                    filtro = "WHERE nombre LIKE ? AND (horaInicio <= ? AND horaFinal > ?)";
                    ps = conexion.prepareStatement(sql + filtro);
                    ps.setString(1, "%" + nombre + "%");
                    ps.setString(2, hora);
                    ps.setString(3, hora);
                }
                case 5 -> {
                    filtro = "WHERE nombre LIKE ?";
                    ps = conexion.prepareStatement(sql + filtro);
                    ps.setString(1, "%" + nombre + "%");
                }
                case 6 -> {
                    filtro = "WHERE ae.idEspecialidad = ?";
                    ps = conexion.prepareStatement(sql2 + filtro);
                    ps.setInt(1, Integer.parseInt(idEspecialidad));
                }
                case 7 -> {
                    filtro = "WHERE (horaInicio <= ? AND horaFinal > ?)";
                    ps = conexion.prepareStatement(sql + filtro);
                    ps.setString(1, hora);
                    ps.setString(2, hora);
                }

            }
            try ( ResultSet rs = ps.executeQuery()) {
                medicos = new ArrayList();
                while (rs.next()) {
                    Medico medico = new Medico();
                    medico.setCodigo(rs.getString("codigo"));
                    medico.setNombre(rs.getString("nombre"));
                    medico.setNoColegiado(rs.getString("noColegiado"));
                    medico.setCUI(rs.getString("cui"));
                    medico.setTelefono(rs.getString("telefono"));
                    medico.setCorreo(rs.getString("correo"));
                    medico.setHoraInicio(LocalTime.parse(rs.getString("horaInicio")));
                    medico.setHoraFinal(LocalTime.parse(rs.getString("horaFinal")));
                    medico.setFechaInicioLabores(LocalDate.parse(rs.getString("fechaInicioLabores")));
                    medico.setPassword(rs.getString("password"));
                    medico.setEspecialidades(asignacionDAO.getEspecialidadesByCodMed(medico.getCodigo()));
                    medicos.add(medico);
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
        return medicos;
    }

    @Override
    public List<Medico> getMedicosConMasinformes(String fechaInicial, String fechaFinal, int opcion) {
        String sql = "SELECT m.*, COUNT(i.codigoConsulta) informes FROM medico m INNER JOIN consulta c "
                + "ON m.codigo=c.codigoMedico INNER JOIN informe i ON c.codigo=i.codigoConsulta "
                + " ";
        String intervalo = "WHERE (i.fecha BETWEEN ? AND ?) ";
        String order = "GROUP BY c.codigoMedico ORDER BY informes DESC LIMIT 10";
        List<Medico> medicos = null;
        PreparedStatement ps = null;

        try {
            switch (opcion) {
                case 1 -> {
                    ps = conexion.prepareStatement(sql + intervalo + order);
                    ps.setString(1, fechaInicial);
                    ps.setString(2, fechaFinal);
                }
                case 2 -> {
                    ps = conexion.prepareStatement(sql + order);
                }
            }
            try ( ResultSet rs = ps.executeQuery()) {
                medicos = new ArrayList();

                while (rs.next()) {
                    Medico medico = new Medico();
                medico.setCodigo(rs.getString("codigo"));
                medico.setNombre(rs.getString("nombre"));
                medico.setNoColegiado(rs.getString("noColegiado"));
                medico.setCUI(rs.getString("cui"));
                medico.setTelefono(rs.getString("telefono"));
                medico.setCorreo(rs.getString("correo"));
                medico.setFechaInicioLabores(LocalDate.parse(rs.getString("fechaInicioLabores")));
                medico.setInformes(rs.getInt("informes"));
                medicos.add(medico);
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
        return medicos;
    }

    @Override
    public List<Medico> getIngresosDeMedicos(String fechaInicial, String fechaFinal, int opcion) {
        String sql = "SELECT m.*, SUM(c.total) ingresos FROM medico m INNER JOIN consulta c "
                + "ON m.codigo=c.codigoMedico "
                + " ";
        String intervalo = "WHERE (c.fecha BETWEEN ? AND ?) ";
        String order = "GROUP BY m.codigo ORDER BY ingresos DESC";
        List<Medico> medicos = null;
        PreparedStatement ps = null;

        try {
            switch (opcion) {
                case 1 -> {
                    ps = conexion.prepareStatement(sql + intervalo + order);
                    ps.setString(1, fechaInicial);
                    ps.setString(2, fechaFinal);
                }
                case 2 -> {
                    ps = conexion.prepareStatement(sql + order);
                }
            }
            try ( ResultSet rs = ps.executeQuery()) {
                medicos = new ArrayList();

                while (rs.next()) {
                    Medico medico = new Medico();
                medico.setCodigo(rs.getString("codigo"));
                medico.setNombre(rs.getString("nombre"));
                medico.setNoColegiado(rs.getString("noColegiado"));
                medico.setCUI(rs.getString("cui"));
                medico.setTelefono(rs.getString("telefono"));
                medico.setCorreo(rs.getString("correo"));
                medico.setFechaInicioLabores(LocalDate.parse(rs.getString("fechaInicioLabores")));
                medico.setIngresos(rs.getFloat("ingresos"));
                medicos.add(medico);
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
        return medicos;
    }

}
