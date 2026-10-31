
package tienda_juguetes.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class VistaGeneral extends JFrame {

    // CAMPOS DEL FORMULARIO
    public JTextField txtCodigo = new JTextField();
    public JTextField txtNombre = new JTextField();
    public JTextField txtCategoria = new JTextField();
    public JTextField txtPrecio = new JTextField();
    public JTextField txtCantidad = new JTextField();

    // BOTONES
    public JButton btnRegistrar = new JButton("Registrar");
    public JButton btnBuscar = new JButton("Buscar");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnLimpiar = new JButton("Limpiar");
    public JButton btnListar = new JButton("Mostrar todos");

    // TABLA
    public DefaultTableModel modeloTabla;
    public JTable tablaProductos;

    // COLORES DE NUESTRA TIENDA
    private final Color fondo = new Color(242, 246, 252);
    private final Color azul = new Color(35, 75, 145);
    private final Color texto = new Color(44, 55, 75);
    private final Color borde = new Color(210, 220, 235);

    public VistaGeneral() {

        setTitle("Tienda de Juguetes | Sistema de Inventario");
        setSize(950, 650);
        setMinimumSize(new Dimension(800, 550));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crearInterfaz();
    }

    private void crearInterfaz() {

        JPanel principal = new JPanel(new BorderLayout(0, 15));
        principal.setBackground(fondo);
        principal.setBorder(
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        // ENCABEZADO
        JPanel encabezado = new JPanel(new GridLayout(2, 1, 0, 5));
        encabezado.setOpaque(false);

        JLabel titulo = new JLabel(
            "TIENDA DE JUGUETES",
            SwingConstants.CENTER
        );
        titulo.setFont(new Font("Arial", Font.BOLD, 27));
        titulo.setForeground(azul);

        JLabel subtitulo = new JLabel(
            "Sistema de gestion de inventario",
            SwingConstants.CENTER
        );
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitulo.setForeground(texto);

        encabezado.add(titulo);
        encabezado.add(subtitulo);

        principal.add(encabezado, BorderLayout.NORTH);

        // PANEL CENTRAL
        JPanel centro = new JPanel(new BorderLayout(0, 15));
        centro.setOpaque(false);

        // FORMULARIO
        JPanel formulario = new JPanel(new GridLayout(5, 2, 12, 10));
        formulario.setBackground(Color.WHITE);

        formulario.setBorder(
            BorderFactory.createCompoundBorder(
                new LineBorder(borde, 1, true),
                BorderFactory.createEmptyBorder(15, 18, 15, 18)
            )
        );

        agregarCampo(formulario, "Codigo del juguete:", txtCodigo);
        agregarCampo(formulario, "Nombre:", txtNombre);
        agregarCampo(formulario, "Categoria:", txtCategoria);
        agregarCampo(formulario, "Precio:", txtPrecio);
        agregarCampo(formulario, "Cantidad disponible:", txtCantidad);

        centro.add(formulario, BorderLayout.NORTH);

        // TABLA DEL INVENTARIO
        String[] columnas = {
            "Codigo", "Nombre", "Categoria", "Precio", "Cantidad"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setRowHeight(30);
        tablaProductos.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaProductos.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        tablaProductos.setSelectionBackground(
            new Color(210, 228, 250)
        );
        tablaProductos.setSelectionForeground(texto);
        tablaProductos.setGridColor(borde);
        tablaProductos.setShowVerticalLines(false);
        tablaProductos.setFillsViewportHeight(true);

        tablaProductos.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 13)
        );
        tablaProductos.getTableHeader().setBackground(azul);
        tablaProductos.getTableHeader().setForeground(Color.WHITE);
        tablaProductos.getTableHeader().setOpaque(true);

        JScrollPane scroll = new JScrollPane(tablaProductos);
        scroll.setBorder(new LineBorder(borde));

        centro.add(scroll, BorderLayout.CENTER);
        principal.add(centro, BorderLayout.CENTER);

        // PANEL DE BOTONES
        JPanel botones = new JPanel(new GridLayout(2, 3, 12, 10));
        botones.setOpaque(false);

        personalizarBoton(btnRegistrar, new Color(37, 99, 235));
        personalizarBoton(btnBuscar, new Color(79, 70, 229));
        personalizarBoton(btnActualizar, new Color(8, 145, 178));
        personalizarBoton(btnEliminar, new Color(220, 38, 38));
        personalizarBoton(btnLimpiar, new Color(100, 116, 139));
        personalizarBoton(btnListar, new Color(15, 118, 110));

        botones.add(btnRegistrar);
        botones.add(btnBuscar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);
        botones.add(btnListar);

        principal.add(botones, BorderLayout.SOUTH);

        setContentPane(principal);
    }

    private void agregarCampo(
            JPanel panel, String nombre, JTextField campo) {

        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 13));
        etiqueta.setForeground(texto);

        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setBorder(
            BorderFactory.createCompoundBorder(
                new LineBorder(borde, 1, true),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
            )
        );

        panel.add(etiqueta);
        panel.add(campo);
    }

    private void personalizarBoton(JButton boton, Color color) {

        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );
        boton.setPreferredSize(new Dimension(150, 42));
    }
}
