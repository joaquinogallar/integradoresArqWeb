package com.grupo08.unicen.trabajointegradortp3.service;

import com.grupo08.unicen.trabajointegradortp3.repository.EstudianteCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteCarreraService {

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;
}
