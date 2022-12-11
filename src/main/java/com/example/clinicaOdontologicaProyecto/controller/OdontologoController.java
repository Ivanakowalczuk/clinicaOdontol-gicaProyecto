package com.example.clinicaOdontologicaProyecto.controller;

import com.example.clinicaOdontologicaProyecto.Service.OdontologoService;
import com.example.clinicaOdontologicaProyecto.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaProyecto.model.dto.OdontologoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService service;

    @PostMapping("/nuevo")
    public ResponseEntity<Object> registerNew(@RequestBody OdontologoDto odontologo){
        ResponseEntity<Object> respuesta = null;

        try {
            odontologo = service.registerNew(odontologo);
            respuesta = ResponseEntity.ok(odontologo);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }

        return respuesta;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDto>> listarTodos(){
        List<OdontologoDto> resultado = service.getAll();

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("encontrar/{matricula}")
    public OdontologoDto buscarPorMatricula(@PathVariable String matricula) throws ResourceNotFoundException {
        return service.buscarPorMatricula(matricula);
    }
    @GetMapping("/buscar/{id}")
    public OdontologoDto buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        return service.buscarPorId(id);
    }
    @PutMapping(value = "/modificar")
    public ResponseEntity<?> modificarOdontologo(@RequestBody OdontologoDto dto) throws ResourceNotFoundException {
       service.modificar(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable int id) throws ResourceNotFoundException {
        service.eliminar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
