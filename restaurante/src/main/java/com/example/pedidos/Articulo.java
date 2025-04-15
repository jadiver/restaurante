package com.example.pedidos;

public class Articulo {
    // Propiedades
    private String EAN;
    private String nombre;

    //Métodos
        //Constructor
    public Articulo(String EAN, String nombre) {
        this.EAN = EAN;
        this.nombre = nombre;
    }
    
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getEAN() {
        return EAN;
    }

}