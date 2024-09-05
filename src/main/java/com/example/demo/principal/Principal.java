package com.example.demo.principal;

import com.example.demo.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    // abaixo foi criado um contante!
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API = "&apikey=a74872f8";
    Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();

    public void exibeMenu(){
        System.out.println("Digite o nome da série para a busaca");
        String nomeSerie = leitura.nextLine();
        var json = consumo.obterDados( ENDERECO + nomeSerie.replace(" ", "+") + API);
        System.out.println(json);
    }

}
