package com.example.demo;

import com.example.demo.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	// foi criada está anotação para rodar a aplicação. Foi implementado CommandLineRunner.
	@Override
	public void run(String... args) throws Exception {
		var consumo = new ConsumoAPI();
		var json = consumo.obterDados("http://www.omdbapi.com/?t=gilmore+girls&Season=1&apikey=a74872f8");
		System.out.println(json);
	}



}
