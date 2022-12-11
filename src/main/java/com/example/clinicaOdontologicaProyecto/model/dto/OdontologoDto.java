package com.example.clinicaOdontologicaProyecto.model.dto;

public class OdontologoDto implements Dto {
    private Integer id;
    private String matricula;
    private String apellido;
    private String nombre;

    public OdontologoDto() {
    }

    public OdontologoDto(Integer id, String matricula, String apellido, String nombre) {
        this.id = id;
        this.matricula = matricula;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
