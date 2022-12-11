package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.configuration.MapperConfiguration;
import com.example.clinicaOdontologicaProyecto.model.dto.PacienteDto;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Domicilio;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Paciente;
import com.example.clinicaOdontologicaProyecto.persistence.repository.PacienteRepository;
import com.example.clinicaOdontologicaProyecto.persistence.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements ApiService<PacienteDto>{
    @Autowired
    private MapperConfiguration mapper;

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private DomicilioRepository repositoryDomicilio;
    @Override
    public PacienteDto registerNew(PacienteDto dto) throws ServiceException {
        Paciente entidad = null;
        Domicilio domicilio;

        if(!dto.getDni().isBlank()) {
           domicilio = repositoryDomicilio.save(mapper.getModelMapper().map(dto.getDomicilio(), Domicilio.class));
            entidad = repository.save(mapper.getModelMapper().map(dto, Paciente.class));
          
        }else{
            throw new ServiceException("El paciente debe tener un número de dni");
        }

        return mapper.getModelMapper().map(entidad, PacienteDto.class);
    }

    @Override
    public List<PacienteDto> getAll() {
        List<PacienteDto> resultado = new ArrayList<>();

        List<Paciente> entidades = repository.findAll();

        entidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, PacienteDto.class)));

        return resultado;
    }

    public PacienteDto getById(Integer id){
        Optional<Paciente> p = repository.findById(id);

        return p.isPresent() ? mapper.getModelMapper().map(p.get(), PacienteDto.class) : null;
    }

}
