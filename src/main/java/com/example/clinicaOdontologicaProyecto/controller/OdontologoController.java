package com.example.clinicaOdontologicaProyecto.controller;

import com.example.clinicaOdontologicaProyecto.Service.OdontologoService;
import com.example.clinicaOdontologicaProyecto.Service.ServiceException;
import com.example.clinicaOdontologicaProyecto.model.dto.OdontologoDto;
import org.springframework.beans.factory.annotation.Autowired;
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
        } catch (ServiceException ex) {
            respuesta = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return respuesta;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDto>> listarTodos(){
        List<OdontologoDto> resultado = service.getAll();

        return ResponseEntity.ok(resultado);
    }
}
