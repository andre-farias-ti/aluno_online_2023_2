package com.br.alunoonline.service;

import com.br.alunoonline.dto.AlunoCursoDTO;
import com.br.alunoonline.entity.Aluno;
import com.br.alunoonline.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Aluno> listaAlunoDiciplina(Long idDiciplina){
        return repository.listaAlunoDiciplina(idDiciplina);
    }

    public Aluno create(Aluno aluno) {

        //Set<Curso> cursoList = Collections.singleton(new Curso().toBuilder().id(dto.getIdCurso()).build());

        //Aluno aluno = Aluno.builder()
        //.nome(dto.getNomeAluno())
        //.email(dto.getEmailAluno())
        //.cursos(cursoList)
        //.build();

        return repository.save(aluno);
    }

    public Aluno update(Aluno aluno) {
        return repository.save(aluno);
    }

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
