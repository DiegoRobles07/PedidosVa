package ejercicio.pedidosva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GestorUsuarios {

    // Método para verificar las credenciales de inicio de sesión
    public static boolean verificarCredenciales(String correo, String contrasena) {
        Connection con = ConexionBD.establecerConexion(); // Obtener la conexión desde la clase ConexionBD

        if (con != null) { // Verificar que la conexión no sea nula
            try {
                // Consulta SQL para verificar las credenciales
                String consulta = "SELECT * FROM clientes WHERE correo = ? AND contrasena = ?";
                PreparedStatement ps = con.prepareStatement(consulta);
                ps.setString(1, correo);
                ps.setString(2, contrasena);
                ResultSet rs = ps.executeQuery();

                // Verificar si se encontraron resultados
                if (rs.next()) {
                    // Credenciales válidas
                    return true;
                } else {
                    // Credenciales inválidas
                    return false;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al verificar las credenciales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } finally {
                ConexionBD.cerrarConexion(con); // Cerrar la conexión después de utilizarla
            }
        } else {
            // La conexión es nula
            JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static String obtenerCorreoCliente(int idCliente) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String correoCliente = null;

        try {
            conexion = ConexionBD.establecerConexion();
            String query = "SELECT correo FROM Clientes WHERE id_cliente = ?";
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                correoCliente = rs.getString("correo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Cerrar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return correoCliente;
    }

  public static int obtenerIdUsuario(String correo) {
    Connection con = ConexionBD.establecerConexion(); // Obtener la conexión desde la clase ConexionBD
    PreparedStatement pst = null;
    ResultSet rs = null;
    int idUsuario = -1;

    if (con != null) { // Verificar que la conexión no sea nula
        try {
            // Consulta SQL para obtener el ID del usuario
            String sql = "SELECT id_cliente FROM clientes WHERE correo = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, correo);
            rs = pst.executeQuery();

            // Si el conjunto de resultados contiene alguna fila, obtenemos el ID del usuario
            if (rs.next()) {
                idUsuario = rs.getInt("id_cliente");
                System.out.println("ID del usuario obtenido correctamente: " + idUsuario); // Mensaje de depuración
            } else {
                System.out.println("No se encontró ningún usuario con el correo: " + correo); // Mensaje de depuración
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del usuario: " + ex.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
    }

    return idUsuario;
}

    public static String obtenerDireccionCliente(int idCliente) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String direccionCliente = null;

        try {
            conexion = ConexionBD.establecerConexion();
            String query = "SELECT direccion FROM Clientes WHERE id_cliente = ?";
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                direccionCliente = rs.getString("direccion");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Cerrar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return direccionCliente;
    }

    public static String obtenerNombreUsuario(int idUsuario) {
        Connection con = ConexionBD.establecerConexion(); // Obtener la conexión desde la clase ConexionBD
        PreparedStatement pst = null;
        ResultSet rs = null;
        String nombreUsuario = "";

        if (con != null) { // Verificar que la conexión no sea nula
            try {
                // Consulta SQL para obtener el nombre del usuario
                String sql = "SELECT nombre FROM clientes WHERE id_cliente = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, idUsuario);
                rs = pst.executeQuery();

                // Si el conjunto de resultados contiene alguna fila, obtenemos el nombre del usuario
                if (rs.next()) {
                    nombreUsuario = rs.getString("nombre");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al obtener el nombre del usuario: " + ex.getMessage());
            } finally {
                // Cerrar recursos
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return nombreUsuario;
    }

    // Método para verificar si un correo ya está registrado
    public static boolean esCorreoRegistrado(String correo) {
        Connection con = ConexionBD.establecerConexion(); // Obtener la conexión desde la clase ConexionBD
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean registrado = false;

        if (con != null) { // Verificar que la conexión no sea nula
            try {
                // Consulta SQL para verificar si el correo ya está registrado
                String sql = "SELECT * FROM clientes WHERE correo = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, correo);
                rs = pst.executeQuery();

                // Verificar si se encontraron resultados
                if (rs.next()) {
                    registrado = true;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al verificar el correo: " + ex.getMessage());
            } finally {
                // Cerrar recursos
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return registrado;
    }

    // Método para crear un nuevo usuario
    public static boolean crearUsuario(String nombre, String apellido, String correo, String telefono, String contrasena, String direccion) {
        if (esCorreoRegistrado(correo)) {
            // Si el correo ya está registrado, devolver falso
            return false;
        }

        Connection con = ConexionBD.establecerConexion(); // Obtener la conexión desde la clase ConexionBD

        if (con != null) { // Verificar que la conexión no sea nula
            try {
                // Consulta SQL para insertar un nuevo usuario
                String sql = "INSERT INTO clientes (nombre, apellido, correo, telefono, contrasena, direccion) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, correo);
                ps.setString(4, telefono);
                ps.setString(5, contrasena);
                ps.setString(6, direccion);

                // Ejecutar la inserción y verificar si se afectaron filas
                int filasAfectadas = ps.executeUpdate();
                return filasAfectadas > 0;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al crear el usuario: " + ex.getMessage());
                return false;
            } finally {
                // Cerrar la conexión después de utilizarla
                ConexionBD.cerrarConexion(con);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    // Método para cerrar sesión
    public static void cerrarSesion() {
        // Limpiar las variables de sesión
        UsuarioActual.setNombreUsuario(null);
        JOptionPane.showMessageDialog(null, "Sesión cerrada exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }
     // Nuevo método para verificar las credenciales de inicio de sesión de administradores
    public static boolean verificarCredencialesAdmin(String correo, String contrasena) {
        Connection con = ConexionBD.establecerConexion(); // Obtener la conexión desde la clase ConexionBD

        if (con != null) { // Verificar que la conexión no sea nula
            try {
                // Consulta SQL para verificar las credenciales de administradores
                String consulta = "SELECT * FROM administradores WHERE correo = ? AND contrasena = ?";
                PreparedStatement ps = con.prepareStatement(consulta);
                ps.setString(1, correo);
                ps.setString(2, contrasena);
                ResultSet rs = ps.executeQuery();

                // Verificar si se encontraron resultados
                if (rs.next()) {
                    // Credenciales válidas
                    return true;
                } else {
                    // Credenciales inválidas
                    return false;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al verificar las credenciales de administrador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } finally {
                ConexionBD.cerrarConexion(con); // Cerrar la conexión después de utilizarla
            }
        } else {
            // La conexión es nula
            JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public static boolean eliminarCliente(int idCliente) {
    Connection con = ConexionBD.establecerConexion(); // Obtener la conexión desde la clase ConexionBD

    if (con != null) { // Verificar que la conexión no sea nula
        try {
            // Consulta SQL para eliminar el cliente
            String sql = "DELETE FROM clientes WHERE id_cliente = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);

            // Ejecutar la eliminación y verificar si se afectaron filas
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + ex.getMessage());
            return false;
        } finally {
            // Cerrar la conexión después de utilizarla
            ConexionBD.cerrarConexion(con);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

public static boolean actualizarCliente(int idCliente, String nombre, String apellido, String correo, String telefono, String contrasena, String direccion) {
    Connection con = ConexionBD.establecerConexion(); // Obtener la conexión desde la clase ConexionBD

    if (con != null) { // Verificar que la conexión no sea nula
        try {
            // Consulta SQL para actualizar el cliente
            String sql = "UPDATE clientes SET nombre = ?, apellido = ?, correo = ?, telefono = ?, contrasena = ?, direccion = ? WHERE id_cliente = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setString(4, telefono);
            ps.setString(5, contrasena);
            ps.setString(6, direccion);
            ps.setInt(7, idCliente);

            // Ejecutar la actualización y verificar si se afectaron filas
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + ex.getMessage());
            return false;
        } finally {
            // Cerrar la conexión después de utilizarla
            ConexionBD.cerrarConexion(con);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
public static String obtenerTelefonoCliente(int idCliente) {
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String telefonoCliente = null;

    try {
        conexion = ConexionBD.establecerConexion();
        String query = "SELECT telefono FROM Clientes WHERE id_cliente = ?";
        ps = conexion.prepareStatement(query);
        ps.setInt(1, idCliente);
        rs = ps.executeQuery();

        if (rs.next()) {
            telefonoCliente = rs.getString("telefono");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        // Cerrar recursos
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return telefonoCliente;
}

}

/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 4 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/