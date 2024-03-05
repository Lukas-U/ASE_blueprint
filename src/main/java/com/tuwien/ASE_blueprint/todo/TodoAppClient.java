package com.tuwien.ASE_blueprint.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TodoAppClient {

    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new GsonBuilder().create();

    // TODO use either Jackson or Gson for Mapping the JSON String to the object
    public Todo mapJSONStringToTodoObject_Jackson() throws Exception {

        String response = sampleApiRequest();
        Todo[] todo = objectMapper.readValue(response, Todo[].class);

        return todo[0];
    }

    public Todo mapJSONStringToTodoObject_Gson() throws Exception {
        String response = sampleApiRequest();
        List<Todo> todo = gson.fromJson(response, new TypeToken<List<Todo>>(){}.getType());
        return todo.get(0);
    }

    String sampleApiRequest() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                // this API returns Todos in JSON Format
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
