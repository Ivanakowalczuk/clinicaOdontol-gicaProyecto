package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.configuration.MapperConfiguration;
import com.example.clinicaOdontologicaProyecto.model.dto.DomicilioDto;
import com.example.clinicaOdontologicaProyecto.model.dto.OdontologoDto;
import com.example.clinicaOdontologicaProyecto.model.dto.PacienteDto;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Domicilio;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Odontologo;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Paciente;
import com.example.clinicaOdontologicaProyecto.persistence.repository.DomicilioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements ApiService<DomicilioDto> {

    @Autowired
    private MapperConfiguration mapper;

    @Autowired
    private ObjectMapper oMapper;
    @Autowired
    DomicilioRepository repository;


    public DomicilioDto search(Integer id){
        Optional<Domicilio> p = repository.findById(id);

        return p.isPresent() ? mapper.getModelMapper().map(p.get(), DomicilioDto.class) : null;
    }






    @Override
    public DomicilioDto registerNew(DomicilioDto dto) throws ServiceException {
        Domicilio entidad = null;

        if(!dto.getCalle().isBlank()) {
            entidad = repository.save(mapper.getModelMapper().map(dto, Domicilio.class));
        }else{
            throw new ServiceException("El paciente debe tener un domicilio");
        }

        return mapper.getModelMapper().map(entidad, DomicilioDto.class);
    }

    @Override
    public List<DomicilioDto> getAll() {

            List<DomicilioDto> resultado = new ArrayList<>();

            List<Domicilio> entidades = repository.findAll();

            entidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, DomicilioDto.class)));

            return resultado;
        }

}
