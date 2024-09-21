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
        String uri = "jdbc:mysql://localhost:3306/integradorDb";

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



            /* Clientes, Productos, Facturas y FacturasProductos insertados a mano */
           /* clienteDao.createCliente(connection, new Cliente(1, "joaquin", "jogallar@alumos.exa.unicen.edu.ar"));
            clienteDao.createCliente(connection, new Cliente(2, "luca", "lochoa@alumos.exa.unicen.edu.ar"));
            clienteDao.createCliente(connection, new Cliente(3, "alejo", "arau@alumos.exa.unicen.edu.ar"));

            productoDao.createProducto(connection, new Producto(1, "A", 10));
            productoDao.createProducto(connection, new Producto(2, "B", 20));
            productoDao.createProducto(connection, new Producto(3, "C", 50));

            facturaDao.createFactura(connection, new Factura(1, 1));
            facturaDao.createFactura(connection, new Factura(2, 3));
            facturaDao.createFactura(connection, new Factura(3, 2));

            facturaProductoDao.createFacturaProducto(connection, new FacturaProducto(1, 3, 5));
            facturaProductoDao.createFacturaProducto(connection, new FacturaProducto(2, 2, 6));
            facturaProductoDao.createFacturaProducto(connection, new FacturaProducto(3, 1, 10));*/

            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void inicializarTablas(Connection connection) throws SQLException {

        ClienteDao clienteDao = new ClienteDaoImp();
        FacturaDao facturaDao = new FacturaDaoImp();
        FacturaProductoDao facturaProductoDao = new FacturaProductoDaoImp();
        ProductoDao productoDao = new ProductoDaoImp();

        /* Creación de las tablas (comentar una vez creadas) */
        clienteDao.createTable(connection);
        productoDao.createTable(connection);
        facturaDao.createTable(connection);
        facturaProductoDao.createTable(connection);

        /* Inserto los clientes del archivo csv */
        try {
            CSVParser parserClientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/clientes.csv"));
            for (CSVRecord fila : parserClientes.getRecords()) {
                Cliente c = new Cliente(Integer.parseInt(fila.get(0)), fila.get(1), fila.get(2));
                clienteDao.createCliente(connection, c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* Inserto los productos del archivo csv */
        try {
            CSVParser parserProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/productos.csv"));
            for(CSVRecord fila : parserProductos) {
                Producto p = new Producto(Integer.parseInt(fila.get("idProducto")), fila.get("nombre"), Float.parseFloat(fila.get("valor")));
                productoDao.createProducto(connection, p);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /* Inserto las facturas del archivo csv */
        try {
            CSVParser parserFacturas = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/facturas.csv"));
            for(CSVRecord fila : parserFacturas.getRecords()) {
                Factura f = new Factura(Integer.parseInt(fila.get(0)), Integer.parseInt(fila.get(1)));
                facturaDao.createFactura(connection, f);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /* Inserto las facturasproductos del archivo csv */
        try {
            CSVParser parserFacturasProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/facturas-productos.csv"));
            for(CSVRecord fila : parserFacturasProductos.getRecords()) {
                FacturaProducto fp = new FacturaProducto(Integer.parseInt(fila.get(0)), Integer.parseInt(fila.get(1)), Integer.parseInt(fila.get(2)));
                facturaProductoDao.createFacturaProducto(connection, fp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
