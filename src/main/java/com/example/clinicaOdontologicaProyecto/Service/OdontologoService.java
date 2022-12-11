package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.configuration.MapperConfiguration;
import com.example.clinicaOdontologicaProyecto.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaProyecto.model.dto.OdontologoDto;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Odontologo;
import com.example.clinicaOdontologicaProyecto.persistence.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;



    @Service
    public class OdontologoService implements IOdontologo<OdontologoDto> {

        @Autowired
        private MapperConfiguration mapper;

        @Autowired
        private ObjectMapper oMapper;

        @Autowired
        private final OdontologoRepository repository;
        public OdontologoService(OdontologoRepository repository) {
            this.repository = repository;
        }

        private Odontologo toEntity(OdontologoDto dto){
            Odontologo odontologo = mapper.getModelMapper().map(dto, Odontologo.class);
            return odontologo;

        }
        @Override
        public OdontologoDto registerNew(OdontologoDto dto) throws ResourceNotFoundException {
            Odontologo entidad = null;

            if(!dto.getMatricula().isBlank()) {
                entidad = repository.save(mapper.getModelMapper().map(dto, Odontologo.class));
            }else{
                throw new ResourceNotFoundException("El odontólogo debe tener un número de matrícula.");
            }

            return mapper.getModelMapper().map(entidad, OdontologoDto.class);
        }

        @Override
        public List<OdontologoDto> getAll() {
            List<OdontologoDto> resultado = new ArrayList<>();

            List<Odontologo> entidades = repository.findAll();

            entidades.forEach(e -> resultado.add(mapper.getModelMapper().map(e, OdontologoDto.class)));

            return resultado;
        }
        @Override
        public OdontologoDto buscarPorId(Integer id) throws ResourceNotFoundException{
            Optional<Odontologo> odontologo = this.repository.findById(id);

            OdontologoDto odontologoDto=null;

            if (odontologo.isPresent()) {
                odontologoDto =  mapper.getModelMapper().map(odontologo.get(), OdontologoDto.class);

            }
            else {
                throw new ResourceNotFoundException("No está registrado en el sistema el odontologo con el id indicado.");
            }
            return  odontologoDto;

        }



        @Override
        public OdontologoDto buscarPorMatricula(String matricula) throws ResourceNotFoundException {
            OdontologoDto resultado = null;

            Odontologo entidad = repository.buscarPorMatricula(matricula);

            if(entidad !=null) {
                resultado = mapper.getModelMapper().map(entidad, OdontologoDto.class);
            }
            else {
                throw new ResourceNotFoundException("No se encuentra registrado en el sistema el odontologo con la matricula indicada.");
            }

            return resultado;
        }



        @Override
        public OdontologoDto modificar(OdontologoDto odontologoDto) throws ResourceNotFoundException {

            Optional<Odontologo> odontologo = repository.findById(odontologoDto.getId());

            if (odontologoDto !=null ||  odontologo.isEmpty()){

                odontologo.get().setApellido(Objects.isNull(odontologoDto.getApellido()) ?
                        odontologo.get().getApellido() : odontologoDto.getApellido());

                odontologo.get().setNombre(Objects.isNull(odontologoDto.getNombre())?
                        odontologo.get().getApellido(): odontologoDto.getNombre());

                odontologo.get().setMatricula(Objects.isNull(odontologoDto.getMatricula()) ?
                        odontologo.get().getMatricula() : odontologoDto.getMatricula());

                repository.save(odontologo.get());

            }else {
                throw new ResourceNotFoundException("No se encuentra registrado y no se pudo modificar el odontologo ingresado");
            }

            return mapper.getModelMapper().map(odontologo, OdontologoDto.class);
        }


        @Override
        public String eliminar(Integer id) throws ResourceNotFoundException {
            if (repository.findById(id).isPresent()) {
                repository.deleteById(id);
                return "El odontologo se eliminó correctamente.";
            }
            else {
                throw new ResourceNotFoundException("El odontologo no se encuentra registrado en el sistema");
            }
        }

    }
