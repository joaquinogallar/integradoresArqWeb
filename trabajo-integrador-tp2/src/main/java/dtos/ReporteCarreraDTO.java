package dtos;

public class ReporteCarreraDTO {
    private String nombreCarrera;
    private int anio;
    private long cantidadInscriptos;
    private long cantidadEgresados;

    public ReporteCarreraDTO(String nombreCarrera, int anio, long cantidadInscriptos, long cantidadEgresados) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadEgresados = cantidadEgresados;
    }

    // Getters y setters
}
