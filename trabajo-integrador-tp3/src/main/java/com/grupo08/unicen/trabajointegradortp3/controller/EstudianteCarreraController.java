package com.grupo08.unicen.trabajointegradortp3.controller;

import com.grupo08.unicen.trabajointegradortp3.service.EstudianteCarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstudianteCarreraController {

    @Autowired
    private EstudianteCarreraService estudianteCarreraService;
}
