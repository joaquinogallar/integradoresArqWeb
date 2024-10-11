import dao.ClienteDao;
import dao.FacturaDao;
import dao.FacturaProductoDao;
import dao.ProductoDao;

import factories.AbstractFactory;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        AbstractFactory mySqlFactory = AbstractFactory.getFactory(AbstractFactory.MYSQL_JDBC_DRIVER);

        ClienteDao clienteDao = mySqlFactory.getClienteDao();
        FacturaDao facturaDao = mySqlFactory.getFacturaDao();
        ProductoDao productoDao = mySqlFactory.getProductoDao();
        FacturaProductoDao facturaProductoDao = mySqlFactory.getFacturaProductoDao();

        try {
            // eliminar primero las tablas con FK para evitar conflictos
            facturaProductoDao.dropTable();
            facturaDao.dropTable();
            productoDao.dropTable();
            clienteDao.dropTable();

            clienteDao.createTable();
            facturaDao.createTable();
            productoDao.createTable();
            facturaProductoDao.createTable();

            clienteDao.loadData();
            facturaDao.loadData();
            productoDao.loadData();
            facturaProductoDao.loadData();


            /* Prueba de métodos solicitados en el tp */
            ResultSet productosMayorRecaudo = productoDao.obtenerProductoMayorRecaudo();
            if (productosMayorRecaudo.next()) {
                String nombreProducto = productosMayorRecaudo.getString("nombre");
                double recaudacion = productosMayorRecaudo.getDouble("recaudacion");
                System.out.println("Producto que más recaudó: " + nombreProducto);
                System.out.println("Recaudación total del producto: " + recaudacion);
                System.out.println();
            } else {
                System.out.println("No hay productos vendidos.");
            }

            ResultSet clientesMayorFacturacion = clienteDao.getClientesOrdenados();
            while (clientesMayorFacturacion.next()) {
                String nombreCliente = clientesMayorFacturacion.getString("nombre");
                double recaudacion = clientesMayorFacturacion.getDouble("totalFacturado");
                System.out.println("Cliente: " + nombreCliente);
                System.out.println("Recaudacion total: " + recaudacion);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
