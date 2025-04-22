package com.example.pedidos;

import com.example.almacen.Almacen;

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

    // === Método main para prueba ===
    public static void main(String[] args) {
        Almacen almacen = new Almacen(10, "Almacén Principal");
        Articulo articulo = new Articulo("0001234567890", "Teclado mecánico", 3.0, almacen);

        DetallePedido detalle = new DetallePedido(501, articulo, 5, 45.99);
        detalle.setCantidadRecibida(2);

        System.out.println("ID Detalle: " + detalle.getId());
        System.out.println("Artículo: " + detalle.getArticulo().getNombre());
        System.out.println("Cantidad Pedida: " + detalle.getCantidadPedida());
        System.out.println("Cantidad Recibida: " + detalle.getCantidadRecibida());
        System.out.println("Estado: " + detalle.getEstado());
        System.out.println("Precio: $" + detalle.getPrecio());
        System.out.println("Almacén: " + detalle.getArticulo().getAlmacen().getNombre());
    }
    
}
