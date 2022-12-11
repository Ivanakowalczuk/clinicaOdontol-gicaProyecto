package com.example.clinicaOdontologicaProyecto.controller;

import com.example.clinicaOdontologicaProyecto.Service.PacienteService;
import com.example.clinicaOdontologicaProyecto.Service.ServiceException;
import com.example.clinicaOdontologicaProyecto.model.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
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
            paciente = service.registerNew(paciente);
            respuesta = ResponseEntity.ok(paciente);
        } catch (ServiceException ex) {
            respuesta = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return respuesta;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDto>> listarTodos(){
        List<PacienteDto> resultado = service.getAll();

        return ResponseEntity.ok(resultado);
    }
}
