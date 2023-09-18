package com.br.alunoonline.controller;


import com.br.alunoonline.entity.Professor;
import com.br.alunoonline.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Professor> create(@RequestBody Professor professor) {
        Professor professorCreated = service.create(professor);

        return ResponseEntity.status(201).body(professorCreated);
    }
}
