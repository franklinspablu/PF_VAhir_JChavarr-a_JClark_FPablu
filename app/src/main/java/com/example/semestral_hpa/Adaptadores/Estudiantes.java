package com.example.semestral_hpa.Adaptadores;

public class Estudiantes {

    private String nombre;
    private String apellido;
    private String cedula;
    private String hora;

    public Estudiantes(String nombre, String apellido, String cedula, String hora) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.hora = hora;
    }

    public String toString(){
        return this.nombre+"|"+this.apellido+"|"+this.cedula+"|"+this.hora;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}