package factories;

import lombok.Data;
import repositories.imp.CarreraRepository;
import repositories.imp.EstudianteCarreraRepository;
import repositories.imp.EstudianteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Data
public class MySqlFactory extends AbstractFactory {
    // Atributos
    private static MySqlFactory instance = null;

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URI = "jdbc:mysql://localhost:3306/integrador2";
    private EntityManagerFactory emf;
    private EntityManager em;

    // Constructor
    private MySqlFactory(EntityManagerFactory emf) {
        this.emf = emf;
        em = emf.createEntityManager();
    }

    // Metodos
    public static MySqlFactory getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new MySqlFactory(emf);
        }
        return instance;
    }

    public void open() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    public void commit() {
        if(em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    public void close() {
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public CarreraRepository getCarreraRepositoty() {
        return CarreraRepository.getInstance(em);
    }

    @Override
    public EstudianteRepository getEstudianteRepositoty() {
        return EstudianteRepository.getInstance(em);
    }

    @Override
    public EstudianteCarreraRepository getEstudianteCarreraRepository() {
        return EstudianteCarreraRepository.getInstance(em);
    }
}
