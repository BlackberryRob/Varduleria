package com.verduleria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactoController {
    @GetMapping("/contacto")
    public String contacto() {
        return "contacto/contacto"; // Carga contacto.html desde src/main/resources/templates/
    }
}
