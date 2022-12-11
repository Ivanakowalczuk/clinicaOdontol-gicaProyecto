package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.configuration.MapperConfiguration;
import com.example.clinicaOdontologicaProyecto.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaProyecto.model.dto.OdontologoDto;
import com.example.clinicaOdontologicaProyecto.model.dto.PacienteDto;
import com.example.clinicaOdontologicaProyecto.model.dto.TurnoDto;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Turno;
import com.example.clinicaOdontologicaProyecto.persistence.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurno<TurnoDto> {
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
    public TurnoDto registrarTurno(TurnoDto dto) throws ResourceNotFoundException {
        if(existenPacienteYOdontologo(dto)){
            Turno respuesta = repository.save(oMapper.convertValue(dto, Turno.class));
            return mapper.getModelMapper().map(respuesta, TurnoDto.class);
        }else{
            throw new ResourceNotFoundException("El paciente y/u Odontólogo no han sido registados");
        }
    }

    @Override
    public List<TurnoDto> buscarTodos() {

        List<TurnoDto> resultado = new ArrayList<>();
        List<Turno> turno = repository.findAll();

        turno.forEach(e -> resultado.add((mapper.getModelMapper().map(e, TurnoDto.class))));


        return resultado;
    }

    private boolean existenPacienteYOdontologo(TurnoDto dto) throws ResourceNotFoundException {
        PacienteDto paciente = pacienteService.buscarPorId(dto.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.buscarPorId(dto.getOdontologo().getId());

        return paciente != null && odontologo != null;
    }
    @Override
    public List<TurnoDto> buscarPorFecha(LocalDate fecha) {
        List<TurnoDto> resultado = new ArrayList<>();
        List<Turno> entidades = repository.buscarPorFecha(fecha);
        entidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, TurnoDto.class)));
        return resultado;
    }
    public TurnoDto buscarPorId(Integer id){
        Optional<Turno> turno = repository.findById(id);
        TurnoDto turnoDto= null;
        if(turno.isPresent()){
            turnoDto = mapper.getModelMapper().map(turno.get(), TurnoDto.class);
        }
        return turnoDto;
    }
}
