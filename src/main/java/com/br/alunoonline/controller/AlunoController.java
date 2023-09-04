package com.br.alunoonline.controller;

import com.br.alunoonline.dto.AlunoCursoDTO;
import com.br.alunoonline.entity.Aluno;
import com.br.alunoonline.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/listar-por-nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> buscarNome(@PathVariable String nome) {
        return service.listaPorNome(nome);
    }

    @PostMapping("/listar-por-nome-curso")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> buscarNomeOrCurso(@RequestBody AlunoCursoDTO dto) {
        return service.listaPorNomeOrCurso(dto);
    }

}
