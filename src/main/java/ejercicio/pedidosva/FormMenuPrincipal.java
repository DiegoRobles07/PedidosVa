package ejercicio.pedidosva;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormMenuPrincipal extends javax.swing.JFrame {

    String categoriaSeleccionada;
    private FormAgregarAlCarrito formAgregarAlCarrito;
    private Carrito carrito;

    // Métodos para establecer y quitar el borde rojo de un panel y su panel hijo
    public void setRedBorder(JPanel panel, JPanel childPanel) {
        panel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        if (childPanel != null) {
            childPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        }
    }

    public void removeRedBorder(JPanel panel, JPanel childPanel) {
        panel.setBorder(BorderFactory.createEmptyBorder());
        if (childPanel != null) {
            childPanel.setBorder(BorderFactory.createEmptyBorder());
        }
    }
    // Eventos para el mouseEntered de los paneles

    private void pnlComboMouseEntered(java.awt.event.MouseEvent evt, JPanel panel, JPanel childPanel) {
        setRedBorder(panel, childPanel);
    }

    // Eventos para el mouseExited de los paneles
    private void pnlComboMouseExited(java.awt.event.MouseEvent evt, JPanel panel, JPanel childPanel) {
        removeRedBorder(panel, childPanel);
    }
    List<Desayunos> desayunos;
    // Definición de las listas como variables de instancia
    private List<JPanel> panelesDesayuno;
    private List<JLabel> lblNombreDesayuno;
    private List<JLabel> lblPrecioDesayuno;
    public List<JLabel> lblImagenDesayuno;
    private List<JLabel> lblDescripcionDesayuno;
// Obtener los desayunos y asignarlos a la lista

    public FormMenuPrincipal(FormAgregarAlCarrito formAgregarAlCarrito) {
        this.setUndecorated(true);
        this.formAgregarAlCarrito = formAgregarAlCarrito;
        this.carrito = carrito;
        initComponents();
        this.setExtendedState(this.MAXIMIZED_BOTH);
        ConexionBD.establecerConexion();

        panelesDesayuno = new ArrayList<>();
        lblNombreDesayuno = new ArrayList<>();
        lblPrecioDesayuno = new ArrayList<>();
        lblImagenDesayuno = new ArrayList<>();
        lblDescripcionDesayuno = new ArrayList<>();

        panelesDesayuno.add(pnlCombo1);
        panelesDesayuno.add(pnlCombo2);
        panelesDesayuno.add(pnlCombo3);
        panelesDesayuno.add(pnlCombo4);
        panelesDesayuno.add(pnlCombo5);
        panelesDesayuno.add(pnlCombo6);
        panelesDesayuno.add(pnlCombo7);
        panelesDesayuno.add(pnlCombo8);

        lblImagenDesayuno.add(lblImagenCombo1);
        lblImagenDesayuno.add(lblImagenCombo2);
        lblImagenDesayuno.add(lblImagenCombo3);
        lblImagenDesayuno.add(lblImagenCombo4);
        lblImagenDesayuno.add(lblImagenCombo5);
        lblImagenDesayuno.add(lblImagenCombo6);
        lblImagenDesayuno.add(lblImagenCombo7);
        lblImagenDesayuno.add(lblImagenCombo8);

        lblPrecioDesayuno.add(lblPrecioCombo1);
        lblPrecioDesayuno.add(lblPrecioCombo2);
        lblPrecioDesayuno.add(lblPrecioCombo3);
        lblPrecioDesayuno.add(lblPrecioCombo4);
        lblPrecioDesayuno.add(lblPrecioCombo5);
        lblPrecioDesayuno.add(lblPrecioCombo6);
        lblPrecioDesayuno.add(lblPrecioCombo7);
        lblPrecioDesayuno.add(lblPrecioCombo8);

        lblDescripcionDesayuno.add(lblDescripcionCombo1);
        lblDescripcionDesayuno.add(lblDescripcionCombo2);
        lblDescripcionDesayuno.add(lblDescripcionCombo3);
        lblDescripcionDesayuno.add(lblDescripcionCombo4);
        lblDescripcionDesayuno.add(lblDescripcionCombo5);
        lblDescripcionDesayuno.add(lblDescripcionCombo6);
        lblDescripcionDesayuno.add(lblDescripcionCombo7);
        lblDescripcionDesayuno.add(lblDescripcionCombo8);

        lblNombreDesayuno.add(lblNombreCombo1);
        lblNombreDesayuno.add(lblNombreCombo2);
        lblNombreDesayuno.add(lblNombreCombo3);
        lblNombreDesayuno.add(lblNombreCombo4);
        lblNombreDesayuno.add(lblNombreCombo5);
        lblNombreDesayuno.add(lblNombreCombo6);
        lblNombreDesayuno.add(lblNombreCombo7);
        lblNombreDesayuno.add(lblNombreCombo8);

        // Agregar impresiones de depuración aquí
        System.out.println("Tamaño de panelesDesayuno: " + panelesDesayuno.size());
        System.out.println("Tamaño de lblNombreDesayuno: " + lblNombreDesayuno.size());
        System.out.println("Tamaño de lblPrecioDesayuno: " + lblPrecioDesayuno.size());
        System.out.println("Tamaño de lblImagenDesayuno: " + lblImagenDesayuno.size());

        cargarDesayunos();
    }

    private void cargarDesayunos() {
        GestorCombos gestorCombos = new GestorCombos();
        List<Desayunos> desayunos = gestorCombos.obtenerDesayunos();

        if (!desayunos.isEmpty()) {
            // Verifica si el tamaño de las listas de etiquetas es mayor o igual al tamaño de la lista de desayunos
            if (lblNombreDesayuno.size() >= desayunos.size()
                    && lblPrecioDesayuno.size() >= desayunos.size()
                    && lblImagenDesayuno.size() >= desayunos.size()
                    && lblDescripcionDesayuno.size() >= desayunos.size()) { // Asegurar que la lista de descripciones tenga el mismo tamaño

                // Itera sobre la lista de desayunos
                for (int i = 0; i < desayunos.size(); i++) {
                    Desayunos desayuno = desayunos.get(i);

                    // Verifica si el índice actual es menor que el tamaño de las listas de etiquetas
                    if (i < lblNombreDesayuno.size() && i < lblPrecioDesayuno.size() && i < lblImagenDesayuno.size() && i < lblDescripcionDesayuno.size()) {
                        lblNombreDesayuno.get(i).setText(desayuno.getNombre());
                        lblPrecioDesayuno.get(i).setText(String.format("%.2f", desayuno.getPrecio()));

                        // Establecer la descripción del desayuno
                        lblDescripcionDesayuno.get(i).setText(desayuno.getDescripcion());

                        // Cargar la imagen desde la ruta almacenada en el objeto Desayuno
                        String rutaImagen = desayuno.getImagen();
                        if (rutaImagen != null && !rutaImagen.isEmpty()) {
                            ImageIcon icon = new ImageIcon(rutaImagen);
                            lblImagenDesayuno.get(i).setIcon(icon);
                        } else {
                            System.err.println("La ruta de la imagen para el desayuno " + desayuno.getNombre() + " está vacía.");
                        }
                    } else {
                        System.err.println("El índice excede el tamaño de las listas de etiquetas.");
                        break; // Termina el bucle para evitar más iteraciones innecesarias
                    }
                }
            } else {
                System.err.println("El tamaño de las listas de etiquetas no coincide con el tamaño de la lista de desayunos.");
            }
        }
    }

    public void actualizarPanelDesayuno(int index, String nombre, double precio, String imagenRuta, String descripcion) {
        if (index < 0 || index >= lblImagenDesayuno.size()) {
            System.err.println("Índice de panel de desayuno inválido: " + index);
            return;
        }

        JLabel lblImagen = lblImagenDesayuno.get(index);

        // Actualiza el nombre, precio y descripción del desayuno
        lblNombreDesayuno.get(index).setText(nombre);
        lblPrecioDesayuno.get(index).setText(String.format("%.2f", precio));
        lblDescripcionDesayuno.get(index).setText(descripcion);

        // Carga la imagen desde la ruta almacenada en la base de datos
        if (imagenRuta != null && !imagenRuta.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagenRuta);
            lblImagen.setIcon(icon);
        } else {
            System.err.println("La ruta de la imagen para el desayuno en el panel " + index + " está vacía.");
        }
    }

    // Método para actualizar un panel de combo de pollo específico con nombre, precio e imagen
    public void actualizarPanelComboPollo(int index, String nombre, double precio, String descripcion, String imagenRuta) {
        if (index < 0 || index >= lblImagenDesayuno.size()) {
            System.err.println("Índice de panel de combo de pollo inválido: " + index);
            return;
        }

        JLabel lblImagen = lblImagenDesayuno.get(index);

        // Actualiza el nombre, precio y descripción del combo de pollo
        lblNombreDesayuno.get(index).setText(nombre);
        lblPrecioDesayuno.get(index).setText(String.format("%.2f", precio));
        lblDescripcionDesayuno.get(index).setText(descripcion);

        // Carga la imagen desde la ruta almacenada en la base de datos
        if (imagenRuta != null && !imagenRuta.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagenRuta);
            lblImagen.setIcon(icon);
        } else {
            System.err.println("La ruta de la imagen para el combo de pollo en el panel " + index + " está vacía.");
        }
    }

// Método para actualizar un panel de combo de carne específico con nombre, precio e imagen
    public void actualizarPanelComboCarne(int index, String nombre, double precio, String descripcion, String imagenRuta) {
        if (index < 0 || index >= lblImagenDesayuno.size()) {
            System.err.println("Índice de panel de combo de carne inválido: " + index);
            return;
        }

        JLabel lblImagen = lblImagenDesayuno.get(index);

        // Actualiza el nombre y precio del combo de carne
        lblNombreDesayuno.get(index).setText(nombre);
        lblPrecioDesayuno.get(index).setText(String.format("%.2f", precio));

        // Establece la descripción del combo de carne
        lblDescripcionDesayuno.get(index).setText(descripcion);

        // Carga la imagen desde la ruta almacenada en la base de datos
        if (imagenRuta != null && !imagenRuta.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagenRuta);
            lblImagen.setIcon(icon);
        } else {
            System.err.println("La ruta de la imagen para el combo de carne en el panel " + index + " está vacía.");
        }
    }

    private void cargarEnsaladas() {
        GestorCombos gestorCombos = new GestorCombos();
        gestorCombos.actualizarPanelesEnsaladas(this);
    }

    private void cargarIndividuales() {
        GestorCombos gestorCombos = new GestorCombos();
        gestorCombos.actualizarPanelesIndividuales(this);
    }

    public void actualizarPanelEnsalada(int index, String nombre, double precio, String descripcion, String imagenRuta) {
        if (index < 0 || index >= lblImagenDesayuno.size()) {
            System.err.println("Índice de panel de ensalada inválido: " + index);
            return;
        }

        JLabel lblImagen = lblImagenDesayuno.get(index);

        // Actualiza el nombre y precio de la ensalada
        lblNombreDesayuno.get(index).setText(nombre);
        lblPrecioDesayuno.get(index).setText(String.format("%.2f", precio));

        // Establece la descripción de la ensalada
        lblDescripcionDesayuno.get(index).setText(descripcion);

        // Carga la imagen desde la ruta almacenada en la base de datos
        if (imagenRuta != null && !imagenRuta.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagenRuta);
            lblImagen.setIcon(icon);
        } else {
            System.err.println("La ruta de la imagen para la ensalada en el panel " + index + " está vacía.");
        }
    }

    // Método para actualizar un panel de individual específico con nombre, precio, imagen y descripción
    public void actualizarPanelIndividual(int index, String nombre, double precio, String imagenRuta, String descripcion) {
        if (index < 0 || index >= lblImagenDesayuno.size()) {
            System.err.println("Índice de panel de individual inválido: " + index);
            return;
        }

        JLabel lblImagen = lblImagenDesayuno.get(index);

        // Actualiza el nombre y precio del individual
        lblNombreDesayuno.get(index).setText(nombre);
        lblPrecioDesayuno.get(index).setText(String.format("%.2f", precio));

        // Establece la descripción del individual
        lblDescripcionDesayuno.get(index).setText(descripcion);

        // Carga la imagen desde la ruta almacenada en el objeto Individual
        if (imagenRuta != null && !imagenRuta.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagenRuta);
            lblImagen.setIcon(icon);
        } else {
            System.err.println("La ruta de la imagen para el individual en el panel " + index + " está vacía.");
        }
    }

// Método para actualizar un panel de bebida específico con nombre, precio, imagen y descripción
    public void actualizarPanelBebida(int index, String nombre, double precio, String descripcion, String imagenRuta) {
        if (index < 0 || index >= lblImagenDesayuno.size()) {
            System.err.println("Índice de panel de bebida inválido: " + index);
            return;
        }

        JLabel lblImagen = lblImagenDesayuno.get(index);

        // Actualiza el nombre, precio y descripción de la bebida
        lblNombreDesayuno.get(index).setText(nombre);
        lblPrecioDesayuno.get(index).setText(String.format("%.2f", precio));
        lblDescripcionDesayuno.get(index).setText(descripcion);

        // Carga la imagen desde la ruta almacenada en el objeto Bebida
        if (imagenRuta != null && !imagenRuta.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagenRuta);
            lblImagen.setIcon(icon);
        } else {
            System.err.println("La ruta de la imagen para la bebida en el panel " + index + " está vacía.");
        }
    }
// Método para actualizar un panel de postre específico con nombre, precio, imagen y descripción

    public void actualizarPanelPostre(int index, String nombre, double precio, String descripcion, String imagenRuta) {
        if (index < 0 || index >= lblImagenDesayuno.size()) {
            System.err.println("Índice de panel de postre inválido: " + index);
            return;
        }

        JLabel lblImagen = lblImagenDesayuno.get(index);

        // Actualiza el nombre, precio y descripción del postre
        lblNombreDesayuno.get(index).setText(nombre);
        lblPrecioDesayuno.get(index).setText(String.format("%.2f", precio));
        lblDescripcionDesayuno.get(index).setText(descripcion);

        // Carga la imagen desde la ruta almacenada en el objeto Postre
        if (imagenRuta != null && !imagenRuta.isEmpty()) {
            ImageIcon icon = new ImageIcon(imagenRuta);
            lblImagen.setIcon(icon);
        } else {
            System.err.println("La ruta de la imagen para el postre en el panel " + index + " está vacía.");
        }
    }

// Agregamos un método para cargar los combos de pollo
    private void cargarCombosPollo() {
        GestorCombos gestorCombos = new GestorCombos();
        gestorCombos.actualizarPanelesCombosPollo(this);
    }

    private void cargarCombosCarne() {
        GestorCombos gestorCombos = new GestorCombos();
        gestorCombos.actualizarPanelesCombosCarne(this);
    }

    private void cargarBebidas() {
        GestorCombos gestorCombos = new GestorCombos();
        gestorCombos.actualizarPanelesBebidas(this);
    }

    private void cargarPostres() {
        GestorCombos gestorCombos = new GestorCombos();
        gestorCombos.actualizarPanelesPostres(this);
    }

    private void abrirFormularioAgregarAlCarrito(String nombre, double precio, String descripcion, String imagen) {
        FormAgregarAlCarrito formAgregarAlCarrito = new FormAgregarAlCarrito(this, carrito);
        formAgregarAlCarrito.actualizarCombo(nombre, precio, descripcion, imagen);
        formAgregarAlCarrito.setLocationRelativeTo(null); // Centrar en la pantalla
        formAgregarAlCarrito.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCombos = new javax.swing.JPanel();
        pnlCombo1 = new javax.swing.JPanel();
        lblPrecioCombo1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblImagenCombo1 = new javax.swing.JLabel();
        pnlInfoCombo1 = new javax.swing.JPanel();
        lblNombreCombo1 = new javax.swing.JLabel();
        lblDescripcionCombo1 = new javax.swing.JLabel();
        pnlCombo5 = new javax.swing.JPanel();
        pnlInfoCombo5 = new javax.swing.JPanel();
        lblNombreCombo5 = new javax.swing.JLabel();
        lblDescripcionCombo5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblPrecioCombo5 = new javax.swing.JLabel();
        lblImagenCombo5 = new javax.swing.JLabel();
        pnlCombo2 = new javax.swing.JPanel();
        lblPrecioCombo2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblImagenCombo2 = new javax.swing.JLabel();
        pnlInfoCombo2 = new javax.swing.JPanel();
        lblNombreCombo2 = new javax.swing.JLabel();
        lblDescripcionCombo2 = new javax.swing.JLabel();
        pnlCombo6 = new javax.swing.JPanel();
        lblPrecioCombo6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblImagenCombo6 = new javax.swing.JLabel();
        pnlInfoCombo6 = new javax.swing.JPanel();
        lblNombreCombo6 = new javax.swing.JLabel();
        lblDescripcionCombo6 = new javax.swing.JLabel();
        pnlCombo4 = new javax.swing.JPanel();
        lblPrecioCombo4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblImagenCombo4 = new javax.swing.JLabel();
        pnlInfoCombo4 = new javax.swing.JPanel();
        lblNombreCombo4 = new javax.swing.JLabel();
        lblDescripcionCombo4 = new javax.swing.JLabel();
        pnlCombo8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblPrecioCombo8 = new javax.swing.JLabel();
        lblImagenCombo8 = new javax.swing.JLabel();
        pnlInfoCombo8 = new javax.swing.JPanel();
        lblNombreCombo8 = new javax.swing.JLabel();
        lblDescripcionCombo8 = new javax.swing.JLabel();
        pnlCombo3 = new javax.swing.JPanel();
        lblPrecioCombo3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblImagenCombo3 = new javax.swing.JLabel();
        pnlInfoCombo3 = new javax.swing.JPanel();
        lblNombreCombo3 = new javax.swing.JLabel();
        lblDescripcionCombo3 = new javax.swing.JLabel();
        pnlCombo7 = new javax.swing.JPanel();
        lblPrecioCombo7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblImagenCombo7 = new javax.swing.JLabel();
        pnlInfoCombo7 = new javax.swing.JPanel();
        lblNombreCombo7 = new javax.swing.JLabel();
        lblDescripcionCombo7 = new javax.swing.JLabel();
        panelFondo = new javax.swing.JPanel();
        separator = new javax.swing.JSeparator();
        btnComboPollo = new javax.swing.JButton();
        btnDesayunos = new javax.swing.JButton();
        btnEnsaladas = new javax.swing.JButton();
        btnComboCarne = new javax.swing.JButton();
        btnConfirmarPedido = new javax.swing.JButton();
        btnBebidas = new javax.swing.JButton();
        btnIndividuales = new javax.swing.JButton();
        btnPostres1 = new javax.swing.JButton();
        btnInicioSesion = new javax.swing.JButton();
        lblIcono = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblSalir = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 255));
        getContentPane().setLayout(null);

        panelCombos.setBackground(new java.awt.Color(204, 204, 204));
        panelCombos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlCombo1.setBackground(new java.awt.Color(255, 255, 255));
        pnlCombo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCombo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCombo1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlCombo1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlCombo1MouseExited(evt);
            }
        });
        pnlCombo1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPrecioCombo1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblPrecioCombo1.setForeground(new java.awt.Color(255, 51, 51));
        lblPrecioCombo1.setText("Precio");
        pnlCombo1.add(lblPrecioCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 0, 60, -1));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("$");
        pnlCombo1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        lblImagenCombo1.setText("jLabel3");
        lblImagenCombo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenCombo1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImagenCombo1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImagenCombo1MouseExited(evt);
            }
        });
        pnlCombo1.add(lblImagenCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 160));

        pnlInfoCombo1.setBackground(new java.awt.Color(0, 0, 255));
        pnlInfoCombo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlInfoCombo1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlInfoCombo1MouseExited(evt);
            }
        });
        pnlInfoCombo1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreCombo1.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lblNombreCombo1.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreCombo1.setText("Nombre de combo");
        pnlInfoCombo1.add(lblNombreCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblDescripcionCombo1.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcionCombo1.setText("descripcion");
        pnlInfoCombo1.add(lblDescripcionCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pnlCombo1.add(pnlInfoCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 250, 80));

        panelCombos.add(pnlCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, 240));

        pnlCombo5.setBackground(new java.awt.Color(255, 255, 255));
        pnlCombo5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCombo5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlCombo5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlCombo5MouseExited(evt);
            }
        });
        pnlCombo5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlInfoCombo5.setBackground(new java.awt.Color(0, 0, 255));
        pnlInfoCombo5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreCombo5.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lblNombreCombo5.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreCombo5.setText("Nombre de combo");
        pnlInfoCombo5.add(lblNombreCombo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblDescripcionCombo5.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcionCombo5.setText("desripcion");
        pnlInfoCombo5.add(lblDescripcionCombo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pnlCombo5.add(pnlInfoCombo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 250, 80));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("$");
        pnlCombo5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        lblPrecioCombo5.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblPrecioCombo5.setForeground(new java.awt.Color(255, 51, 51));
        lblPrecioCombo5.setText("Precio");
        pnlCombo5.add(lblPrecioCombo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 0, 60, -1));

        lblImagenCombo5.setText("jLabel3");
        lblImagenCombo5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenCombo5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImagenCombo5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImagenCombo5MouseExited(evt);
            }
        });
        pnlCombo5.add(lblImagenCombo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 160));

        panelCombos.add(pnlCombo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 270, 240));

        pnlCombo2.setBackground(new java.awt.Color(255, 255, 255));
        pnlCombo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCombo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlCombo2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlCombo2MouseExited(evt);
            }
        });
        pnlCombo2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPrecioCombo2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblPrecioCombo2.setForeground(new java.awt.Color(255, 51, 51));
        lblPrecioCombo2.setText("Precio");
        pnlCombo2.add(lblPrecioCombo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 0, 60, -1));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("$");
        pnlCombo2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        lblImagenCombo2.setText("jLabel3");
        lblImagenCombo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenCombo2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImagenCombo2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImagenCombo2MouseExited(evt);
            }
        });
        pnlCombo2.add(lblImagenCombo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 160));

        pnlInfoCombo2.setBackground(new java.awt.Color(0, 0, 255));
        pnlInfoCombo2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreCombo2.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lblNombreCombo2.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreCombo2.setText("Nombre de combo");
        pnlInfoCombo2.add(lblNombreCombo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblDescripcionCombo2.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcionCombo2.setText("descr");
        pnlInfoCombo2.add(lblDescripcionCombo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pnlCombo2.add(pnlInfoCombo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 250, 80));

        panelCombos.add(pnlCombo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 270, 240));

        pnlCombo6.setBackground(new java.awt.Color(255, 255, 255));
        pnlCombo6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCombo6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlCombo6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlCombo6MouseExited(evt);
            }
        });
        pnlCombo6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPrecioCombo6.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblPrecioCombo6.setForeground(new java.awt.Color(255, 51, 51));
        lblPrecioCombo6.setText("Precio");
        pnlCombo6.add(lblPrecioCombo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 0, 60, -1));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("$");
        pnlCombo6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        lblImagenCombo6.setText("jLabel3");
        lblImagenCombo6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenCombo6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImagenCombo6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImagenCombo6MouseExited(evt);
            }
        });
        pnlCombo6.add(lblImagenCombo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 160));

        pnlInfoCombo6.setBackground(new java.awt.Color(0, 0, 255));
        pnlInfoCombo6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreCombo6.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lblNombreCombo6.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreCombo6.setText("Nombre de combo");
        pnlInfoCombo6.add(lblNombreCombo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblDescripcionCombo6.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcionCombo6.setText("descripicon");
        pnlInfoCombo6.add(lblDescripcionCombo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pnlCombo6.add(pnlInfoCombo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 250, 80));

        panelCombos.add(pnlCombo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 270, 240));

        pnlCombo4.setBackground(new java.awt.Color(255, 255, 255));
        pnlCombo4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCombo4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlCombo4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlCombo4MouseExited(evt);
            }
        });
        pnlCombo4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPrecioCombo4.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblPrecioCombo4.setForeground(new java.awt.Color(255, 51, 51));
        lblPrecioCombo4.setText("Precio");
        pnlCombo4.add(lblPrecioCombo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 0, 60, -1));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("$");
        pnlCombo4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        lblImagenCombo4.setText("jLabel3");
        lblImagenCombo4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenCombo4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImagenCombo4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImagenCombo4MouseExited(evt);
            }
        });
        pnlCombo4.add(lblImagenCombo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 160));

        pnlInfoCombo4.setBackground(new java.awt.Color(0, 0, 255));
        pnlInfoCombo4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreCombo4.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lblNombreCombo4.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreCombo4.setText("Nombre de combo");
        pnlInfoCombo4.add(lblNombreCombo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblDescripcionCombo4.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcionCombo4.setText("descripcion");
        pnlInfoCombo4.add(lblDescripcionCombo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pnlCombo4.add(pnlInfoCombo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 250, 80));

        panelCombos.add(pnlCombo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, 270, 240));

        pnlCombo8.setBackground(new java.awt.Color(255, 255, 255));
        pnlCombo8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCombo8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlCombo8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlCombo8MouseExited(evt);
            }
        });
        pnlCombo8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("$");
        pnlCombo8.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, -1));

        lblPrecioCombo8.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblPrecioCombo8.setForeground(new java.awt.Color(255, 51, 51));
        lblPrecioCombo8.setText("Precio");
        pnlCombo8.add(lblPrecioCombo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 0, 80, -1));

        lblImagenCombo8.setText("jLabel3");
        lblImagenCombo8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenCombo8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImagenCombo8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImagenCombo8MouseExited(evt);
            }
        });
        pnlCombo8.add(lblImagenCombo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 160));

        pnlInfoCombo8.setBackground(new java.awt.Color(0, 0, 255));
        pnlInfoCombo8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreCombo8.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lblNombreCombo8.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreCombo8.setText("Nombre de combo");
        pnlInfoCombo8.add(lblNombreCombo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblDescripcionCombo8.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcionCombo8.setText("descripcion");
        pnlInfoCombo8.add(lblDescripcionCombo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pnlCombo8.add(pnlInfoCombo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 250, 80));

        panelCombos.add(pnlCombo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 280, 270, 240));

        pnlCombo3.setBackground(new java.awt.Color(255, 255, 255));
        pnlCombo3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCombo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlCombo3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlCombo3MouseExited(evt);
            }
        });
        pnlCombo3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPrecioCombo3.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblPrecioCombo3.setForeground(new java.awt.Color(255, 51, 51));
        lblPrecioCombo3.setText("Precio");
        pnlCombo3.add(lblPrecioCombo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 0, 60, -1));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("$");
        pnlCombo3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        lblImagenCombo3.setText("jLabel3");
        lblImagenCombo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenCombo3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImagenCombo3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImagenCombo3MouseExited(evt);
            }
        });
        pnlCombo3.add(lblImagenCombo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 160));

        pnlInfoCombo3.setBackground(new java.awt.Color(0, 0, 255));
        pnlInfoCombo3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreCombo3.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        lblNombreCombo3.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreCombo3.setText("Nombre de combo");
        pnlInfoCombo3.add(lblNombreCombo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblDescripcionCombo3.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcionCombo3.setText("descrip");
        pnlInfoCombo3.add(lblDescripcionCombo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pnlCombo3.add(pnlInfoCombo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 250, 80));

        panelCombos.add(pnlCombo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 270, 240));

        pnlCombo7.setBackground(new java.awt.Color(255, 255, 255));
        pnlCombo7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCombo7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlCombo7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlCombo7MouseExited(evt);
            }
        });
        pnlCombo7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPrecioCombo7.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblPrecioCombo7.setForeground(new java.awt.Color(255, 51, 51));
        lblPrecioCombo7.setText("Precio");
        pnlCombo7.add(lblPrecioCombo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 0, 60, -1));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setText("$");
        pnlCombo7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, -1));

        lblImagenCombo7.setText("jLabel3");
        lblImagenCombo7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenCombo7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblImagenCombo7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblImagenCombo7MouseExited(evt);
            }
        });
        pnlCombo7.add(lblImagenCombo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 160));

        pnlInfoCombo7.setBackground(new java.awt.Color(0, 0, 255));
        pnlInfoCombo7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreCombo7.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        lblNombreCombo7.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreCombo7.setText("Nombre de combo");
        pnlInfoCombo7.add(lblNombreCombo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 270, 40));

        lblDescripcionCombo7.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcionCombo7.setText("descripcion");
        pnlInfoCombo7.add(lblDescripcionCombo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pnlCombo7.add(pnlInfoCombo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 250, 80));

        panelCombos.add(pnlCombo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 280, 270, 240));

        getContentPane().add(panelCombos);
        panelCombos.setBounds(20, 160, 1240, 540);

        panelFondo.setBackground(new java.awt.Color(204, 51, 0));
        panelFondo.setForeground(new java.awt.Color(0, 0, 0));
        panelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        separator.setBackground(new java.awt.Color(0, 0, 0));
        panelFondo.add(separator, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1290, 10));

        btnComboPollo.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnComboPollo.setText("Combos Pollo");
        btnComboPollo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComboPollo.setFocusable(false);
        btnComboPollo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComboPolloActionPerformed(evt);
            }
        });
        panelFondo.add(btnComboPollo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 150, 50));

        btnDesayunos.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnDesayunos.setText("Desayunos");
        btnDesayunos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDesayunos.setFocusable(false);
        btnDesayunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesayunosActionPerformed(evt);
            }
        });
        panelFondo.add(btnDesayunos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 150, 50));

        btnEnsaladas.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnEnsaladas.setText("Ensaladas");
        btnEnsaladas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnsaladas.setFocusable(false);
        btnEnsaladas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnsaladasActionPerformed(evt);
            }
        });
        panelFondo.add(btnEnsaladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 150, 50));

        btnComboCarne.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnComboCarne.setText("Combos Carne");
        btnComboCarne.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComboCarne.setFocusable(false);
        btnComboCarne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComboCarneActionPerformed(evt);
            }
        });
        panelFondo.add(btnComboCarne, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 160, 50));

        btnConfirmarPedido.setBackground(new java.awt.Color(0, 0, 0));
        btnConfirmarPedido.setFont(new java.awt.Font("Trebuchet MS", 1, 19)); // NOI18N
        btnConfirmarPedido.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bolsa_.png"))); // NOI18N
        btnConfirmarPedido.setText("Confirmar Pedido");
        btnConfirmarPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmarPedido.setFocusable(false);
        panelFondo.add(btnConfirmarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 230, 60));

        btnBebidas.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnBebidas.setText("Bebidas");
        btnBebidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBebidas.setFocusable(false);
        btnBebidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBebidasActionPerformed(evt);
            }
        });
        panelFondo.add(btnBebidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 150, 50));

        btnIndividuales.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnIndividuales.setText("Individuales");
        btnIndividuales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIndividuales.setFocusable(false);
        btnIndividuales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIndividualesActionPerformed(evt);
            }
        });
        panelFondo.add(btnIndividuales, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, 150, 50));

        btnPostres1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnPostres1.setText("Postres");
        btnPostres1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPostres1.setFocusable(false);
        btnPostres1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostres1ActionPerformed(evt);
            }
        });
        panelFondo.add(btnPostres1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 100, 150, 50));

        btnInicioSesion.setBackground(new java.awt.Color(51, 102, 255));
        btnInicioSesion.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        btnInicioSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnInicioSesion.setText("Iniciar Sesión");
        btnInicioSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicioSesion.setFocusable(false);
        btnInicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioSesionActionPerformed(evt);
            }
        });
        panelFondo.add(btnInicioSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 150, 60));

        lblIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ico.png"))); // NOI18N
        panelFondo.add(lblIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, -1));

        lblTitulo.setFont(new java.awt.Font("Trebuchet MS", 1, 65)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("F R E D D Y ´S");
        panelFondo.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 5, -1, 80));

        lblSalir.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lblSalir.setForeground(new java.awt.Color(255, 255, 255));
        lblSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit (1).png"))); // NOI18N
        lblSalir.setText("SALIR");
        lblSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalirMouseClicked(evt);
            }
        });
        panelFondo.add(lblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 120, 60));

        getContentPane().add(panelFondo);
        panelFondo.setBounds(0, 0, 1290, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlCombo1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo1MouseEntered
        pnlComboMouseEntered(evt, pnlCombo1, pnlInfoCombo1);
    }//GEN-LAST:event_pnlCombo1MouseEntered

    private void pnlCombo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo1MouseExited
        pnlComboMouseExited(evt, pnlCombo1, pnlInfoCombo1);
    }//GEN-LAST:event_pnlCombo1MouseExited

    private void pnlCombo2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo2MouseEntered
        pnlComboMouseEntered(evt, pnlCombo2, pnlInfoCombo2);
    }//GEN-LAST:event_pnlCombo2MouseEntered

    private void pnlCombo2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo2MouseExited
        pnlComboMouseExited(evt, pnlCombo2, pnlInfoCombo2);
    }//GEN-LAST:event_pnlCombo2MouseExited

    private void pnlCombo3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo3MouseEntered
        pnlComboMouseEntered(evt, pnlCombo3, pnlInfoCombo3);
    }//GEN-LAST:event_pnlCombo3MouseEntered

    private void pnlCombo3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo3MouseExited
        pnlComboMouseExited(evt, pnlCombo3, pnlInfoCombo3);
    }//GEN-LAST:event_pnlCombo3MouseExited

    private void pnlCombo4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo4MouseEntered
        pnlComboMouseEntered(evt, pnlCombo4, pnlInfoCombo4);
    }//GEN-LAST:event_pnlCombo4MouseEntered

    private void pnlCombo4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo4MouseExited
        pnlComboMouseExited(evt, pnlCombo4, pnlInfoCombo4);
    }//GEN-LAST:event_pnlCombo4MouseExited

    private void pnlCombo5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo5MouseEntered
        pnlComboMouseEntered(evt, pnlCombo5, pnlInfoCombo5);
    }//GEN-LAST:event_pnlCombo5MouseEntered

    private void pnlCombo5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo5MouseExited
        pnlComboMouseExited(evt, pnlCombo5, pnlInfoCombo5);
    }//GEN-LAST:event_pnlCombo5MouseExited

    private void pnlCombo6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo6MouseEntered
        pnlComboMouseEntered(evt, pnlCombo6, pnlInfoCombo6);
    }//GEN-LAST:event_pnlCombo6MouseEntered

    private void pnlCombo6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo6MouseExited
        pnlComboMouseExited(evt, pnlCombo6, pnlInfoCombo6);
    }//GEN-LAST:event_pnlCombo6MouseExited

    private void pnlCombo7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo7MouseEntered
        pnlComboMouseEntered(evt, pnlCombo7, pnlInfoCombo7);
    }//GEN-LAST:event_pnlCombo7MouseEntered

    private void pnlCombo7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo7MouseExited
        pnlComboMouseExited(evt, pnlCombo7, pnlInfoCombo7);
    }//GEN-LAST:event_pnlCombo7MouseExited

    private void pnlCombo8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo8MouseEntered
        pnlComboMouseEntered(evt, pnlCombo8, pnlInfoCombo8);;
    }//GEN-LAST:event_pnlCombo8MouseEntered

    private void pnlCombo8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo8MouseExited
        pnlComboMouseExited(evt, pnlCombo8, pnlInfoCombo8);
    }//GEN-LAST:event_pnlCombo8MouseExited

    private void btnDesayunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesayunosActionPerformed
        cargarDesayunos();
        categoriaSeleccionada = "Desayunos";
    }//GEN-LAST:event_btnDesayunosActionPerformed

    private void btnComboPolloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComboPolloActionPerformed
        cargarCombosPollo();
        categoriaSeleccionada = "CombosPollo";
    }//GEN-LAST:event_btnComboPolloActionPerformed

    private void btnComboCarneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComboCarneActionPerformed
        cargarCombosCarne();
        categoriaSeleccionada = "CombosCarne";
    }//GEN-LAST:event_btnComboCarneActionPerformed

    private void pnlInfoCombo1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInfoCombo1MouseEntered
        pnlComboMouseEntered(evt, pnlCombo1, pnlInfoCombo1);
    }//GEN-LAST:event_pnlInfoCombo1MouseEntered

    private void pnlInfoCombo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlInfoCombo1MouseExited
        pnlComboMouseExited(evt, pnlCombo1, pnlInfoCombo1);
    }//GEN-LAST:event_pnlInfoCombo1MouseExited

    private void btnEnsaladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnsaladasActionPerformed
        cargarEnsaladas();
        categoriaSeleccionada = "Ensaladas";
    }//GEN-LAST:event_btnEnsaladasActionPerformed

    private void btnIndividualesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIndividualesActionPerformed
        cargarIndividuales();
        categoriaSeleccionada = "Individuales";
    }//GEN-LAST:event_btnIndividualesActionPerformed

    private void lblSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblSalirMouseClicked

    private void btnBebidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBebidasActionPerformed
        cargarBebidas();
        categoriaSeleccionada = "Bebidas";
    }//GEN-LAST:event_btnBebidasActionPerformed

    private void btnPostres1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostres1ActionPerformed
        cargarPostres();
        categoriaSeleccionada = "Postres";
    }//GEN-LAST:event_btnPostres1ActionPerformed

    private void pnlCombo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCombo1MouseClicked

    }//GEN-LAST:event_pnlCombo1MouseClicked

    private void lblImagenCombo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo1MouseClicked

        // Obtener datos del combo de desayuno seleccionado
        int index = panelesDesayuno.indexOf(pnlCombo1); // Obtén el índice del panel de desayuno seleccionado
        String nombre = lblNombreDesayuno.get(index).getText(); // Obtén el nombre del combo
        String descripcion = lblDescripcionDesayuno.get(index).getText(); // Obtén la descripción del combo
        double precio = Double.parseDouble(lblPrecioDesayuno.get(index).getText()); // Obtén el precio del combo

        // Obtener la imagen del combo
        JLabel lblImagenCombo = lblImagenDesayuno.get(index);
        Icon icon = lblImagenCombo.getIcon(); // Obtén el icono del JLabel
        ImageIcon imageIcon = null;
        String imagen = "";

        // Verifica si el icono es una instancia de ImageIcon
        if (icon instanceof ImageIcon) {
            imageIcon = (ImageIcon) icon;
            imagen = imageIcon != null ? imageIcon.getDescription() : "";
        } else {
            System.err.println("El icono no es una instancia de ImageIcon");
        }

        // Abrir el formulario FormAgregarAlCarrito y pasarle los datos del combo
        abrirFormularioAgregarAlCarrito(nombre, precio, descripcion, imagen);
        this.setEnabled(false);
    }//GEN-LAST:event_lblImagenCombo1MouseClicked

    private void lblImagenCombo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo2MouseClicked

        // Obtener datos del combo de desayuno seleccionado
        int index = panelesDesayuno.indexOf(pnlCombo2); // Obtén el índice del panel de desayuno seleccionado
        String nombre = lblNombreDesayuno.get(index).getText(); // Obtén el nombre del combo
        String descripcion = lblDescripcionDesayuno.get(index).getText(); // Obtén la descripción del combo
        double precio = Double.parseDouble(lblPrecioDesayuno.get(index).getText()); // Obtén el precio del combo

        // Obtener la imagen del combo
        JLabel lblImagenCombo = lblImagenDesayuno.get(index);
        Icon icon = lblImagenCombo.getIcon(); // Obtén el icono del JLabel
        ImageIcon imageIcon = null;
        String imagen = "";

        // Verifica si el icono es una instancia de ImageIcon
        if (icon instanceof ImageIcon) {
            imageIcon = (ImageIcon) icon;
            imagen = imageIcon != null ? imageIcon.getDescription() : "";
        } else {
            System.err.println("El icono no es una instancia de ImageIcon");
        }

        // Abrir el formulario FormAgregarAlCarrito y pasarle los datos del combo
        abrirFormularioAgregarAlCarrito(nombre, precio, descripcion, imagen);
       this.setEnabled(false);
    }//GEN-LAST:event_lblImagenCombo2MouseClicked

    private void lblImagenCombo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo3MouseClicked
        // Obtener datos del combo de desayuno seleccionado
        int index = panelesDesayuno.indexOf(pnlCombo3); // Obtén el índice del panel de desayuno seleccionado
        String nombre = lblNombreDesayuno.get(index).getText(); // Obtén el nombre del combo
        String descripcion = lblDescripcionDesayuno.get(index).getText(); // Obtén la descripción del combo
        double precio = Double.parseDouble(lblPrecioDesayuno.get(index).getText()); // Obtén el precio del combo

        // Obtener la imagen del combo
        JLabel lblImagenCombo = lblImagenDesayuno.get(index);
        Icon icon = lblImagenCombo.getIcon(); // Obtén el icono del JLabel
        ImageIcon imageIcon = null;
        String imagen = "";

        // Verifica si el icono es una instancia de ImageIcon
        if (icon instanceof ImageIcon) {
            imageIcon = (ImageIcon) icon;
            imagen = imageIcon != null ? imageIcon.getDescription() : "";
        } else {
            System.err.println("El icono no es una instancia de ImageIcon");
        }

        // Abrir el formulario FormAgregarAlCarrito y pasarle los datos del combo
        abrirFormularioAgregarAlCarrito(nombre, precio, descripcion, imagen);
        this.setEnabled(false);
    }//GEN-LAST:event_lblImagenCombo3MouseClicked

    private void lblImagenCombo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo4MouseClicked
        // Obtener datos del combo de desayuno seleccionado
        int index = panelesDesayuno.indexOf(pnlCombo4); // Obtén el índice del panel de desayuno seleccionado
        String nombre = lblNombreDesayuno.get(index).getText(); // Obtén el nombre del combo
        String descripcion = lblDescripcionDesayuno.get(index).getText(); // Obtén la descripción del combo
        double precio = Double.parseDouble(lblPrecioDesayuno.get(index).getText()); // Obtén el precio del combo

        // Obtener la imagen del combo
        JLabel lblImagenCombo = lblImagenDesayuno.get(index);
        Icon icon = lblImagenCombo.getIcon(); // Obtén el icono del JLabel
        ImageIcon imageIcon = null;
        String imagen = "";

        // Verifica si el icono es una instancia de ImageIcon
        if (icon instanceof ImageIcon) {
            imageIcon = (ImageIcon) icon;
            imagen = imageIcon != null ? imageIcon.getDescription() : "";
        } else {
            System.err.println("El icono no es una instancia de ImageIcon");
        }

        // Abrir el formulario FormAgregarAlCarrito y pasarle los datos del combo
        abrirFormularioAgregarAlCarrito(nombre, precio, descripcion, imagen);
        this.setEnabled(false);
    }//GEN-LAST:event_lblImagenCombo4MouseClicked

    private void lblImagenCombo5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo5MouseClicked
        // Obtener datos del combo de desayuno seleccionado
        int index = panelesDesayuno.indexOf(pnlCombo5); // Obtén el índice del panel de desayuno seleccionado
        String nombre = lblNombreDesayuno.get(index).getText(); // Obtén el nombre del combo
        String descripcion = lblDescripcionDesayuno.get(index).getText(); // Obtén la descripción del combo
        double precio = Double.parseDouble(lblPrecioDesayuno.get(index).getText()); // Obtén el precio del combo

        // Obtener la imagen del combo
        JLabel lblImagenCombo = lblImagenDesayuno.get(index);
        Icon icon = lblImagenCombo.getIcon(); // Obtén el icono del JLabel
        ImageIcon imageIcon = null;
        String imagen = "";

        // Verifica si el icono es una instancia de ImageIcon
        if (icon instanceof ImageIcon) {
            imageIcon = (ImageIcon) icon;
            imagen = imageIcon != null ? imageIcon.getDescription() : "";
        } else {
            System.err.println("El icono no es una instancia de ImageIcon");
        }

        // Abrir el formulario FormAgregarAlCarrito y pasarle los datos del combo
        abrirFormularioAgregarAlCarrito(nombre, precio, descripcion, imagen);
        this.setEnabled(false);
    }//GEN-LAST:event_lblImagenCombo5MouseClicked

    private void lblImagenCombo6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo6MouseClicked
        // Obtener datos del combo de desayuno seleccionado
        int index = panelesDesayuno.indexOf(pnlCombo6); // Obtén el índice del panel de desayuno seleccionado
        String nombre = lblNombreDesayuno.get(index).getText(); // Obtén el nombre del combo
        String descripcion = lblDescripcionDesayuno.get(index).getText(); // Obtén la descripción del combo
        double precio = Double.parseDouble(lblPrecioDesayuno.get(index).getText()); // Obtén el precio del combo

        // Obtener la imagen del combo
        JLabel lblImagenCombo = lblImagenDesayuno.get(index);
        Icon icon = lblImagenCombo.getIcon(); // Obtén el icono del JLabel
        ImageIcon imageIcon = null;
        String imagen = "";

        // Verifica si el icono es una instancia de ImageIcon
        if (icon instanceof ImageIcon) {
            imageIcon = (ImageIcon) icon;
            imagen = imageIcon != null ? imageIcon.getDescription() : "";
        } else {
            System.err.println("El icono no es una instancia de ImageIcon");
        }

        // Abrir el formulario FormAgregarAlCarrito y pasarle los datos del combo
        abrirFormularioAgregarAlCarrito(nombre, precio, descripcion, imagen);
        this.setEnabled(false);
    }//GEN-LAST:event_lblImagenCombo6MouseClicked

    private void lblImagenCombo7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo7MouseClicked
// Obtener datos del combo de desayuno seleccionado
        int index = panelesDesayuno.indexOf(pnlCombo7); // Obtén el índice del panel de desayuno seleccionado
        String nombre = lblNombreDesayuno.get(index).getText(); // Obtén el nombre del combo
        String descripcion = lblDescripcionDesayuno.get(index).getText(); // Obtén la descripción del combo
        double precio = Double.parseDouble(lblPrecioDesayuno.get(index).getText()); // Obtén el precio del combo

        // Obtener la imagen del combo
        JLabel lblImagenCombo = lblImagenDesayuno.get(index);
        Icon icon = lblImagenCombo.getIcon(); // Obtén el icono del JLabel
        ImageIcon imageIcon = null;
        String imagen = "";

        // Verifica si el icono es una instancia de ImageIcon
        if (icon instanceof ImageIcon) {
            imageIcon = (ImageIcon) icon;
            imagen = imageIcon != null ? imageIcon.getDescription() : "";
        } else {
            System.err.println("El icono no es una instancia de ImageIcon");
        }

        // Abrir el formulario FormAgregarAlCarrito y pasarle los datos del combo
        abrirFormularioAgregarAlCarrito(nombre, precio, descripcion, imagen);
        this.setEnabled(false);
    }//GEN-LAST:event_lblImagenCombo7MouseClicked

    private void lblImagenCombo8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo8MouseClicked
        // Obtener datos del combo de desayuno seleccionado
        int index = panelesDesayuno.indexOf(pnlCombo8); // Obtén el índice del panel de desayuno seleccionado
        String nombre = lblNombreDesayuno.get(index).getText(); // Obtén el nombre del combo
        String descripcion = lblDescripcionDesayuno.get(index).getText(); // Obtén la descripción del combo
        double precio = Double.parseDouble(lblPrecioDesayuno.get(index).getText()); // Obtén el precio del combo

        // Obtener la imagen del combo
        JLabel lblImagenCombo = lblImagenDesayuno.get(index);
        Icon icon = lblImagenCombo.getIcon(); // Obtén el icono del JLabel
        ImageIcon imageIcon = null;
        String imagen = "";

        // Verifica si el icono es una instancia de ImageIcon
        if (icon instanceof ImageIcon) {
            imageIcon = (ImageIcon) icon;
            imagen = imageIcon != null ? imageIcon.getDescription() : "";
        } else {
            System.err.println("El icono no es una instancia de ImageIcon");
        }

        // Abrir el formulario FormAgregarAlCarrito y pasarle los datos del combo
        abrirFormularioAgregarAlCarrito(nombre, precio, descripcion, imagen);
        this.setEnabled(false);
    }//GEN-LAST:event_lblImagenCombo8MouseClicked

    private void btnInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioSesionActionPerformed
        FormInicioSesion formIncioSesion = new FormInicioSesion();
        formIncioSesion.setVisible(true);
    }//GEN-LAST:event_btnInicioSesionActionPerformed

    private void lblImagenCombo1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo1MouseEntered
        setRedBorder(pnlCombo1, pnlInfoCombo1);
    }//GEN-LAST:event_lblImagenCombo1MouseEntered

    private void lblImagenCombo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo1MouseExited
        removeRedBorder(pnlCombo1, pnlInfoCombo1);
    }//GEN-LAST:event_lblImagenCombo1MouseExited

    private void lblImagenCombo2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo2MouseEntered
        setRedBorder(pnlCombo2, pnlInfoCombo2);
    }//GEN-LAST:event_lblImagenCombo2MouseEntered

    private void lblImagenCombo2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo2MouseExited
        removeRedBorder(pnlCombo2, pnlInfoCombo2);
    }//GEN-LAST:event_lblImagenCombo2MouseExited

    private void lblImagenCombo3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo3MouseEntered
        setRedBorder(pnlCombo3, pnlInfoCombo3);
    }//GEN-LAST:event_lblImagenCombo3MouseEntered

    private void lblImagenCombo3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo3MouseExited
        removeRedBorder(pnlCombo3, pnlInfoCombo3);
    }//GEN-LAST:event_lblImagenCombo3MouseExited

    private void lblImagenCombo4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo4MouseEntered
         setRedBorder(pnlCombo4, pnlInfoCombo4);
    }//GEN-LAST:event_lblImagenCombo4MouseEntered

    private void lblImagenCombo4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo4MouseExited
       removeRedBorder(pnlCombo4, pnlInfoCombo4);
    }//GEN-LAST:event_lblImagenCombo4MouseExited

    private void lblImagenCombo5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo5MouseEntered
        setRedBorder(pnlCombo5, pnlInfoCombo5);
    }//GEN-LAST:event_lblImagenCombo5MouseEntered

    private void lblImagenCombo5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo5MouseExited
        removeRedBorder(pnlCombo5, pnlInfoCombo5);
    }//GEN-LAST:event_lblImagenCombo5MouseExited

    private void lblImagenCombo6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo6MouseEntered
        setRedBorder(pnlCombo6, pnlInfoCombo6);
    }//GEN-LAST:event_lblImagenCombo6MouseEntered

    private void lblImagenCombo6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo6MouseExited
        removeRedBorder(pnlCombo6, pnlInfoCombo6);
    }//GEN-LAST:event_lblImagenCombo6MouseExited

    private void lblImagenCombo7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo7MouseEntered
         setRedBorder(pnlCombo7, pnlInfoCombo7);
    }//GEN-LAST:event_lblImagenCombo7MouseEntered

    private void lblImagenCombo7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo7MouseExited
        removeRedBorder(pnlCombo7, pnlInfoCombo7);
    }//GEN-LAST:event_lblImagenCombo7MouseExited

    private void lblImagenCombo8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo8MouseEntered
        setRedBorder(pnlCombo8, pnlInfoCombo8);
    }//GEN-LAST:event_lblImagenCombo8MouseEntered

    private void lblImagenCombo8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenCombo8MouseExited
        removeRedBorder(pnlCombo8, pnlInfoCombo8);
    }//GEN-LAST:event_lblImagenCombo8MouseExited

    public static void main(String args[]) {
        // Crear una instancia de la clase Carrito
        Carrito carrito = new Carrito();

        // Crear una instancia del formulario para agregar al carrito y pasarle tanto el formulario principal como el carrito como argumentos
        FormAgregarAlCarrito formAgregarAlCarrito = new FormAgregarAlCarrito(null, carrito);

        // Crear una instancia del formulario principal y pasarle el carrito como argumento
        FormMenuPrincipal formMenuPrincipal = new FormMenuPrincipal(formAgregarAlCarrito);

        // Hacer visible el formulario principal en el hilo de despacho de eventos de Swing
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formMenuPrincipal.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBebidas;
    private javax.swing.JButton btnComboCarne;
    private javax.swing.JButton btnComboPollo;
    private javax.swing.JButton btnConfirmarPedido;
    private javax.swing.JButton btnDesayunos;
    private javax.swing.JButton btnEnsaladas;
    private javax.swing.JButton btnIndividuales;
    private javax.swing.JButton btnInicioSesion;
    private javax.swing.JButton btnPostres1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblDescripcionCombo1;
    private javax.swing.JLabel lblDescripcionCombo2;
    private javax.swing.JLabel lblDescripcionCombo3;
    private javax.swing.JLabel lblDescripcionCombo4;
    private javax.swing.JLabel lblDescripcionCombo5;
    private javax.swing.JLabel lblDescripcionCombo6;
    private javax.swing.JLabel lblDescripcionCombo7;
    private javax.swing.JLabel lblDescripcionCombo8;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel lblImagenCombo1;
    private javax.swing.JLabel lblImagenCombo2;
    private javax.swing.JLabel lblImagenCombo3;
    private javax.swing.JLabel lblImagenCombo4;
    private javax.swing.JLabel lblImagenCombo5;
    private javax.swing.JLabel lblImagenCombo6;
    private javax.swing.JLabel lblImagenCombo7;
    private javax.swing.JLabel lblImagenCombo8;
    private javax.swing.JLabel lblNombreCombo1;
    private javax.swing.JLabel lblNombreCombo2;
    private javax.swing.JLabel lblNombreCombo3;
    private javax.swing.JLabel lblNombreCombo4;
    private javax.swing.JLabel lblNombreCombo5;
    private javax.swing.JLabel lblNombreCombo6;
    private javax.swing.JLabel lblNombreCombo7;
    private javax.swing.JLabel lblNombreCombo8;
    private javax.swing.JLabel lblPrecioCombo1;
    private javax.swing.JLabel lblPrecioCombo2;
    private javax.swing.JLabel lblPrecioCombo3;
    private javax.swing.JLabel lblPrecioCombo4;
    private javax.swing.JLabel lblPrecioCombo5;
    private javax.swing.JLabel lblPrecioCombo6;
    private javax.swing.JLabel lblPrecioCombo7;
    private javax.swing.JLabel lblPrecioCombo8;
    private javax.swing.JLabel lblSalir;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelCombos;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel pnlCombo1;
    private javax.swing.JPanel pnlCombo2;
    private javax.swing.JPanel pnlCombo3;
    private javax.swing.JPanel pnlCombo4;
    private javax.swing.JPanel pnlCombo5;
    private javax.swing.JPanel pnlCombo6;
    private javax.swing.JPanel pnlCombo7;
    private javax.swing.JPanel pnlCombo8;
    private javax.swing.JPanel pnlInfoCombo1;
    private javax.swing.JPanel pnlInfoCombo2;
    private javax.swing.JPanel pnlInfoCombo3;
    private javax.swing.JPanel pnlInfoCombo4;
    private javax.swing.JPanel pnlInfoCombo5;
    private javax.swing.JPanel pnlInfoCombo6;
    private javax.swing.JPanel pnlInfoCombo7;
    private javax.swing.JPanel pnlInfoCombo8;
    private javax.swing.JSeparator separator;
    // End of variables declaration//GEN-END:variables
}