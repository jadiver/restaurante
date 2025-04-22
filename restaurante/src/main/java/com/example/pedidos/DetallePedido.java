package com.example.pedidos;

import java.time.LocalDate;

public class DetallePedido {
    // Propiedades
    public enum Estado {
        PENDIENTE, SERVIDO, CANCELADO
    }

    private int id;
    private Articulo articulo;
    private int cantidadPedida;
    private int cantidadRecibida;
    private Estado estado;
    private double precio;
    private LocalDate fechaCancelacion;

    //Métodos
        //Constructor
    public DetallePedido(int id, Articulo articulo, int cantidadPedida, double precio) {
        this.id = id;
        this.articulo = articulo;
        this.cantidadPedida = cantidadPedida;
        this.cantidadRecibida = 0;
        this.precio = precio;
        this.estado = Estado.PENDIENTE;
        this.fechaCancelacion = null;
    }

    //Getters y Setters
    public void setCantidadRecibida(int cantidad) {
        this.cantidadRecibida = cantidad;
    }

    public int getCantidadRecibida() {
        return cantidadRecibida;
    }

    public int getCantidadPedida() {
        return cantidadPedida;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public Estado getEstado() {
        return estado;
    }

    public double getPrecio() {
        return precio;
    }

    public LocalDate getFechaCancelacion() {
        return fechaCancelacion;
    }

    public int getId() {
        return id;
    }
    
}
