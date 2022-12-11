package com.example.clinicaOdontologicaProyecto.model.dto;

import com.example.clinicaOdontologicaProyecto.persistence.entities.Domicilio;

import java.time.LocalDate;

public class PacienteDto implements Dto {
    private Integer id;
    private String dni;
    private String nombre;

    private String apellido;

    private LocalDate fechaIngreso;

    private DomicilioDto domicilio;


    public PacienteDto() {
    }

    public PacienteDto(Integer id, String dni, String nombre, String apellido, LocalDate fechaIngreso, DomicilioDto domicilio) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDto domicilio) {
        this.domicilio = domicilio;
    }
}
