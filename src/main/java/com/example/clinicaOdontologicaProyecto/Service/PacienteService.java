package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.configuration.MapperConfiguration;
import com.example.clinicaOdontologicaProyecto.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaProyecto.model.dto.PacienteDto;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Domicilio;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Paciente;
import com.example.clinicaOdontologicaProyecto.persistence.repository.PacienteRepository;
import com.example.clinicaOdontologicaProyecto.persistence.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PacienteService implements IPaciente<PacienteDto>{
    @Autowired
    private MapperConfiguration mapper;

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private DomicilioRepository repositoryDomicilio;
    @Override
    public PacienteDto registrarNuevo(PacienteDto dto) throws ResourceNotFoundException {
        Paciente entidad = null;
        Domicilio domicilio;

        if(!dto.getDni().isBlank()) {
           domicilio = repositoryDomicilio.save(mapper.getModelMapper().map(dto.getDomicilio(), Domicilio.class));
            entidad = repository.save(mapper.getModelMapper().map(dto, Paciente.class));
          
        }else{
            throw new ResourceNotFoundException("El paciente debe tener un número de dni");
        }

        return mapper.getModelMapper().map(entidad, PacienteDto.class);
    }

    @Override
    public List<PacienteDto> buscarTodos() {
        List<PacienteDto> resultado = new ArrayList<>();

        List<Paciente> entidades = repository.findAll();

        entidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, PacienteDto.class)));

        return resultado;
    }

    public PacienteDto buscarPorId(Integer id){
        Optional<Paciente> p = repository.findById(id);

        return p.isPresent() ? mapper.getModelMapper().map(p.get(), PacienteDto.class) : null;
    }

    @Override
    public List<PacienteDto> buscarPorNombre(String nombre) {

        List<PacienteDto> resultado = new ArrayList<>();

        List<Paciente> entidades = repository.buscarPorNombre(nombre);

        entidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, PacienteDto.class)));

        return resultado;
    }

    @Override
    public List<PacienteDto> buscarPorApellido(String apellido) {
        List<PacienteDto> resultado = new ArrayList<>();

        List<Paciente> entidades = repository.buscarPorApellido(apellido);

        entidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, PacienteDto.class)));

        return resultado;
    }

    @Override
    public PacienteDto modificar(PacienteDto pacienteDto) throws ResourceNotFoundException {

        Optional<Paciente> paciente = repository.findById(pacienteDto.getId());

        if (pacienteDto !=null ||  paciente.isEmpty()){

            paciente.get().setApellido(Objects.isNull(pacienteDto.getApellido()) ?
                    paciente.get().getApellido() : pacienteDto.getApellido());

            paciente.get().setNombre(Objects.isNull(pacienteDto.getNombre())?
                    paciente.get().getApellido(): pacienteDto.getNombre());

            paciente.get().setDni(Objects.isNull(pacienteDto.getDni()) ?
                    paciente.get().getDni() : pacienteDto.getDni());

            paciente.get().setFechaIngreso(Objects.isNull(pacienteDto.getFechaIngreso()) ?
                    paciente.get().getFechaIngreso() : pacienteDto.getFechaIngreso());

            repository.save(paciente.get());

        }else {
            throw new ResourceNotFoundException("No existe o no fue posible encontrar el paciente ingresado");
        }

        return mapper.getModelMapper().map(paciente, PacienteDto.class);
    }


    @Override
    public String eliminar(Integer id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return "El paciente se ha eliminado correctamente.";
        }
        else {
            throw new ResourceNotFoundException("No se encuentra registrado en el sistema el paciente con el id buscado");
        }
    }

}
