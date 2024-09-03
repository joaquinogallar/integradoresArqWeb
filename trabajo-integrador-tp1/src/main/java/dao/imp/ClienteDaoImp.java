package dao.imp;

import dao.ClienteDao;
import entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDaoImp implements ClienteDao {

    @Override
    public ResultSet getAllClientes(Connection conn) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getClienteById(Connection conn, Long id) throws SQLException {
        return null;
    }

    @Override
    public void createCliente(Connection conn, Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, cliente.getIdCliente());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getEmail());

        ps.executeUpdate();
        conn.commit();
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
            String table = "CREATE TABLE cliente(" +
                    "idCliente INT PRIMARY KEY, " +
                    "nombre VARCHAR(500), " +
                    "email VARCHAR(150))";

            conn.createStatement().execute(table);
            conn.commit();

    }

    /**
     * Obtiene un ResultSet con la lista de clientes ordenada por el total facturado.
     * El total facturado se calcula como la suma del producto entre la cantidad y el valor
     * de cada producto en todas las facturas asociadas a un cliente.
     *
     * @param conn la conexión a la base de datos
     * @return un ResultSet que contiene la lista de clientes ordenada por total facturado en orden descendente
     * @throws SQLException si ocurre un error durante la consulta
     */
    @Override
    public ResultSet getClientesOrdenados(Connection conn) throws SQLException {
        String sql = "SELECT c.idCliente, c.nombre, c.email, SUM(fp.cantidad * p.valor) AS totalFacturado " +
                "FROM cliente c " +
                "JOIN factura f ON c.idCliente = f.idCliente " +
                "JOIN facturaProducto fp ON f.idFactura = fp.idFactura " +
                "JOIN producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre, c.email " +
                "ORDER BY totalFacturado DESC";

        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }


}
