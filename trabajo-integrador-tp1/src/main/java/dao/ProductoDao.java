package dao;

import entities.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductoDao {
    public ResultSet getAllProductos(Connection conn) throws SQLException;
    public ResultSet getProductoById(Connection conn, Long id) throws SQLException;
    public void createProducto(Connection conn, Producto producto) throws SQLException;
    public void createTable(Connection conn) throws SQLException;
    public ResultSet obtenerProductoMayorRecaudo(Connection conn) throws SQLException;
}
