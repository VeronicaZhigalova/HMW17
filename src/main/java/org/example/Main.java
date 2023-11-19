package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String domain = "https://thecocktaildb.com";
        String searchTerm = "margarita";
        int limit = 10;
        int offset = 0;
        String searchUri = domain + "/api/json/v1/1/search.php?s=" + searchTerm + "&l=" + limit + "&o=" + offset;
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(searchUri))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(response.body());
                for (JsonNode drink : jsonResponse.path("drinks")) {
                    String idDrink = drink.path("idDrink").asText();
                    String strDrink = drink.path("strDrink").asText();
                    String strCategory = drink.path("strCategory").asText();
                    String strAlcoholic = drink.path("strAlcoholic").asText();
                    System.out.println(" _____THE FIRST ONE_____");
                    System.out.println();
                    System.out.println("idDrink: " + idDrink);
                    System.out.println("strDrink: " + strDrink);
                    System.out.println("strCategory: " + strCategory);
                    System.out.println("strAlcoholic: " + strAlcoholic);
                    System.out.println();
                }
            } else {
                System.out.println("Failed to retrieve cocktails. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }


        HttpClient httpClient1 = HttpClient.newHttpClient();
        String domain1 = "https://thecocktaildb.com";
        String searchTerm1 = "a";
        int limit1 = 10;
        int offset1 = 0;
        String searchUri1 = domain1 + "/api/json/v1/1/search.php?f=" + searchTerm1 + "&l=" + limit1 + "&o=" + offset1;
        HttpRequest request1 = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(searchUri1))
                .build();
        try {
            HttpResponse<String> response1 = httpClient1.send(request1, HttpResponse.BodyHandlers.ofString());
            if (response1.statusCode() >= 200 && response1.statusCode() < 300) {
                ObjectMapper objectMapper1 = new ObjectMapper();
                JsonNode jsonResponse1 = objectMapper1.readTree(response1.body());
                for (JsonNode drink : jsonResponse1.path("drinks")) {
                    String idDrink = drink.path("idDrink").asText();
                    String strDrink = drink.path("strDrink").asText();
                    String strCategory = drink.path("strCategory").asText();
                    String strAlcoholic = drink.path("strAlcoholic").asText();
                    System.out.println(" _____THE SECOND ONE_____");
                    System.out.println();
                    System.out.println("idDrink: " + idDrink);
                    System.out.println("strDrink: " + strDrink);
                    System.out.println("strCategory: " + strCategory);
                    System.out.println("strAlcoholic: " + strAlcoholic);
                    System.out.println();
                }
            } else {
                System.out.println("Failed to retrieve cocktails. Status code: " + response1.statusCode());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }


        HttpClient httpClient2 = HttpClient.newHttpClient();
        String domain2 = "https://thecocktaildb.com";
        String searchTerm2 = "vodka";
        int limit2 = 10;
        int offset2 = 0;
        String searchUri2 = domain2 + "/api/json/v1/1/search.php?i=" + searchTerm2 + "&l=" + limit2 + "&o=" + offset2;
        HttpRequest request2 = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(searchUri2))
                .build();
        try {
            HttpResponse<String> response1 = httpClient2.send(request2, HttpResponse.BodyHandlers.ofString());
            if (response1.statusCode() >= 200 && response1.statusCode() < 300) {
                ObjectMapper objectMapper1 = new ObjectMapper();
                JsonNode jsonResponse1 = objectMapper1.readTree(response1.body());
                for (JsonNode drink : jsonResponse1.path("ingredients")) {
                    String idIngredient = drink.path("idIngredient").asText();
                    String strIngredient = drink.path("strIngredient").asText();
                    String strType = drink.path("strType").asText();
                    String strAlcohol = drink.path("strAlcohol").asText();
                    System.out.println(" _____THE THIRD ONE_____");
                    System.out.println();
                    System.out.println("idIngredient: " + idIngredient);
                    System.out.println("strIngredient: " + strIngredient);
                    System.out.println("strType: " + strType);
                    System.out.println("strAlcoholic: " + strAlcohol);
                    System.out.println();
                }
            } else {
                System.out.println("Failed to retrieve cocktails. Status code: " + response1.statusCode());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}