import factories.FactoryEntity;

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
                EstudianteRepositoryImp estudianteDao = EstudianteRepositoryImp.getInstance(em);

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

                estudianteDao.createEstudiante(estudiante1);
                estudianteDao.createEstudiante(estudiante2);
                estudianteDao.createEstudiante(estudiante3);
                estudianteDao.createEstudiante(estudiante4);
                estudianteDao.createEstudiante(estudiante5);
                estudianteDao.createEstudiante(estudiante6);
                estudianteDao.createEstudiante(estudiante7);
                estudianteDao.createEstudiante(estudiante8);
                estudianteDao.createEstudiante(estudiante9);
                estudianteDao.createEstudiante(estudiante10);

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

                estudiante1.inscribirseCarrera(carrera1);
                estudiante2.inscribirseCarrera(carrera2);
                estudiante3.inscribirseCarrera(carrera3);
                estudiante4.inscribirseCarrera(carrera4);
                estudiante5.inscribirseCarrera(carrera5);
                estudiante6.inscribirseCarrera(carrera6);
                estudiante7.inscribirseCarrera(carrera7);
                estudiante8.inscribirseCarrera(carrera8);
                estudiante9.inscribirseCarrera(carrera9);
                estudiante10.inscribirseCarrera(carrera10);

            } finally {
                mySqlFactory.close();
            }
        } else {
            throw new RuntimeException("Error en la creación del MysqlFactory");
        }
    }
}
