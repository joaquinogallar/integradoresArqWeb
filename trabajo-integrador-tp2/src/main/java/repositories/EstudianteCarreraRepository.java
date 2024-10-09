package repositories;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;

public interface EstudianteCarreraRepository {
    public void setEstudianteAGraduado(Estudiante estudiante, Carrera carrera);
}
