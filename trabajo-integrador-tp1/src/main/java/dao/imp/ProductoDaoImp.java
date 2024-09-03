package dao.imp;

import dao.ProductoDao;
import entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDaoImp implements ProductoDao {

    @Override
    public ResultSet getAllProductos(Connection conn) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getProductoById(Connection conn, Long id) throws SQLException {
        return null;
    }

    @Override
    public void createProducto(Connection conn, Producto producto) throws SQLException {
        String sql = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, producto.getIdProducto());
        ps.setString(2, producto.getNombre());
        ps.setFloat(3, producto.getValor());

        ps.executeUpdate();
        conn.commit();
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
            String table = "CREATE TABLE producto(" +
                    "idProducto INT PRIMARY KEY, " +
                    "nombre VARCHAR(45), " +
                    "valor FLOAT)";

            conn.createStatement().execute(table);
            conn.commit();

    }

    /**
     * Obtiene el producto con el mayor recaudo, calculado como la suma del producto
     * entre la cantidad vendida y el valor de cada producto en todas las facturas.
     *
     * @param conn la conexión a la base de datos
     * @return un ResultSet que contiene el nombre del producto con el mayor recaudo y su valor total
     * @throws SQLException si ocurre un error durante la consulta
     */
    public ResultSet obtenerProductoMayorRecaudo(Connection conn) throws SQLException {
        String sql = "SELECT p.nombre, SUM(fp.cantidad * p.valor) AS recaudacion " +
                "FROM facturaProducto fp " +
                "JOIN producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY p.idProducto, p.nombre " +
                "ORDER BY recaudacion DESC " +
                "LIMIT 1";

        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }


}
