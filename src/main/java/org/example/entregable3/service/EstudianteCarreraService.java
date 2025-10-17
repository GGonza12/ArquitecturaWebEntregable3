package org.example.entregable3.service;

import org.example.entregable3.dto.EstudianteCarreraDTO;
import org.example.entregable3.dto.EstudianteDTO;
import org.example.entregable3.dto.ReporteCarreraDTO;
import org.example.entregable3.model.Carrera;
import org.example.entregable3.model.Estudiante;
import org.example.entregable3.model.EstudianteCarrera;
import org.example.entregable3.model.InscriptionId;
import org.example.entregable3.repository.CarreraRepository;
import org.example.entregable3.repository.EstudianteCarreraRepository;
import org.example.entregable3.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EstudianteCarreraService {
    private final EstudianteCarreraRepository repo;
    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;

    public EstudianteCarreraService(EstudianteCarreraRepository repo,EstudianteRepository eRepo,CarreraRepository cRepo) {
        this.repo = repo;
        this.carreraRepository=cRepo;
        this.estudianteRepository=eRepo;
    }

    public List<EstudianteCarreraDTO> getAll(){
        List<EstudianteCarrera> list = repo.findAll();
            return list.stream().map( ec -> {
                return new EstudianteCarreraDTO(ec.getId(),ec.getEstudiante().getDni(),ec.getCarrera().getId_carrera(),ec.getInscripcion(),ec.getGraduacion(),ec.getAntiguedad());
            }).collect(Collectors.toList());
    }

    public EstudianteCarrera save(EstudianteCarreraDTO e){
        InscriptionId id  = new InscriptionId(e.getId_estudiante(),e.getId_carrera());

        Estudiante est= estudianteRepository.findById(e.getId_estudiante()).orElse(null);
        Carrera car = carreraRepository.findById(e.getId_carrera()).orElse(null);

        return repo.save(new EstudianteCarrera(id,e.getInscripcion(),e.getGraduacion(),e.getAntiguedad(),est,car));
    }

    // Mapea las inscripciones a DTOs de estudiante
    public List<EstudianteDTO> getEstudiantesCarreraCiudad(Carrera carrera, String ciudad) {
        List<EstudianteCarrera> inscripciones = repo.getEstudiantesCarreraCiudad(carrera, ciudad);
        return inscripciones.stream()
                .map(ec -> {
                    Estudiante e = ec.getEstudiante();
                    return new EstudianteDTO(
                            e.getDni(),
                            e.getNombre(),
                            e.getApellido(),
                            e.getEdad(),
                            e.getGenero(),
                            e.getCiudad(),
                            e.getLu()
                    );
                })
                .collect(Collectors.toList());
    }

    // Convierte cada fila Object[] en ReporteCarreraDTO
    public List<ReporteCarreraDTO> getReporteCarreras() {
        List<Object[]> rows = repo.getReporteCarreras();
        return rows.stream()
                .map(row -> {
                    String nombreCarrera = (String) row[0];
                    // row[1] puede ser Integer/Long dependiendo de JPA/DB
                    int anio = ((Number) row[1]).intValue();
                    int cantidad = ((Number) row[2]).intValue();
                    int graduados = ((Number) row[3]).intValue();
                    return new ReporteCarreraDTO(nombreCarrera, anio, cantidad, graduados);
                })
                .collect(Collectors.toList());
    }
}
