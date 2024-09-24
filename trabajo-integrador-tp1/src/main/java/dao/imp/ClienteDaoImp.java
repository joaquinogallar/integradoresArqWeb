package dao.imp;

import dao.ClienteDao;
import entities.Cliente;
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

public class ClienteDaoImp implements ClienteDao {

    private final Connection connection;

    public ClienteDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet getAllClientes() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getClienteById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void createCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, cliente.getIdCliente());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getEmail());

        ps.executeUpdate();
        connection.commit();
    }

    @Override
    public void createTable() throws SQLException {
            String table = "CREATE TABLE IF NOT EXISTS cliente(" +
                    "idCliente INT PRIMARY KEY, " +
                    "nombre VARCHAR(500), " +
                    "email VARCHAR(150))";

            connection.createStatement().execute(table);
            connection.commit();

    }

    @Override
    public void dropTable() throws SQLException {
        String query = "DROP TABLE IF EXISTS cliente";

        connection.createStatement().execute(query);
        connection.commit();
    }

    @Override
    public void loadData() throws SQLException {
        try {
            CSVParser parserClientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/clientes.csv"));
            for (CSVRecord fila : parserClientes.getRecords()) {
                Cliente c = new Cliente(Integer.parseInt(fila.get(0)), fila.get(1), fila.get(2));
                createCliente(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene un ResultSet con la lista de clientes ordenada por el total facturado.
     * El total facturado se calcula como la suma del producto entre la cantidad y el valor
     * de cada producto en todas las facturas asociadas a un cliente.
     *
     * @return un ResultSet que contiene la lista de clientes ordenada por total facturado en orden descendente
     * @throws SQLException si ocurre un error durante la consulta
     */
    @Override
    public ResultSet getClientesOrdenados() throws SQLException {
        String sql = "SELECT c.idCliente, c.nombre, c.email, SUM(fp.cantidad * p.valor) AS totalFacturado " +
                "FROM cliente c " +
                "JOIN factura f ON c.idCliente = f.idCliente " +
                "JOIN facturaProducto fp ON f.idFactura = fp.idFactura " +
                "JOIN producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre, c.email " +
                "ORDER BY totalFacturado DESC";

        PreparedStatement ps = connection.prepareStatement(sql);
        return ps.executeQuery();
    }


}
