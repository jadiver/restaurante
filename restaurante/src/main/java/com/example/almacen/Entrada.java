package com.example.almacen;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.pedidos.Articulo;
import com.example.pedidos.Pedido;
import com.example.pedidos.Proveedor;
import com.example.utilidades.EAN;;

public class Entrada {

    // Propiedades
    int id;
    Articulo articulo;
    Pedido pedido;
    LocalTime momento;
    double cantidad;
    double stock;
    LocalDate caducidad;
    int albaran;
    String lote;

    //Métodos
        //Constructor
    public Entrada(int id, Articulo articulo, Pedido pedido, double cantidad, LocalDate caducidad, int albaran, String lote) {
        this.id = id;
        this.articulo = articulo;
        this.pedido = pedido;
        this.cantidad = cantidad;
        this.stock = cantidad; // al principio el stock es igual a la cantidad que entra
        this.caducidad = caducidad;
        this.albaran = albaran;
        this.lote = lote;
        this.momento = LocalTime.now(); // momento actual al crear la entrada
    }

    // Método para obtener el stock actual de un artículo concreto
    public double stockActual(Articulo articulo) {
        if (this.articulo.getEAN() == articulo.getEAN()) {
            return this.stock;
        }
        return 0.0;
    }

    // Método para sacar una cantidad del stock si hay suficiente
    public boolean sacar(Articulo articulo, String lote, double cantidad) {
        if (this.articulo.getEAN() == articulo.getEAN() && this.lote.equals(lote)) {
            if (cantidad <= this.stock) {
                this.stock -= cantidad;
                return true;
            }
        }
        return false;
    }

    // Getters opcionales
    public int getId() {
        return id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public LocalTime getMomento() {
        return momento;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getStock() {
        return stock;
    }

    public LocalDate getCaducidad() {
        return caducidad;
    }

    public int getAlbaran() {
        return albaran;
    }

    public String getLote() {
        return lote;
    }

    // Método main para prueba
    public static void main(String[] args) {
        // Creamos un almacén y un artículo de prueba
        Almacen almacen = new Almacen(0, "general");
        Articulo articulo = new Articulo(new EAN("0001234567890"), "Botella de agua", 50, almacen);
        Pedido pedido = new Pedido(1, new Proveedor(1, "Proveedor A"));  // Usando la clase Proveedor de prueba

        // Creamos una entrada de prueba
        Entrada entrada = new Entrada(1, articulo, pedido, 100.0, LocalDate.now().plusDays(30), 12345, "Lote001");

        // Imprimir detalles de la entrada
        System.out.println("Entrada ID: " + entrada.getId());
        System.out.println("Artículo: " + entrada.getArticulo().getNombre());
        System.out.println("Cantidad en stock: " + entrada.getStock());
        System.out.println("Lote: " + entrada.getLote());
        
        // Probar el método sacar
        boolean resultado = entrada.sacar(articulo, "Lote001", 50);
        System.out.println("¿Se ha sacado la cantidad? " + resultado);
        System.out.println("Stock restante: " + entrada.getStock());
    }
}

