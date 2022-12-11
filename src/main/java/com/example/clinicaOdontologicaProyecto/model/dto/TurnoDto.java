package com.example.clinicaOdontologicaProyecto.model.dto;

import java.time.LocalDate;

public class TurnoDto implements Dto {
    private Integer id;
    private PacienteDto paciente;
    private OdontologoDto odontologo;
    private LocalDate fecha;

    public TurnoDto() {
    }

    public TurnoDto(Integer id, PacienteDto paciente, OdontologoDto odontologo, LocalDate fecha) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    public OdontologoDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDto odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
