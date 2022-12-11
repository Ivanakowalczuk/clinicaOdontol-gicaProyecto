package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.exceptions.ResourceNotFoundException;

import java.io.Serializable;
import java.util.List;

public interface IOdontologo <D extends Serializable>{
    D registerNew(D dto) throws ResourceNotFoundException;

    List<D> getAll();

    D buscarPorId(Integer id) throws ResourceNotFoundException;
    D buscarPorMatricula(String matricula) throws ResourceNotFoundException;

    D modificar(D dto) throws ResourceNotFoundException;

    String eliminar(Integer id) throws ResourceNotFoundException;
}
