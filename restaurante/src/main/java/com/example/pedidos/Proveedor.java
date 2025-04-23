package com.example.pedidos;

import java.util.List;
import java.util.Map;

import com.example.utilidades.BaseDeDatos;

public class Proveedor {
    // Propiedades
    int id;
    String nombre;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    // Métodos
        // Constructor
    public Proveedor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public boolean guarda() {
        String sql = "INSERT INTO Proveedores VALUES (" + id + ",'" + nombre + "')";
        return BaseDeDatos.ejecutar(sql) >= 0;
    }

    public boolean borra() {
        String sql = "DELETE FROM Proveedores WHERE ID= " + id;
        return BaseDeDatos.ejecutar(sql) >= 0;
    }

    public static Proveedor carga(int id) {
        String sql = "SELECT nombre FROM Proveedores WHERE ID= " + id;
        List<Map<String, Object>> resultado = BaseDeDatos.consultar(sql);
        if (resultado.isEmpty()) {
            return null;
        }
        Proveedor nuevoProveedor = new Proveedor(id, (String) resultado.get(0).get("nombre"));
        return nuevoProveedor;
    }

    @Override
    public String toString() {
        return ("ID....: " + id + "\n"
                + "Nombre: " + nombre);
    }

    // Método main para prueba
    public static void main(String[] args) {

        BaseDeDatos.conecta();
        Proveedor primerProveedor = new Proveedor(1, "Chiquito");
        primerProveedor.guarda();
        Proveedor recuperaProveedor = Proveedor.carga(1);
        System.out.println(recuperaProveedor);
        recuperaProveedor.borra();
        recuperaProveedor = Proveedor.carga(1);
        if (recuperaProveedor == null) {
            System.out.println("Registo no encontrado");
        }
    }
}
