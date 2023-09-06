package com.br.alunoonline.service;

import com.br.alunoonline.dto.AlunoCursoDTO;
import com.br.alunoonline.entity.Aluno;
import com.br.alunoonline.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void atualizaEmailAluno(String email, Long id){
        repository.atualizaEmailAluno(email, id);
    }

    public List<Aluno> listaAlunoDiciplina(Long idDiciplina){
        return repository.listaAlunoDiciplina(idDiciplina);
    }

}
