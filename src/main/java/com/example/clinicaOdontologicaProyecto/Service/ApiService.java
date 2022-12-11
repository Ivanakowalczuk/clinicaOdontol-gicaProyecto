package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.model.dto.Dto;

import java.util.List;

public interface ApiService<D extends Dto> {
    D registerNew(D dto) throws ServiceException;

    List<D> getAll();
}
