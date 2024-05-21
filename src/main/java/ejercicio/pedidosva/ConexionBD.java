//clase para estableceer la conexion a la base de datos con Postgresql 16, necesario tener instalado Postgresql version 16
package ejercicio.pedidosva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


//clase conexionDB para la logica de la conexion al servidor de postgresql
public class ConexionBD {

    static Connection conectar = null;
    static String usuario = "postgres";
    static String contrasena = "dongato876";
    static String bd = "PedidosVa";
    static String ip = "localhost";
    static String puerto = "5432";
    static String cadena = "jdbc:postgresql://" + ip + ":" + puerto + "/" + bd;

    // Método estático para establecer la conexión a la base de datos
    public static Connection establecerConexion() {
        Connection conexion = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(cadena, usuario, contrasena);
           //JOptionPane.showMessageDialog(null, "Conexión Exitosa");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el controlador de base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return conexion;
    }
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }

    static Connection obtenerConexion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 4 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/
