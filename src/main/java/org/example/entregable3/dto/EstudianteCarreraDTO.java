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

    public EstudianteCarreraDTO(int id_estudiante, Integer id_carrera, LocalDate inscripcion, LocalDate graduacion, int antiguedad){
        this.id_estudiante=id_estudiante;
        this.id_carrera=id_carrera;
        this.inscripcion=inscripcion;
        this.graduacion=graduacion;
        this.antiguedad=antiguedad;
        this.id=new InscriptionId(id_estudiante,id_carrera);
    }
}


