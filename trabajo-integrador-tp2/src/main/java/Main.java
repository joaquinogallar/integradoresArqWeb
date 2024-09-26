import factories.FactoryEntity;

import repositories.CarreraRepository;
import repositories.EstudianteRepository;
import repositories.imp.CarreraRepositoryImp;
import repositories.imp.EstudianteRepositoryImp;
import entities.Carrera;
import entities.Estudiante;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        FactoryEntity mySqlFactory = FactoryEntity.getFactoryEntity(FactoryEntity.MY_SQL);
        if (mySqlFactory != null) {
            try {
                mySqlFactory.open();

                EntityManager em = mySqlFactory.getEm();
                EstudianteRepository estudianteRepository = EstudianteRepositoryImp.getInstance(em);
                CarreraRepository carreraRepository = CarreraRepositoryImp.getInstance(em);

                /* ESTUDIANTES */
                Estudiante estudiante1 = new Estudiante("Juan", "Pérez", 20, "Masculino", "12345678", "Buenos Aires", "LU12345");
                Estudiante estudiante2 = new Estudiante("Ana", "Gómez", 22, "Femenino", "87654321", "Córdoba", "LU54321");
                Estudiante estudiante3 = new Estudiante("Carlos", "Rodríguez", 19, "Masculino", "11223344", "Rosario", "LU11223");
                Estudiante estudiante4 = new Estudiante("María", "Fernández", 21, "Femenino", "55667788", "Mendoza", "LU33445");
                Estudiante estudiante5 = new Estudiante("Pedro", "Martínez", 23, "Masculino", "99887766", "La Plata", "LU77889");
                Estudiante estudiante6 = new Estudiante("Lucía", "López", 20, "Femenino", "44332211", "Mar del Plata", "LU11234");
                Estudiante estudiante7 = new Estudiante("Jorge", "González", 24, "Masculino", "33445566", "Santa Fe", "LU55667");
                Estudiante estudiante8 = new Estudiante("Valentina", "Sosa", 22, "Femenino", "66778899", "Tucumán", "LU77890");
                Estudiante estudiante9 = new Estudiante("Federico", "Ruiz", 21, "Masculino", "99884477", "Neuquén", "LU44556");
                Estudiante estudiante10 = new Estudiante("Sofía", "Pereyra", 23, "Femenino", "22334455", "Salta", "LU33456");

                estudianteRepository.createEstudiante(estudiante1);
                estudianteRepository.createEstudiante(estudiante2);
                estudianteRepository.createEstudiante(estudiante3);
                estudianteRepository.createEstudiante(estudiante4);
                estudianteRepository.createEstudiante(estudiante5);
                estudianteRepository.createEstudiante(estudiante6);
                estudianteRepository.createEstudiante(estudiante7);
                estudianteRepository.createEstudiante(estudiante8);
                estudianteRepository.createEstudiante(estudiante9);
                estudianteRepository.createEstudiante(estudiante10);

                /* CARRERAS */
                Carrera carrera1 = new Carrera("Ingeniería en Sistemas");
                Carrera carrera2 = new Carrera("Medicina");
                Carrera carrera3 = new Carrera("Arquitectura");
                Carrera carrera4 = new Carrera("Derecho");
                Carrera carrera5 = new Carrera("Psicología");
                Carrera carrera6 = new Carrera("Economía");
                Carrera carrera7 = new Carrera("Ingeniería Civil");
                Carrera carrera8 = new Carrera("Farmacia");
                Carrera carrera9 = new Carrera("Veterinaria");
                Carrera carrera10 = new Carrera("Comunicación Social");

                carreraRepository.createCarrera(carrera1);
                carreraRepository.createCarrera(carrera2);
                carreraRepository.createCarrera(carrera3);
                carreraRepository.createCarrera(carrera4);
                carreraRepository.createCarrera(carrera5);
                carreraRepository.createCarrera(carrera6);
                carreraRepository.createCarrera(carrera7);
                carreraRepository.createCarrera(carrera8);
                carreraRepository.createCarrera(carrera9);
                carreraRepository.createCarrera(carrera10);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Error en la creación del MysqlFactory");
        }
    }
}
