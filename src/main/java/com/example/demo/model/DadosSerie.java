package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// Utilizamos recordes, e a Anotação JsonAlias e JsonProperty para dar um apelido para aas variaveis que vão ser vinculadas com
// os dados da API
// Json Property ler e insere dados.
// JSon Alias só ler
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String Titulo,
                         @JsonAlias("totalSeasons")Integer totalTemporadas,
                         @JsonAlias("imdbRating")String avaliacao,
                         @JsonProperty("imdbVotes")String votos){
}
