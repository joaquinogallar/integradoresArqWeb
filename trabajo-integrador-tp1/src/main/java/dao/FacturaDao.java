package dao;

import entities.Factura;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface FacturaDao {
    public ResultSet getAllFacturas() throws SQLException;
    public ResultSet getFacturaById(int idFactura) throws SQLException;
    public void createFactura(Factura factura) throws SQLException;
    public void createTable() throws SQLException;
    public void dropTable() throws SQLException;
    public void loadData() throws SQLException;
}
