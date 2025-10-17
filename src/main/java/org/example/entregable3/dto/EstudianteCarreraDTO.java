package org.example.entregable3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entregable3.model.InscriptionId;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteCarreraDTO {
    private InscriptionId id;
    private int id_estudiante;
    private Integer id_carrera;
    private LocalDate inscripcion;
    private LocalDate graduacion;
    private int antiguedad;
}
