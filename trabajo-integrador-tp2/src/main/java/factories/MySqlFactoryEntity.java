package factories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;

public class MySqlFactoryEntity extends FactoryEntity {

    private final EntityManagerFactory emf;
    private final EntityManager em;
    private static MySqlFactoryEntity instance;

    private MySqlFactoryEntity(EntityManagerFactory emf) {
        this.emf = emf;
        em = emf.createEntityManager();
    }

    public static synchronized MySqlFactoryEntity getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new MySqlFactoryEntity(emf);
        }
        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager getEm() {
        return em;
    }

}
