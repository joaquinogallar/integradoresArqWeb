package factories;

import dao.ClienteDao;
import dao.FacturaDao;
import dao.FacturaProductoDao;
import dao.ProductoDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLFactory extends  AbstractFactory {

    // Atributos
    private static MySQLFactory instance = null;

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URI = "jdbc:mysql://localhost:3306/integrador1";
    public static Connection connection;

    // Constructor
    private MySQLFactory() {
    }

    // Metodos
    public static MySQLFactory getInstance() {
        if (instance == null) {
            instance = new MySQLFactory();
        }
        return instance;
    }

    public static Connection createConnection() {
        if(connection != null) {
            return connection;
        }

        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            connection = DriverManager.getConnection(URI, "root", "");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClienteDao getClienteDao() {
        return null;
    }

    @Override
    public FacturaDao getFacturaDao() {
        return null;
    }

    @Override
    public ProductoDao getProductoDao() {
        return null;
    }

    @Override
    public FacturaProductoDao getFacturaProductoDao() {
        return null;
    }
}
