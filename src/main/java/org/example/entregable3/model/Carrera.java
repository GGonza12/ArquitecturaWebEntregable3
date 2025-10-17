package org.example.entregable3.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Carrera {
    @Id
    @Column(name = "id_carrera")
    private Integer id_carrera;
    @Column(name = "carrera", length = 50, nullable = false)
    private String carrera;
    @Column
    private int duracion;
    @OneToMany(mappedBy = "carrera")
    private List<EstudianteCarrera> estudiantes;

    public Carrera() {}


    public Carrera(int idCarrera, String nombre, int duracion) {
        this.id_carrera = idCarrera;
        this.carrera = nombre;
        this.duracion = duracion;
    }
}
