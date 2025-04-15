package com.example.pedidos;

public class Proveedor {
    // Propiedades
    private int id;
    private String nombre;

    //Métodos
        //Constructor
    public Proveedor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
