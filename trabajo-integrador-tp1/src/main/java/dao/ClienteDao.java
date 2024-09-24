package dao;

import entities.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ClienteDao {
    public ResultSet getAllClientes() throws SQLException;
    public ResultSet getClienteById(Long id) throws SQLException;
    public void createCliente(Cliente cliente) throws SQLException;
    public void createTable() throws SQLException;
    public void dropTable() throws SQLException;
    public void loadData() throws SQLException;
    public ResultSet getClientesOrdenados() throws SQLException;
}
