package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@AllArgsConstructor
@Slf4j

public class HttpService {

    public static final TypeReference<CocktailResponse> COCKTAIL_RESPONSE_TYPE_REFERENCE = new TypeReference<>() {
    };

    public static final TypeReference<List<CocktailResponse>> COCKTAIL_LIST_RESPONSE_TYPE_REFERENCE = new TypeReference<>() {
    };

    private final HttpClient httpClient;

    private final String domain;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(HttpService.class);

    public static final String BASE_PATH = "/api/json/v1/1";


    public CocktailResponse getCocktailByName(String name) {
        URI uri = URI.create(domain + BASE_PATH + "/search.php?s=" + name);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return handleResponse(response, COCKTAIL_RESPONSE_TYPE_REFERENCE);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve cocktail by ID", e);
        }
    }

    public CocktailResponse getCocktailByFirstLetter(String firstLetter) {
        URI uri = URI.create(domain + BASE_PATH + "/search.php?f=" + firstLetter);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return handleResponse(response, COCKTAIL_RESPONSE_TYPE_REFERENCE);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve cocktail by ID", e);
        }
    }

    public CocktailResponse getCocktailByIngredient(String ingredient) {
        URI uri = URI.create(domain + BASE_PATH + "/search.php?i=" + ingredient);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return handleResponse(response, COCKTAIL_RESPONSE_TYPE_REFERENCE);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve cocktail by ID", e);
        }
    }


    public List<CocktailResponse> getCocktailsById(List<String> ids,
                                                   int limit,
                                                   int offset) {
        URI uri = URI.create(buildQueryParameters(ids, limit, offset));
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return handleResponse(response, COCKTAIL_LIST_RESPONSE_TYPE_REFERENCE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private <T> T handleResponse(HttpResponse<String> response,
                                 TypeReference<T> typeReference) throws JsonProcessingException {
        int statusCode = response.statusCode();
        JsonNode node = mapper.readValue(response.body(), JsonNode.class);
        if (statusCode >= 200 && statusCode < 300) {
            JsonNode drinks = node.get("drinks");
            logger.info("Successful response");
            return mapper.readValue(drinks.toString(), typeReference);
        } else if (statusCode >= 400 && statusCode < 500) {
            JsonNode error = node.get("Error");
            logger.warn(String.format("Client side error: %s", error));
        } else if (statusCode >= 500) {
            logger.error("Downstream error: ");

        }
        return null;
    }


    private String buildQueryParameters(List<String> ids, int limit, int offset) {
        StringBuilder builder = new StringBuilder();
        builder.append(domain);
        builder.append(BASE_PATH);
        builder.append("?");
        if (limit > 0) {
            builder.append("limit=");
            builder.append(limit);
            builder.append("&");
        }
        if (offset > 0) {
            builder.append("offset=");
            builder.append(offset);
            builder.append("&");
        }
        if (!ids.isEmpty()) {
            builder.append("ids=");
            builder.append(String.join(",", ids));
        }
        return builder.toString();
    }
}