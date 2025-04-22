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
    // Método main para prueba
    public static void main(String[] args) {
        Proveedor proveedor = new Proveedor(1, "Distribuciones García");

        System.out.println("ID del proveedor: " + proveedor.getId());
        System.out.println("Nombre del proveedor: " + proveedor.getNombre());
    }
}
