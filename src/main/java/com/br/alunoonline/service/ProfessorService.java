package com.br.alunoonline.service;


import com.br.alunoonline.entity.Professor;
import com.br.alunoonline.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository repository;

    public Professor create(Professor professor) {
        return repository.save(professor);
    }
}
