/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.verduleria.controller;

import com.verduleria.domain.Categoria;
import com.verduleria.service.CategoriaService;
import com.verduleria.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);
        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String listado(Model model, Categoria categoria) {
        var productos = categoriaService.getCategoria(categoria).getProductos();
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);
        return "/pruebas/listado";
    }

    @GetMapping("/listado2")
    public String listado2(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("productos", productos);
        return "/pruebas/listado2";
    }


    /*Practica #4 Debe agregar al proyecto tienda una versión de una consulta ampliada de su libre escogencia, 
    puede ser de tabla producto, categoria o venta.*/
    @PostMapping("/query1")
    public String consultaQueryDescripcion(@RequestParam(value = "descripcion") String descripcion, Model model) {
        var productos = productoService.findByDescripcionContainingIgnoreCaseOrderByPrecioAsc(descripcion);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("productos", productos);
        model.addAttribute("descripcion", descripcion);
        return "/pruebas/listado2";
    }

    //Ejemplo 2
    @PostMapping("/query2")
    public String consultaQuery2(@RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup, Model model) {
        var productos = productoService.metodoJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";
    }

    //Ejemplo 3
    @PostMapping("/query3")
    public String consultaQueryCategoria(@RequestParam(value = "descripcionCategoria") String descripcionCategoria, Model model) {
        var productos = productoService.metodoNativo(descripcionCategoria);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("descripcionCategoria", descripcionCategoria);
        return "/pruebas/listado2";
    }
}
