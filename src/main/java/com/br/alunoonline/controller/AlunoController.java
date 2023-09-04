package com.br.alunoonline.controller;

import com.br.alunoonline.entity.Aluno;
import com.br.alunoonline.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService service;

    @GetMapping("/listar-todos")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> findAll() {
        return service.listaTodos();
    }

}
