package repositories;

import entities.Carrera;

import java.util.List;

public interface CarreraRepository {
    public List<Carrera> getCarrerasConEstudiantes(Carrera carrera);
}
