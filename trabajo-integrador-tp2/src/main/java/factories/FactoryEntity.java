package factories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class FactoryEntity {
    public static final int MY_SQL = 1;
    public static final int DERBY = 2;

    public abstract EntityManagerFactory getEmf();
    public abstract EntityManager getEm();

    public void open() {
        getEm().getTransaction().begin();
    }

    public void close() {
        getEm().getTransaction().commit();
        getEm().close();
        getEmf().close();
    }

    public static FactoryEntity getFactoryEntity(int fNumber) {
        switch (fNumber) {
            case MY_SQL:
                EntityManagerFactory emfMySql = Persistence.createEntityManagerFactory("mysql_config");
                return MySqlFactoryEntity.getInstance(emfMySql);
            case DERBY:
                EntityManagerFactory emfDerby = Persistence.createEntityManagerFactory("derby_config");
                return DerbyFactoryEntity.getInstance(emfDerby);
            default:
                return null;
        }
    }
}


