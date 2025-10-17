package org.example.entregable3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Estudiante {
    @Id
    private int dni;
    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(length = 50, nullable = false)
    private String apellido;
    @Column()
    private int edad;
    @Column(length = 50, nullable = false)
    private String genero;
    @Column(length = 50, nullable = false)
    private String ciudad;
    @Column(name = "lu",unique = true)
    private int lu;
    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteCarrera> estudianteCarreras;

    public Estudiante() {}

    public Estudiante(Integer dni, String nombre, String apellido, int edad, String genero, String ciudad, int lu) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.lu = lu;
    }
}
