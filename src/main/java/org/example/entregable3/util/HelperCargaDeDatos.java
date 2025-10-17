package org.example.entregable3.util;

import jakarta.annotation.PostConstruct;
import org.example.entregable3.model.Carrera;
import org.example.entregable3.model.Estudiante;
import org.example.entregable3.model.EstudianteCarrera;
import org.example.entregable3.model.InscriptionId;
import org.example.entregable3.repository.CarreraRepository;
import org.example.entregable3.repository.EstudianteCarreraRepository;
import org.example.entregable3.repository.EstudianteRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class HelperCargaDeDatos {
    private final CarreraRepository carreraRepository;
    private final EstudianteRepository estudianteRepository;
    private final EstudianteCarreraRepository estudianteCarreraRepository;

    public HelperCargaDeDatos(CarreraRepository cr, EstudianteRepository er, EstudianteCarreraRepository ecr) {
        this.carreraRepository = cr;
        this.estudianteRepository = er;
        this.estudianteCarreraRepository = ecr;
    }

    @PostConstruct
    private void init() {
        try {
            // evita recargar si ya hay datos
            if (carreraRepository.count() == 0 && estudianteRepository.count() == 0 && estudianteCarreraRepository.count() == 0) {
                cargarDatosDesdeCSV();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cargarDatosDesdeCSV() throws IOException {
        // rutas esperadas: src/main/resources/csv/...
        File carreraCSV = ResourceUtils.getFile("src/main/java/org/example/entregable3/csv/carreras.csv");
        File estudianteCSV = ResourceUtils.getFile("src/main/java/org/example/entregable3/csv/estudiantes.csv");
        File estudianteCarreraCSV = ResourceUtils.getFile("src/main/java/org/example/entregable3/csv/estudianteCarrera.csv");


        // CARRERAS
        try (CSVReader reader = new CSVReader(new FileReader(carreraCSV))) {
            String[] linea;
            reader.readNext(); // salto header
            while ((linea = reader.readNext()) != null) {
                int id_carrera = Integer.parseInt(linea[0]);
                String nombre = linea[1];
                int duracion = Integer.parseInt(linea[2]);
                Carrera carrera = new Carrera(id_carrera, nombre, duracion);
                carreraRepository.save(carrera);
            }
            System.out.println("Carreras insertadas exitosamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ESTUDIANTES
        try (CSVReader reader = new CSVReader(new FileReader(estudianteCSV))) {
            String[] linea;
            reader.readNext();
            while ((linea = reader.readNext()) != null) {
                Integer dni = Integer.parseInt(linea[0]);
                String nombre = linea[1];
                String apellido = linea[2];
                int edad = Integer.parseInt(linea[3]);
                String genero = linea[4];
                String ciudad = linea[5];
                int lu = Integer.parseInt(linea[6]);
                Estudiante est = new Estudiante(dni, nombre, apellido, edad, genero, ciudad, lu);
                estudianteRepository.save(est);
            }
            System.out.println("Estudiantes insertados exitosamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ESTUDIANTE_CARRERA
        try (CSVReader reader = new CSVReader(new FileReader(estudianteCarreraCSV))) {
            String[] linea;
            reader.readNext();
            while ((linea = reader.readNext()) != null) {
                int id_estudiante = Integer.parseInt(linea[1]);
                int id_carrera = Integer.parseInt(linea[2]);

                LocalDate inscripcion = LocalDate.of(Integer.parseInt(linea[3]), 1, 1);
                LocalDate graduacion = (linea[4].equals("0") || linea[4].isEmpty()) ? null : LocalDate.of(Integer.parseInt(linea[4]), 1, 1);
                int antiguedad = Integer.parseInt(linea[5]);

                Optional<Estudiante> estudianteOpt = estudianteRepository.findById(id_estudiante);
                Optional<Carrera> carreraOpt = carreraRepository.findById(id_carrera);

                if (estudianteOpt.isEmpty() || carreraOpt.isEmpty()) {
                    System.out.println("No se encontró estudiante o carrera para la inscripción: " + id_estudiante + ", " + id_carrera);
                    continue;
                }

                InscriptionId id = new InscriptionId(id_estudiante,id_carrera);
                if (estudianteCarreraRepository.findById(id).isEmpty()) {
                    EstudianteCarrera estCarr = new EstudianteCarrera(id, inscripcion, graduacion, antiguedad, estudianteOpt.get(), carreraOpt.get());
                    estudianteCarreraRepository.save(estCarr);
                }
            }
            System.out.println("EstudianteCarreras insertadas exitosamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
