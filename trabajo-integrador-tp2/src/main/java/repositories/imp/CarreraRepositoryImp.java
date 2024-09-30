package repositories.imp;

import dtos.CarreraDTO;
import dtos.EstudianteDTO;
import dtos.ReporteCarreraDTO;
import entities.Carrera;
import entities.Estudiante;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import repositories.CarreraRepository;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    @Override
    public void cargarDatos(String ruta) throws IOException {
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(ruta));
        for(CSVRecord fila : csvParser.getRecords()) {
            Carrera carrera = new Carrera(fila.get(0));
            createCarrera(carrera);
        }
    }

    // CONSULTAS TP
    @Override
    public List<CarreraDTO> getCarrerasConEstudiantes(Long id) {
        Carrera carrera = em.find(Carrera.class, id);
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
        List<Carrera> carreras = em.createQuery("SELECT c FROM Carrera c ORDER BY c.nombreCarrera ASC" , Carrera.class).getResultList();
        List<ReporteCarreraDTO> reportes = new ArrayList<>();

        for(Carrera c : carreras) {
            ReporteCarreraDTO reporteCarrera = new ReporteCarreraDTO(c);

            List<EstudianteDTO> inscriptos = obtenerInscriptosPorCarrera(c.getId());
            reporteCarrera.getInscriptos().addAll(inscriptos);

            List<EstudianteDTO> egresados = obtenerEgresadosPorCarrera(c.getId());
            reporteCarrera.getEgresados().addAll(egresados);

            reportes.add(reporteCarrera);
        }

        return reportes;
    }

    @Override
    public List<EstudianteDTO> obtenerInscriptosPorCarrera(Long id) {
        Carrera carrera = em.find(Carrera.class, id);
        List<Estudiante> estudiantes = em.createQuery("SELECT ec.estudiante FROM EstudianteCarrera ec WHERE ec.carrera = :carrera", Estudiante.class)
                .setParameter("carrera", carrera)
                .getResultList();
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();

        for(Estudiante e : estudiantes)
            estudiantesDTO.add(new EstudianteDTO(e));

        return estudiantesDTO;
    }

    @Override
    public List<EstudianteDTO> obtenerEgresadosPorCarrera(Long id) {
        Carrera carrera = em.find(Carrera.class, id);
        List<Estudiante> estudiantes = em.createQuery("SELECT ec.estudiante FROM EstudianteCarrera ec WHERE ec.carrera = :carrera AND ec.graduado = true", Estudiante.class)
                .setParameter("carrera", carrera)
                .getResultList();
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();

        for(Estudiante e : estudiantes)
            estudiantesDTO.add(new EstudianteDTO(e));

        return estudiantesDTO;
    }

}
