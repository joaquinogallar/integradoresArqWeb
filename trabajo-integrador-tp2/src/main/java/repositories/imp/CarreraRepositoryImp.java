package repositories.imp;

import entities.Carrera;
import repositories.CarreraRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class CarreraRepositoryImp implements CarreraRepository {
    private EntityManager em;
    private static CarreraRepositoryImp instance;

    private CarreraRepositoryImp(EntityManager em) {
        this.em = em;
    }

    public static synchronized CarreraRepositoryImp getInstance(EntityManager em) {
        if(instance == null)
            return new CarreraRepositoryImp(em);
        return instance;
    }

    public void createCarrera(Carrera carreraInscripta) {
        em.persist(carreraInscripta);
    }

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
    public List<Carrera> getCarrerasConEstudiantes(Carrera carrera) {
        return List.of();
    }
}
