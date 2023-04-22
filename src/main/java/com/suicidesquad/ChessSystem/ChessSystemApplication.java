package com.suicidesquad.ChessSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ChessSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessSystemApplication.class, args);
	}

	@GetMapping
	public String Hello() {
		return "Hello World";
	};
}
