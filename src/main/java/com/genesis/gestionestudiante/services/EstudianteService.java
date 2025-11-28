package com.genesis.gestionestudiante.services;

import com.genesis.gestionestudiante.modelos.Estudiantes;
import com.genesis.gestionestudiante.modelos.Notas;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface EstudianteService {
    List<Estudiantes> listar();
    Estudiantes estudiante(Estudiantes estudiante);
    Notas calcularNotas(Long idEstudiante, double n1, double n2, double n3, double examen);
    Estudiantes buscarPorId(Long id);
    Notas obtenerNotas(Long idEstudiante);
}
