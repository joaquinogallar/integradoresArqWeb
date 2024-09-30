package factories;

import entities.EstudianteCarrera;
import repositories.CarreraRepository;
import repositories.EstudianteRepository;

public abstract class AbstractFactory {
    public static final int MYSQL_DRIVER = 1;
    public static final int ORACLE_DRIVER = 2;

    public abstract CarreraRepository getCarreraRepositoty();
    public abstract EstudianteRepository getEstudianteRepositoty();
    public abstract EstudianteCarrera getEstudianteCarreraRepository();

    public static AbstractFactory getFactory(int mode) {
        switch (mode) {
            case MYSQL_DRIVER: return MySqlFactory.getInstance();
            case ORACLE_DRIVER: return null;
            default: return null;
        }
    }
}


