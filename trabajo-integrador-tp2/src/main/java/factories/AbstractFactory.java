package factories;

import repositories.imp.CarreraRepository;
import repositories.imp.EstudianteCarreraRepository;
import repositories.imp.EstudianteRepository;

import javax.persistence.Persistence;

public abstract class AbstractFactory {
    public static final int MYSQL_DRIVER = 1;
    public static final int ORACLE_DRIVER = 2;

    public abstract CarreraRepository getCarreraRepositoty();
    public abstract EstudianteRepository getEstudianteRepositoty();
    public abstract EstudianteCarreraRepository getEstudianteCarreraRepository();

    public abstract void open();
    public abstract void commit();
    public abstract void close();

    public static AbstractFactory getFactory(int mode) {
        switch (mode) {
            case MYSQL_DRIVER: return MySqlFactory.getInstance(Persistence.createEntityManagerFactory("mysql_config"));
            case ORACLE_DRIVER: return null;
            default: return null;
        }
    }
}


