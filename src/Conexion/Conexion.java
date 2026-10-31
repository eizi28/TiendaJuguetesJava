
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
    "jdbc:mysql://127.0.0.1:3306/inventario_juguetes";

    public static Connection conectar() throws SQLException {

        String usuario = System.getenv("DB_USER");
        String clave = System.getenv("DB_PASSWORD");

        if (usuario == null || usuario.isBlank()) {
            throw new SQLException("Falta configurar DB_USER.");
        }

        if (clave == null) {
            clave = "";
        }

        return DriverManager.getConnection(
            URL, usuario, clave
        );
    }
}
