package ejercicio.pedidosva;

import ejercicio.pedidosva.InicioDeSesion;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class FormConfirmarPedido extends javax.swing.JFrame implements SessionObserver {

    private Carrito carrito;
    private DefaultListModel<Producto> listModel;
    private FormMenuPrincipal formMenuPrincipal;
    private InicioDeSesion inicioSesion;

    private String formatearDireccion(String direccion, int caracteresPorLinea) {
        StringBuilder direccionFormateada = new StringBuilder();
        int caracteresActuales = 0;

        for (char c : direccion.toCharArray()) {
            direccionFormateada.append(c);
            caracteresActuales++;

            // Si se alcanza el número máximo de caracteres por línea, agregar un salto de línea
            if (caracteresActuales == caracteresPorLinea) {
                direccionFormateada.append("\n");
                caracteresActuales = 0; // Reiniciar contador de caracteres para la siguiente línea
            }
        }

        return direccionFormateada.toString();
    }

    public FormConfirmarPedido(Carrito carrito, FormMenuPrincipal formMenuPrincipal, InicioDeSesion inicioSesion) {
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        this.carrito = carrito;
        this.formMenuPrincipal = formMenuPrincipal;
        this.inicioSesion = inicioSesion;

        // Verificar si la instancia de inicioSesion se recibe correctamente
        System.out.println("Instancia de InicioDeSesion recibida en ConfirmarPedido: " + inicioSesion);

        // Verificar si hay una sesión iniciada
        if (!inicioSesion.sesionIniciada()) {
            // Si no hay una sesión iniciada, muestra un mensaje de error y finaliza el constructor
            JOptionPane.showMessageDialog(this, "Error: sesión no iniciada.");
            btnRealizarPedido.setEnabled(false);
            btnLimpiarLista.setEnabled(false);
            return;
        }

        // Si hay una sesión iniciada, continúa con la inicialización del formulario
        cargarProductosEnLista();
        cargarTiposDePago();
        // Obtener la dirección del cliente
        int idCliente = inicioSesion.getIdUsuarioActual();
        String direccionCliente = GestorUsuarios.obtenerDireccionCliente(idCliente);

// Reemplazar saltos de línea en la dirección con <br> para HTML
        direccionCliente = direccionCliente.replaceAll("\n", "<br>");

// Establecer el texto HTML en el JLabel
        lblDireccionCliente.setText("<html>" + direccionCliente + "</html>");

    }
@Override
    public void onSesionIniciada(int idUsuario) {
        // Aquí defines lo que deseas que ocurra cuando la sesión se inicie
        // Por ejemplo, podrías actualizar la interfaz o realizar otras acciones necesarias
        // También puedes agregar mensajes de depuración si lo deseas
        System.out.println("Sesión iniciada para el usuario con ID: " + idUsuario);

        // Actualizar la interfaz o realizar otras acciones necesarias
        // Por ejemplo, podrías habilitar o deshabilitar botones
    }

    @Override
    public void onSesionCerrada() {
        // Aquí defines lo que deseas que ocurra cuando la sesión se cierre
        // Por ejemplo, podrías actualizar la interfaz o realizar otras acciones necesarias
        // También puedes agregar mensajes de depuración si lo deseas
        System.out.println("Sesión cerrada");

        // Actualizar la interfaz o realizar otras acciones necesarias
        // Por ejemplo, podrías habilitar o deshabilitar botones
    }
    private void cargarProductosEnLista() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        double total = 0.0; // Inicializar el total
        DecimalFormat df = new DecimalFormat("#,##0.00"); // Formateador para dos decimales

        // Agregar cada producto a la lista
        for (Producto producto : carrito.getProductos()) {
            // Calcular el precio total del producto
            double precioTotalProducto = producto.getPrecioUnitario() * producto.getCantidad();
            total += precioTotalProducto; // Sumar al total general

            // Formatear el precio total con dos decimales
            String precioFormateado = df.format(precioTotalProducto);

            // Agregar cada producto con su nombre, cantidad y precio como una cadena de texto
            String item = producto.getNombre() + " - Cantidad: " + producto.getCantidad() + " - Precio: $" + precioFormateado;
            listModel.addElement(item);
        }

        // Asignar el listModel al jList1
        listPedido.setModel(listModel);

        // Formatear el total con dos decimales
        String totalFormateado = df.format(total);

        // Mostrar el total en el JLabel
        lblTotalPedido.setText("Total de tu orden: $" + totalFormateado);
    }

    private void cargarTiposDePago() {
        List<String> tiposDePago = GestorPedidos.obtenerTiposDePago();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        for (String tipoDePago : tiposDePago) {
            comboBoxModel.addElement(tipoDePago);
        }

        cmbTipoPago.setModel(comboBoxModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPedido = new javax.swing.JList<>();
        btnRegresarMenuPrincipal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnRealizarPedido = new javax.swing.JButton();
        btnLimpiarLista = new javax.swing.JButton();
        cmbTipoPago = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblDireccionCliente = new javax.swing.JLabel();
        lblTotalPedido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlFondo.setBackground(new java.awt.Color(255, 51, 51));
        pnlFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listPedido.setBackground(new java.awt.Color(255, 255, 255));
        listPedido.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        listPedido.setForeground(new java.awt.Color(0, 0, 0));
        listPedido.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listPedido.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listPedido.setToolTipText("Tus Combos Seleccionados");
        listPedido.setAlignmentX(1.0F);
        listPedido.setAlignmentY(1.0F);
        listPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(listPedido);

        pnlFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 460, 140));

        btnRegresarMenuPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresarMenuPrincipal.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnRegresarMenuPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresarMenuPrincipal.setText("Regresar");
        btnRegresarMenuPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresarMenuPrincipal.setFocusable(false);
        btnRegresarMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarMenuPrincipalActionPerformed(evt);
            }
        });
        pnlFondo.add(btnRegresarMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 110, 60));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Finalizar Pedido");
        pnlFondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 380, 60));

        btnRealizarPedido.setBackground(new java.awt.Color(51, 153, 255));
        btnRealizarPedido.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnRealizarPedido.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarPedido.setText("REALIZAR PEDIDO");
        btnRealizarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRealizarPedido.setFocusable(false);
        btnRealizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPedidoActionPerformed(evt);
            }
        });
        pnlFondo.add(btnRealizarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 150, 60));

        btnLimpiarLista.setBackground(new java.awt.Color(0, 0, 0));
        btnLimpiarLista.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnLimpiarLista.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarLista.setText("Limpiar");
        btnLimpiarLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiarLista.setFocusable(false);
        btnLimpiarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarListaActionPerformed(evt);
            }
        });
        pnlFondo.add(btnLimpiarLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 110, 60));

        cmbTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipoPago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlFondo.add(cmbTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 120, -1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Método de pago:");
        pnlFondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, 30));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 255, 255));
        jLabel3.setText("Dirección de Entrega:");
        pnlFondo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, 30));

        lblDireccionCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDireccionCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblDireccionCliente.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblDireccionCliente.setText("Direccion");
        lblDireccionCliente.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlFondo.add(lblDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, 20));

        lblTotalPedido.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblTotalPedido.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPedido.setText("TOTAL");
        pnlFondo.add(lblTotalPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 310, 60));

        getContentPane().add(pnlFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarMenuPrincipalActionPerformed
        formMenuPrincipal.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarMenuPrincipalActionPerformed

    private void btnRealizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPedidoActionPerformed
         int idCliente = inicioSesion.getIdUsuarioActual();

        if (idCliente == -1) {
            JOptionPane.showMessageDialog(this, "Inicia sesión para continuar.");
            return;
        }

        // Mostrar mensaje de confirmación
        int opcion = JOptionPane.showConfirmDialog(this, "¿Desea realizar el pedido?", "Confirmación de Pedido", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.NO_OPTION) {
            return; // El usuario ha seleccionado No, salir del método
        }

        Date fechaPedido = new Date();
        String estadoPedido = "REALIZADA";

        double precioTotal = calcularPrecioTotal(carrito.getProductos());
        String nombreCliente = GestorUsuarios.obtenerNombreUsuario(idCliente);

        // Obtener tipo de pago seleccionado
        String tipoPago = (String) cmbTipoPago.getSelectedItem();

        // Obtener dirección del cliente
        String direccionCliente = GestorUsuarios.obtenerDireccionCliente(idCliente);

        boolean pedidoGuardado = GestorPedidos.guardarPedido(idCliente, fechaPedido, estadoPedido, precioTotal, tipoPago, direccionCliente, carrito.getProductos());

        if (pedidoGuardado) {
            // Formatear el precio total con dos decimales
            DecimalFormat formatoPrecio = new DecimalFormat("#0.00");
            String precioTotalFormateado = formatoPrecio.format(precioTotal);

            // Formatear el contenido del correo
            StringBuilder contenidoCorreo = new StringBuilder();
            contenidoCorreo.append("Detalles del Pedido\n");
            contenidoCorreo.append("Cliente: ").append(nombreCliente).append("\n");
            contenidoCorreo.append("Fecha: ").append(fechaPedido.toString()).append("\n");
            contenidoCorreo.append("Estado: ").append(estadoPedido).append("\n");
            contenidoCorreo.append("Precio Total: $").append(precioTotalFormateado).append("\n");
            contenidoCorreo.append("Método de Pago: ").append(tipoPago).append("\n");
            contenidoCorreo.append("Dirección: ").append(direccionCliente).append("\n\n");
            contenidoCorreo.append("Este día disfrutarás de:\n\n");
            for (Producto producto : carrito.getProductos()) {
                contenidoCorreo.append("\tProducto: ").append(producto.getNombre()).append("\n");
                contenidoCorreo.append("\tCantidad: ").append(producto.getCantidad()).append("\n");
                contenidoCorreo.append("\tPrecio: ").append(producto.getPrecioUnitario()).append("\n\n");
            }
            contenidoCorreo.append("Gracias por su compra!");

            // Obtener el destinatario del correo
            String destinatario = GestorUsuarios.obtenerCorreoCliente(idCliente);

            // Enviar el correo
            String asunto = "Confirmación de Pedido";
            boolean correoEnviado = GestorCorreo.enviarCorreo(destinatario, asunto, contenidoCorreo.toString());

            if (correoEnviado) {
                JOptionPane.showMessageDialog(this, "Pedido realizado con éxito. Se ha enviado un correo de confirmación.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al enviar el correo de confirmación. Por favor, verifica tu dirección de correo.");
            }

            carrito.vaciarCarrito();
            formMenuPrincipal.setEnabled(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al realizar el pedido. Por favor, inténtalo de nuevo.");
        }
    }//GEN-LAST:event_btnRealizarPedidoActionPerformed
    private double calcularPrecioTotal(List<Producto> productos) {
        double precioTotal = 0.0;
        for (Producto producto : productos) {
            precioTotal += producto.getPrecioUnitario() * producto.getCantidad();
        }
        return precioTotal;
    }
    private void btnLimpiarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarListaActionPerformed
        carrito.vaciarCarrito();
        DefaultListModel<String> listModel = (DefaultListModel<String>) listPedido.getModel();
        listModel.clear();
    }//GEN-LAST:event_btnLimpiarListaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpiarLista;
    private javax.swing.JButton btnRealizarPedido;
    private javax.swing.JButton btnRegresarMenuPrincipal;
    private javax.swing.JComboBox<String> cmbTipoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDireccionCliente;
    private javax.swing.JLabel lblTotalPedido;
    private javax.swing.JList<String> listPedido;
    private javax.swing.JPanel pnlFondo;
    // End of variables declaration//GEN-END:variables
}
