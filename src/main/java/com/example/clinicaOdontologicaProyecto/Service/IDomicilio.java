package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.exceptions.ResourceNotFoundException;

import java.io.Serializable;
import java.util.List;

public interface IDomicilio <D extends Serializable> {
    D registrarNuevo(D dto) throws ResourceNotFoundException;
    List<D> buscarTodos();
    D buscarPorId(Integer id) throws ResourceNotFoundException;
}
