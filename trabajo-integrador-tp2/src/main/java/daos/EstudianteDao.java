package daos;

import entities.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class EstudianteDao {

    @PersistenceContext
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


    public void deleteEstudiante(Long id) {
        Estudiante estudiante = getEstudianteById(id);
        if(estudiante != null)
            em.remove(estudiante);
    }

    public Estudiante getEstudianteById(Long id) {
        return em.find(Estudiante.class, id);
    }


    // CONSULTAS TP
    public List<Estudiante> getEstudiantes() {
        return em.createNativeQuery("SELECT * FROM estudiante e ORDER BY e.nombre", Estudiante.class).getResultList();
    }

    public Estudiante getEstudianteByNumeroLibreta(String numeroLibreta) {
        try {
            return em.createQuery("SELECT e FROM Estudiante e WHERE e.numeroLibretaUniversitaria = :numeroLibreta", Estudiante.class)
                    .setParameter("numeroLibreta", numeroLibreta)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
