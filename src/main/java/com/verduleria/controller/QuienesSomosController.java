package com.verduleria.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class QuienesSomosController {
    @GetMapping("/quienesSomos")
    public String quienesSomos() {
        return "quienesSomos/quienesSomos"; // Carga quienesSomos.html desde src/main/resources/templates/
    }
}
