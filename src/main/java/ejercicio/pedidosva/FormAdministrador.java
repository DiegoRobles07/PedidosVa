//Formulario para la gestión general de la aplicacion de PedidosVa con la Franquicia fictia Freddy´s Food Service sv
//hay algunas librerias para la generación de reportes pdf

package ejercicio.pedidosva;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static ejercicio.pedidosva.GestorUsuarios.actualizarCliente;
import static ejercicio.pedidosva.GestorUsuarios.eliminarCliente;
import static ejercicio.pedidosva.GestorUsuarios.obtenerIdUsuario;
import java.io.File;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormAdministrador extends javax.swing.JFrame {

    public boolean viendoClientes = true;
    
    private FormMenuPrincipal formMenuPrincipal;
    
    public FormAdministrador(FormMenuPrincipal formMenuPrincipal) {
        this.setUndecorated(true);
        this.formMenuPrincipal = formMenuPrincipal;
        initComponents();
        this.setLocationRelativeTo(null);
        cargarDatosClientes();
        viendoClientes = true;
        btnVerClientes.setEnabled(false);
        establecerFechaHora();

        // Crear un Timer que llame a establecerFechaHora() cada 1000 milisegundos
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                establecerFechaHora();
            }
        });
        timer.start();
    }

    private void establecerFechaHora() {
        // Crear un objeto de fecha
        Date fecha = new Date();

        // Crear formato para la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' yyyy");
        String fechaFormateada = formatoFecha.format(fecha);
        // Asegurarse de que la primera letra esté en mayúsculas
        fechaFormateada = Character.toUpperCase(fechaFormateada.charAt(0)) + fechaFormateada.substring(1);
        lblFecha.setText(fechaFormateada);

        // Crear formato para la hora
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaFormateada = formatoHora.format(fecha);
        lblHora.setText(horaFormateada);
    }

    private void cargarDatosPedidos() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = ConexionBD.establecerConexion();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM pedidosdetalles");

            // Crear el modelo de la tabla con todas las columnas
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID Pedido", "ID Cliente", "Fecha Pedido", "Estado Pedido", "Precio Total", "Tipo Pago", "Dirección", "Productos"}, 0);

            // Rellenar el modelo con los datos obtenidos
            while (rs.next()) {
                int idPedido = rs.getInt("idpedido");
                int idCliente = rs.getInt("idcliente");
                String fechaPedido = rs.getString("fechapedido");
                String estadoPedido = rs.getString("estadopedido");
                double precioTotal = rs.getDouble("preciototal");
                String tipoPago = rs.getString("tipopago");
                String direccion = rs.getString("direccion");
                String productos = rs.getString("productos");
                model.addRow(new Object[]{idPedido, idCliente, fechaPedido, estadoPedido, precioTotal, tipoPago, direccion, productos});
            }

            // Asignar el modelo a la tabla
            tblAdministracion.setModel(model);

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
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ConexionBD.cerrarConexion(con);
        }
    }
//funcion para actualizar datos de un cliente
    private void actualizarDatosCliente(int idCliente) {
        Connection con = ConexionBD.establecerConexion();
        if (con != null) {
            try {
                String query = "UPDATE clientes SET nombre = ?, apellido = ?, correo = ?, telefono = ?, direccion = ?, contrasena = ? WHERE id_cliente = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, txtNombreCliente.getText());
                ps.setString(2, txtApellido.getText());
                ps.setString(3, txtEmailCliente.getText());
                ps.setString(4, txtTelefono.getText());
                ps.setString(5, txtDireccionCliente.getText());
                ps.setString(6, txtContrasenaCliente.getText());
                ps.setInt(7, idCliente);

                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Datos del cliente actualizados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarDatosClientes(); // Actualizar la tabla de clientes
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar los datos del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar los datos del cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                ConexionBD.cerrarConexion(con);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//funcion para cargar los datos de un cliente seleccionado en la tabla del panel general
    private void cargarDatosClienteSeleccionado(int idCliente) {
        Connection con = ConexionBD.establecerConexion();
        if (con != null) {
            try {
                String query = "SELECT * FROM clientes WHERE id_cliente = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, idCliente);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    txtNombreCliente.setText(rs.getString("nombre"));
                    txtApellido.setText(rs.getString("apellido"));
                    txtTelefono.setText(rs.getString("telefono"));
                    txtEmailCliente.setText(rs.getString("correo"));
                    txtDireccionCliente.setText(rs.getString("direccion"));
                    txtContrasenaCliente.setText(rs.getString("contrasena"));
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar los datos del cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                ConexionBD.cerrarConexion(con);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//funcion para cargar la data de los clientes
    private void cargarDatosClientes() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Correo");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Dirección");

        Connection con = ConexionBD.establecerConexion();
        if (con != null) {
            try {
                String query = "SELECT * FROM clientes";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Object[] fila = new Object[6];
                    fila[0] = rs.getInt("id_cliente");
                    fila[1] = rs.getString("nombre");
                    fila[2] = rs.getString("apellido");
                    fila[3] = rs.getString("correo");
                    fila[4] = rs.getString("telefono");
                    fila[5] = rs.getString("direccion");
                    modelo.addRow(fila);
                }

                tblAdministracion.setModel(modelo);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar los datos de clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                ConexionBD.cerrarConexion(con);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//funcion para general pdf, simple pero efectivo
    private void generarReportePDF() {
        Document document = new Document();
        try {
            // Ruta específica proporcionada
            String filePath = "C:\\Users\\Diego\\OneDrive\\Escritorio\\reportes\\reporte_pedidos.pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Agregar nombre de la empresa
            Paragraph empresa = new Paragraph("Freddy's");
            empresa.setAlignment(Element.ALIGN_CENTER);
            document.add(empresa);

            // Agregar fecha de generación del reporte
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String fecha = sdf.format(new Date());
            Paragraph fechaGeneracion = new Paragraph("Fecha de generación: " + fecha);
            fechaGeneracion.setAlignment(Element.ALIGN_CENTER);
            document.add(fechaGeneracion);

            // Agregar descripción
            Paragraph descripcion = new Paragraph("Este reporte muestra los detalles de los pedidos.");
            descripcion.setAlignment(Element.ALIGN_CENTER);
            document.add(descripcion);

            // Agregar un espacio
            document.add(new Paragraph(" "));

            // Crear tabla PDF
            PdfPTable table = new PdfPTable(8); // número de columnas
            table.setWidthPercentage(100);

            // Agregar encabezados
            String[] encabezados = {"ID Pedido", "ID Cliente", "Fecha Pedido", "Estado Pedido", "Precio Total", "Tipo Pago", "Dirección", "Productos"};
            for (String encabezado : encabezados) {
                PdfPCell cell = new PdfPCell(new Phrase(encabezado));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            // Obtener datos de la base de datos
            Connection con = ConexionBD.establecerConexion();
            if (con != null) {
                String query = "SELECT * FROM pedidosdetalles";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    table.addCell(String.valueOf(rs.getInt("idpedido")));
                    table.addCell(String.valueOf(rs.getInt("idcliente")));
                    table.addCell(rs.getString("fechapedido"));
                    table.addCell(rs.getString("estadopedido"));
                    table.addCell(String.valueOf(rs.getDouble("preciototal")));
                    table.addCell(rs.getString("tipopago"));
                    table.addCell(rs.getString("direccion"));
                    table.addCell(rs.getString("productos"));
                }

                ConexionBD.cerrarConexion(con);
            } else {
                JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }

            document.add(table);
            document.close();

            JOptionPane.showMessageDialog(null, "Reporte generado exitosamente en " + filePath, "Información", JOptionPane.INFORMATION_MESSAGE);

        } catch (DocumentException | IOException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//funcion para limpiar los text field
    private void limpiarCamposCliente() {
        txtNombreCliente.setText("");
        txtApellido.setText("");
        txtEmailCliente.setText("");
        txtTelefono.setText("");
        txtDireccionCliente.setText("");
        txtContrasenaCliente.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdministracion = new javax.swing.JTable();
        lblInfoTabla = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnActualizarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        lblHora = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEmailCliente = new javax.swing.JTextField();
        txtContrasenaCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnCerrarPanel = new javax.swing.JButton();
        btnVerPedidos = new javax.swing.JButton();
        btnVerClientes = new javax.swing.JButton();
        btnGenerarReporte = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        tblAdministracion.setBackground(new java.awt.Color(51, 51, 51));
        tblAdministracion.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        tblAdministracion.setForeground(new java.awt.Color(255, 255, 255));
        tblAdministracion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAdministracion.setOpaque(false);
        tblAdministracion.setSelectionBackground(new java.awt.Color(255, 0, 0));
        tblAdministracion.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblAdministracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdministracionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAdministracion);

        lblInfoTabla.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblInfoTabla.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoTabla.setText("Clientes Registrados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(306, Short.MAX_VALUE)
                .addComponent(lblInfoTabla)
                .addGap(297, 297, 297))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblInfoTabla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(363, 363, 363))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 830, 290));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 44)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Panel de Administración");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/admin.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(307, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 60));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Télefono");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contraseña");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Dirección");

        btnActualizarCliente.setBackground(new java.awt.Color(255, 204, 0));
        btnActualizarCliente.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnActualizarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/update.png"))); // NOI18N
        btnActualizarCliente.setText("Actualizar Información");
        btnActualizarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setBackground(new java.awt.Color(255, 51, 51));
        btnEliminarCliente.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnEliminarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminarCliente.setText("Eliminar Cliente");
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        lblHora.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("hora");

        lblFecha.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("fecha");

        txtNombreCliente.setBackground(new java.awt.Color(51, 51, 51));
        txtNombreCliente.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreCliente.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNombreCliente.setSelectedTextColor(new java.awt.Color(102, 255, 102));
        txtNombreCliente.setSelectionColor(new java.awt.Color(255, 0, 0));

        txtApellido.setBackground(new java.awt.Color(51, 51, 51));
        txtApellido.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(255, 255, 255));
        txtApellido.setSelectedTextColor(new java.awt.Color(102, 255, 102));
        txtApellido.setSelectionColor(new java.awt.Color(255, 0, 0));

        txtTelefono.setBackground(new java.awt.Color(51, 51, 51));
        txtTelefono.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.setSelectedTextColor(new java.awt.Color(102, 255, 102));
        txtTelefono.setSelectionColor(new java.awt.Color(255, 0, 0));

        txtEmailCliente.setBackground(new java.awt.Color(51, 51, 51));
        txtEmailCliente.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        txtEmailCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtEmailCliente.setFocusable(false);
        txtEmailCliente.setSelectedTextColor(new java.awt.Color(102, 255, 102));
        txtEmailCliente.setSelectionColor(new java.awt.Color(255, 0, 0));

        txtContrasenaCliente.setBackground(new java.awt.Color(51, 51, 51));
        txtContrasenaCliente.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        txtContrasenaCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtContrasenaCliente.setSelectedTextColor(new java.awt.Color(102, 255, 102));
        txtContrasenaCliente.setSelectionColor(new java.awt.Color(255, 0, 0));
        txtContrasenaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaClienteActionPerformed(evt);
            }
        });

        txtDireccionCliente.setBackground(new java.awt.Color(51, 51, 51));
        txtDireccionCliente.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        txtDireccionCliente.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccionCliente.setSelectedTextColor(new java.awt.Color(102, 255, 102));
        txtDireccionCliente.setSelectionColor(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(btnActualizarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombreCliente)
                    .addComponent(txtContrasenaCliente))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(120, 120, 120))
                            .addComponent(txtApellido))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel4))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtEmailCliente)))
                    .addComponent(jLabel7)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnEliminarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDireccionCliente))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(40, 40, 40)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtContrasenaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHora)
                    .addComponent(lblFecha)
                    .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 830, 210));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCerrarPanel.setBackground(new java.awt.Color(51, 51, 255));
        btnCerrarPanel.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        btnCerrarPanel.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarPanel.setText("Cerrar Panel");
        btnCerrarPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarPanel.setFocusable(false);
        btnCerrarPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarPanelActionPerformed(evt);
            }
        });
        jPanel5.add(btnCerrarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 180, 58));

        btnVerPedidos.setBackground(new java.awt.Color(51, 51, 255));
        btnVerPedidos.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        btnVerPedidos.setForeground(new java.awt.Color(255, 255, 255));
        btnVerPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pedidos.png"))); // NOI18N
        btnVerPedidos.setText("Ver Pedidos");
        btnVerPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerPedidos.setFocusable(false);
        btnVerPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPedidosActionPerformed(evt);
            }
        });
        jPanel5.add(btnVerPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 220, 60));

        btnVerClientes.setBackground(new java.awt.Color(51, 51, 255));
        btnVerClientes.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        btnVerClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnVerClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cliente.png"))); // NOI18N
        btnVerClientes.setText("Ver Clientes");
        btnVerClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerClientes.setFocusable(false);
        btnVerClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerClientesActionPerformed(evt);
            }
        });
        jPanel5.add(btnVerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 220, 60));

        btnGenerarReporte.setBackground(new java.awt.Color(51, 51, 255));
        btnGenerarReporte.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        btnGenerarReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icoPDF.png"))); // NOI18N
        btnGenerarReporte.setText("Reporte PDF");
        btnGenerarReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarReporte.setFocusable(false);
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });
        jPanel5.add(btnGenerarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 220, 60));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Opciones");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 120, 40));

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Abrir App");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 180, 60));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, 510));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblAdministracionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdministracionMouseClicked
        int row = tblAdministracion.getSelectedRow();
        if (row != -1) {
            int idCliente = (int) tblAdministracion.getValueAt(row, 0); // Suponiendo que la primera columna es el ID del cliente

            // Verificar si se están viendo clientes antes de cargar datos de cliente
            if (viendoClientes) {
                // Llamar a cargarDatosClienteSeleccionado solo si se están viendo clientes
                cargarDatosClienteSeleccionado(idCliente);
            }
        }
    }//GEN-LAST:event_tblAdministracionMouseClicked

    private void btnActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClienteActionPerformed
        String correoCliente = txtEmailCliente.getText();
    int idClienteAActualizar = obtenerIdUsuario(correoCliente);
    if (idClienteAActualizar != -1) {
        System.out.println("ID del cliente a actualizar: " + idClienteAActualizar);
        
        String nombre = txtNombreCliente.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccionCliente.getText();
        String contrasena = txtContrasenaCliente.getText();

        System.out.println("Datos del cliente a actualizar:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Correo: " + correoCliente);
        System.out.println("Dirección: " + direccion);
        // No imprimimos la contraseña por motivos de seguridad

        if (actualizarCliente(idClienteAActualizar, nombre, apellido, correoCliente, telefono, contrasena, direccion)) {
            JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente.");
        }
    } else {
        System.out.println("No se pudo obtener el ID del cliente.");
    }
    }//GEN-LAST:event_btnActualizarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        int idClienteAEliminar = obtenerIdUsuario(txtEmailCliente.getText());
        if (idClienteAEliminar != -1) {
            System.out.println("ID del cliente a eliminar: " + idClienteAEliminar);
            if (eliminarCliente(idClienteAEliminar)) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
                limpiarCamposCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el cliente.");
            }
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void txtContrasenaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaClienteActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        limpiarCamposCliente();
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        limpiarCamposCliente();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        limpiarCamposCliente();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        limpiarCamposCliente();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        generarReportePDF();
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void btnVerClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerClientesActionPerformed
        viendoClientes = true;
        cargarDatosClientes();
        limpiarCamposCliente();
        btnVerPedidos.setEnabled(true);
        btnVerClientes.setEnabled(false);
        lblInfoTabla.setText("Clientes Registrados");
    }//GEN-LAST:event_btnVerClientesActionPerformed

    private void btnVerPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPedidosActionPerformed
        viendoClientes = false;
        cargarDatosPedidos();
        limpiarCamposCliente();
        btnVerPedidos.setEnabled(false);
        btnVerClientes.setEnabled(true);
        lblInfoTabla.setText("Pedidos Registrados");
    }//GEN-LAST:event_btnVerPedidosActionPerformed

    private void btnCerrarPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarPanelActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_btnCerrarPanelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        formMenuPrincipal.setVisible(true);
        formMenuPrincipal.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarCliente;
    private javax.swing.JButton btnCerrarPanel;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnVerClientes;
    private javax.swing.JButton btnVerPedidos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblInfoTabla;
    private javax.swing.JTable tblAdministracion;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtContrasenaCliente;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtEmailCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}


/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 4 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/