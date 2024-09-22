package daos;

import entities.Estudiante;

import javax.persistence.EntityManager;
import java.util.List;

public class EstudianteDao {
    private EntityManager em;
    private static EstudianteDao instance;

    private EstudianteDao(EntityManager em) {
        this.em = em;
    }

    public static synchronized EstudianteDao getInstance(EntityManager em) {
        if(instance == null)
            return new EstudianteDao(em);
        return instance;
    }

    public void createEstudiante(Estudiante estudiante) {
        em.persist(estudiante);
    }

    public List<Estudiante> getEstudiantes() {
        return em.createQuery("SELECT e FROM estudiante e", Estudiante.class).getResultList();
    }

    public void deleteEstudiante(Long id) {
        Estudiante estudiante = getEstudianteById(id);
        if(estudiante != null)
            em.remove(estudiante);
    }

    public Estudiante getEstudianteById(Long id) {
        return em.find(Estudiante.class, id);
    }
}
