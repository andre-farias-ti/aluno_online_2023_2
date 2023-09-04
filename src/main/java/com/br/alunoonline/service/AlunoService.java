package com.br.alunoonline.service;

import com.br.alunoonline.dto.AlunoCursoDTO;
import com.br.alunoonline.entity.Aluno;
import com.br.alunoonline.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> listaTodos(){
        return repository.findAll();
    }

    public List<Aluno> listaPorNome(String nome){
        return repository.findByNomeContainsIgnoreCase(nome);
    }

    public List<Aluno> listaPorNomeOrCurso(AlunoCursoDTO dto){
        return repository.findByNomeOrCursos(dto.getNome(), dto.getCurso());
    }

}
