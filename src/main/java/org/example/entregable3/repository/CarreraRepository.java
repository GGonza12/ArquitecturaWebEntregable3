package org.example.entregable3.repository;

import org.example.entregable3.dto.CarreraInscriptosDTO;
import org.example.entregable3.model.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    @Query("SELECT c FROM Carrera c WHERE c.carrera = :name")
    public Carrera getCarreraPorNombre(@Param("name") String name);

    // Devuelve las carreras que tienen inscriptos, ordenadas por cantidad de inscriptos (desc)
    @Query("SELECT c FROM EstudianteCarrera ec JOIN ec.carrera c GROUP BY c HAVING COUNT(ec.estudiante) > 0 ORDER BY COUNT(ec.estudiante) DESC")
    public List<Carrera> obtenerCarrerasConEstudiantesInscriptos();

    // Conteo de inscriptos por carrera (se usará en el service para construir el DTO)
    @Query("SELECT COUNT(ec.estudiante) FROM EstudianteCarrera ec WHERE ec.carrera.id_carrera = :carreraId")
    public Long countInscriptosByCarreraId(@Param("carreraId") Integer carreraId);
}
