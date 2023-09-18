package com.br.alunoonline.repository;

import com.br.alunoonline.entity.Aluno;
import com.br.alunoonline.entity.MatriculaAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaAlunoRepository extends JpaRepository<MatriculaAluno, Long> {
    public List<MatriculaAluno> findByAlunoId(Long alunoId);

    @Query(value = "SELECT (sum(e.nota1) + sum(nota2)) / (count(nota1) + count(nota2)) media FROM matricula_aluno e", nativeQuery = true)
    public Double retornaMedia();

    public List<MatriculaAluno> findByStatus(String status);
    @Query(value = "select a from MatriculaAluno m, Aluno a where a.id = m.aluno.id and " +
            " m.status = :status")
    public List<Aluno> retornaAlunos(String status);
}
