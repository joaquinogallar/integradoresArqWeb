package factories;

import entities.EstudianteCarrera;
import repositories.CarreraRepository;
import repositories.EstudianteRepository;
import repositories.imp.CarreraRepositoryImp;
import repositories.imp.EstudianteCarreraRepositoryImp;
import repositories.imp.EstudianteRepositoryImp;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlFactory extends AbstractFactory {
    // Atributos
    private static MySqlFactory instance = null;

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URI = "jdbc:mysql://localhost:3306/integrador2";
    public static EntityManager em;

    // Constructor
    private MySqlFactory() {
    }

    // Metodos
    public static MySqlFactory getInstance() {
        if (instance == null) {
            instance = new MySqlFactory();
        }
        return instance;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CarreraRepository getCarreraRepositoty() {
        return CarreraRepositoryImp.getInstance(em);
    }

    @Override
    public EstudianteRepository getEstudianteRepositoty() {
        return EstudianteRepositoryImp.getInstance(em);
    }

    @Override
    public EstudianteCarrera getEstudianteCarreraRepository() {
        return EstudianteCarreraRepositoryImp.get;
    }
}
