package org.example.entregable3.repository;

import org.example.entregable3.model.Carrera;
import org.example.entregable3.model.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Object> {
    // devuelve las inscripciones (EstudianteCarrera) para poder mapear al estudiante en el service
    @Query("SELECT ec FROM EstudianteCarrera ec JOIN ec.estudiante e WHERE ec.carrera = :carrera AND e.ciudad = :ciudad")
    List<EstudianteCarrera> getEstudiantesCarreraCiudad(Carrera carrera, String ciudad);

    // devuelve filas como Object[]: [carreraNombre, anio, cantidad, graduados]
    @Query("SELECT c.carrera, YEAR(ec.inscripcion), COUNT(ec), SUM(CASE WHEN ec.graduacion IS NOT NULL THEN 1 ELSE 0 END) " +
           "FROM EstudianteCarrera ec JOIN ec.carrera c " +
           "GROUP BY c.carrera, YEAR(ec.inscripcion) " +
           "ORDER BY c.carrera ASC, YEAR(ec.inscripcion) ASC")
    List<Object[]> getReporteCarreras();
}
