package org.example.entregable3.service;

import org.example.entregable3.dto.CarreraDTO;
import org.example.entregable3.dto.CarreraInscriptosDTO;

import org.example.entregable3.model.Carrera;
import org.example.entregable3.repository.CarreraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarreraService {

    private final CarreraRepository carreraRepository;

    public Carrera getCarreraPorNombre(String name){
        return carreraRepository.getCarreraPorNombre(name);
    }

    // ahora el repository devuelve List<Carrera>; el service construye los DTOs respetando orden y filtro
    public List<CarreraInscriptosDTO> obtenerCarrerasConEstudiantesInscriptos(){
        List<Carrera> carreras = carreraRepository.obtenerCarrerasConEstudiantesInscriptos();
        return carreras.stream()
                .map(c -> {
                    Long count = carreraRepository.countInscriptosByCarreraId(c.getId_carrera());
                    return new CarreraInscriptosDTO(c.getCarrera(), count);
                })
                .collect(Collectors.toList());
    }

    public CarreraDTO getCarreraById(Integer id) {
        Carrera carrera = carreraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        return toDTO(carrera);
    }

    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public List<CarreraDTO> findAll() {
        List<Carrera> carreras = carreraRepository.findAll();
        return carreras.stream().map( carrera -> {
            return new CarreraDTO(carrera.getCarrera(),carrera.getDuracion());
        }).collect(Collectors.toList());
    }

    public Optional<Carrera> findById(Integer id) {
        return carreraRepository.findById(id);
    }

    public Carrera save(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    public CarreraDTO update(Integer id, Carrera datosActualizados) {
        return carreraRepository.findById(id)
            .map(c -> {
                c.setCarrera(datosActualizados.getCarrera());
                c.setDuracion(datosActualizados.getDuracion());
                Carrera cd = carreraRepository.save(c);
                return toDTO(cd);
            })
            .orElseThrow(() -> new NoSuchElementException("Carrera no encontrada con id: " + id));
    }

    public void deleteById(Integer id) {
        if (!carreraRepository.existsById(id)) {
            throw new NoSuchElementException("Carrera no encontrada con id: " + id);
        }
        carreraRepository.deleteById(id);
    }

      public CarreraDTO toDTO(Carrera c){
        return new CarreraDTO(
                c.getCarrera(),
                c.getDuracion()
        );
    }
}
