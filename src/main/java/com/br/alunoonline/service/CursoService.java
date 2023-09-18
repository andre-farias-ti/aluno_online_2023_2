package com.br.alunoonline.service;

import com.br.alunoonline.entity.Aluno;
import com.br.alunoonline.entity.Curso;
import com.br.alunoonline.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    CursoRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Curso create(Curso curso) {
        return repository.save(curso);
    }

    public List<Curso> findAll() {
        return repository.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Aluno> buscarListaAlunos(Long id){
        Query query = entityManager.createNativeQuery("select a.* from aluno_online.curso c" +
                "  inner join aluno_online.aluno_curso ac on c.id = ac.id_curso" +
                "  inner join  aluno_online.aluno a on ac.id_aluno = a.id" +
                "  where c.id = " + id, "AlunosCursosMapping");
        List<Aluno> pessoas = query.getResultList();
        return pessoas;
    }
    
}
