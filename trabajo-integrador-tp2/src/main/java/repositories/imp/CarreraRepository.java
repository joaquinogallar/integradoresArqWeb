package repositories.imp;

import dtos.CarreraDTO;
import dtos.EgresadoDTO;
import dtos.EstudianteDTO;
import dtos.ReporteCarreraDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import repositories.BaseJPARepository;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarreraRepository extends BaseJPARepository<Carrera, Long> {
    private EntityManager em;
    private static CarreraRepository instance;

    private CarreraRepository(EntityManager em) {
        super(Carrera.class, em);
        this.em = em;
    }

    // SINGLETON
    public static synchronized CarreraRepository getInstance(EntityManager em) {
        if(instance == null)
            return new CarreraRepository(em);
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

    public void createCarrera(Carrera carrera) {
        em.persist(carrera);
    }

    public void cargarDatos(String ruta) throws IOException {
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(ruta));
        for(CSVRecord fila : csvParser.getRecords()) {
            Carrera carrera = new Carrera(fila.get(0));
            createCarrera(carrera);
        }
    }

    // CONSULTAS TP
    public List<CarreraDTO> getCarrerasConEstudiantes(Carrera carrera) {
        List<Carrera> carreras = em.createQuery("SELECT c FROM EstudianteCarrera ec JOIN ec.carrera c WHERE ec.carrera = :carrera", Carrera.class)
                .setParameter("carrera", carrera)
                .getResultList();
        List<CarreraDTO> carrerasDTO = new ArrayList<>();
        for (Carrera c : carreras)
            carrerasDTO.add(new CarreraDTO(c));

        return carrerasDTO;
    }

    public List<CarreraDTO> getCarrerasOrdenadasPorInscripciones() {
        List<Carrera> carreras = em.createQuery("SELECT c, COUNT(ec) as inscriptos FROM EstudianteCarrera ec JOIN ec.carrera c GROUP BY c ORDER BY inscriptos DESC", Carrera.class).getResultList();
        List<CarreraDTO> carrerasDTO = new ArrayList<>();
        for(Carrera c : carreras)
            carrerasDTO.add(new CarreraDTO(c));

        return carrerasDTO;
    }

    public List<ReporteCarreraDTO> generarReporteCarreras() {
        List<Carrera> carreras = em.createQuery("SELECT c FROM Carrera c ORDER BY c.nombreCarrera ASC" , Carrera.class).getResultList();
        List<ReporteCarreraDTO> reportes = new ArrayList<>();

        for(Carrera c : carreras) {
            ReporteCarreraDTO reporteCarrera = new ReporteCarreraDTO(c);

            List<EstudianteDTO> inscriptos = getInscriptosPorCarrera(c);
            reporteCarrera.getInscriptos().addAll(inscriptos);

            List<EgresadoDTO> egresados = getEgresadosPorCarrera(c);
            reporteCarrera.getEgresados().addAll(egresados);

            reportes.add(reporteCarrera);
        }

        return reportes;
    }

    public List<EstudianteDTO> getInscriptosPorCarrera(Carrera carrera) {
        List<Estudiante> estudiantes = em.createQuery("SELECT ec.estudiante FROM EstudianteCarrera ec WHERE ec.carrera = :carrera", Estudiante.class)
                .setParameter("carrera", carrera)
                .getResultList();
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();

        for(Estudiante e : estudiantes)
            estudiantesDTO.add(new EstudianteDTO(e));

        return estudiantesDTO;
    }

    public List<EgresadoDTO> getEgresadosPorCarrera(Carrera carrera) {
        List<Estudiante> estudiantes = em.createQuery("SELECT ec.estudiante FROM EstudianteCarrera ec WHERE ec.carrera = :carrera AND ec.graduado = true ORDER BY ec.anioGraduado", Estudiante.class)
                .setParameter("carrera", carrera)
                .getResultList();
        List<EgresadoDTO> egresadosDTO = new ArrayList<>();


        for(Estudiante e : estudiantes) {
            EstudianteCarrera ec = em.createQuery("SELECT ec FROM EstudianteCarrera ec WHERE ec.estudiante = :estudiante AND ec.carrera = :carrera", EstudianteCarrera.class)
                    .setParameter("estudiante", e)
                    .setParameter("carrera", carrera)
                    .getSingleResult();

            egresadosDTO.add(new EgresadoDTO(e, ec));
        }
        return egresadosDTO;
    }

}
