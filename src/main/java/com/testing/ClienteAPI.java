package com.testing;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClienteAPI {

    public static void main (String[] args) throws IOException {
        String endpoint = "";
      //  int numberOfDecks = 0;
        request(endpoint);
    }

    public static void request (String endpoint) throws IOException {

        //Le concateno a la URL base, el nro de Decks ingresado
        // + numberOfDecks;

        //Guardo mi URL completa con parametro incluido en la variable url
        String url = endpoint;

        System.out.println("La URL que vas a consumir es:\n" + url);

        HttpClientBuilder hcBuilder = HttpClients.custom();
        HttpClient client = hcBuilder.build() ;

        HttpGet requestShuffle = new HttpGet(url);

        //Setting header parameters
        requestShuffle.addHeader("Content-Type", "application/json");
        requestShuffle.addHeader("Accept", "application/json");

        //Executing the call
        HttpResponse response = client.execute(requestShuffle);
        System.out.println("\nEnviando 'Get' a " + url);
        System.out.println("HTTP Response: " +  response.getStatusLine().getStatusCode());

        //Reading the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();

        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

    }
}
