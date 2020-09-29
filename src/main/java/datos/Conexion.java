package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asael
 */
public class Conexion {
    private static Connection conexion = null;
    private final String url = "jdbc:mariadb://localhost:3306/SistemaHospital";
    private final String usuario = "userDB";
    private final String password = "123";

    private Conexion() {
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static Connection getConexion() {
        if (conexion == null) {
            new Conexion();
        }
        return conexion;
    }
}
