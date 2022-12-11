package com.example.clinicaOdontologicaProyecto.persistence.repository;

import com.example.clinicaOdontologicaProyecto.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Integer>{
    @Query(value = "SELECT * FROM Odontologos o where o.matricula = :matricula ", nativeQuery = true)
    Odontologo buscarPorMatricula(@Param("matricula") String matricula);
}
