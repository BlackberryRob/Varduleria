package com.verduleria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.verduleria.service.CorreoService;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;

@Controller
public class ContactoController {

    @Autowired
    private CorreoService correoService;

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto/contacto"; // Carga contacto.html
    }

    @PostMapping("/contacto/enviar")
    public String enviarCorreo(
            @RequestParam("nombre") String nombre,
            @RequestParam("correo") String correo,
            @RequestParam("telefono") String telefono,
            @RequestParam("comentario") String comentario,
            Model model) {

        String destinatario = "rob.mora@outlook.com"; 
        String asunto = "Nuevo mensaje de contacto";
        String contenido = """
                <h3>Nuevo mensaje recibido</h3>
                <p><strong>Nombre:</strong> %s</p>
                <p><strong>Correo:</strong> %s</p>
                <p><strong>Teléfono:</strong> %s</p>
                <p><strong>Comentario:</strong> %s</p>
                """.formatted(nombre, correo, telefono, comentario);

        try {
            correoService.enviarCorreoHtml(destinatario, asunto, contenido);
            model.addAttribute("mensaje", "Correo enviado con éxito");
            return "redirect:/contacto";
        } catch (MessagingException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al enviar el correo");
            return "redirect:/errores";
        }
    }
}
