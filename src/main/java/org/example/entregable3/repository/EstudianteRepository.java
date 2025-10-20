package org.example.entregable3.repository;

import org.example.entregable3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    @Query("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
    List<Estudiante> obtenerEstudiantesPorApellido();

    @Query("SELECT e FROM Estudiante e WHERE e.lu = :eLu")
    Estudiante obtenerEstudiantePorNumeroLibreta(int eLu);

    @Query("SELECT e FROM Estudiante e Where e.dni=:dni")
    Estudiante obtenerEstudiantePorDni(int dni);

    @Query("SELECT e FROM Estudiante e WHERE e.genero LIKE :genero")
    List<Estudiante> findByGender(String genero);
}
