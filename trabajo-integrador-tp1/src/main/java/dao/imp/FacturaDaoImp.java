package dao.imp;

import dao.FacturaDao;
import entities.Factura;
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

public class FacturaDaoImp implements FacturaDao {

    private final Connection connection;

    public FacturaDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet getAllFacturas() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getFacturaById(int idFactura) throws SQLException {
        return null;
    }

    @Override
    public void createFactura(Factura factura) throws SQLException {
        String sql = "INSERT INTO factura(idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, factura.getIdFactura());
        ps.setInt(2, factura.getIdCliente());

        ps.executeUpdate();
        connection.commit();
    }

    @Override
    public void createTable() throws SQLException {
            String tabla = "CREATE TABLE IF NOT EXISTS factura(" +
                    "idFactura INT PRIMARY KEY, " +
                    "idCliente INT, " +
                    "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente))";

            connection.createStatement().execute(tabla);
            connection.commit();
    }

    @Override
    public void dropTable() throws SQLException {
        String query = "DROP TABLE IF EXISTS factura";

        connection.createStatement().execute(query);
        connection.commit();
    }

    @Override
    public void loadData() throws SQLException {
        try {
            CSVParser parserFacturas = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/facturas.csv"));
            for(CSVRecord fila : parserFacturas.getRecords()) {
                Factura f = new Factura(Integer.parseInt(fila.get(0)), Integer.parseInt(fila.get(1)));
                createFactura(f);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
