package org.example.entregable3.dto;

public class CarreraDTO {
    private String carrera;
    private int duracion;

    public CarreraDTO(){}

    public CarreraDTO(String carrera, int duracion) {
        this.carrera = carrera;
        this.duracion = duracion;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "CarreraDTO{" +
                "carrera='" + carrera + '\'' +
                ", duracion=" + duracion +
                '}';
    }
}
