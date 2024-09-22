import daos.EstudianteDao;
import entities.Estudiante;
import factories.FactoryEntity;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        FactoryEntity mySqlFactory = FactoryEntity.getFactoryEntity(FactoryEntity.MY_SQL);
        if (mySqlFactory != null) {
            try {
                mySqlFactory.open();

                EntityManager em = mySqlFactory.getEm();
                EstudianteDao estudianteDao = EstudianteDao.getInstance(em);

                Estudiante agus = new Estudiante("Agustina Avril", "Sabbatini Olivera", 20,
                        "Femenino", "444", "Montevideo", "555");

                estudianteDao.createEstudiante(agus);

            } finally {
                mySqlFactory.close();
            }
        } else {
            throw new RuntimeException("Error: no se pudo obtener la fábrica de entidades para MySQL.");
        }
    }
}
