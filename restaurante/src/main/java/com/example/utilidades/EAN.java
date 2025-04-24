package com.example.utilidades;

//Crear clase
public class EAN {
    private final String valor;

    public EAN(String valor) {
        if (valor == null || valor.length() != 13) {
            throw new IllegalArgumentException("El EAN debe tener exactamente 13 caracteres.");
        }
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }

    public static void main(String[] args) {
        try {
            EAN ean = new EAN("1234567890123"); // Correcto
            System.out.println("EAN válido: " + ean);

            EAN eanNoValido = new EAN("12345"); // Incorrecto, lanzará una excepción
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}