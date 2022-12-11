package com.example.clinicaOdontologicaProyecto.persistence.repository;

import com.example.clinicaOdontologicaProyecto.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Integer>{
}
