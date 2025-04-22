package com.example.almacen;

public class Almacen {
    // Propiedades
    private int id;
    private String nombre;

    //Métodos
        //Constructor
    public Almacen(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Método main para prueba
    public static void main(String[] args) {
        Almacen almacen = new Almacen(1, "Almacén Norte");

        System.out.println("ID del almacén: " + almacen.getId());
        System.out.println("Nombre del almacén: " + almacen.getNombre());
    }
}
