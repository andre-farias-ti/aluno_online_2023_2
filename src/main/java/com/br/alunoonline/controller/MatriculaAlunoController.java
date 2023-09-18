package com.br.alunoonline.controller;


import com.br.alunoonline.dto.HistoricoAlunoDto;
import com.br.alunoonline.dto.MatriculaAlunoNotasOnlyDto;
import com.br.alunoonline.entity.Aluno;
import com.br.alunoonline.entity.MatriculaAluno;
import com.br.alunoonline.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula-aluno")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MatriculaAluno> create(@RequestBody MatriculaAluno matriculaAluno) {
        MatriculaAluno matriculaAlunoCreated = service.create(matriculaAluno);

        return ResponseEntity.status(201).body(matriculaAlunoCreated);
    }

    @PatchMapping("/trancar-matricula/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void patchStatus(@PathVariable Long id) throws Exception {
        service.trancarMatricula(id);
    }

    @GetMapping("/estudante/media/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Double retornaMediaAlunos() {

        return service.retonaMediaAluno();
    }

    @GetMapping(value = "/estudante/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> retornaAlunosStatus(@PathVariable String status) {

        return service.retonaAlunosStatus(status);
    }

}
