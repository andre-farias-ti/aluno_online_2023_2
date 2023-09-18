package com.br.alunoonline.service;


import com.br.alunoonline.entity.Disciplina;
import com.br.alunoonline.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    public Disciplina create(Disciplina disciplina) {
        return repository.save(disciplina);
    }

    public List<Disciplina> getByProfessorId(Long professorId) {

        return repository.findByProfessorId(professorId);

    }
}
