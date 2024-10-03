package arrupestores;

public class ArrupeStores {

    public static void main(String[] args) {
        try {
            // Llama al método main de ConexionSQLServer para establecer la conexión y ejecutar consultas
            ConexionSQLServer.main(args);
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al ejecutar ConexionSQLServer
            System.err.println("Error al ejecutar ConexionSQLServer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
