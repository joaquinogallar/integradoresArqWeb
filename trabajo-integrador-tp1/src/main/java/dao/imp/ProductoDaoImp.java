package dao.imp;

import dao.ProductoDao;
import entities.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDaoImp implements ProductoDao {

    private final Connection connection;

    public ProductoDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet getAllProductos() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getProductoById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void createProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, producto.getIdProducto());
        ps.setString(2, producto.getNombre());
        ps.setFloat(3, producto.getValor());

        ps.executeUpdate();
        connection.commit();
    }

    @Override
    public void createTable() throws SQLException {
            String table = "CREATE TABLE IF NOT EXISTS producto(" +
                    "idProducto INT PRIMARY KEY, " +
                    "nombre VARCHAR(45), " +
                    "valor FLOAT)";

            connection.createStatement().execute(table);
            connection.commit();

    }

    @Override
    public void dropTable() throws SQLException {
        String query = "DROP TABLE IF EXISTS producto";

        connection.createStatement().execute(query);
        connection.commit();
    }

    @Override
    public void loadData() throws SQLException {
        try {
            CSVParser parserProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/productos.csv"));
            for(CSVRecord fila : parserProductos) {
                Producto p = new Producto(Integer.parseInt(fila.get("idProducto")), fila.get("nombre"), Float.parseFloat(fila.get("valor")));
                createProducto(p);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {w
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene el producto con el mayor recaudo, calculado como la suma del producto
     * entre la cantidad vendida y el valor de cada producto en todas las facturas.
     *
     * @return un ResultSet que contiene el nombre del producto con el mayor recaudo y su valor total
     * @throws SQLException si ocurre un error durante la consulta
     */
    public ResultSet obtenerProductoMayorRecaudo() throws SQLException {
        String sql = "SELECT p.nombre, SUM(fp.cantidad * p.valor) AS recaudacion " +
                "FROM facturaProducto fp " +
                "JOIN producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY p.idProducto, p.nombre " +
                "ORDER BY recaudacion DESC " +
                "LIMIT 1";

        PreparedStatement ps = connection.prepareStatement(sql);
        return ps.executeQuery();
    }


}
