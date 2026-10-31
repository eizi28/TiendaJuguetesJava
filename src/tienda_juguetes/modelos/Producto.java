
package tienda_juguetes.modelos;

import java.math.BigDecimal;

public class Producto {

    private String codigo;
    private String nombre;
    private String categoria;
    private BigDecimal precio;
    private int cantidad;

    // Constructor
    public Producto(String codigo, String nombre,
                    String categoria, BigDecimal precio,
                    int cantidad) {

        actualizarDatos(nombre, categoria, precio, cantidad);

        if (codigo == null || codigo.trim().isEmpty()
                || codigo.trim().length() > 10) {
            throw new IllegalArgumentException(
                "El codigo debe tener entre 1 y 10 caracteres."
            );
        }

        this.codigo = codigo.trim().toUpperCase();
    }

    // Actualizar datos del producto
    public void actualizarDatos(String nombre, String categoria,
                                BigDecimal precio, int cantidad) {

        if (nombre == null || nombre.trim().isEmpty()
                || nombre.trim().length() > 60) {
            throw new IllegalArgumentException(
                "El nombre debe tener entre 1 y 60 caracteres."
            );
        }

        if (categoria == null || categoria.trim().isEmpty()
                || categoria.trim().length() > 40) {
            throw new IllegalArgumentException(
                "La categoria debe tener entre 1 y 40 caracteres."
            );
        }

        if (precio == null || precio.signum() < 0
                || precio.precision() - precio.scale() > 10
                || precio.scale() > 2) {
            throw new IllegalArgumentException(
                "El precio debe ser positivo y tener maximo 2 decimales."
            );
        }

        if (cantidad < 0) {
            throw new IllegalArgumentException(
                "La cantidad no puede ser negativa."
            );
        }

        this.nombre = nombre.trim();
        this.categoria = categoria.trim();
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return codigo + " | " + nombre
                + " | " + categoria
                + " | $" + precio
                + " | Cantidad: " + cantidad;
    }
}
