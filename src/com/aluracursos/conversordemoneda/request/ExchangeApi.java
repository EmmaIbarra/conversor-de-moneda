package com.aluracursos.conversordemoneda.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeApi {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";
    private String apiKey;

    public ExchangeApi(String apiKey) {
        this.apiKey = apiKey;
    }

    public double obtenerValorMoneda(String monedaBase, String monedaDestino) throws IOException, InterruptedException {
        String url = API_URL + apiKey + "/latest/" + monedaBase ;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        if (response.statusCode() == 200){
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            if (jsonObject.has("conversion_rates") && jsonObject.getAsJsonObject("conversion_rates")
                    .has(monedaDestino)) {
                return jsonObject.getAsJsonObject("conversion_rates")
                        .get(monedaDestino).getAsDouble();
            } else {
                throw new IOException("No se encontró el tipo de cambio para " + monedaDestino);
            }
        } else {
            throw new IOException("Error al obtener el tipo de cambio desde la API. Código de destado: " + response.statusCode());
        }

    }
}




























   /* private String apiKey;
   public ExchangeApi(String apiKey) {
        this.apiKey = apiKey;
    }
    public double elegirMoneda(String monedaBase, String monedaDestino) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" +
                apiKey + "/latest/" + monedaBase + "/" + monedaDestino);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
        try {
            HttpResponse<String> response = client.send
                    (request, HttpResponse.BodyHandlers.ofString());
            JsonReader reader = new JsonReader((new StringReader(response.body())));
            reader.setLenient(true);
            JsonElement jsonElement = JsonParser.parseString(response.body());

            if (jsonElement.isJsonObject()){
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonElement valorDeMoneda = jsonObject.get("conversion_rates");
                return valorDeMoneda.getAsDouble();
            }else {
                throw new RuntimeException("La respuesta no contiene un objeto JSON válido");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}*/
