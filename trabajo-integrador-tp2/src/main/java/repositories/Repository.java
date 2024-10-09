package repositories;

import java.io.Serializable;
import java.util.List;

public interface Repository<Entity, ID extends Serializable> {
    public Entity findById(ID id);
    public void persist(Entity entity);
    public Entity delete(ID id);
    public List<Entity> findAll();
}
