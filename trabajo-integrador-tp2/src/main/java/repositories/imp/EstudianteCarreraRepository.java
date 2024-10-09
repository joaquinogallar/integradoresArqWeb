package repositories.imp;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import repositories.BaseJPARepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Year;

public class EstudianteCarreraRepository extends BaseJPARepository<EstudianteCarrera, Long> {

    @PersistenceContext
    private EntityManager em;
    private static EstudianteCarreraRepository instance;

    private EstudianteCarreraRepository(EntityManager em) {
        super(EstudianteCarrera.class, em);
        this.em = em;
    }

    public static synchronized EstudianteCarreraRepository getInstance(EntityManager em) {
        if (instance == null) {
            instance = new EstudianteCarreraRepository(em);
        }
        return instance;
    }

    public void setEstudianteAGraduado(Estudiante estudiante, Carrera carrera) {
        EstudianteCarrera estudianteCarrera = em.createQuery("SELECT ec FROM EstudianteCarrera ec WHERE ec.estudiante = :estudiante AND ec.carrera = :carrera", EstudianteCarrera.class)
                .setParameter("estudiante", estudiante)
                .setParameter("carrera", carrera)
                .getSingleResult();

        Integer anioActual = Year.now().getValue();
        estudianteCarrera.setGraduado(true);
        estudianteCarrera.setAnioGraduado(anioActual);

        em.merge(estudianteCarrera);
    }
}
