package com.example.clinicaOdontologicaProyecto.controller;

import com.example.clinicaOdontologicaProyecto.Service.ServiceException;
import com.example.clinicaOdontologicaProyecto.Service.TurnoService;
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
    public ResponseEntity<Object> registerNew(@RequestBody TurnoDto turno){
        ResponseEntity<Object> respuesta = null;

        try {
            turno = service.registerNew(turno);
            respuesta = ResponseEntity.ok(turno);
        } catch (ServiceException ex) {
            respuesta = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return respuesta;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDto>> listarTodos(){
        List<TurnoDto> resultado = service.getAll();

        return ResponseEntity.ok(resultado);
    }
}
