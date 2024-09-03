package dao;

import entities.FacturaProducto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface FacturaProductoDao {
    public ResultSet getAllFacturaProductos(Connection conn) throws SQLException;
    public ResultSet getFacturaProductoById(Connection conn, int id) throws SQLException;
    public void createFacturaProducto(Connection conn, FacturaProducto facturaProducto) throws SQLException;
    public void createTable(Connection conn) throws SQLException;
}
