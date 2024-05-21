/*formulario para la de una cuenta de un cliente*/
package ejercicio.pedidosva;

import javax.swing.JOptionPane;

public class FormCrearCuenta extends javax.swing.JFrame {

    private FormMenuPrincipal formMenuPrincipal;

    public FormCrearCuenta(FormMenuPrincipal formMenuPrincipal) {
        this.formMenuPrincipal = formMenuPrincipal;
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 110, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Apellido");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 130, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Email");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 80, -1));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Télefono");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 140, -1));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Direccion");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 130, -1));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Contraseña");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 130, -1));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("(Recuerda ingresar los datos precisos para la entrega de tu pedido)");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        txtApellido.setBackground(new java.awt.Color(0, 0, 0));
        txtApellido.setFont(new java.awt.Font("Trebuchet MS", 1, 21)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(255, 255, 255));
        txtApellido.setBorder(new javax.swing.border.MatteBorder(null));
        txtApellido.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 230, 40));

        txtDireccion.setBackground(new java.awt.Color(0, 0, 0));
        txtDireccion.setFont(new java.awt.Font("Trebuchet MS", 1, 21)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(255, 255, 255));
        txtDireccion.setBorder(new javax.swing.border.MatteBorder(null));
        txtDireccion.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 440, 40));

        txtNombre.setBackground(new java.awt.Color(0, 0, 0));
        txtNombre.setFont(new java.awt.Font("Trebuchet MS", 1, 21)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.setBorder(new javax.swing.border.MatteBorder(null));
        txtNombre.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 230, 40));

        txtEmail.setBackground(new java.awt.Color(0, 0, 0));
        txtEmail.setFont(new java.awt.Font("Trebuchet MS", 1, 21)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setBorder(new javax.swing.border.MatteBorder(null));
        txtEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 230, 40));

        txtTelefono.setBackground(new java.awt.Color(0, 0, 0));
        txtTelefono.setFont(new java.awt.Font("Trebuchet MS", 1, 21)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.setBorder(new javax.swing.border.MatteBorder(null));
        txtTelefono.setCaretColor(new java.awt.Color(255, 255, 255));
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 230, 40));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 39)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("CREA TU CUENTA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 340, 70));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new_User.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 80));

        txtContrasena.setBackground(new java.awt.Color(0, 0, 0));
        txtContrasena.setFont(new java.awt.Font("Trebuchet MS", 1, 21)); // NOI18N
        txtContrasena.setForeground(new java.awt.Color(255, 255, 255));
        txtContrasena.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 230, 40));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 500));

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("CREAR");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 170, 60));

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 170, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        formMenuPrincipal.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtEmail.getText();
        String telefono = txtTelefono.getText();
        String contrasena = txtContrasena.getText();
        String direccion = txtDireccion.getText();

        // Verificación de campos vacíos
        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()
                || telefono.isEmpty() || contrasena.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de formato de correo
        if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "El correo no tiene un formato válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de nombre y apellido solo letras y al menos 5 caracteres
        if (!nombre.matches("[a-zA-Z]{3,}") || !apellido.matches("[a-zA-Z]{5,}")) {
            JOptionPane.showMessageDialog(this, "El nombre y el apellido deben contener solo letras y al menos 5 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de teléfono de 8 dígitos
        if (!telefono.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "El teléfono debe contener exactamente 8 números.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de contraseña con al menos 10 caracteres
        if (contrasena.length() < 7) {
            JOptionPane.showMessageDialog(this, "La contraseña debe contener al menos 10 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de dirección con al menos 30 caracteres
        if (direccion.length() < 30) {
            JOptionPane.showMessageDialog(this, "La dirección debe contener al menos 30 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (GestorUsuarios.esCorreoRegistrado(correo)) {
            JOptionPane.showMessageDialog(this, "El correo ya está registrado. Intente con otro correo.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean exito = GestorUsuarios.crearUsuario(nombre, apellido, correo, telefono, contrasena, direccion);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Usuario creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                formMenuPrincipal.setEnabled(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
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
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}



/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 4 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/