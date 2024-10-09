package repositories;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class BaseJPARepository<Entity, ID extends Serializable> implements Repository<Entity, ID> {

    private EntityManager em;
    private Class<Entity> entityClass;

    public BaseJPARepository(Class<Entity> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    @Override
    public Entity findById(ID id) {
        em.getTransaction().begin();
        Entity entity = em.find(entityClass, id);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public void persist(Entity entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public Entity delete(ID id) {
        Entity e = em.find(entityClass, id);
        if (e != null) {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        }
        return e;
    }

    @Override
    public List<Entity> findAll() {
        em.getTransaction().begin();
        List<Entity> entities = em.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
        em.getTransaction().commit();
        return entities;
    }
}
