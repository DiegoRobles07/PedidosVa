package ejercicio.pedidosva;
import ejercicio.pedidosva.Producto;
import ejercicio.pedidosva.GestorPedidos;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FormAgregarAlCarrito extends javax.swing.JFrame {

    private String descripcion;
    private String imagenPath;
    private FormMenuPrincipal formMenuPrincipal;
    private Carrito carrito;
    private int cantidadCombo = 1;
    private String categoriaSeleccionada;

    public int getCantidadCombo() {
        return cantidadCombo;
    }

    public FormAgregarAlCarrito(FormMenuPrincipal formMenuPrincipal, Carrito carrito, String categoriaSeleccionada) {
        this.formMenuPrincipal = formMenuPrincipal;
        this.carrito = carrito;
        System.out.println("Carrito recibido en FormAgregarAlCarrito: " + carrito);

        this.categoriaSeleccionada = categoriaSeleccionada;
        this.setUndecorated(true); // Establecer undecorated a true
        initComponents();
        id_Combo.setVisible(false);
    }

    public Carrito getCarrito() {
        return this.carrito;
    }

    public int obtenerIdClienteActual() {
        // prubeas...
        int idClienteActual = 1; // ID de cliente predeterminado
        return idClienteActual; // Suponiendo que idClienteActual es la variable que contiene el ID del cliente actual
    }

  public void actualizarCombo(int id, String nombre, double precio, String descripcion, String imagenPath) {
    this.descripcion = descripcion;
    this.imagenPath = imagenPath;
    lblNombreCombo.setText(nombre);
    lblDescripcionCombo.setText(descripcion); // Actualiza la descripción
    
    // Formatear el precio con dos decimales
    DecimalFormat formatoPrecio = new DecimalFormat("#0.00");
    String precioFormateado = formatoPrecio.format(precio);
    lblPrecioCombo.setText("$" + precioFormateado); // Actualiza el precio
    
    id_Combo.setText(Integer.toString(id)); // Actualiza el ID del combo

    // Intentar cargar la imagen
    try {
        File imagenFile = new File(imagenPath);
        if (imagenFile.exists()) {
            BufferedImage imagen = ImageIO.read(imagenFile);
            if (imagen != null) {
                // Escalar la imagen para que se ajuste al tamaño del JLabel
                ImageIcon icon = new ImageIcon(imagen.getScaledInstance(lblImagenCombo.getWidth(), lblImagenCombo.getHeight(), Image.SCALE_SMOOTH));
                lblImagenCombo.setIcon(icon);
            } else {
                // Mostrar un mensaje de error si la imagen no se pudo cargar
                System.err.println("Error al cargar la imagen del combo");
            }
        } else {
            // Mostrar un mensaje de error si la imagen no se encuentra
            System.err.println("Imagen no encontrada para el combo");
        }
    } catch (IOException e) {
        // Capturar y manejar cualquier excepción de E/S
        e.printStackTrace();
        System.err.println("Error al cargar la imagen del combo");
    }
}


    // Método para incrementar la cantidad del combo
    private void incrementarCantidad() {
        cantidadCombo++;
        lblCantidad.setText(Integer.toString(cantidadCombo));
    }

    // Método para decrementar la cantidad del combo
    private void decrementarCantidad() {
        if (cantidadCombo > 1) {
            cantidadCombo--;
            lblCantidad.setText(Integer.toString(cantidadCombo));
        }
    }

    public void setFormMenuPrincipal(FormMenuPrincipal formMenuPrincipal, Carrito carrito) {
        this.formMenuPrincipal = formMenuPrincipal;
        this.carrito = carrito;
        this.setLocationRelativeTo(null); // Centrar en la pantalla
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblDescripcionCombo = new javax.swing.JLabel();
        lblImagenCombo = new javax.swing.JLabel();
        btnAgregarAlCarrito = new javax.swing.JButton();
        btnIncrementar = new javax.swing.JButton();
        btnDecrementar = new javax.swing.JButton();
        lblCantidad = new javax.swing.JLabel();
        lblNombreCombo = new javax.swing.JLabel();
        lblPrecioCombo = new javax.swing.JLabel();
        btnRegresarMenuPrincipal = new javax.swing.JButton();
        id_Combo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDescripcionCombo.setBackground(new java.awt.Color(0, 0, 0));
        lblDescripcionCombo.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        lblDescripcionCombo.setForeground(new java.awt.Color(0, 0, 0));
        lblDescripcionCombo.setText("Aqui se vera la descripción");
        lblDescripcionCombo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(lblDescripcionCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 230, 40));

        lblImagenCombo.setForeground(new java.awt.Color(0, 0, 0));
        lblImagenCombo.setText("imagen Combo");
        lblImagenCombo.setToolTipText("Imagen combo");
        jPanel2.add(lblImagenCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 250, 140));

        btnAgregarAlCarrito.setBackground(new java.awt.Color(51, 51, 255));
        btnAgregarAlCarrito.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        btnAgregarAlCarrito.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarAlCarrito.setText("Añadir al Carrito");
        btnAgregarAlCarrito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarAlCarrito.setFocusable(false);
        btnAgregarAlCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAlCarritoActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarAlCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 220, 70));

        btnIncrementar.setBackground(new java.awt.Color(0, 255, 0));
        btnIncrementar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnIncrementar.setForeground(new java.awt.Color(0, 0, 0));
        btnIncrementar.setText("+");
        btnIncrementar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIncrementar.setFocusable(false);
        btnIncrementar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncrementarActionPerformed(evt);
            }
        });
        jPanel2.add(btnIncrementar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 70, 60));

        btnDecrementar.setBackground(new java.awt.Color(204, 0, 0));
        btnDecrementar.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnDecrementar.setForeground(new java.awt.Color(0, 0, 0));
        btnDecrementar.setText("-");
        btnDecrementar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDecrementar.setFocusable(false);
        btnDecrementar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecrementarActionPerformed(evt);
            }
        });
        jPanel2.add(btnDecrementar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 70, 60));

        lblCantidad.setFont(new java.awt.Font("Trebuchet MS", 1, 30)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(0, 0, 0));
        lblCantidad.setText("1");
        jPanel2.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 50, -1));

        lblNombreCombo.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        lblNombreCombo.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreCombo.setText("jLabel1");
        jPanel2.add(lblNombreCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 60));

        lblPrecioCombo.setBackground(new java.awt.Color(255, 0, 0));
        lblPrecioCombo.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        lblPrecioCombo.setForeground(new java.awt.Color(255, 0, 51));
        lblPrecioCombo.setText("precio");
        jPanel2.add(lblPrecioCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 160, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 500, 300));

        btnRegresarMenuPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresarMenuPrincipal.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnRegresarMenuPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresarMenuPrincipal.setText("Cancelar");
        btnRegresarMenuPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresarMenuPrincipal.setFocusable(false);
        btnRegresarMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarMenuPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresarMenuPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 140, 40));

        id_Combo.setText("id");
        jPanel1.add(id_Combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, -1, -1));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("¡Qué buena elección!");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -4, -1, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncrementarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncrementarActionPerformed
        incrementarCantidad();
    }//GEN-LAST:event_btnIncrementarActionPerformed

    private void btnDecrementarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecrementarActionPerformed
        decrementarCantidad();
    }//GEN-LAST:event_btnDecrementarActionPerformed

    private void btnRegresarMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarMenuPrincipalActionPerformed

        // Volver a activar el formulario principal
        formMenuPrincipal.setEnabled(true);
        // Cerrar el formulario actual
        this.dispose();
    }//GEN-LAST:event_btnRegresarMenuPrincipalActionPerformed

    private void btnAgregarAlCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAlCarritoActionPerformed
     // Obtener los detalles del combo desde la interfaz gráfica
    int id = Integer.parseInt(id_Combo.getText());
    String nombre = lblNombreCombo.getText();
    double precio = Double.parseDouble(lblPrecioCombo.getText().replace("$", ""));
    int cantidad = Integer.parseInt(lblCantidad.getText());

    // Agregar mensajes de depuración para verificar los valores obtenidos
    System.out.println("Detalles del combo:");
    System.out.println("ID: " + id);
    System.out.println("Nombre: " + nombre);
    System.out.println("Precio: " + precio);
    System.out.println("Cantidad: " + cantidad);

    // Crear un objeto Producto correspondiente al combo seleccionado
    Producto producto = new Producto(id, nombre, cantidad, precio);

    // Agregar el producto al carrito
    carrito.agregarProducto(producto);

    // Mostrar mensaje de éxito
    JOptionPane.showMessageDialog(this, "Producto agregado al carrito");

    // Cerrar la ventana de agregar al carrito
    this.dispose();

    // Mostrar el formulario principal
    formMenuPrincipal.setEnabled(true);
    formMenuPrincipal.setVisible(true);

    // Agregar mensajes de depuración para verificar si el producto se agregó correctamente al carrito
    System.out.println("Producto agregado al carrito correctamente: " + producto.getNombre());
    System.out.println("Cantidad de productos en el carrito: " + carrito.getProductos().size());
    }//GEN-LAST:event_btnAgregarAlCarritoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarAlCarrito;
    private javax.swing.JButton btnDecrementar;
    private javax.swing.JButton btnIncrementar;
    private javax.swing.JButton btnRegresarMenuPrincipal;
    private javax.swing.JLabel id_Combo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblDescripcionCombo;
    private javax.swing.JLabel lblImagenCombo;
    private javax.swing.JLabel lblNombreCombo;
    private javax.swing.JLabel lblPrecioCombo;
    // End of variables declaration//GEN-END:variables
}


/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 4 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/