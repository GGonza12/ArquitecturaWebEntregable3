package org.example.entregable3.controller;

import org.example.entregable3.dto.EstudianteDTO;
import org.example.entregable3.model.Estudiante;
import org.example.entregable3.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }
    @GetMapping()
    public ResponseEntity<List<EstudianteDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    // GET /estudiante
    @GetMapping("/apellido")
    public ResponseEntity<List<EstudianteDTO>> listarPorApellido() {
        return ResponseEntity.ok(service.obtenerEstudiantesPorApellido());
    }
    // GET /estudiante/dni/{dni}
    @GetMapping("/dni/{dni}")
    public ResponseEntity<EstudianteDTO> obtenerPorDni(@PathVariable int dni) {
        EstudianteDTO dto = service.obtenerEstudiantePorDni(dni);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    // GET /estudiante/libreta/{lu}
    @GetMapping("/libreta/{lu}")
    public ResponseEntity<EstudianteDTO> obtenerPorLu(@PathVariable int lu) {
        EstudianteDTO dto = service.obtenerEstudiantePorNumeroLibreta(lu);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    // GET /estudiante/genero/{genero}
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<EstudianteDTO>> obtenerPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(service.findByGender(genero));
    }

    //POST /estudiante
    @PostMapping()
    public ResponseEntity<Estudiante> create(@RequestBody EstudianteDTO dto) {
         Estudiante saved = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //PUT /estudiante/{dni}
    @PutMapping("/dni/{dni}")
    public ResponseEntity<EstudianteDTO> actualizar(@PathVariable int dni, @RequestBody EstudianteDTO datos) {
        try {
            EstudianteDTO updated = service.update(dni, datos);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
