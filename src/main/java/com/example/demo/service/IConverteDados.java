package com.example.demo.service;

public interface IConverteDados {
    // retorna um Generics
    // Abaixo a maneira de criar um generics;
    <T> T obterDados(String json, Class<T> classe);
}
