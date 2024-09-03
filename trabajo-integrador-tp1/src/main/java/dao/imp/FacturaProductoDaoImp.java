package dao.imp;

import dao.FacturaProductoDao;
import entities.FacturaProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaProductoDaoImp implements FacturaProductoDao {
    @Override
    public ResultSet getAllFacturaProductos(Connection conn) throws SQLException {
        return null;
    }

    @Override
    public ResultSet getFacturaProductoById(Connection conn, int id) throws SQLException {
        return null;
    }

    @Override
    public void createFacturaProducto(Connection conn, FacturaProducto facturaProducto) throws SQLException {
        String sql = "INSERT INTO facturaProducto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, facturaProducto.getIdFactura());
        ps.setInt(2, facturaProducto.getIdProducto());
        ps.setInt(3, facturaProducto.getCantidad());

        ps.executeUpdate();
        conn.commit();
    }

    @Override
    public void createTable(Connection conn) throws SQLException {
            String tabla = "CREATE TABLE facturaProducto(" +
                    "idFactura INT, " +
                    "idProducto INT, " +
                    "cantidad INT, " +
                    "PRIMARY KEY (idFactura, idProducto), " +
                    "FOREIGN KEY (idFactura) REFERENCES factura(idFactura), " +
                    "FOREIGN KEY (idProducto) REFERENCES producto(idProducto))";

            conn.createStatement().execute(tabla);
            conn.commit();
    }
}
