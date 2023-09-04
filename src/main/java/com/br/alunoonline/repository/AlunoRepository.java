package com.br.alunoonline.repository;

import com.br.alunoonline.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNomeContainsIgnoreCase(String nome);

    List<Aluno> findByNomeOrCursos(String nome, String curso);

}
