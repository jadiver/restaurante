package com.example.pedidos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.utilidades.BaseDeDatos;
import com.example.utilidades.EAN;

public class Pedido {
    // Propiedades
    public int id;
    boolean pendiente;
    Proveedor proveedor;
    Date fecha;
    int diasReclamacion;
    List<DetallePedido> lista;

    // Métodos
    // Constructor
    public Pedido(int id, Proveedor proveedor) {
        this.id = id;
        pendiente = true;
        this.proveedor = proveedor;
        fecha = new Date(id);
        diasReclamacion = 15;
        lista = new ArrayList<>();
    }

    public void addLinea(DetallePedido linea) {
        lista.add(linea);
    }

    public void borraLinea(Articulo articulo) {
        Iterator<DetallePedido> iterator = lista.iterator();
        while (iterator.hasNext()) {
            DetallePedido detalle = iterator.next();
            // Comparamos el artículo de la línea con el artículo proporcionado
            if (detalle.getArticulo().equals(articulo)) {
                // Eliminamos la línea si coincide
                iterator.remove();
                return; // Salimos del método tras eliminar la primera coincidencia
            }
        }
    }

    public boolean guarda() {
        boolean ok = true;
        // Primero guardar cabecera
        String strPendiente = "true";
        if (!pendiente) {
            strPendiente = "false";
        }
        int idProveedor = proveedor.id;
        String sql = "INSERT INTO Pedidos VALUES ("
                + id + ","

                + strPendiente + ","
                + idProveedor + ",'"
                + fecha + "',"
                + diasReclamacion
                + ")";
        ok = ok && (BaseDeDatos.ejecutar(sql) >= 0);
        // Luego guardar detalle
        for (DetallePedido linea : lista) {
            String fechaCancelacion = "null";
            if (linea.fechaCancelacion != null) {
                fechaCancelacion = "'" + linea.fechaCancelacion + "'";
            }
            String sqlLinea = "INSERT INTO Lineas_Pedido VALUES ("
                    + id + ",'"
                    + linea.articulo.id.getValor() + "',"
                    + linea.cantidadPedida + ","
                    + linea.cantidadRecibida + ","
                    + linea.estado.ordinal() + ","
                    + linea.precio + ","
                    + fechaCancelacion
                    + ")";
            System.out.println(sqlLinea);
            ok = ok && (BaseDeDatos.ejecutar(sqlLinea) >= 0);
        }
        return ok;
    }

    public boolean borra() {
        String sql = "DELETE FROM Pedidos WHERE id= " + id;
        return BaseDeDatos.ejecutar(sql) >= 0;
    }

    public static Pedido carga(int id) {
        String sql = "SELECT pendiente,proveedor,fecha,dias_reclamacion FROM Pedidos WHERE id= " + id;
        List<Map<String, Object>> resultado = BaseDeDatos.consultar(sql);
        if (resultado.isEmpty()) {
            return null;
        }
        int idProveedor = (int) resultado.get(0).get("proveedor");
        Proveedor proveedor = Proveedor.carga(idProveedor);
        Pedido miPedido = new Pedido(id, proveedor);
        miPedido.pendiente = (boolean) resultado.get(0).get("pendiente");
        miPedido.fecha = (Date) resultado.get(0).get("fecha");
        miPedido.diasReclamacion = (int) resultado.get(0).get("dias_reclamacion");
        return miPedido;
    }

    @Override
    public String toString() {
        String cabecera = "Pedido: " + id + "\n"

                + "Proveedor: " + proveedor;
        String detalle = "";
        for (DetallePedido linea : lista) {
            detalle += " Articulo: " + linea.getArticulo() + "\n"
                    + " cantidad pedida: " + linea.getCantidadPedida();
        }
        return cabecera + "\n" + detalle + "\n";
    }

    public static void main(String... parametros) {
        BaseDeDatos.conecta();
        Proveedor p = new Proveedor(1, "Coca Cola");
        Pedido miPedido = new Pedido(1, p);
        System.out.println(miPedido.toString());
        EAN ean1 = new EAN("1234567890123");
        Articulo articulo1 = new Articulo(ean1, "Articulo1");
        double precio = 12.0;
        DetallePedido d1 = new DetallePedido(miPedido, articulo1, 1000, precio);
        miPedido.addLinea(d1);
        EAN ean2 = new EAN("1234567890123");
        Articulo articulo2 = new Articulo(ean2, "Articulo2");

        double precio2 = 22.0;
        DetallePedido d2 = new DetallePedido(miPedido, articulo2, 1500, precio2);
        miPedido.addLinea(d2);
        System.out.println(miPedido.toString());
        miPedido.borraLinea(articulo1);
        System.out.println("\nDespues de borrar...\n");
        System.out.println(miPedido.toString());
        miPedido.borra();
        miPedido.guarda();
    }
}