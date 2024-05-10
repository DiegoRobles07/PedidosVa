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
            form.actualizarPanelDesayuno(panelIndex, desayuno.getNombre(), desayuno.getPrecio(), desayuno.getImagen(), desayuno.getDescripcion());
            panelIndex++;
        } else {
            break;
        }
    }
}


    // Método para obtener los combos de pollo desde la base de datos
    public List<CombosPollo> obtenerCombosPollo() {
        List<CombosPollo> combosPollo = new ArrayList<>();
        Connection conexion = ConexionBD.establecerConexion();
        String consulta = "SELECT * FROM CombosPollo";

        try (PreparedStatement ps = conexion.prepareStatement(consulta); ResultSet rs = ps.executeQuery()) {

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
            ConexionBD.cerrarConexion(conexion);
        }

        return combosPollo;
    }

public void actualizarPanelesCombosPollo(FormMenuPrincipal form) {
    List<CombosPollo> combosPollo = obtenerCombosPollo();
    int panelIndex = 0; // Empezamos desde 1 para que coincida con la lógica del método actualizarPanelesDesayuno
    for (CombosPollo comboPollo : combosPollo) {
        if (panelIndex <= 8) {
            form.actualizarPanelComboPollo(panelIndex, comboPollo.getNombre(), comboPollo.getPrecio(), comboPollo.getDescripcion(), comboPollo.getImagen());
            panelIndex++;
        } else {
            break;
        }
    }
}



    public List<CombosCarne> obtenerCombosCarne() {
        List<CombosCarne> combosCarne = new ArrayList<>();
        Connection conexion = ConexionBD.establecerConexion();
        String consulta = "SELECT * FROM CombosCarne";

        try (PreparedStatement ps = conexion.prepareStatement(consulta); ResultSet rs = ps.executeQuery()) {

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
            ConexionBD.cerrarConexion(conexion);
        }

        return combosCarne;
    }

 public void actualizarPanelesCombosCarne(FormMenuPrincipal form) {
    List<CombosCarne> combosCarne = obtenerCombosCarne();
    int panelIndex = 0; // Empezamos desde 1 para que coincida con la lógica del método actualizarPanelesDesayuno
    for (CombosCarne comboCarne : combosCarne) {
        if (panelIndex <= 8) {
            form.actualizarPanelComboCarne(panelIndex, comboCarne.getNombre(), comboCarne.getPrecio(), comboCarne.getDescripcion(), comboCarne.getImagen());
            panelIndex++;
        } else {
            break;
        }
    }
}


// Método para obtener las ensaladas desde la base de datos

    public List<Ensaladas> obtenerEnsaladas() {
        List<Ensaladas> ensaladas = new ArrayList<>();
        Connection conexion = ConexionBD.establecerConexion();
        String consulta = "SELECT * FROM Ensaladas";

        try (PreparedStatement ps = conexion.prepareStatement(consulta); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_ensalada");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String imagen = rs.getString("imagen");

                Ensaladas ensalada = new Ensaladas(id, nombre, descripcion, precio, imagen);
                ensaladas.add(ensalada);
            }

        } catch (SQLException e) {
            System.err.println("Error al recuperar las ensaladas: " + e.getMessage());
        } finally {
            ConexionBD.cerrarConexion(conexion);
        }

        return ensaladas;
    }

    public void actualizarPanelesEnsaladas(FormMenuPrincipal form) {
    List<Ensaladas> ensaladas = obtenerEnsaladas();
    int panelIndex = 0; // Empezamos desde 1 para que coincida con la lógica del método actualizarPanelesDesayuno
    for (Ensaladas ensalada : ensaladas) {
        if (panelIndex <= 8) {
            form.actualizarPanelEnsalada(panelIndex, ensalada.getNombre(), ensalada.getPrecio(), ensalada.getDescripcion(), ensalada.getImagen());
            panelIndex++;
        } else {
            break;
        }
    }
}

    // Método para obtener los individuales desde la base de datos

    public List<Individuales> obtenerIndividuales() {
        List<Individuales> individuales = new ArrayList<>();
        Connection conexion = ConexionBD.establecerConexion();
        String consulta = "SELECT * FROM Individuales";

        try (PreparedStatement ps = conexion.prepareStatement(consulta); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_individual");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String imagen = rs.getString("imagen");

                Individuales individual = new Individuales(id, nombre, descripcion, precio, imagen);
                individuales.add(individual);
            }

        } catch (SQLException e) {
            System.err.println("Error al recuperar los individuales: " + e.getMessage());
        } finally {
            ConexionBD.cerrarConexion(conexion);
        }

        return individuales;
    }

// Método para actualizar los paneles de individuales en el formulario principal
public void actualizarPanelesIndividuales(FormMenuPrincipal form) {
    List<Individuales> individuales = obtenerIndividuales();
    int panelIndex = 0; // Empezamos desde 1 para que coincida con la lógica del método actualizarPanelesDesayuno
    for (Individuales individual : individuales) {
        if (panelIndex <= 8) {
            form.actualizarPanelIndividual(panelIndex, individual.getNombre(), individual.getPrecio(), individual.getImagen(), individual.getDescripcion());
            panelIndex++;
        } else {
            break;
        }
    }
}

 // Método para obtener las bebidas desde la base de datos
    public List<Bebidas> obtenerBebidas() {
        List<Bebidas> bebidas = new ArrayList<>();
        Connection conexion = ConexionBD.establecerConexion();
        String consulta = "SELECT * FROM Bebidas";

        try (PreparedStatement ps = conexion.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_bebida");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                String imagen = rs.getString("imagen");

                Bebidas bebida = new Bebidas(id, nombre, descripcion, precio, imagen);
                bebidas.add(bebida);
            }

        } catch (SQLException e) {
            System.err.println("Error al recuperar las bebidas: " + e.getMessage());
        } finally {
            ConexionBD.cerrarConexion(conexion);
        }

        return bebidas;
    }

// Método para actualizar los paneles de bebidas en el formulario principal
public void actualizarPanelesBebidas(FormMenuPrincipal form) {
    List<Bebidas> bebidas = obtenerBebidas();
    int panelIndex = 0; // Empezamos desde 1 para que coincida con la lógica del método actualizarPanelesDesayuno
    for (Bebidas bebida : bebidas) {
        if (panelIndex <= 8) {
            form.actualizarPanelBebida(panelIndex, bebida.getNombre(), bebida.getPrecio(), bebida.getDescripcion(), bebida.getImagen());
            panelIndex++;
        } else {
            break;
        }
    }
}



    public List<Postres> obtenerPostres() {
    List<Postres> postres = new ArrayList<>();
    Connection conexion = ConexionBD.establecerConexion();
    String consulta = "SELECT * FROM Postres";

    try (PreparedStatement ps = conexion.prepareStatement(consulta);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id_postre");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            String imagen = rs.getString("imagen");

            Postres postre = new Postres(id, nombre, descripcion, precio, imagen);
            postres.add(postre);
        }

    } catch (SQLException e) {
        System.err.println("Error al recuperar los postres: " + e.getMessage());
    } finally {
        ConexionBD.cerrarConexion(conexion);
    }

    return postres;
}
public void actualizarPanelesPostres(FormMenuPrincipal form) {
    List<Postres> postres = obtenerPostres();
    int panelIndex = 0; // Empezamos desde 1 para que coincida con la lógica del método actualizarPanelesDesayuno
    for (Postres postre : postres) {
        if (panelIndex <= 8) {
            form.actualizarPanelPostre(panelIndex, postre.getNombre(), postre.getPrecio(), postre.getDescripcion(), postre.getImagen());
            panelIndex++;
        } else {
            break;
        }
    }
}



}
