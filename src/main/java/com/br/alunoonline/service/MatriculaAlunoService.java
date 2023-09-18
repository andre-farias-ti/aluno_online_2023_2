package com.br.alunoonline.service;

import com.br.alunoonline.dto.DisciplinasAlunoDto;
import com.br.alunoonline.dto.HistoricoAlunoDto;
import com.br.alunoonline.dto.MatriculaAlunoNotasOnlyDto;
import com.br.alunoonline.entity.Aluno;
import com.br.alunoonline.entity.MatriculaAluno;
import com.br.alunoonline.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatriculaAlunoService {

    static final Double gradesAvgToApprove = 7.0;

    @Autowired
    MatriculaAlunoRepository repository;

    public MatriculaAluno create(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus("MATRICULADO");
        return repository.save(matriculaAluno);
    }

    public void trancarMatricula(Long matriculaAlunoId) throws Exception {
        Optional<MatriculaAluno> matriculaAlunoToUpdate = repository.findById(matriculaAlunoId);

        if (matriculaAlunoToUpdate.isPresent()) {
            if (Objects.equals(matriculaAlunoToUpdate.get().getStatus(), "MATRICULADO")) {
                matriculaAlunoToUpdate.ifPresent(matriculaAluno -> matriculaAluno.setStatus("TRANCADA"));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível trancar com status MATRICULADO.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada.");
        }
        repository.save(matriculaAlunoToUpdate.get());
    }

    public Double retonaMediaAluno() {

        return repository.retornaMedia();
    }

    public List<Aluno> retonaAlunosStatus(String status) {

        return repository.retornaAlunos(status);
    }

}
