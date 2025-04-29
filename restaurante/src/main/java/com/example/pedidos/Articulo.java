package com.example.pedidos;

import com.example.almacen.Almacen;
import com.example.utilidades.BaseDeDatos;

import java.util.List;
import java.util.Map;
import com.example.utilidades.EAN;

public class Articulo {
    // Propiedades
    EAN id; // char(13)
    String nombre;
    double cantidadMinima;
    Almacen almacen;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public EAN getEAN() {
        return id;
    }

    public double getCantidadMinima() {
        return cantidadMinima;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    // Métodos
        // Constructor
    public Articulo(EAN id, String nombre, double cantidadMinima, Almacen almacen) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadMinima = cantidadMinima;
        this.almacen = almacen;
    }

        //Guardar articulo
    public boolean guarda() {
        String sql = "INSERT INTO Articulos VALUES ('" + id.getValor() + "','" + nombre + "')";
        return BaseDeDatos.ejecutar(sql) >= 0;
    }

        //Borrar articulo
    public boolean borra() {
        String sql = "DELETE FROM Articulos WHERE ean= '" + id.getValor() + "'";
        return BaseDeDatos.ejecutar(sql) >= 0;
    }

        //Consultar articulo
    public static Articulo carga(EAN id) {
        String sql = "SELECT nombre, cantidad_minima, almacen FROM Articulos WHERE ean= '" + id.getValor() + "'";
        List<Map<String, Object>> resultado = BaseDeDatos.consultar(sql);
        if (resultado.isEmpty()) {
            return null;
        }
        Almacen almacenesPepe = Almacen.carga((int)resultado.get(0).get("almacen"));
        Articulo nuevoArticulo = new Articulo(
            id, 
            (String)resultado.get(0).get("nombre"),
            (double)resultado.get(0).get("cantidad_minima"),
            almacenesPepe
            );
        return nuevoArticulo;
    }

    @Override
    public String toString() {
        return ("ID....: " + id.getValor() + "\n"
                + "Nombre: " + nombre);
    }

    public static void main(String[] args) {
        BaseDeDatos.conecta();
        Almacen almacen = new Almacen(0, "general");
        Articulo primerArticulo = new Articulo(new EAN("1234567890123"), "Nombre", 200, almacen);
        primerArticulo.guarda();
        Articulo recuperaArticulo = Articulo.carga(new EAN("1234567890123"));
        System.out.println(recuperaArticulo);
        recuperaArticulo.borra();

        recuperaArticulo = Articulo.carga(new EAN("1234567890123"));
        if (recuperaArticulo == null) {
            System.out.println("Registo no encontrado");
        }
    }

}