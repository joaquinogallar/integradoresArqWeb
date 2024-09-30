package repositories.imp;

import dtos.EstudianteDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import entities.Genero;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import repositories.EstudianteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class EstudianteRepositoryImp implements EstudianteRepository {

    @PersistenceContext
    private EntityManager em;
    private static EstudianteRepositoryImp instance;

    private EstudianteRepositoryImp(EntityManager em) {
        this.em = em;
    }

    // SINGLETON
    public static synchronized EstudianteRepositoryImp getInstance(EntityManager em) {
        if(instance == null)
            return new EstudianteRepositoryImp(em);
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

    @Override
    public void cargarDatos(String ruta) throws IOException {
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(ruta));
        for(CSVRecord fila : csvParser.getRecords()) {
            Estudiante estudiante = new Estudiante(fila.get(0), fila.get(1), Integer.parseInt(fila.get(2)), fila.get(3), fila.get(4), fila.get(5), fila.get(6));
            createEstudiante(estudiante);
        }
    }

    // CONSULTAS TP
    @Override
    public void darAltaEstudiante(Estudiante estudiante, Carrera carrera) {
        EstudianteCarrera estudianteCarrera = em.createQuery("SELECT ec FROM EstudianteCarrera ec WHERE ec.estudiante = :estudiante AND ec.carrera = :carrera", EstudianteCarrera.class)
                .setParameter("estudiante", estudiante)
                .setParameter("carrera", carrera)
                .getSingleResult();

        if(estudianteCarrera == null) throw new IllegalArgumentException("No existe vinculo entre el alumno y la carrera");
        em.remove(estudianteCarrera);
    }

    @Override
    public void inscribirEstudiante(Estudiante estudiante, Carrera carrera) {
        EstudianteCarrera ec = new EstudianteCarrera(estudiante, carrera);
        em.persist(ec);
    }
    
    @Override
    public List<Estudiante> getEstudiantes() {
        return em.createNativeQuery("SELECT * FROM Estudiante e ORDER BY e.nombre", Estudiante.class).getResultList();
    }

    @Override
    public Estudiante getEstudianteByNumeroLibreta(String numeroLibreta) {
        try {
            return em.createQuery("SELECT e FROM Estudiante e WHERE e.numeroLibretaUniversitaria = :numeroLibreta", Estudiante.class)
                    .setParameter("numeroLibreta", numeroLibreta)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Estudiante> getEstudiantesByGenero(Genero genero) {
        return em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class)
                .setParameter("genero", genero)
                .getResultList();
    }

    @Override
    public List<Estudiante> getEstudiantesPorCarreraYCiudad(Carrera carrera, String ciudadResidencia) {
        return em.createQuery("SELECT ec.estudiante FROM EstudianteCarrera ec JOIN ec.estudiante e  WHERE ec.carrera = :carrera AND e.ciudadResidencia = :ciudadResidencia", Estudiante.class)
                .setParameter("carrera", carrera)
                .setParameter("ciudadResidencia", ciudadResidencia)
                .getResultList();
    }
}
