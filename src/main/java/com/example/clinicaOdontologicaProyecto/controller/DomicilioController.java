package com.example.clinicaOdontologicaProyecto.controller;

import com.example.clinicaOdontologicaProyecto.Service.DomicilioService;
import com.example.clinicaOdontologicaProyecto.Service.ServiceException;
import com.example.clinicaOdontologicaProyecto.model.dto.DomicilioDto;
import com.example.clinicaOdontologicaProyecto.model.dto.OdontologoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("domicilio")
public class DomicilioController {
    @Autowired
    private DomicilioService service;
    @PostMapping("/nuevo")
    public ResponseEntity<Object> registerNew(@RequestBody DomicilioDto domicilio){
        ResponseEntity<Object> respuesta = null;

        try {
            domicilio = service.registerNew(domicilio);
            respuesta = ResponseEntity.ok(domicilio);
        } catch (ServiceException ex) {
            respuesta = ResponseEntity.badRequest().body(ex.getMessage());
        }

        return respuesta;
    }

}

