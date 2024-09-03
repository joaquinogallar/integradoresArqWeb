package dao.imp;

import dao.FacturaDao;
import entities.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaDaoImp implements FacturaDao {
    @Override
    public ResultSet getAllFacturas(Connection conn) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getFacturaById(Connection conn, int idFactura) throws SQLException {
        return null;
    }

    @Override
    public void createFactura(Connection conn, Factura factura) throws SQLException {
        String sql = "INSERT INTO factura(idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, factura.getIdFactura());
        ps.setInt(2, factura.getIdCliente());

        ps.executeUpdate();
        conn.commit();
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
            String tabla = "CREATE TABLE factura(" +
                    "idFactura INT PRIMARY KEY, " +
                    "idCliente INT, " +
                    "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente))";

            conn.createStatement().execute(tabla);
            conn.commit();
    }
}
