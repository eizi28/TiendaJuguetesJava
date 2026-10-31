
package tienda_juguetes.controlador;

import tienda_juguetes.modelos.Producto;
import tienda_juguetes.modelos.ProductoDAO;
import tienda_juguetes.vista.VistaGeneral;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorGeneral {

    private final VistaGeneral vista;
    private final ProductoDAO productoDAO;

    public ControladorGeneral(VistaGeneral vista) {
        this.vista = vista;
        this.productoDAO = new ProductoDAO();

        configurarBotones();
        mostrarProductos();
    }

    // CONECTAR LOS BOTONES CON SUS FUNCIONES
    private void configurarBotones() {

        vista.btnRegistrar.addActionListener(e -> registrar());
        vista.btnBuscar.addActionListener(e -> buscar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnListar.addActionListener(e -> mostrarProductos());

        // Seleccionar un producto de la tabla
        vista.tablaProductos.getSelectionModel()
            .addListSelectionListener(e -> {

                if (!e.getValueIsAdjusting()) {
                    int fila = vista.tablaProductos.getSelectedRow();

                    if (fila >= 0) {
                        int filaModelo = vista.tablaProductos
                                .convertRowIndexToModel(fila);

                        vista.txtCodigo.setText(
                            vista.modeloTabla.getValueAt(
                                filaModelo, 0).toString()
                        );

                        vista.txtNombre.setText(
                            vista.modeloTabla.getValueAt(
                                filaModelo, 1).toString()
                        );

                        vista.txtCategoria.setText(
                            vista.modeloTabla.getValueAt(
                                filaModelo, 2).toString()
                        );

                        vista.txtPrecio.setText(
                            vista.modeloTabla.getValueAt(
                                filaModelo, 3).toString()
                        );

                        vista.txtCantidad.setText(
                            vista.modeloTabla.getValueAt(
                                filaModelo, 4).toString()
                        );
                    }
                }
            });
    }

    // LEER LOS DATOS DEL FORMULARIO
    private Producto obtenerDatos() {

        String codigo = vista.txtCodigo.getText().trim();
        String nombre = vista.txtNombre.getText().trim();
        String categoria = vista.txtCategoria.getText().trim();

        BigDecimal precio = new BigDecimal(
            vista.txtPrecio.getText().trim()
        );

        int cantidad = Integer.parseInt(
            vista.txtCantidad.getText().trim()
        );

        return new Producto(
            codigo, nombre, categoria, precio, cantidad
        );
    }

    // REGISTRAR UN JUGUETE
    private void registrar() {

        try {
            Producto producto = obtenerDatos();

            boolean registrado =
                    productoDAO.registrarProducto(producto);

            if (registrado) {
                mostrarMensaje("Juguete registrado correctamente.");
                limpiar();
                mostrarProductos();
            }

        } catch (SQLException | IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    // BUSCAR UN JUGUETE
    private void buscar() {

        String codigo = vista.txtCodigo.getText().trim();

        if (codigo.isEmpty()) {
            mostrarError("Ingresa el codigo del juguete.");
            return;
        }

        try {
            Producto producto =
                    productoDAO.buscarProducto(codigo);

            if (producto == null) {
                mostrarMensaje("No se encontro el juguete.");
                return;
            }

            vista.txtCodigo.setText(producto.getCodigo());
            vista.txtNombre.setText(producto.getNombre());
            vista.txtCategoria.setText(producto.getCategoria());
            vista.txtPrecio.setText(
                producto.getPrecio().toString()
            );
            vista.txtCantidad.setText(
                String.valueOf(producto.getCantidad())
            );

            mostrarMensaje("Juguete encontrado.");

        } catch (SQLException e) {
            mostrarError(e.getMessage());
        }
    }

    // ACTUALIZAR UN JUGUETE
    private void actualizar() {

        try {
            Producto producto = obtenerDatos();

            boolean actualizado =
                    productoDAO.actualizarProducto(producto);

            if (actualizado) {
                mostrarMensaje("Juguete actualizado correctamente.");
                limpiar();
                mostrarProductos();
            } else {
                mostrarMensaje("No existe ese codigo.");
            }

        } catch (SQLException | IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    // ELIMINAR UN JUGUETE
    private void eliminar() {

        String codigo = vista.txtCodigo.getText().trim();

        if (codigo.isEmpty()) {
            mostrarError("Ingresa el codigo que deseas eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
            vista,
            "¿Deseas eliminar el juguete " + codigo + "?",
            "Confirmar eliminacion",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            boolean eliminado =
                    productoDAO.eliminarProducto(codigo);

            if (eliminado) {
                mostrarMensaje("Juguete eliminado correctamente.");
                limpiar();
                mostrarProductos();
            } else {
                mostrarMensaje("No existe ese juguete.");
            }

        } catch (SQLException e) {
            mostrarError(e.getMessage());
        }
    }

    // MOSTRAR TODOS LOS JUGUETES EN LA TABLA
    private void mostrarProductos() {

        try {
            List<Producto> productos =
                    productoDAO.listarProductos();

            vista.modeloTabla.setRowCount(0);

            for (Producto producto : productos) {

                Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getCategoria(),
                    producto.getPrecio(),
                    producto.getCantidad()
                };

                vista.modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            mostrarError(e.getMessage());
        }
    }

    // LIMPIAR EL FORMULARIO
    private void limpiar() {

        vista.tablaProductos.clearSelection();

        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtCategoria.setText("");
        vista.txtPrecio.setText("");
        vista.txtCantidad.setText("");

        vista.txtCodigo.requestFocus();
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
            vista,
            mensaje,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
