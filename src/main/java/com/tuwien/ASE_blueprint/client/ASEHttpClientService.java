package com.tuwien.ASE_blueprint.client;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Component
public class ASEHttpClientService {

    private static final HttpClient httpClient = HttpClient.newBuilder().build();

    public String sendSampleGETRequest() throws URISyntaxException, IOException, InterruptedException {
        // Example to retrieve Token:
        // https://reset.inso.tuwien.ac.at/ase/<scenarioPrefix>/assignment/<yourMatrikelnr>/token
        // Example to retrieve Testcase:
        // https://reset.inso.tuwien.ac.at/ase/<scenarioPrefix>/assignment/<yourMatrikelnr>/stage/2/testcase/<testcase>?token=<token>
        // TODO how to send URL params??
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/get"))
                .GET()
                .header("Content-Type", "text/plain;charset=UTF-8")
                .timeout(Duration.of(10, SECONDS))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        HttpHeaders responseHeaders = response.headers();
        String responseBody = response.body();

        return responseBody;
    }

    public String sendSamplePOSTRequest() throws URISyntaxException, IOException, InterruptedException {
        // Example to Post Solution:
        // https://reset.inso.tuwien.ac.at/ase/<scenarioPrefix>/assignment/<yourMatrikelnr>/stage/2/testcase/<testcase>?token=<token>
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/post"))
                .POST(HttpRequest.BodyPublishers.ofString("Sample request body"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        HttpHeaders responseHeaders = response.headers();
        String responseBody = response.body();

        return responseBody;
    }

    private void postRequestWithAuthentication(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = HttpClient.newBuilder()
            .authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            "username",
                            "password".toCharArray());
                }
            }).build()
            .send(request, HttpResponse.BodyHandlers.ofString());
    }
}
