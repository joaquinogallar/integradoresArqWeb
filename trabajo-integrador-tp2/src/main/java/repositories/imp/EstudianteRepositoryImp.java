package repositories.imp;

import entities.Estudiante;
import entities.Genero;
import repositories.EstudianteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class EstudianteRepositoryImp implements EstudianteRepository {

    @PersistenceContext
    private EntityManager em;
    private static EstudianteRepositoryImp instance;

    private EstudianteRepositoryImp(EntityManager em) {
        this.em = em;
    }

    // SINGLETON
    public static synchronized EstudianteRepositoryImp getInstance(EntityManager em) {
        if(instance == null)
            return new EstudianteRepositoryImp(em);
        return instance;
    }

    // CONSULTAS BASICAS
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
    @Override
    public void darAltaEstudiante(Estudiante estudiante) {

    }

    @Override
    public void inscribirEstudiante(Estudiante estudiante) {

    }
    
    @Override
    public List<Estudiante> getEstudiantes() {
        return em.createNativeQuery("SELECT * FROM estudiante e ORDER BY e.nombre", Estudiante.class).getResultList();
    }

    @Override
    public Estudiante getEstudianteByNumeroLibreta(String numeroLibreta) {
        try {
            return em.createQuery("SELECT e FROM estudiante e WHERE e.numeroLibretaUniversitaria = :numeroLibreta", Estudiante.class)
                    .setParameter("numeroLibreta", numeroLibreta)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Estudiante> getEstudiantesByGenero(Genero genero) {
        return em.createQuery("SELECT e FROM estudiante e WHERE e.genero = :genero", Estudiante.class)
                .setParameter("genero", genero)
                .getResultList();
    }
}
