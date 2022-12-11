package com.example.clinicaOdontologicaProyecto.persistence.repository;


import com.example.clinicaOdontologicaProyecto.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {
}
