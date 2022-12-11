package com.example.clinicaOdontologicaProyecto.persistence.repository;

import com.example.clinicaOdontologicaProyecto.persistence.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
