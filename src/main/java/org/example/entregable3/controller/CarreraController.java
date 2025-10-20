package org.example.entregable3.controller;

import org.example.entregable3.dto.CarreraDTO;
import org.example.entregable3.dto.CarreraInscriptosDTO;
import org.example.entregable3.model.Carrera;
import org.example.entregable3.service.CarreraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    private final CarreraService carreraService;

    public CarreraController(CarreraService service) {
        this.carreraService = service;
    }

    @GetMapping
    public ResponseEntity<List<CarreraDTO>> listar() {
        return ResponseEntity.ok(carreraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraDTO> getCarrera(@PathVariable Integer id) {
        CarreraDTO dto = carreraService.getCarreraById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/nombre/{name}")
    public ResponseEntity<Carrera> obtenerPorNombre(@PathVariable String name) {
        Carrera c = carreraService.getCarreraPorNombre(name);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    @PostMapping
    public ResponseEntity<Carrera> crear(@RequestBody Carrera carrera) {
        Carrera saved = carreraService.save(carrera);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarreraDTO> actualizar(@PathVariable Integer id, @RequestBody Carrera datos) {
        try {
            CarreraDTO updated = carreraService.update(id, datos);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            carreraService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint que devuelve la lista de DTOs con cantidad de inscriptos
    @GetMapping("/inscriptos")
    public ResponseEntity<List<CarreraInscriptosDTO>> obtenerCarrerasConInscriptos() {
        List<CarreraInscriptosDTO> lista = carreraService.obtenerCarrerasConEstudiantesInscriptos();
        return ResponseEntity.ok(lista);
    }
}
