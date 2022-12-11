package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.exceptions.ResourceNotFoundException;
import java.io.Serializable;
import java.util.List;

public interface IPaciente <P extends Serializable>{
    P registrarNuevo(P dto) throws ResourceNotFoundException;

    List<P> buscarTodos();
    P buscarPorId(Integer id) throws ResourceNotFoundException;
    List<P> buscarPorNombre(String nombre);
    List<P> buscarPorApellido(String apellido);

    P modificar(P dto) throws ResourceNotFoundException;

    String eliminar(Integer id) throws ResourceNotFoundException;
}

