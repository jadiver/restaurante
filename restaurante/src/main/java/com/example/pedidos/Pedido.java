package com.example.pedidos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}
