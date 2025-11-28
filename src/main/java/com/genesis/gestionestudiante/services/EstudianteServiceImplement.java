package com.genesis.gestionestudiante.services;

import com.genesis.gestionestudiante.modelos.Estudiantes;
import com.genesis.gestionestudiante.modelos.Notas;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@WebService(endpointInterface = "com.genesis.gestionestudiante.services.EstudianteService")
public class EstudianteServiceImplement implements EstudianteService {
    private static final List<Estudiantes> estudiantes = new ArrayList<>();
    private static final List<Notas> notasList = new ArrayList<>();
    private static final AtomicLong idGenerado = new AtomicLong();

    @WebMethod
    @Override
    public List<Estudiantes> listar() {
        return estudiantes;
    }
    @WebMethod
    @Override
    public Estudiantes estudiante(Estudiantes estudiantes) {
        estudiantes.setIdEstudiante(idGenerado.getAndIncrement());
        EstudianteServiceImplement.estudiantes.add(estudiantes);
        return estudiantes;
    }
    @WebMethod

    @Override
    public Notas calcularNotas(Long idEstudiante, double n1, double n2, double n3, double examen) {
        Estudiantes estudiantes = EstudianteServiceImplement.estudiantes.stream()
                .filter(e -> e.getIdEstudiante().equals(idEstudiante))
                .findFirst()
                .orElse(null);
        if (estudiantes == null) {
            return null;
        }
        double promedio = n1 * 0.22 + n2 * 0.22 + n3 * 0.22 + examen * 0.34;
        String estado;
        if(promedio >= 7) {
            estado = "APROBADO";
        } else if (promedio > 3.6 && promedio <= 6.99) {
            estado = "SUPLETORIO";
        } else {
            estado = "REPROBADO";
        }

        Notas notas = new Notas();
        notas.setIdEstudiante(idEstudiante);
        notas.setNota1(n1);
        notas.setNota2(n2);
        notas.setNota3(n3);
        notas.setExamen(examen);
        notas.setPromedio(promedio);
        notas.setEstado(estado);

        notasList.add(notas);
        return notas;
    }
    @WebMethod

    @Override
    public Estudiantes buscarPorId(Long id) {
        return estudiantes.stream()
                .filter(e -> e.getIdEstudiante().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Notas obtenerNotas(Long idEstudiante) {
        return notasList.stream()
                .filter(n-> n.getIdEstudiante().equals(idEstudiante))
                .findFirst()
                .orElse(null);
    }
}
