package org.example.entregable3.dto;

public class CarreraInscriptosDTO {
    private String nombreCarrera;
    private Long cantidadInscriptos;

    public CarreraInscriptosDTO(String nombreCarrera, Long cantidadInscriptos) {
        this.nombreCarrera = nombreCarrera;
        this.cantidadInscriptos = cantidadInscriptos;
    }

    public String getNombreCarrera() { return nombreCarrera; }
    public Long getCantidadInscriptos() { return cantidadInscriptos; }

    @Override
    public String toString() {
        return nombreCarrera + " - " + cantidadInscriptos + " inscriptos";
    }
}

