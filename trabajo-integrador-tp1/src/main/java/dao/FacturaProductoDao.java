package dao;

import entities.FacturaProducto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface FacturaProductoDao {
    public ResultSet getAllFacturaProductos() throws SQLException;
    public ResultSet getFacturaProductoById(int id) throws SQLException;
    public void createFacturaProducto(FacturaProducto facturaProducto) throws SQLException;
    public void createTable() throws SQLException;
    public void dropTable() throws SQLException;
    public void loadData() throws SQLException;
}
