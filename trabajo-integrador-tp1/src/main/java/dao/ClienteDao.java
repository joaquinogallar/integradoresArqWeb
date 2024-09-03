package dao;

import entities.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ClienteDao {
    public ResultSet getAllClientes(Connection conn) throws SQLException;
    public ResultSet getClienteById(Connection conn, Long id) throws SQLException;
    public void createCliente(Connection conn, Cliente cliente) throws SQLException;
    public void createTable(Connection conn) throws SQLException;
    public ResultSet getClientesOrdenados(Connection conn) throws SQLException;
}
