package repositories.imp;

import dtos.EstudianteDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import entities.Genero;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import repositories.BaseJPARepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepository extends BaseJPARepository<Estudiante, Long> {

    @PersistenceContext
    private EntityManager em;
    private static EstudianteRepository instance;

    private EstudianteRepository(EntityManager em) {
        super(Estudiante.class, em);
        this.em = em;
    }

    // SINGLETON
    public static synchronized EstudianteRepository getInstance(EntityManager em) {
        if(instance == null)
            return new EstudianteRepository(em);
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

    public void cargarDatos(String ruta) throws IOException {
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(ruta));
        for(CSVRecord fila : csvParser.getRecords()) {
            Estudiante estudiante = new Estudiante(fila.get(0), fila.get(1), Integer.parseInt(fila.get(2)), fila.get(3), fila.get(4), fila.get(5), fila.get(6));
            createEstudiante(estudiante);
        }
    }

    // CONSULTAS TP
    public void darAltaEstudiante(Estudiante estudiante, Carrera carrera) {
        EstudianteCarrera estudianteCarrera = em.createQuery("SELECT ec FROM EstudianteCarrera ec WHERE ec.estudiante = :estudiante AND ec.carrera = :carrera", EstudianteCarrera.class)
                .setParameter("estudiante", estudiante)
                .setParameter("carrera", carrera)
                .getSingleResult();

        if(estudianteCarrera == null) throw new IllegalArgumentException("No existe vinculo entre el alumno y la carrera");
        em.remove(estudianteCarrera);
    }

    public void inscribirEstudiante(Estudiante estudiante, Carrera carrera) {
        EstudianteCarrera ec = new EstudianteCarrera(estudiante, carrera);
        estudiante.getCarreras().add(ec);
        carrera.getEstudiantes().add(ec);
        em.persist(ec);
    }

    public List<EstudianteDTO> getEstudiantes() {
        List<Estudiante> estudiantes = em.createNativeQuery("SELECT * FROM Estudiante e ORDER BY e.nombre", Estudiante.class).getResultList();
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();
        for(Estudiante estudiante : estudiantes) {
            estudiantesDTO.add(new EstudianteDTO(estudiante));
        }
        return estudiantesDTO;
    }

    public EstudianteDTO getEstudianteByNumeroLibreta(String numeroLibreta) {
        try {
            Estudiante estudiante = em.createQuery("SELECT e FROM Estudiante e WHERE e.numeroLibretaUniversitaria = :numeroLibreta", Estudiante.class)
                    .setParameter("numeroLibreta", numeroLibreta)
                    .getSingleResult();
            return new EstudianteDTO(estudiante);
        } catch (Exception e) {
            return null;
        }
    }

    public List<EstudianteDTO> getEstudiantesByGenero(Genero genero) {
        List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class)
                .setParameter("genero", genero)
                .getResultList();
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();
        for(Estudiante estudiante : estudiantes) {
            estudiantesDTO.add(new EstudianteDTO(estudiante));
        }
        return estudiantesDTO;
    }

    public List<EstudianteDTO> getEstudiantesPorCarreraYCiudad(Carrera carrera, String ciudadResidencia) {
        List<Estudiante> estudiantes = em.createQuery("SELECT ec.estudiante FROM EstudianteCarrera ec JOIN ec.estudiante e  WHERE ec.carrera = :carrera AND e.ciudadResidencia = :ciudadResidencia", Estudiante.class)
                .setParameter("carrera", carrera)
                .setParameter("ciudadResidencia", ciudadResidencia)
                .getResultList();
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();
        for(Estudiante estudiante : estudiantes) {
            estudiantesDTO.add(new EstudianteDTO(estudiante));
        }
        return estudiantesDTO;
    }
}
