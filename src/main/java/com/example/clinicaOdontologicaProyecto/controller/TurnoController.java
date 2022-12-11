package com.example.clinicaOdontologicaProyecto.controller;

import com.example.clinicaOdontologicaProyecto.Service.TurnoService;
import com.example.clinicaOdontologicaProyecto.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologicaProyecto.model.dto.TurnoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("turno")
public class TurnoController {
    @Autowired
    private TurnoService service;

    @PostMapping("/nuevo")
    public ResponseEntity<Object> registerNew(@RequestBody TurnoDto turno) throws ResourceNotFoundException {
        ResponseEntity<Object> respuesta = null;

        try {
            turno = service.registrarTurno(turno);
            respuesta = ResponseEntity.ok(turno);
        } catch (ResourceNotFoundException ex ) {
            respuesta = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return respuesta;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDto>> buscarTodos(){
        List<TurnoDto> resultado = service.buscarTodos();

        return ResponseEntity.ok(resultado);
    }
    @GetMapping("/buscar/{id}")
    public TurnoDto buscarPorId(@PathVariable Integer id){
        return  service.buscarPorId(id);
    }

}
