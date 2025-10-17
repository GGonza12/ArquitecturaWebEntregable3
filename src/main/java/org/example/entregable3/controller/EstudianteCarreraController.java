package org.example.entregable3.controller;

import org.example.entregable3.dto.EstudianteCarreraDTO;
import org.example.entregable3.dto.EstudianteDTO;
import org.example.entregable3.dto.ReporteCarreraDTO;
import org.example.entregable3.model.Carrera;
import org.example.entregable3.model.EstudianteCarrera;
import org.example.entregable3.service.CarreraService;
import org.example.entregable3.service.EstudianteCarreraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudianteCarrera")
public class EstudianteCarreraController {

    private final EstudianteCarreraService service;
    private final CarreraService carreraService;

    public EstudianteCarreraController(EstudianteCarreraService service, CarreraService carreraService) {
        this.service = service;
        this.carreraService = carreraService;
    }
    @GetMapping()
    public ResponseEntity<List<EstudianteCarreraDTO>>  getAllEstudianteCarrera()
    {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping()
    public ResponseEntity<EstudianteCarrera> createEstudianteCarrera(@RequestBody EstudianteCarreraDTO ecDTO) {
        EstudianteCarrera saved = service.save(ecDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /inscripciones/carrera/{id}/ciudad/{ciudad}
    @GetMapping("/carrera/{id}/ciudad/{ciudad}")
    public ResponseEntity<List<EstudianteDTO>> estudiantesPorCarreraYCiudad(
            @PathVariable Integer id,
            @PathVariable String ciudad
    ) {
        Optional<Carrera> cOpt = carreraService.findById(id);
        if (cOpt.isEmpty()) return ResponseEntity.notFound().build();
        List<EstudianteDTO> lista = service.getEstudiantesCarreraCiudad(cOpt.get(), ciudad);
        return ResponseEntity.ok(lista);
    }

    // GET /inscripciones/reporte
    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteCarreraDTO>> reporteCarreras() {
        return ResponseEntity.ok(service.getReporteCarreras());
    }
}
