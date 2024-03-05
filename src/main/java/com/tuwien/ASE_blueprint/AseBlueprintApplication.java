package com.tuwien.ASE_blueprint;

import com.tuwien.ASE_blueprint.client.ASEHttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
@RestController
public class AseBlueprintApplication {

	@Autowired
	private ASEHttpClientService aseHttpClientService;

	public static void main(String[] args) {
		SpringApplication.run(AseBlueprintApplication.class, args);
	}

	@GetMapping("/entryTest/fetchToken")
	public String fetchToken() throws URISyntaxException, IOException, InterruptedException {
		return aseHttpClientService.sendSampleGETRequest();
	}

	@GetMapping("/entryTest/sendToken")
	public String sendToken() throws URISyntaxException, IOException, InterruptedException {
		return aseHttpClientService.sendSamplePOSTRequest();
	}

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return String.format("Hello %s!", name);
	}
}
