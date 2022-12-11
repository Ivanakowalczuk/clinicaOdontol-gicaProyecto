package com.example.clinicaOdontologicaProyecto.Service;

import com.example.clinicaOdontologicaProyecto.configuration.MapperConfiguration;
import com.example.clinicaOdontologicaProyecto.model.dto.OdontologoDto;
import com.example.clinicaOdontologicaProyecto.persistence.entities.Odontologo;
import com.example.clinicaOdontologicaProyecto.persistence.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



    @Service
    public class OdontologoService implements ApiService<OdontologoDto> {

        @Autowired
        private MapperConfiguration mapper;

        @Autowired
        private ObjectMapper oMapper;

        @Autowired
        private OdontologoRepository repository;

        @Override
        public OdontologoDto registerNew(OdontologoDto dto) throws ServiceException {
            Odontologo entidad = null;

            if(!dto.getMatricula().isBlank()) {
                entidad = repository.save(mapper.getModelMapper().map(dto, Odontologo.class));
            }else{
                throw new ServiceException("El odontólogo debe tener un número de matrícula.");
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

        public OdontologoDto getById(Integer id){
            Optional<Odontologo> p = repository.findById(id);

            return p.isPresent() ? mapper.getModelMapper().map(p.get(), OdontologoDto.class) : null;
        }
}
