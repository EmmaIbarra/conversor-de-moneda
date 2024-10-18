package com.aluracursos.conversordemoneda.principal;

import com.aluracursos.conversordemoneda.modelos.CalculosDeMoneda;
import com.aluracursos.conversordemoneda.modelos.Monedas;
import com.aluracursos.conversordemoneda.modelos.SelectorDeMonedas;
import com.aluracursos.conversordemoneda.request.ExchangeApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ExchangeApi exchangeApi = new ExchangeApi("561246189f1959db6eb8c7df");
        CalculosDeMoneda calculosDeMoneda = new CalculosDeMoneda();
        SelectorDeMonedas selectorDeMonedas = new SelectorDeMonedas();
        Scanner teclado = new Scanner(System.in);

        List<String> historialDeConversiones = new ArrayList<>();

        while (true) {
            try{System.out.println("***************************************");
                System.out.println("Bienvenid@. Elija la opción que desee \n");
                System.out.println("1. Peso argentino => Dólar");
                System.out.println("2. Dólar => Peso argentino");
                System.out.println("3. Real brasileño => Dólar");
                System.out.println("4. Dólar => Real brasileño");
                System.out.println("5. Peso chileno => Dólar");
                System.out.println("6. Dólar => Peso chileno");
                System.out.println("7. Historial de conversiones ");
                System.out.println("9. Salir \n");
                System.out.println("***************************************");
                String eleccion = teclado.nextLine();

                if (eleccion.equals("9")) {
                    System.out.println("Cerrando aplicación.");
                    break;
                }

                if (eleccion.equals("7")) {
                    if (historialDeConversiones.isEmpty()) {
                        System.out.println("No hay conversiones registradas por el momento");
                    }else {
                        System.out.println("Historial de conversiones:");
                        for (String conversion : historialDeConversiones){
                            System.out.println(conversion);
                        }
                    }
                    continue;
                }

                System.out.println("Ingrese el monto a convertir: ");
                double valorAConvertir = teclado.nextDouble();
                teclado.nextLine();

                Monedas monedas = SelectorDeMonedas.seleccionarMonedas(eleccion, valorAConvertir);

                if(monedas.getMonedaBase() == null || monedas.getMonedaBase().isEmpty() ||
                        monedas.getMonedaDestino() == null || monedas.getMonedaDestino().isEmpty()) {
                    System.out.println("Selección no válida, inténtelo nuevamente");
                    continue;
                }

                double tasaDeConversion = exchangeApi.obtenerValorMoneda(monedas.getMonedaBase(), monedas.getMonedaDestino());


                double resultado = calculosDeMoneda.conversion(valorAConvertir, tasaDeConversion);
                System.out.println(String.format("El valor de " + valorAConvertir +
                        "(" + monedas.getMonedaBase() + ") equivale a: %.3f %s", resultado, monedas.getMonedaDestino()));

               String conversion = String.format("Conversión de: %.2f %s ---> %.2f %s",
                        monedas.getValorAConvertir(), monedas.getMonedaBase(),
                        resultado, monedas.getMonedaDestino());
                historialDeConversiones.add(conversion);

            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número válido.");
                teclado.nextLine();
            } catch (NullPointerException e) {
                System.out.println("Hubo un error en la selección de las monedas. Por favor, intente de nuevo.");
            } catch (RuntimeException | IOException | InterruptedException e) {
                System.out.println("Se produjo un error inesperado: " + e.getMessage());


            }
        }
    }
}
