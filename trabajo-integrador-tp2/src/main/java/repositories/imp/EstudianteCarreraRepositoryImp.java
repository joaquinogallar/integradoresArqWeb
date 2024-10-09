package repositories.imp;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import repositories.EstudianteCarreraRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Year;

public class EstudianteCarreraRepositoryImp implements EstudianteCarreraRepository {

    @PersistenceContext
    private EntityManager em;
    private static EstudianteCarreraRepositoryImp instance;

    private EstudianteCarreraRepositoryImp(EntityManager em) {
        this.em = em;
    }

    public static EstudianteCarreraRepositoryImp getInstance(EntityManager em) {
        if (instance == null) {
            instance = new EstudianteCarreraRepositoryImp(em);
        }
        return instance;
    }

    @Override
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
