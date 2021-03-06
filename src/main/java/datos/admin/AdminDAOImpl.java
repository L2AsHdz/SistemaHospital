package datos.admin;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.usuario.Admin;

/**
 *
 * @author asael
 */
public class AdminDAOImpl implements AdminDAO {

    private static AdminDAOImpl adminDAO = null;
    private Connection conexion = Conexion.getConexion();

    private AdminDAOImpl() {
    }

    public static AdminDAOImpl getAdminDAO() {
        if (adminDAO == null) {
            adminDAO = new AdminDAOImpl();
        }
        return adminDAO;
    }

    @Override
    public List<Admin> getListado() {
        String sql = "SELECT * FROM admin";
        List<Admin> admins = null;

        try ( PreparedStatement declaracion = conexion.prepareStatement(sql);  
                ResultSet rs = declaracion.executeQuery()) {
            admins = new ArrayList();

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setCodigo(rs.getString("codigo"));
                admin.setNombre(rs.getString("nombre"));
                admin.setCUI(rs.getString("cui"));
                admin.setPassword(rs.getString("password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return admins;
    }

    @Override
    public void create(Admin admin) {
        String sql = "INSERT INTO admin VALUES (?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, admin.getCodigo());
            ps.setString(2, admin.getNombre());
            ps.setString(3, admin.getCUI());
            ps.setString(4, admin.getEncryptPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Admin getObject(String codigo) {
        String sql = "SELECT * FROM admin WHERE codigo = ?";

        Admin admin = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    admin = new Admin();
                    admin.setCodigo(codigo);
                    admin.setNombre(rs.getString("nombre"));
                    admin.setCUI(rs.getString("cui"));
                    admin.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return admin;
    }

    @Override
    public void update(Admin admin) {
        String sql = "UPDATE admin SET nombre = ?, cui = ? WHERE codigo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getCUI());
            ps.setString(3, admin.getCodigo());
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
        String sql = "SELECT codigo FROM admin where codigo = ?";
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
