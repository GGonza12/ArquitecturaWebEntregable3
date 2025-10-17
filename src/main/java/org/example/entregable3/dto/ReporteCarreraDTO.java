package org.example.entregable3.dto;

public class ReporteCarreraDTO {
    private String nombreCarrera;
    private long inscriptos;
    private long egresados;
    private long anio;

    public ReporteCarreraDTO() {}

    public ReporteCarreraDTO(String nombreCarrera, long anio, long inscriptos, long egresados) {
        this.nombreCarrera = nombreCarrera;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
        this.anio = anio;
    }
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public long getInscriptos() {
        return inscriptos;
    }

    public long getEgresados() {
        return egresados;
    }

    public long getAnio() {
        return anio;
    }


    @Override
    public String toString() {
        return "ReporteDTO{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", inscriptos=" + inscriptos +
                ", egresados=" + egresados +
                ", anio=" + anio +
                '}';
    }
}

