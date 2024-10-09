import dtos.CarreraDTO;
import dtos.EstudianteDTO;
import dtos.ReporteCarreraDTO;
import factories.AbstractFactory;

import repositories.CarreraRepository;
import repositories.EstudianteCarreraRepository;
import repositories.EstudianteRepository;
import repositories.imp.CarreraRepositoryImp;
import repositories.imp.EstudianteRepositoryImp;
import entities.Carrera;
import entities.Estudiante;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AbstractFactory mySqlFactory = AbstractFactory.getFactory(AbstractFactory.MYSQL_DRIVER);

        String ruta = "src/main/java/csv/";

        if (mySqlFactory != null) {
            mySqlFactory.open();

            EstudianteRepository estudianteRepository = mySqlFactory.getEstudianteRepositoty();
            CarreraRepository carreraRepository = mySqlFactory.getCarreraRepositoty();
            EstudianteCarreraRepository estudianteCarreraRepository = mySqlFactory.getEstudianteCarreraRepository();

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
            Estudiante estudiante11 = new Estudiante("Gonzalo", "Torres", 20, "Masculino", "55556666", "La Plata", "LU99876");
            Estudiante estudiante12 = new Estudiante("Isabella", "Ríos", 19, "Femenino", "22223333", "Bahía Blanca", "LU99877");
            Estudiante estudiante13 = new Estudiante("Fernando", "Cruz", 22, "Masculino", "77778888", "Tucumán", "LU99878");
            Estudiante estudiante14 = new Estudiante("Camila", "Salazar", 21, "Femenino", "88889999", "Córdoba", "LU99879");
            Estudiante estudiante15 = new Estudiante("Martín", "Mendoza", 23, "Masculino", "99990000", "San Juan", "LU99880");
            Estudiante estudiante16 = new Estudiante("Luciana", "Salas", 20, "Femenino", "11112222", "Santa Cruz", "LU99881");
            Estudiante estudiante17 = new Estudiante("Diego", "Acosta", 24, "Masculino", "33334444", "Mendoza", "LU99882");
            Estudiante estudiante18 = new Estudiante("Sofia", "Valdés", 22, "Femenino", "44445555", "Rosario", "LU99883");
            Estudiante estudiante19 = new Estudiante("Nicolás", "Silva", 21, "Masculino", "66667777", "Santa Fe", "LU99884");
            Estudiante estudiante20 = new Estudiante("Carla", "Núñez", 23, "Femenino", "55557777", "Salta", "LU99885");

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
            estudianteRepository.createEstudiante(estudiante11);
            estudianteRepository.createEstudiante(estudiante12);
            estudianteRepository.createEstudiante(estudiante13);
            estudianteRepository.createEstudiante(estudiante14);
            estudianteRepository.createEstudiante(estudiante15);
            estudianteRepository.createEstudiante(estudiante16);
            estudianteRepository.createEstudiante(estudiante17);
            estudianteRepository.createEstudiante(estudiante18);
            estudianteRepository.createEstudiante(estudiante19);
            estudianteRepository.createEstudiante(estudiante20);

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

            estudianteRepository.inscribirEstudiante(estudiante1, carrera1);
            estudianteRepository.inscribirEstudiante(estudiante2, carrera2);
            estudianteRepository.inscribirEstudiante(estudiante3, carrera3);
            estudianteRepository.inscribirEstudiante(estudiante4, carrera4);
            estudianteRepository.inscribirEstudiante(estudiante5, carrera5);
            estudianteRepository.inscribirEstudiante(estudiante6, carrera6);
            estudianteRepository.inscribirEstudiante(estudiante7, carrera1);
            estudianteRepository.inscribirEstudiante(estudiante8, carrera2);
            estudianteRepository.inscribirEstudiante(estudiante9, carrera3);
            estudianteRepository.inscribirEstudiante(estudiante10, carrera4);
            estudianteRepository.inscribirEstudiante(estudiante11, carrera5);
            estudianteRepository.inscribirEstudiante(estudiante18, carrera7);
            estudianteRepository.inscribirEstudiante(estudiante19, carrera8);
            estudianteRepository.inscribirEstudiante(estudiante20, carrera9);
            estudianteRepository.inscribirEstudiante(estudiante3, carrera5);
            estudianteRepository.inscribirEstudiante(estudiante4, carrera6);
            estudianteRepository.inscribirEstudiante(estudiante5, carrera7);
            estudianteRepository.inscribirEstudiante(estudiante6, carrera8);
            estudianteRepository.inscribirEstudiante(estudiante7, carrera9);
            estudianteRepository.inscribirEstudiante(estudiante8, carrera10);
            estudianteRepository.inscribirEstudiante(estudiante9, carrera6);
            estudianteRepository.inscribirEstudiante(estudiante10, carrera7);

            estudianteCarreraRepository.setEstudianteAGraduado(estudiante1, carrera1);
            estudianteCarreraRepository.setEstudianteAGraduado(estudiante2, carrera2);
            estudianteCarreraRepository.setEstudianteAGraduado(estudiante3, carrera3);
            estudianteCarreraRepository.setEstudianteAGraduado(estudiante4, carrera4);


            List<CarreraDTO> carrerasEstudiantesInscriptos = carreraRepository.getCarrerasConEstudiantes(carrera1);
            List<EstudianteDTO> estudiantesPorResidencia = estudianteRepository.getEstudiantesPorCarreraYCiudad(carrera6, "Mar del plata");

            carrerasEstudiantesInscriptos.forEach(carreraDTO -> System.out.println(carreraDTO.toString()));
            estudiantesPorResidencia.forEach(carreraDTO -> System.out.println(carreraDTO.toString()));

            List<ReporteCarreraDTO> reportes = carreraRepository.generarReporteCarreras();
            reportes.forEach(r -> System.out.println(r));

            mySqlFactory.commit();

        } else {
            throw new RuntimeException("Error en la creación del MysqlFactory");
        }
    }
}
