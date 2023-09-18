package com.br.alunoonline.repository;

import com.br.alunoonline.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    public List<Disciplina> findByProfessorId(Long professorId);
}
