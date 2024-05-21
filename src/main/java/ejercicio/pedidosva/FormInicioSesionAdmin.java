
package ejercicio.pedidosva;

import javax.swing.JOptionPane;


public class FormInicioSesionAdmin extends javax.swing.JFrame {

   private FormMenuPrincipal menuPrincipal;
   
    public FormInicioSesionAdmin(FormMenuPrincipal menuPrincipal) {
        this.setUndecorated(true);
        this.menuPrincipal = menuPrincipal;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 38)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Modo Administrador");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 360, -1));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ingresa tus credenciales");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 180, 30));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("E-mail");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Contraseña");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 230, -1));

        btnIniciarSesion.setBackground(new java.awt.Color(0, 51, 204));
        btnIniciarSesion.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Entrar");
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        jPanel2.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 150, 50));

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 150, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eye.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 100, 90));

        txtEmail.setBackground(new java.awt.Color(0, 0, 0));
        txtEmail.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 330, 40));

        txtContrasena.setBackground(new java.awt.Color(0, 0, 0));
        txtContrasena.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        txtContrasena.setForeground(new java.awt.Color(255, 255, 255));
        txtContrasena.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 330, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        String correo = txtEmail.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (GestorUsuarios.verificarCredencialesAdmin(correo, contrasena)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Crear una instancia del formulario de administrador y mostrarlo
            FormAdministrador formAdmin = new FormAdministrador(menuPrincipal);
            formAdmin.setVisible(true);
            formAdmin.setLocationRelativeTo(null); // Centrar el formulario en la pantalla

            // Cerrar el formulario de inicio de sesión
            this.dispose();
            menuPrincipal.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        menuPrincipal.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}
