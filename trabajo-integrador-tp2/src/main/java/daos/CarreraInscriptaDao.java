package daos;

import entities.CarreraInscripta;

import javax.persistence.EntityManager;
import java.util.List;

public class CarreraInscriptaDao {
    private EntityManager em;
    private static CarreraInscriptaDao instance;

    private CarreraInscriptaDao(EntityManager em) {
        this.em = em;
    }

    public static synchronized CarreraInscriptaDao getInstance(EntityManager em) {
        if(instance == null)
            return new CarreraInscriptaDao(em);
        return instance;
    }

    public void createCarreraInscripta(CarreraInscripta carreraInscripta) {
        em.persist(carreraInscripta);
    }

    public List<CarreraInscripta> getCaerrerasInscriptas() {
        return em.createQuery("SELECT e FROM carrera_inscripta e", CarreraInscripta.class).getResultList();
    }

    public void deleteCarreraInscripta(Long id) {
        CarreraInscripta carreraInscripta = getCarreraInscriptaById(id);
        if(carreraInscripta != null)
            em.remove(carreraInscripta);
    }

    public CarreraInscripta getCarreraInscriptaById(Long id) {
        return em.find(CarreraInscripta.class, id);
    }

}
