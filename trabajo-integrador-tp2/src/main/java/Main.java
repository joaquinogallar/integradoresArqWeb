import factories.AbstractFactory;

import repositories.CarreraRepository;
import repositories.EstudianteCarreraRepository;
import repositories.EstudianteRepository;
import repositories.imp.CarreraRepositoryImp;
import repositories.imp.EstudianteRepositoryImp;
import entities.Carrera;
import entities.Estudiante;

import javax.persistence.EntityManager;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AbstractFactory mySqlFactory = AbstractFactory.getFactory(AbstractFactory.MYSQL_DRIVER);

        String ruta = "src/main/java/csv/";

        if (mySqlFactory != null) {
            mySqlFactory.open();

            EstudianteRepository estudianteRepository = mySqlFactory.getEstudianteRepositoty();
            CarreraRepository carreraRepository = mySqlFactory.getCarreraRepositoty();
            EstudianteCarreraRepository estudianteCarreraRepository = mySqlFactory.getEstudianteCarreraRepository();

            // CARRERAS
            carreraRepository.cargarDatos(ruta + "carreras.csv");

            // ESTUDIANTES
            estudianteRepository.cargarDatos(ruta + "estudiantes.csv");

            // ANOTAR ESTUDIANTES



            mySqlFactory.commit();

        } else {
            throw new RuntimeException("Error en la creación del MysqlFactory");
        }
    }
}
