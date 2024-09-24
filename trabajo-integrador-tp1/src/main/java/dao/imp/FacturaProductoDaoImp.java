package dao.imp;

import dao.FacturaProductoDao;
import entities.FacturaProducto;
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

public class FacturaProductoDaoImp implements FacturaProductoDao {

    private final Connection connection;

    public FacturaProductoDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet getAllFacturaProductos() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getFacturaProductoById(int id) throws SQLException {
        return null;
    }

    @Override
    public void createFacturaProducto(FacturaProducto facturaProducto) throws SQLException {
        String sql = "INSERT INTO facturaProducto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, facturaProducto.getIdFactura());
        ps.setInt(2, facturaProducto.getIdProducto());
        ps.setInt(3, facturaProducto.getCantidad());

        ps.executeUpdate();
        connection.commit();
    }

    @Override
    public void createTable() throws SQLException {
            String tabla = "CREATE TABLE IF NOT EXISTS facturaProducto(" +
                    "idFactura INT, " +
                    "idProducto INT, " +
                    "cantidad INT, " +
                    "PRIMARY KEY (idFactura, idProducto), " +
                    "FOREIGN KEY (idFactura) REFERENCES factura(idFactura), " +
                    "FOREIGN KEY (idProducto) REFERENCES producto(idProducto))";

            connection.createStatement().execute(tabla);
            connection.commit();
    }

    @Override
    public void dropTable() throws SQLException {
        String query = "DROP TABLE IF EXISTS facturaProducto";
        connection.createStatement().execute(query);
        connection.commit();
    }

    @Override
    public void loadData() throws SQLException {
        try {
            CSVParser parserFacturasProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/facturas-productos.csv"));
            for(CSVRecord fila : parserFacturasProductos.getRecords()) {
                FacturaProducto fp = new FacturaProducto(Integer.parseInt(fila.get(0)), Integer.parseInt(fila.get(1)), Integer.parseInt(fila.get(2)));
                createFacturaProducto(fp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
