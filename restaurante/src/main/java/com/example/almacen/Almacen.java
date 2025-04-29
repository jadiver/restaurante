package com.example.almacen;

import java.util.List;
import java.util.Map;

import com.example.pedidos.Proveedor;
import com.example.utilidades.BaseDeDatos;

public class Almacen {
    // Propiedades
    int id;
    String nombre;

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

    public static Almacen carga(int id) {
        String sql = "SELECT nombre FROM Almacenes WHERE ID= " + id;
        List<Map<String, Object>> resultado = BaseDeDatos.consultar(sql);
        if (resultado.isEmpty()) {
            return null;
        }
        Almacen nuevoAlmacen = new Almacen(id, (String) resultado.get(0).get("nombre"));
        return nuevoAlmacen;
    }

    // Método main para prueba
    public static void main(String[] args) {
        Almacen almacen = new Almacen(1, "Almacén Norte");

        System.out.println("ID del almacén: " + almacen.getId());
        System.out.println("Nombre del almacén: " + almacen.getNombre());
    }
}
