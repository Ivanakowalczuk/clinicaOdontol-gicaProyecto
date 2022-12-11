package com.example.clinicaOdontologicaProyecto.controller;

import com.example.clinicaOdontologicaProyecto.Service.PacienteService;
import com.example.clinicaOdontologicaProyecto.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaProyecto.model.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacient")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @PostMapping("/nuevo")
    public ResponseEntity<Object> registerNew(@RequestBody PacienteDto paciente){
        ResponseEntity<Object> respuesta = null;

        try {
            paciente = service.registrarNuevo(paciente);
            respuesta = ResponseEntity.ok(paciente);
        } catch (ResourceNotFoundException ex) {
            respuesta = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return respuesta;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDto>> buscarTodos(){
        List<PacienteDto> resultado = service.buscarTodos();

        return ResponseEntity.ok(resultado);
    }
    @RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
    public PacienteDto buscarPorId(@PathVariable int id) throws ResourceNotFoundException {
        return service.buscarPorId(id);
    }

    @RequestMapping(value = "/encontrar/{nombre}", method = RequestMethod.GET)
    public  List<PacienteDto> obtenerPorNombre(@PathVariable String nombre){
        return service.buscarPorNombre(nombre);
    }
    @RequestMapping(value = "/hallar/{apellido}", method = RequestMethod.GET)
    public  List<PacienteDto> buscarPorApellido(@PathVariable String apellido){
        return service.buscarPorApellido(apellido);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        service.eliminar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/modificar")
    public ResponseEntity<?> actualizarPaciente(@RequestBody PacienteDto dto) throws ResourceNotFoundException {
        service.modificar(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
