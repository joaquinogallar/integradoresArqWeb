import dao.ClienteDao;
import dao.FacturaDao;
import dao.FacturaProductoDao;
import dao.ProductoDao;
import dao.imp.ClienteDaoImp;
import dao.imp.FacturaDaoImp;
import dao.imp.FacturaProductoDaoImp;
import dao.imp.ProductoDaoImp;
import entities.Cliente;
import entities.Factura;
import entities.FacturaProducto;
import entities.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/integrador1";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            Connection connection = DriverManager.getConnection(uri, "root", "rootpassword");
            connection.setAutoCommit(false);

            ClienteDao clienteDao = new ClienteDaoImp();
            FacturaDao facturaDao = new FacturaDaoImp();
            FacturaProductoDao facturaProductoDao = new FacturaProductoDaoImp();
            ProductoDao productoDao = new ProductoDaoImp();

            inicializarTablas(connection);

            /* Prueba de métodos solicitados en el tp */
            ResultSet productosMayorRecaudo = productoDao.obtenerProductoMayorRecaudo(connection);
            if (productosMayorRecaudo.next()) {
                String nombreProducto = productosMayorRecaudo.getString("nombre");
                double recaudacion = productosMayorRecaudo.getDouble("recaudacion");
                System.out.println("Producto que más recaudó: " + nombreProducto);
                System.out.println("Recaudación total del producto: " + recaudacion);
                System.out.println();
            } else {
                System.out.println("No hay productos vendidos.");
            }

            ResultSet clientesMayorFacturacion = clienteDao.getClientesOrdenados(connection);
            while (clientesMayorFacturacion.next()) {
                String nombreCliente = clientesMayorFacturacion.getString("nombre");
                double recaudacion = clientesMayorFacturacion.getDouble("totalFacturado");
                System.out.println("Cliente: " + nombreCliente);
                System.out.println("Recaudacion total: " + recaudacion);
            }


            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
