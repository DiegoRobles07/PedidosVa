package ejercicio.pedidosva;

import ejercicio.pedidosva.FormMenuPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorCombos {

 
    
public List<Desayunos> obtenerDesayunos() {
    List<Desayunos> desayunos = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conexion = ConexionBD.establecerConexion();
        String consulta = "SELECT id_desayuno, nombre, descripcion, precio, imagen FROM Desayunos";
        ps = conexion.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_desayuno");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagenRuta = rs.getString("imagen");

            Desayunos desayuno = new Desayunos(id, nombre, descripcion, precio, imagenRuta);
            desayunos.add(desayuno);
        }
    } catch (SQLException e) {
        System.err.println("Error al recuperar los desayunos: " + e.getMessage());
    } finally {
        // Cerrar los recursos en el orden inverso de su apertura para evitar fugas de recursos.
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    return desayunos;
}


    public void actualizarPanelesDesayuno(FormMenuPrincipal form) {
        List<Desayunos> desayunos = obtenerDesayunos();
        int panelIndex = 0;
        for (Desayunos desayuno : desayunos) {
            if (panelIndex <= 8) {
                form.actualizarPanelDesayuno(panelIndex, desayuno.getId(), desayuno.getNombre(), desayuno.getPrecio(), desayuno.getDescripcion(), desayuno.getImagen());
                panelIndex++;
            } else {
                break;
            }
        }
    }
public List<CombosPollo> obtenerCombosPollo() {
    List<CombosPollo> combosPollo = new ArrayList<>();
    Connection conexion = ConexionBD.establecerConexion();
    String consulta = "SELECT * FROM CombosPollo";
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        ps = conexion.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_combo_pollo");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagen = rs.getString("imagen");

            CombosPollo comboPollo = new CombosPollo(id, nombre, descripcion, precio, imagen);
            combosPollo.add(comboPollo);
        }

    } catch (SQLException e) {
        System.err.println("Error al recuperar los combos de pollo: " + e.getMessage());
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    return combosPollo;
}

public void actualizarPanelesCombosPollo(FormMenuPrincipal form) {
    List<CombosPollo> combosPollo = obtenerCombosPollo();
    int panelIndex = 0;
    for (CombosPollo comboPollo : combosPollo) {
        if (panelIndex <= 8) {
            form.actualizarPanelComboPollo(panelIndex, comboPollo.getId(), comboPollo.getNombre(), comboPollo.getPrecio(), comboPollo.getDescripcion(), comboPollo.getImagen());
            panelIndex++;
        } else {
            break;
        }
    }
}

// Repite el mismo patrón para las demás tablas (CombosCarne, Ensaladas, Individuales, Bebidas, Postres).

public List<CombosCarne> obtenerCombosCarne() {
    List<CombosCarne> combosCarne = new ArrayList<>();
    Connection conexion = ConexionBD.establecerConexion();
    String consulta = "SELECT * FROM CombosCarne";
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        ps = conexion.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_combo_carne");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagen = rs.getString("imagen");

            CombosCarne comboCarne = new CombosCarne(id, nombre, descripcion, precio, imagen);
            combosCarne.add(comboCarne);
        }

    } catch (SQLException e) {
        System.err.println("Error al recuperar los combos de carne: " + e.getMessage());
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    return combosCarne;
}

public void actualizarPanelesCombosCarne(FormMenuPrincipal form) {
    List<CombosCarne> combosCarne = obtenerCombosCarne();
    int panelIndex = 0;
    for (CombosCarne comboCarne : combosCarne) {
        if (panelIndex <= 8) {
            form.actualizarPanelComboCarne(panelIndex, comboCarne.getId(), comboCarne.getNombre(), comboCarne.getPrecio(), comboCarne.getDescripcion(), comboCarne.getImagen());
            panelIndex++;
        } else {
            break;
        }
    }
}

public List<Ensaladas> obtenerEnsaladas() {
    List<Ensaladas> ensaladas = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conexion = ConexionBD.establecerConexion();
        String consulta = "SELECT id_ensalada, nombre, descripcion, precio, imagen FROM Ensaladas";
        ps = conexion.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_ensalada");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagenRuta = rs.getString("imagen");

            Ensaladas ensalada = new Ensaladas(id, nombre, descripcion, precio, imagenRuta);
            ensaladas.add(ensalada);
        }
    } catch (SQLException e) {
        System.err.println("Error al recuperar las ensaladas: " + e.getMessage());
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    return ensaladas;
}

public void actualizarPanelesEnsaladas(FormMenuPrincipal form) {
    List<Ensaladas> ensaladas = obtenerEnsaladas();
    int panelIndex = 0;
    for (Ensaladas ensalada : ensaladas) {
        if (panelIndex <= 8) {
            form.actualizarPanelEnsalada(panelIndex, ensalada.getId(), ensalada.getNombre(), ensalada.getPrecio(), ensalada.getDescripcion(), ensalada.getImagen());
            panelIndex++;
        } else {
            break;
        }
    }
}

public List<Individuales> obtenerIndividuales() {
    List<Individuales> individuales = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conexion = ConexionBD.establecerConexion();
        String consulta = "SELECT id_individual, nombre, descripcion, precio, imagen FROM Individuales";
        ps = conexion.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_individual");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagenRuta = rs.getString("imagen");

            Individuales individual = new Individuales(id, nombre, descripcion, precio, imagenRuta);
            individuales.add(individual);
        }
    } catch (SQLException e) {
        System.err.println("Error al recuperar los individuales: " + e.getMessage());
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    return individuales;
}

public void actualizarPanelesIndividuales(FormMenuPrincipal form) {
    List<Individuales> individuales = obtenerIndividuales();
    int panelIndex = 0;
    for (Individuales individual : individuales) {
        if (panelIndex <= 8) {
            form.actualizarPanelIndividual(panelIndex, individual.getId(), individual.getNombre(), individual.getPrecio(), individual.getImagen(), individual.getDescripcion());
            panelIndex++;
        } else {
            break;
        }
    }
}
public List<Bebidas> obtenerBebidas() {
    List<Bebidas> bebidas = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conexion = ConexionBD.establecerConexion();
        String consulta = "SELECT id_bebida, nombre, descripcion, precio, imagen FROM Bebidas";
        ps = conexion.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_bebida");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagenRuta = rs.getString("imagen");

            Bebidas bebida = new Bebidas(id, nombre, descripcion, precio, imagenRuta);
            bebidas.add(bebida);
        }
    } catch (SQLException e) {
        System.err.println("Error al recuperar las bebidas: " + e.getMessage());
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    return bebidas;
}

public void actualizarPanelesBebidas(FormMenuPrincipal form) {
    List<Bebidas> bebidas = obtenerBebidas();
    int panelIndex = 0;
    for (Bebidas bebida : bebidas) {
        if (panelIndex <= 8) {
            form.actualizarPanelBebida(panelIndex, bebida.getId(), bebida.getNombre(), bebida.getPrecio(), bebida.getDescripcion(), bebida.getImagen());
            panelIndex++;
        } else {
            break;
        }
    }
}

public List<Postres> obtenerPostres() {
    List<Postres> postres = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conexion = ConexionBD.establecerConexion();
        String consulta = "SELECT id_postre, nombre, descripcion, precio, imagen FROM Postres";
        ps = conexion.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_postre");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagenRuta = rs.getString("imagen");

            Postres postre = new Postres(id, nombre, descripcion, precio, imagenRuta);
            postres.add(postre);
        }
    } catch (SQLException e) {
        System.err.println("Error al recuperar los postres: " + e.getMessage());
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar el PreparedStatement: " + e.getMessage());
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    return postres;
}

public void actualizarPanelesPostres(FormMenuPrincipal form) {
    List<Postres> postres = obtenerPostres();
    int panelIndex = 0;
    for (Postres postre : postres) {
        if (panelIndex <= 8) {
            form.actualizarPanelPostre(panelIndex, postre.getId(), postre.getNombre(), postre.getPrecio(), postre.getDescripcion(), postre.getImagen());
            panelIndex++;
        } else {
            break;
        }
    }
}

}
