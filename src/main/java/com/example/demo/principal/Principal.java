package com.example.demo.principal;

import com.example.demo.model.DadosEpisodio;
import com.example.demo.model.DadosSerie;
import com.example.demo.model.DadosTemporada;
import com.example.demo.service.ConsumoAPI;
import com.example.demo.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        //Abaixo foi usado o metodo forEach para mostrar as Temporadas.
        temporadas.forEach(System.out::println);

        // Abaixo o codigo tras as informações dos Episodios.
        // utlizamos o for para interar.
        /* for (int i = 0; i < dados.totalTemporadas(); i++) {
            List<DadosEpisodio> episodios = temporadas.get(i).episodios();
            for(int j = 0; j < episodios.size(); j++ ){
                System.out.println(episodios.get(j).titulo());
            }
       } */

        // abaixo uma forma mais simples de trazer os dados de Episodios.
        // LAMBIDA!
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));








    }
}