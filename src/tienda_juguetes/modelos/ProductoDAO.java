
package tienda_juguetes.modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // REGISTRAR PRODUCTO EN MYSQL
    public boolean registrarProducto(Producto producto)
            throws SQLException {

        String sql = "INSERT INTO productos "
                + "(id_producto, nombre, categoria, precio, cantidad) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta =
                     conexion.prepareStatement(sql)) {

            consulta.setString(1, producto.getCodigo());
            consulta.setString(2, producto.getNombre());
            consulta.setString(3, producto.getCategoria());
            consulta.setBigDecimal(4, producto.getPrecio());
            consulta.setInt(5, producto.getCantidad());

            return consulta.executeUpdate() > 0;
        }
    }

    // CONSULTAR TODOS LOS PRODUCTOS
    public List<Producto> listarProductos() throws SQLException {

        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT id_producto, nombre, categoria, "
                + "precio, cantidad FROM productos "
                + "ORDER BY id_producto";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta =
                     conexion.prepareStatement(sql);
             ResultSet resultado = consulta.executeQuery()) {

            while (resultado.next()) {
                productos.add(convertirProducto(resultado));
            }
        }

        return productos;
    }

    // BUSCAR PRODUCTO POR CODIGO
    public Producto buscarProducto(String codigo)
            throws SQLException {

        String sql = "SELECT id_producto, nombre, categoria, "
                + "precio, cantidad FROM productos "
                + "WHERE id_producto = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta =
                     conexion.prepareStatement(sql)) {

            consulta.setString(1, codigo);

            try (ResultSet resultado = consulta.executeQuery()) {

                if (resultado.next()) {
                    return convertirProducto(resultado);
                }
            }
        }

        return null;
    }

    // ACTUALIZAR DATOS DE UN PRODUCTO
    public boolean actualizarProducto(Producto producto)
            throws SQLException {

        String sql = "UPDATE productos SET nombre = ?, "
                + "categoria = ?, precio = ?, cantidad = ? "
                + "WHERE id_producto = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta =
                     conexion.prepareStatement(sql)) {

            consulta.setString(1, producto.getNombre());
            consulta.setString(2, producto.getCategoria());
            consulta.setBigDecimal(3, producto.getPrecio());
            consulta.setInt(4, producto.getCantidad());
            consulta.setString(5, producto.getCodigo());

            return consulta.executeUpdate() > 0;
        }
    }

    // ELIMINAR PRODUCTO POR CODIGO
    public boolean eliminarProducto(String codigo)
            throws SQLException {

        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta =
                     conexion.prepareStatement(sql)) {

            consulta.setString(1, codigo);

            return consulta.executeUpdate() > 0;
        }
    }

    // CONVERTIR UNA FILA MYSQL EN OBJETO JAVA
    private Producto convertirProducto(ResultSet resultado)
            throws SQLException {

        return new Producto(
            resultado.getString("id_producto"),
            resultado.getString("nombre"),
            resultado.getString("categoria"),
            resultado.getBigDecimal("precio"),
            resultado.getInt("cantidad")
        );
    }
}
