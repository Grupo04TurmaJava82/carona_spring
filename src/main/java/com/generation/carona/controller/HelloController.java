package com.generation.carona.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teste")
public class HelloController {

    @GetMapping
    public String hello() {
        return "Olá, Testando!";
    }
}
