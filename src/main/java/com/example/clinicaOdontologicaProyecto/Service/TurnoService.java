package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.configuration.MapperConfiguration;
import com.example.clinicaOdontologicaProyecto.model.dto.OdontologoDto;
import com.example.clinicaOdontologicaProyecto.model.dto.PacienteDto;
import com.example.clinicaOdontologicaProyecto.model.dto.TurnoDto;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Turno;
import com.example.clinicaOdontologicaProyecto.persistence.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ApiService<TurnoDto> {
    @Autowired
    private MapperConfiguration mapper;

    @Autowired
    ObjectMapper oMapper;

    @Autowired
    private TurnoRepository repository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Override
    public TurnoDto registerNew(TurnoDto dto) throws ServiceException {
        if(existenPacienteYOdontologo(dto)){
            Turno respuesta = repository.save(oMapper.convertValue(dto, Turno.class));
            return mapper.getModelMapper().map(respuesta, TurnoDto.class);
        }else{
            throw new ServiceException("El paciente y/u Odontólogo no han sido registados");
        }
    }

    @Override
    public List<TurnoDto> getAll() {
        return null;
    }

    private boolean existenPacienteYOdontologo(TurnoDto dto) {
        PacienteDto paciente = pacienteService.getById(dto.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.getById(dto.getOdontologo().getId());

        return paciente != null && odontologo != null;
    }
}
