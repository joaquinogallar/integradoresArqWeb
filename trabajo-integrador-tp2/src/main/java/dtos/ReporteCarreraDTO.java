package dtos;

public class ReporteCarreraDTO {
    private String nombreCarrera;
    private int anio;
    private int cantidadInscriptos;
    private int cantidadEgresados;

    public ReporteCarreraDTO(String nombreCarrera, int anio, int cantidadInscriptos, int cantidadEgresados) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadEgresados = cantidadEgresados;
    }
}
