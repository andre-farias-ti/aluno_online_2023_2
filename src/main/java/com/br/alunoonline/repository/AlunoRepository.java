package com.br.alunoonline.repository;

import com.br.alunoonline.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNomeContainsIgnoreCase(String nome);

    @Query(value = "select " +
            "    a.* " +
            "    from " +
            "    aluno_online_2023_2.aluno a " +
            "    inner join matricula_aluno ma on " +
            "    ma.id = a.id " +
            "    inner join disciplina d on " +
            "    d.id = ma.disciplina_id " +
            "            where " +
            "    d.id = ?1", nativeQuery = true)
    public List<Aluno> listaAlunoDiciplina(Long idDiciplina);


}
