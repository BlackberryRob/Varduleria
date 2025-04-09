package com.verduleria.controller;

import com.verduleria.service.ReporteService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/usuarios")
    public ResponseEntity<Resource> reporteUsuarios(@RequestParam String tipo) throws Exception {
        return reporteService.generaReporte("usuarios", new HashMap<>(), tipo);
    }

    @GetMapping("/ventas")
    public ResponseEntity<Resource> reporteVentas(@RequestParam String tipo) throws Exception {
        return reporteService.generaReporte("ventas", new HashMap<>(), tipo);
    }

    @GetMapping("/ventasTotales")
    public ResponseEntity<Resource> reporteVentasTotales(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin,
            @RequestParam String tipo) throws Exception {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicial", fechaInicio);
        parametros.put("fechaFinal", fechaFin);

        return reporteService.generaReporte("ventasTotales", parametros, tipo);
    }
}
