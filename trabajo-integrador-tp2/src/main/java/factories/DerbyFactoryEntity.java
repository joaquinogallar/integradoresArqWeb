package factories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DerbyFactoryEntity extends FactoryEntity {

    private final EntityManagerFactory emf;
    private final EntityManager em;
    private static DerbyFactoryEntity instance;

    private DerbyFactoryEntity(EntityManagerFactory emf) {
        this.emf = emf;
        em = emf.createEntityManager();
    }

    public static synchronized DerbyFactoryEntity getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new DerbyFactoryEntity(emf);
        }
        return instance;
    }

    @Override
    public EntityManagerFactory getEmf() {
        return emf;
    }

    @Override
    public EntityManager getEm() {
        return em;
    }
}
