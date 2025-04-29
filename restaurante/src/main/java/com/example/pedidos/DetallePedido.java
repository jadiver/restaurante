package com.example.pedidos;

import com.example.almacen.Almacen;
import com.example.utilidades.EAN;

import java.time.LocalDate;

public class DetallePedido {
    // Propiedades
    public enum Estado {
        PENDIENTE, SERVIDO, CANCELADO
    }

    Pedido pedido;
    Articulo articulo;
    double cantidadPedida;
    double cantidadRecibida;
    Estado estado;
    double precio;
    LocalDate fechaCancelacion;

    // Métodos
    // Constructor
    public DetallePedido(Pedido pedido, Articulo articulo, double cantidadPedida, double precio) {
        this.pedido = pedido;
        this.articulo = articulo;
        this.cantidadPedida = cantidadPedida;
        this.cantidadRecibida = 0;
        this.precio = precio;
        this.estado = Estado.SERVIDO;
        this.fechaCancelacion = null;
    }

    // Getters y Setters
    public void setCantidadRecibida(int cantidad) {
        this.cantidadRecibida = cantidad;
    }

    public double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public double getCantidadPedida() {
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

    public Pedido getPedido() {
        return pedido;
    }

    @Override
    public String toString() {
        return "Pedido: " + pedido
                + "Articulo: " + articulo
                + "Cantidad Pedida: " + cantidadPedida
                + "Cantidad Servida: " + cantidadRecibida
                + "Estado: " + estado
                + "Precio: " + precio
                + "Fecha Cancelacion: " + fechaCancelacion;
    }

    // === Método main para prueba ===
    public static void main(String[] args) {
        Almacen almacen = new Almacen(0, "general");
        Articulo articulo = new Articulo(new EAN("0001234567890"), "Teclado mecánico", 200, almacen);

        Proveedor proveedor = new Proveedor(1, "chiquito");
        Pedido pedido = new Pedido(1, proveedor);

        DetallePedido detalle = new DetallePedido(pedido, articulo, 5, 45.99);
        detalle.setCantidadRecibida(2);

        System.out.println("ID Detalle: " + detalle.getPedido());
        System.out.println("Artículo: " + detalle.getArticulo().getNombre());
        System.out.println("Cantidad Pedida: " + detalle.getCantidadPedida());
        System.out.println("Cantidad Recibida: " + detalle.getCantidadRecibida());
        System.out.println("Estado: " + detalle.getEstado());
        System.out.println("Precio: $" + detalle.getPrecio());
    }

}
