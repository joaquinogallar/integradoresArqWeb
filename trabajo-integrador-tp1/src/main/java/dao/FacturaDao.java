package dao;

import entities.Factura;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface FacturaDao {
    public ResultSet getAllFacturas(Connection conn) throws SQLException;
    public ResultSet getFacturaById(Connection conn, int idFactura) throws SQLException;
    public void createFactura(Connection conn, Factura factura) throws SQLException;
    public void createTable(Connection conn) throws SQLException;
}
