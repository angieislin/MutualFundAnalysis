package com.anonymity.scraping.mutualfundanalysis.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
public class GenAiUtils {

    public static String getOverallIdea(String apiKey) {
        String prompt = "Translate the following English text to French: 'Hello, how are you?'";
        String engine = "text-davinci-002";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/engines/" + engine + "/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(new JSONObject()
                        .put("prompt", prompt)
                        .put("max_tokens", 60)
                        .toString()))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
