package ejercicio.pedidosva;
import javax.swing.JOptionPane;
import ejercicio.pedidosva.InicioDeSesion;

public class FormInicioSesion extends javax.swing.JFrame implements SessionObserver{

    private FormMenuPrincipal menuPrincipal;
    private InicioDeSesion inicioSesion;

    public FormInicioSesion(FormMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        this.inicioSesion = InicioDeSesion.obtenerInstancia();
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(this);
        // Registrar este formulario como un observador en InicioDeSesion
        this.inicioSesion.agregarObservador(this);

        // Mensajes de depuración
        System.out.println("Instancia de FormMenuPrincipal recibida: " + menuPrincipal);
        System.out.println("Instancia de InicioDeSesion recibida: " + inicioSesion);

    }
@Override
    public void onSesionIniciada(int idUsuario) {
       // Por ejemplo, podrías mostrar un mensaje de bienvenida o realizar alguna acción específica
    JOptionPane.showMessageDialog(this, "¡Sesión iniciada! Bienvenido.");

    // Cambiar el estado de algún componente de la interfaz de usuario, por ejemplo:
    menuPrincipal.actualizarEstadoSesion(true);
    }

    @Override
    public void onSesionCerrada() {
         // Por ejemplo, podrías mostrar un mensaje de despedida o realizar alguna acción específica
    JOptionPane.showMessageDialog(this, "¡Sesión cerrada! Hasta luego.");

    // Cambiar el estado de algún componente de la interfaz de usuario, por ejemplo:
    menuPrincipal.actualizarEstadoSesion(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        lblCrearCuenta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 449, 110, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Inicio de sesión");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 50));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("E-mail:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 170, -1));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Contraseña:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 190, -1));

        btnIniciarSesion.setBackground(new java.awt.Color(51, 153, 255));
        btnIniciarSesion.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Iniciar Sesion");
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        jPanel3.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 200, 60));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Olvidé mi contraseña");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 120, -1));

        txtCorreo.setBackground(new java.awt.Color(0, 0, 0));
        txtCorreo.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(255, 255, 255));
        txtCorreo.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 350, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 90, -1));

        txtContrasena.setBackground(new java.awt.Color(0, 0, 0));
        txtContrasena.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        txtContrasena.setForeground(new java.awt.Color(255, 255, 255));
        txtContrasena.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 350, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 390, 420));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("¿No tienes cuenta?");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, 130, -1));

        lblCrearCuenta.setBackground(new java.awt.Color(255, 255, 255));
        lblCrearCuenta.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lblCrearCuenta.setForeground(new java.awt.Color(255, 255, 51));
        lblCrearCuenta.setText("Crea una aquí");
        lblCrearCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCrearCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCrearCuentaMouseClicked(evt);
            }
        });
        jPanel1.add(lblCrearCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, 100, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        menuPrincipal.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        // Obtener el correo y la contraseña ingresados por el usuario
        String correo = txtCorreo.getText();
        String contrasena = new String(txtContrasena.getPassword());

        // Verificar que ambos campos no estén vacíos
        if (!correo.isEmpty() && !contrasena.isEmpty()) {
            // Verificar las credenciales del usuario
            if (GestorUsuarios.verificarCredenciales(correo, contrasena)) {
                // Obtener el ID del usuario
                int idUsuario = GestorUsuarios.obtenerIdUsuario(correo);

                // Obtener el nombre del usuario
                String nombreUsuario = GestorUsuarios.obtenerNombreUsuario(idUsuario);

                // Verificar que se haya obtenido el nombre del usuario
                if (nombreUsuario != null && !nombreUsuario.isEmpty()) {
                    // Establecer el nombre del usuario en el JLabel del menú principal
                    menuPrincipal.getlblNombreUsuario().setText(nombreUsuario);
                    menuPrincipal.getlblNombreUsuario().setVisible(true);
                    // Iniciar sesión con el ID del usuario
                    inicioSesion.iniciarSesion(idUsuario);

                    // Mostrar mensaje de bienvenida al usuario
                    JOptionPane.showMessageDialog(null, "¡Bienvenido, " + nombreUsuario + "!");

                    // Mostrar el menú principal
                    menuPrincipal.setEnabled(true);
                    // Cerrar el formulario de inicio de sesión
                    this.dispose();

                } else {
                    // Mostrar un mensaje de error si no se pudo obtener el nombre del usuario
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el nombre del usuario.");
                }
            } else {
                // Mostrar un mensaje de error si las credenciales son incorrectas
                JOptionPane.showMessageDialog(null, "Correo electrónico o contraseña incorrectos.");
            }
        } else {
            // Mostrar un mensaje de error si alguno de los campos está vacío
            JOptionPane.showMessageDialog(null, "Por favor, ingresa tu correo electrónico y contraseña.");
        }
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void lblCrearCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearCuentaMouseClicked
        FormCrearCuenta formCrearCuenta = new FormCrearCuenta(menuPrincipal);
        formCrearCuenta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblCrearCuentaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCrearCuenta;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
