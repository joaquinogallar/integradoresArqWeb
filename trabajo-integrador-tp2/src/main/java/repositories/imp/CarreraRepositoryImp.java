package repositories.imp;

import entities.Carrera;
import repositories.CarreraRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class CarreraRepositoryImp implements CarreraRepository {
    private EntityManager em;
    private static CarreraRepositoryImp instance;
    private Carrera carrera;

    private CarreraRepositoryImp(EntityManager em) {
        this.em = em;
    }

    // SINGLETON
    public static synchronized CarreraRepositoryImp getInstance(EntityManager em) {
        if(instance == null)
            return new CarreraRepositoryImp(em);
        return instance;
    }

    // CONSULTAS BASICAS
    public List<Carrera> getCaerrerasInscriptas() {
        return em.createQuery("SELECT e FROM carrera e", Carrera.class).getResultList();
    }

    public void deleteCarrera(Long id) {
        Carrera carrera = getCarreraById(id);
        if(carrera != null)
            em.remove(carrera);
    }

    public Carrera getCarreraById(Long id) {
        return em.find(Carrera.class, id);
    }

    @Override
    public void createCarrera(Carrera carrera) {
        this.carrera = carrera;
        em.persist(carrera);
    }

    // CONSULTAS TP
    @Override
    public List<Carrera> getCarrerasConEstudiantes(Carrera carrera) {
        return List.of();
    }

    @Override
    public List<Carrera> getCarrerasOrdenadasPorInscripciones() {
        return em.createQuery("SELECT c, COUNT(ec) as inscriptos FROM EstudianteCarrera ec JOIN ec.carrera c GROUP BY c ORDER BY inscriptos DESC", Carrera.class).getResultList();
    }


}
