package daos;

import entities.Carrera;

import javax.persistence.EntityManager;
import java.util.List;

public class CarreraDao {
    private EntityManager em;
    private static CarreraDao instance;

    private CarreraDao(EntityManager em) {
        this.em = em;
    }

    public static synchronized CarreraDao getInstance(EntityManager em) {
        if(instance == null)
            return new CarreraDao(em);
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

}
