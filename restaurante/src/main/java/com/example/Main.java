package com.example;

import com.example.utilidades.BaseDeDatos;

public class Main {
    public static void main(String[] args) {
         // Crear la instancia de BaseDeDatos y conectarse pasando usuario y contraseña
        BaseDeDatos bd = new BaseDeDatos("postgres", "lolirosa123");


        // Cerrar la conexión cuando ya no sea necesaria
        bd.cerrarConexion();
    }
}