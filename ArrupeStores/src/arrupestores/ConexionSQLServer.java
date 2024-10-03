package arrupestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionSQLServer {

    // Datos de conexión
    private static final String usuario = "Ulis3s0012";
    private static final String contraseña = "hola";
    private static final String urlConexion = "jdbc:sqlserver://Ulises_Mejia\\SQLSERVER:1433;databaseName=ArrupeStore;encrypt=true;trustServerCertificate=true;";

    // Método para establecer la conexión
    public static Connection conectar() throws SQLException {
        try {
            // Cargar el driver JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Establecer la conexión
            Connection conn = DriverManager.getConnection(urlConexion, usuario, contraseña);
            System.out.println("Conexión establecida con éxito a SQL Server!");
            return conn;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            throw new SQLException("Error al conectar con la base de datos", e);
        }
    }

    // Método para consultar usuarios con su cargo y email
    public static void consultarUsuariosConCargoYEmail() {
        String consulta = "SELECT u.UsuarioID, u.Nombre, u.Apellido, u.Email, c.NombreCargo "
                        + "FROM Usuarios u "
                        + "INNER JOIN Cargos c ON u.CargoID = c.CargoID";
        
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(consulta);
             ResultSet rs = stmt.executeQuery()) {
            
            // Iterar por los resultados
            while (rs.next()) {
                int id = rs.getInt("UsuarioID");
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String email = rs.getString("Email");
                String cargo = rs.getString("NombreCargo");
                
                // Ejemplo: imprimir los resultados
                System.out.println("Usuario ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Email: " + email + ", Cargo: " + cargo);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    // Método main para probar la conexión y las consultas
    public static void main(String[] args) {
        try {
            // Establecer la conexión
            Connection conn = conectar();

            // Ejecutar consulta de usuarios con cargo y email
            consultarUsuariosConCargoYEmail();

            // Cerrar la conexión
            conn.close();
            System.out.println("Conexión cerrada.");

        } catch (SQLException e) {
            System.out.println("Error en la aplicación: " + e.getMessage());
        }
    }
}
