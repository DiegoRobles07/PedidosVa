package ejercicio.pedidosva;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
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

    public FormAdministrador() {
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        cargarDatosPedidos();
        btnVerPedidos.setEnabled(false);
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
            jTable1.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Aquí puedes añadir un manejo de errores más avanzado
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

    private void actualizarDatosCliente(int idCliente) {
        Connection con = ConexionBD.establecerConexion();
        if (con != null) {
            try {
                String query = "UPDATE clientes SET nombre = ?, apellido = ?, correo = ?, telefono = ?, direccion = ?, contrasena = ? WHERE id_cliente = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, txtNombreCliente.getText());
                ps.setString(2, jTextPane2.getText());
                ps.setString(3, txtEmailCliente.getText());
                ps.setString(4, jTextPane3.getText());
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
                    jTextPane2.setText(rs.getString("apellido"));
                    jTextPane3.setText(rs.getString("telefono"));
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

                jTable1.setModel(modelo);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar los datos de clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                ConexionBD.cerrarConexion(con);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: no se pudo establecer conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
    private void limpiarCamposCliente() {
        txtNombreCliente.setText("");
        jTextPane2.setText("");
        txtEmailCliente.setText("");
        jTextPane3.setText("");
        txtDireccionCliente.setText("");
        txtContrasenaCliente.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnVerClientes = new javax.swing.JButton();
        btnVerPedidos = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNombreCliente = new javax.swing.JTextPane();
        txtApellidoCliente = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        txtTelefonoCliente = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtEmailCliente = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtDireccionCliente = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtContrasenaCliente = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnActualizarCliente = new javax.swing.JButton();
        btnGenerarReporte = new javax.swing.JButton();
        btnCerrarPanel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jTable1.setBackground(new java.awt.Color(51, 51, 51));
        jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setOpaque(false);
        jTable1.setSelectionBackground(new java.awt.Color(255, 0, 0));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 1060, 380));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Panel de Administración");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(249, 249, 249))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1060, 70));

        btnVerClientes.setBackground(new java.awt.Color(51, 51, 255));
        btnVerClientes.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        btnVerClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnVerClientes.setText("Ver Clientes");
        btnVerClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerClientes.setFocusable(false);
        btnVerClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerClientesActionPerformed(evt);
            }
        });
        jPanel2.add(btnVerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 220, 30));

        btnVerPedidos.setBackground(new java.awt.Color(51, 51, 255));
        btnVerPedidos.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        btnVerPedidos.setForeground(new java.awt.Color(255, 255, 255));
        btnVerPedidos.setText("Ver Pedidos");
        btnVerPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerPedidos.setFocusable(false);
        btnVerPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPedidosActionPerformed(evt);
            }
        });
        jPanel2.add(btnVerPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 220, 30));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        txtNombreCliente.setFocusable(false);
        jScrollPane2.setViewportView(txtNombreCliente);

        jTextPane2.setFocusable(false);
        txtApellidoCliente.setViewportView(jTextPane2);

        jTextPane3.setFocusable(false);
        txtTelefonoCliente.setViewportView(jTextPane3);

        txtEmailCliente.setFocusable(false);
        jScrollPane5.setViewportView(txtEmailCliente);

        txtDireccionCliente.setFocusable(false);
        jScrollPane6.setViewportView(txtDireccionCliente);

        jScrollPane7.setFocusable(false);
        jScrollPane7.setViewportView(txtContrasenaCliente);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Télefono");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contraseña");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Dirección");

        btnActualizarCliente.setText("Actualizar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnActualizarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addComponent(jScrollPane7))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane6))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 820, 170));

        btnGenerarReporte.setBackground(new java.awt.Color(51, 51, 255));
        btnGenerarReporte.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        btnGenerarReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarReporte.setFocusable(false);
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });
        jPanel2.add(btnGenerarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 220, 30));

        btnCerrarPanel.setBackground(new java.awt.Color(51, 51, 255));
        btnCerrarPanel.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        btnCerrarPanel.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarPanel.setText("Cerrar Panel");
        btnCerrarPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarPanel.setFocusable(false);
        btnCerrarPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarPanelActionPerformed(evt);
            }
        });
        jPanel2.add(btnCerrarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 220, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarPanelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarPanelActionPerformed

    private void btnVerClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerClientesActionPerformed
        cargarDatosClientes();
        limpiarCamposCliente();
        btnVerPedidos.setEnabled(true);
        btnVerClientes.setEnabled(false);
    }//GEN-LAST:event_btnVerClientesActionPerformed

    private void btnVerPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPedidosActionPerformed
        cargarDatosPedidos();
        limpiarCamposCliente();
        btnVerPedidos.setEnabled(false);
        btnVerClientes.setEnabled(true);
    }//GEN-LAST:event_btnVerPedidosActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        generarReportePDF();
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            int idCliente = (int) jTable1.getValueAt(row, 0); // Suponiendo que la primera columna es el ID del cliente
            cargarDatosClienteSeleccionado(idCliente);
        }
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarCliente;
    private javax.swing.JButton btnCerrarPanel;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnVerClientes;
    private javax.swing.JButton btnVerPedidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JScrollPane txtApellidoCliente;
    private javax.swing.JTextPane txtContrasenaCliente;
    private javax.swing.JTextPane txtDireccionCliente;
    private javax.swing.JTextPane txtEmailCliente;
    private javax.swing.JTextPane txtNombreCliente;
    private javax.swing.JScrollPane txtTelefonoCliente;
    // End of variables declaration//GEN-END:variables
}
