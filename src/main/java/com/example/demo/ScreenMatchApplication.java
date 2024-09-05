package com.example.demo;

import com.example.demo.model.DadosEpisodio;
import com.example.demo.model.DadosSerie;
import com.example.demo.model.DadosTemporada;
import com.example.demo.service.ConsumoAPI;
import com.example.demo.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	// foi criada está anotação para rodar a aplicação. Foi implementado CommandLineRunner.
	@Override
	public void run(String... args) throws Exception {
		var consumo = new ConsumoAPI();
		var json = consumo.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=a74872f8");
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		var json1 = consumo.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=a74872f8");
		DadosEpisodio dados1 = conversor.obterDados(json1, DadosEpisodio.class);
		System.out.println(dados1);

		List<DadosTemporada> temporadas = new ArrayList<>();
		for(int i = 1; i<=dados.totalTemporadas(); i++){
			var json2 = consumo.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season="+i+"&apikey=a74872f8");
			DadosTemporada dadosTemporada = conversor.obterDados(json2, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		// Abaixo foi usado o metodo forEach.
		temporadas.forEach(System.out::println);
	}
}
