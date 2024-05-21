
package ejercicio.pedidosva;


public class UsuarioActual {
    private static int idUsuario;
    private static String nombreUsuario;

    // Constructor privado para evitar instanciación de la clase
    private UsuarioActual() {}

    // Métodos para acceder y modificar el ID del usuario
    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int id) {
        idUsuario = id;
    }

    // Métodos para acceder y modificar el nombre del usuario
    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombre) {
        nombreUsuario = nombre;
    }
    
    // Método para cerrar sesión
    public static void cerrarSesion() {
        // Limpiar las variables de sesión
        idUsuario = -1;
        nombreUsuario = null;
    }
}


/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 4 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/