package com.example.clinicaOdontologicaProyecto.persistence.repository;

import com.example.clinicaOdontologicaProyecto.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
