package repositories.imp;

import dtos.CarreraDTO;
import dtos.ReporteCarreraDTO;
import entities.Carrera;
import repositories.CarreraRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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
    public List<CarreraDTO> getCarreras() {
        List<Carrera> carreras = em.createNativeQuery("SELECT * FROM carrera", Carrera.class).getResultList();
        List<CarreraDTO> carrerasDTO = new ArrayList<>();
        for (Carrera c : carreras)
            carrerasDTO.add(new CarreraDTO(c));

        return carrerasDTO;
    }

    public void deleteCarrera(Long id) {
        Carrera carrera = em.find(Carrera.class, id);
        if(carrera != null)
            em.remove(carrera);
    }

    public CarreraDTO getCarreraById(Long id) {
        Carrera carrera = em.find(Carrera.class, id);
        if(carrera != null) {
            CarreraDTO carreraDTO = new CarreraDTO(carrera);
            return carreraDTO;
        }
        return null;
    }

    @Override
    public void createCarrera(Carrera carrera) {
        this.carrera = carrera;
        em.persist(carrera);
    }

    // CONSULTAS TP
    @Override
    public List<CarreraDTO> getCarrerasConEstudiantes(Carrera carrera) {
        List<Carrera> carreras = em.createQuery("SELECT c FROM EstudianteCarrera ec JOIN ec.carrera c WHERE ec.carrera = :carrera", Carrera.class)
                .setParameter("carrera", carrera)
                .getResultList();
        List<CarreraDTO> carrerasDTO = new ArrayList<>();
        for (Carrera c : carreras)
            carrerasDTO.add(new CarreraDTO(c));

        return carrerasDTO;
    }

    @Override
    public List<CarreraDTO> getCarrerasOrdenadasPorInscripciones() {
        List<Carrera> carreras = em.createQuery("SELECT c, COUNT(ec) as inscriptos FROM EstudianteCarrera ec JOIN ec.carrera c GROUP BY c ORDER BY inscriptos DESC", Carrera.class).getResultList();
        List<CarreraDTO> carrerasDTO = new ArrayList<>();
        for(Carrera c : carreras)
            carrerasDTO.add(new CarreraDTO(c));

        return carrerasDTO;
    }

    @Override
    public List<ReporteCarreraDTO> generarReporteCarreras() {
        List<Carrera> carreras = em.createNativeQuery("SELECT * FROM carrera", Carrera.class).getResultList();

    }

}
