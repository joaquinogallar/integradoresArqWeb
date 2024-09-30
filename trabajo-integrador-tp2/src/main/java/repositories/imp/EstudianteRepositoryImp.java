package repositories.imp;

import dtos.EstudianteDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
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
        Estudiante estudiante = em.find(Estudiante.class, id);
        if(estudiante != null)
            em.remove(estudiante);
    }

    public EstudianteDTO getEstudianteById(Long id) {
        Estudiante estudiante = em.find(Estudiante.class, id);
        return new EstudianteDTO(estudiante);
    }

    // CONSULTAS TP
    @Override
    public void darAltaEstudiante(Long idEstudiante, Long idCarrera) {
        EstudianteCarrera estudianteCarrera = em.createQuery("SELECT ec FROM EstudianteCarrera ec WHERE ec.estudiante.id = :idEstudiante AND ec.carrera.id = :idCarrera", EstudianteCarrera.class)
                .setParameter("idEstudiante", idEstudiante)
                .setParameter("idCarrera", idCarrera)
                .getSingleResult();

        if(estudianteCarrera == null) throw new IllegalArgumentException("No existe vinculo entre el alumno y la carrera");
        em.remove(estudianteCarrera);
    }

    @Override
    public void inscribirEstudiante(Long idEstudiante, Long idCarrera) {
        Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
        if(estudiante == null) throw new IllegalArgumentException("Estudiante no encontrado");

        Carrera carrera = em.find(Carrera.class, idCarrera);
        if(carrera == null) throw new IllegalArgumentException("Carrera no encontrado");

        EstudianteCarrera ec = new EstudianteCarrera(estudiante, carrera);
        em.persist(ec);
    }
    
    @Override
    public List<Estudiante> getEstudiantes() {
        return em.createNativeQuery("SELECT * FROM Estudiante e ORDER BY e.nombre", Estudiante.class).getResultList();
    }

    @Override
    public Estudiante getEstudianteByNumeroLibreta(String numeroLibreta) {
        try {
            return em.createQuery("SELECT e FROM Estudiante e WHERE e.numeroLibretaUniversitaria = :numeroLibreta", Estudiante.class)
                    .setParameter("numeroLibreta", numeroLibreta)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Estudiante> getEstudiantesByGenero(Genero genero) {
        return em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class)
                .setParameter("genero", genero)
                .getResultList();
    }
}
