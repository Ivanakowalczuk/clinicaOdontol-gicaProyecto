package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaProyecto.model.dto.TurnoDto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface ITurno <T extends Serializable>{
    TurnoDto registrarTurno(TurnoDto turnoDto) throws Exception, ResourceNotFoundException;
    TurnoDto buscarPorId(Integer id) throws ResourceNotFoundException;
    List<TurnoDto> buscarTodos();

    List<TurnoDto> buscarPorFecha(LocalDate fecha);
}
