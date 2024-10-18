package com.aluracursos.conversordemoneda.modelos;

public class Monedas {
    private String monedaBase;
    private String monedaDestino;
    private double valorAConvertir;

    public Monedas(String monedaBase, String monedaDestino, double valorAConvertir) {
        this.monedaBase = monedaBase;
        this.monedaDestino = monedaDestino;
        this.valorAConvertir = valorAConvertir;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getValorAConvertir() {
        return valorAConvertir;
    }
}
