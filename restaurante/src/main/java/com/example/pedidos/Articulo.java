package com.example.pedidos;

import com.example.almacen.Almacen;

public class Articulo {
    // Propiedades
    private String EAN; // char(13)
    private String nombre;
    private double cantidadMinima;
    private Almacen almacen;

    // Métodos
    // Constructor
    public Articulo(String EAN, String nombre, double cantidadMinima, Almacen almacen) {
        this.EAN = EAN;
        this.nombre = nombre;
        this.cantidadMinima = cantidadMinima;
        this.almacen = almacen;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getEAN() {
        return EAN;
    }

    public double getCantidadMinima() {
        return cantidadMinima;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    // Método main para prueba
    public static void main(String[] args) {
        Almacen almacen = new Almacen(101, "Almacén Central");
        Articulo articulo = new Articulo("1234567890123", "Botella de agua", 20.0, almacen);

        System.out.println("EAN: " + articulo.getEAN());
        System.out.println("Nombre: " + articulo.getNombre());
        System.out.println("Cantidad mínima: " + articulo.getCantidadMinima());
        System.out.println("Almacén: " + articulo.getAlmacen().getNombre());
    }

}