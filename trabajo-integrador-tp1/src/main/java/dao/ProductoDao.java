package dao;

import entities.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductoDao {
    public ResultSet getAllProductos() throws SQLException;
    public ResultSet getProductoById(Long id) throws SQLException;
    public void createProducto(Producto producto) throws SQLException;
    public void createTable() throws SQLException;
    public void dropTable() throws SQLException;
    public void loadData() throws SQLException;
    public ResultSet obtenerProductoMayorRecaudo() throws SQLException;
}
