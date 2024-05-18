package ejercicio.pedidosva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorPedidos {

    public static int insertarPedido(int idCliente, Date fechaPedido, String estado) {
        Connection conexion = ConexionBD.establecerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idPedido = -1;

        try {
            // Insertar el pedido
            String query = "INSERT INTO Pedidos (id_cliente, fecha_pedido, estado) VALUES (?, ?, ?)";
            ps = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idCliente);
            ps.setDate(2, new java.sql.Date(fechaPedido.getTime())); // Convertir Date a java.sql.Date
            ps.setString(3, estado);

            // Imprimir mensaje de depuración para verificar la consulta SQL
            System.out.println("Query para insertar pedido: " + ps.toString());

            ps.executeUpdate();

            // Obtener el ID del pedido recién insertado
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idPedido = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion(conexion);
        }

        // Imprimir mensaje de depuración para verificar el ID del pedido
        System.out.println("ID del pedido insertado: " + idPedido);

        return idPedido;
    }

    public static void insertarDetallesPedido(int idPedido, int idProducto, int cantidad) {
        Connection conexion = ConexionBD.establecerConexion();
        PreparedStatement ps = null;

        try {
            // Insertar los detalles del pedido
            String query = "INSERT INTO DetallesPedido (id_pedido, id_producto, cantidad) VALUES (?, ?, ?)";
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idPedido);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);

            // Imprimir mensaje de depuración para verificar la consulta SQL
            System.out.println("Query para insertar detalles del pedido: " + ps.toString());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion(conexion);
        }
    }
public static boolean guardarPedido(int idCliente, Date fechaPedido, String estadoPedido, double precioTotal, String tipoPago, String direccion, List<Producto> productos) {
        Connection connection = null;
        PreparedStatement psPedido = null;

        try {
            // Obtener la conexión a la base de datos
            connection = ConexionBD.establecerConexion();
            connection.setAutoCommit(false); // Iniciar transacción

            // Convertir la lista de productos a una cadena de texto
            StringBuilder productosBuilder = new StringBuilder();
            for (Producto producto : productos) {
                productosBuilder.append("Producto: ").append(producto.getNombre())
                        .append(", Cantidad: ").append(producto.getCantidad())
                        .append(", Precio: ").append(producto.getPrecioUnitario())
                        .append("\n");
            }
            String productosStr = productosBuilder.toString();

            // Insertar el pedido en la tabla PedidosDetalles
            String sqlPedido = "INSERT INTO PedidosDetalles (idCliente, fechaPedido, estadoPedido, precioTotal, tipoPago, direccion, productos) VALUES (?, ?, ?, ?, ?, ?, ?)";
            psPedido = connection.prepareStatement(sqlPedido);
            psPedido.setInt(1, idCliente);
            psPedido.setDate(2, new java.sql.Date(fechaPedido.getTime()));
            psPedido.setString(3, estadoPedido);
            psPedido.setDouble(4, precioTotal);
            psPedido.setString(5, tipoPago);
            psPedido.setString(6, direccion);
            psPedido.setString(7, productosStr);

            int filasAfectadas = psPedido.executeUpdate();
            if (filasAfectadas == 0) {
                connection.rollback();
                return false; // Error al insertar el pedido
            }

            connection.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (psPedido != null) {
                    psPedido.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void guardarProducto(int idPedido, Producto producto, int cantidad) {
    // Llama al método insertarDetallesPedido para insertar el producto en la base de datos
    insertarDetallesPedido(idPedido, producto.getId(), cantidad);
    // Imprimir mensaje de depuración para verificar si la función está funcionando correctamente
    System.out.println("Producto guardado con éxito en la base de datos.");
}

    public static int obtenerUltimoIdPedido() {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ultimoIdPedido = 0;

        try {
            conexion = ConexionBD.establecerConexion();

            String query = "SELECT MAX(id_pedido) AS ultimo_id FROM Pedidos";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                ultimoIdPedido = rs.getInt("ultimo_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
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
        
        // Imprimir mensaje de depuración para verificar el último ID de pedido obtenido
        System.out.println("Último ID de pedido obtenido: " + ultimoIdPedido);

        return ultimoIdPedido;
    }
   public static void insertarDetallesPedidoNuevo(int idPedido, int idProducto, int cantidad) {
    Connection conexion = ConexionBD.establecerConexion();
    PreparedStatement ps = null;

    try {
        // Obtener el precio unitario del producto desde la base de datos
        double precioUnitario = obtenerPrecioUnitarioProducto(idProducto); // Reemplaza esta línea con la forma de obtener el precio unitario del producto desde la base de datos

        // Calcular el precio total por producto
        double subtotal = precioUnitario * cantidad;

        // Insertar los detalles del pedido en la nueva tabla Detalles_Pedido_Nuevo
        String query = "INSERT INTO Detalles_Pedido_Nuevo (id_pedido, id_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        ps = conexion.prepareStatement(query);
        ps.setInt(1, idPedido);
        ps.setInt(2, idProducto);
        ps.setInt(3, cantidad);
        ps.setDouble(4, subtotal); // Agregar el precio total por producto al statement

        // Imprimir mensaje de depuración para verificar la consulta SQL
        System.out.println("Query para insertar detalles del pedido (nueva tabla): " + ps.toString());

        ps.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        ConexionBD.cerrarConexion(conexion);
    }
}

public static double obtenerPrecioUnitarioProducto(int idProducto) throws SQLException {
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    double precioUnitario = 0.0;

    try {
        conexion = ConexionBD.establecerConexion();
        String query = "SELECT precio FROM <nombre_tabla> WHERE <condicion>";
        ps = conexion.prepareStatement(query);
        ps.setInt(1, idProducto);
        rs = ps.executeQuery();

        if (rs.next()) {
            precioUnitario = rs.getDouble("precio");
        }
    } finally {
        // Cerrar recursos
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        ConexionBD.cerrarConexion(conexion);
    }

    return precioUnitario;
}

 public static List<String> obtenerTiposDePago() {
        List<String> tiposDePago = new ArrayList<>();
        Connection conexion = ConexionBD.establecerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT descripcion FROM tipo_de_pago";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                tiposDePago.add(rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
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
            ConexionBD.cerrarConexion(conexion);
        }

        return tiposDePago;
    }

}
