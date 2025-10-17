package org.example.entregable3.service;

import org.example.entregable3.dto.EstudianteDTO;
import org.example.entregable3.model.Estudiante;
import org.example.entregable3.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EstudianteService {
    private final EstudianteRepository repo;

    public EstudianteService(EstudianteRepository repo) {
        this.repo = repo;
    }

    public List<EstudianteDTO> findAll(){
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Estudiante save(EstudianteDTO e){
       return repo.save(new Estudiante(e.getDni(),e.getNombre(),e.getApellido(),e.getEdad(),e.getGenero(),e.getCiudad(),e.getNroLibreta()));
    }

    public List<EstudianteDTO> obtenerEstudiantesPorApellido() {
        return repo.obtenerEstudiantesPorApellido()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<Estudiante> findById(Integer id) {
        return repo.findById(id);
    }


    public EstudianteDTO obtenerEstudiantePorNumeroLibreta(int lu) {
        Estudiante e = repo.obtenerEstudiantePorNumeroLibreta(lu);
        return e == null ? null : toDto(e);
    }

    public List<EstudianteDTO> findByGender(String genero) {
        return repo.findByGender(genero)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private EstudianteDTO toDto(Estudiante e) {
        return new EstudianteDTO(
                e.getDni(),
                e.getNombre(),
                e.getApellido(),
                e.getEdad(),
                e.getGenero(),
                e.getCiudad(),
                e.getLu()
        );
    }
}
