package org.example.entregable3.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class InscriptionId implements Serializable {
    private Integer dni;
    private Integer id_carrera;

    public InscriptionId() {}
    public InscriptionId(Integer dni, int id_carrera) {
        this.dni = dni;
        this.id_carrera = id_carrera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InscriptionId)) return false;
        InscriptionId inscriptionId = (InscriptionId) o;

        return Objects.equals(id_carrera,inscriptionId.id_carrera) && Objects.equals(dni,inscriptionId.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, id_carrera);
    }

}
