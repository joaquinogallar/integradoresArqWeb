package factories;

import dao.ClienteDao;
import dao.FacturaDao;
import dao.FacturaProductoDao;
import dao.ProductoDao;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC_DRIVER = 1;
    public static final int ORACLE_JDBC_DRIVER = 2;

    public abstract ClienteDao getClienteDao();
    public abstract FacturaDao getFacturaDao();
    public abstract ProductoDao getProductoDao();
    public abstract FacturaProductoDao getFacturaProductoDao();

    public static AbstractFactory getFactory(int mode) {
        switch (mode) {
            case MYSQL_JDBC_DRIVER: return MySQLFactory.getInstance();
            case ORACLE_JDBC_DRIVER: return null;
            default: return null;
        }
    }
}
