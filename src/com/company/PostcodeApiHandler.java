package com.company;

import com.google.gson.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PostcodeApiHandler {
    URI base = URI.create("http://api.postcodes.io/postcodes/");
    HttpClient client = HttpClient.newHttpClient();

    public Boolean validate(String postcode) {
        String validatePostcode = postcode + "/validate/";
        Boolean valid;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(base.resolve(validatePostcode))
                .build();
        String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        int status = jsonObject.get("status").getAsInt();
        if (status!=200){
            System.out.println("Error " + status);
            return false;
            // Could do more advanced error handling here
            // e.g. to report status code back so can tell
            // whether network issue or code logic issue
        }
        valid = jsonObject.get("result").getAsBoolean();
        return valid;
    }

    public Postcode getDetails(String postcode){
        Gson g = new Gson();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(base.resolve(postcode))
                .build();
        String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        String responseBody = new JsonParser()
                .parse(response)
                .getAsJsonObject()
                .get("result")
                .toString();
        Postcode details = g.fromJson(responseBody, Postcode.class);
        return details;
    }

    public List<String> nearest(String postcode) {
        Gson g = new Gson();
        List<String> results = new ArrayList<>();
        String nearest = postcode + "/nearest/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(base.resolve(nearest))
                .build();
        String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        JsonArray nearestPostcodes = new JsonParser()
                .parse(response)
                .getAsJsonObject()
                .get("result")
                .getAsJsonArray();
        for (JsonElement element : nearestPostcodes){
            results.add(g.fromJson(element, Postcode.class).getPostcode());
        }
        return results;
    }
}
