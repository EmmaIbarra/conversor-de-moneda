package com.aluracursos.conversordemoneda.modelos;

public class SelectorDeMonedas {
    public static Monedas seleccionarMonedas(String eleccion, double valorAConvertir){
        String monedaBase = "";
        String monedaDestino = "";

        switch (eleccion){
            case "1":
                monedaBase = "ARS";
                monedaDestino = "USD";
                break;
            case "2":
                monedaBase = "USD";
                monedaDestino = "ARS";
                break;
            case "3":
                monedaBase = "BRL";
                monedaDestino = "USD";
                break;
            case "4":
                monedaBase = "USD";
                monedaDestino = "BRL";
                break;
            case "5":
                monedaBase = "CLP";
                monedaDestino = "USD";
                break;
            case "6":
                monedaBase = "USD";
                monedaDestino = "CLP";
                break;

            default:
                System.out.println("Elija una opción válida");
                break;
        }
        return new Monedas(monedaBase, monedaDestino, valorAConvertir);
    }
}
