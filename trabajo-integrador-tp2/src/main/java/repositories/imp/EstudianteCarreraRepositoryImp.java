package repositories.imp;

import repositories.EstudianteCarreraRepository;

public class EstudianteCarreraRepositoryImp implements EstudianteCarreraRepository {

    private static EstudianteCarreraRepositoryImp instance;

    private EstudianteCarreraRepositoryImp() {}

    public static EstudianteCarreraRepositoryImp getInstance() {
        if (instance == null) {
            instance = new EstudianteCarreraRepositoryImp();
        }
        return instance;
    }
}
