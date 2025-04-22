package com.example.pedidos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.almacen.Almacen;

public class Pedido {
    // Propiedades
    private int idPedido;
    private boolean pendiente;
    private Proveedor proveedor;
    private LocalDate fecha;
    private int diasReclamacion;
    private List<DetallePedido> listaArticulos;

    //Métodos
        //Constructor
    public Pedido(int idPedido, Proveedor proveedor, int diasReclamacion) {
        this.idPedido = idPedido;
        this.proveedor = proveedor;
        this.diasReclamacion = diasReclamacion;
        this.pendiente = true;
        this.fecha = LocalDate.now();
        this.listaArticulos = new ArrayList<>();
    }
        //Borrar pedido
    public void borrarPedido() {
        listaArticulos.clear();
        pendiente = false;
    }
        //Añadir artículo
    public void addArticulo(DetallePedido detalle) {
        listaArticulos.add(detalle);
    }
        //Borrar artículo
    public void borrarArticulo(DetallePedido detalle) {
        listaArticulos.remove(detalle);
    }
        //Lista de reclamados
    public List<DetallePedido> listaDeReclamados() {
        return listaArticulos.stream()
                .filter(d -> d.getCantidadRecibida() < d.getCantidadPedida())
                .collect(Collectors.toList());
    }

    //Getters y Setters
    public void setPendiente(boolean estado) {
        this.pendiente = estado;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getDiasReclamacion() {
        return diasReclamacion;
    }

    public List<DetallePedido> getListaArticulos() {
        return listaArticulos;
    }

    // Al final de la clase Pedido

    public static void main(String[] args) {
        // Crear proveedor de prueba
        Proveedor proveedor = new Proveedor(100, "Proveedor Tech S.A.");

        // Crear artículo y detalle de pedido
        Almacen almacen = new Almacen(1, "Almacén Central");
        Articulo articulo1 = new Articulo("1111111111111", "Mouse óptico", 10, almacen);
        Articulo articulo2 = new Articulo("2222222222222", "Pantalla 27\"", 5, almacen);

        DetallePedido detalle1 = new DetallePedido(1, articulo1, 5, 15.0);
        DetallePedido detalle2 = new DetallePedido(2, articulo2, 3, 250.0);

        // Simular recepción parcial
        detalle1.setCantidadRecibida(3); // incompleto
        detalle2.setCantidadRecibida(3); // completo

        // Crear el pedido
        Pedido pedido = new Pedido(5001, proveedor, 7);
        pedido.addArticulo(detalle1);
        pedido.addArticulo(detalle2);

        // Mostrar datos del pedido
        System.out.println("ID Pedido: " + pedido.getIdPedido());
        System.out.println("Proveedor: " + pedido.getProveedor().getNombre());
        System.out.println("Fecha: " + pedido.getFecha());
        System.out.println("Artículos reclamables:");

        for (DetallePedido d : pedido.listaDeReclamados()) {
            System.out.println("- " + d.getArticulo().getNombre() + 
                               " (Recibidos: " + d.getCantidadRecibida() + 
                               " / Pedidos: " + d.getCantidadPedida() + ")");
        }
    }

    
}
