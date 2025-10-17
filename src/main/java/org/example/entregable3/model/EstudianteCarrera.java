package org.example.entregable3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class EstudianteCarrera {
    @EmbeddedId
    private InscriptionId id;
    @Column
    private LocalDate inscripcion;

    @Column
    private LocalDate graduacion;

    @Column
    private int antiguedad ;
    @MapsId("dni")
    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;
    @MapsId("id_carrera")
    @ManyToOne
    @JoinColumn(name = "id_carrera", nullable = false)
    private Carrera carrera;

    public EstudianteCarrera() {}

    public EstudianteCarrera(InscriptionId id, LocalDate inscripcion, LocalDate graduacion, int antiguedad, Estudiante estudiante, Carrera carrera) {
        this.id = id;
        this.inscripcion = inscripcion;
        this.graduacion = graduacion;
        this.antiguedad = antiguedad;
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
}
