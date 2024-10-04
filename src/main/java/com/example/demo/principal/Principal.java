package com.example.demo.principal;

import com.example.demo.model.DadosEpisodio;
import com.example.demo.model.DadosSerie;
import com.example.demo.model.DadosTemporada;
import com.example.demo.model.Episodios;
import com.example.demo.service.ConsumoAPI;
import com.example.demo.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    // abaixo foi criado um contante!
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API = "&apikey=a74872f8";
    Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        System.out.println("Digite o nome da série para a busca");
        String nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
        List<DadosTemporada> temporadas = new ArrayList<>();
        // Aqui realizamos um For para que ocorra a interação.
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            var json2 = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API);
            DadosTemporada dadosTemporada = conversor.obterDados(json2, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        //Abaixo foi usado o metodo forEach para listar/mostrar as Temporadas.
        temporadas.forEach(System.out::println);

        // O codigo abaixo trás as informações dos Episodios.
        // utlizamos o for para interar.
        /* for (int i = 0; i < dados.totalTemporadas(); i++) {
            List<DadosEpisodio> episodios = temporadas.get(i).episodios();
            for(int j = 0; j < episodios.size(); j++ ){
                System.out.println(episodios.get(j).titulo());
            }
       } */

        // Abaixo uma forma mais simples de trazer os dados de Episodios utilizando Lambda!
        // LAMBDA!
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        // Abaixo o codigo tratasse de um Stream para pegar os episodios
        // Foi utilizado os metodos: stream, flatMap(utilizado para criar outra lista), collect.
        // sorted, comparing, reversed
        List<DadosEpisodio> dadosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream()).collect(Collectors.toList());
        // Mostra a lista;
        System.out.println("\nTop 5 episódios: ");
        dadosEpisodios.stream().filter(e -> !e.avaliacao().equalsIgnoreCase("N/A")).sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()).limit(5).forEach(System.out::println);

        //
        System.out.println("Teste:   ");
        List<Episodios> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodios(t.numero(), d))
                ).collect(Collectors.toList());


    }
}