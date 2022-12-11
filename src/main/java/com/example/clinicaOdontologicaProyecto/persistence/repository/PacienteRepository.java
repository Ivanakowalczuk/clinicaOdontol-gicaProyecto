package com.example.clinicaOdontologicaProyecto.persistence.repository;

import com.example.clinicaOdontologicaProyecto.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    @Query(value = "SELECT * FROM Pacientes p where p.nombre = :nombre ", nativeQuery = true)
    List<Paciente> buscarPorNombre(@Param("nombre") String nombre);
    @Query(value = "SELECT * FROM Pacientes p where p.apellido = :apellido ", nativeQuery = true)
    List<Paciente> buscarPorApellido(@Param("apellido") String apellido);
}
