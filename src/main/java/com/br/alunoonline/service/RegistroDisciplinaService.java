package com.br.alunoonline.service;

import com.br.alunoonline.dto.DisciplinasAlunoDto;
import com.br.alunoonline.dto.HistoricoAlunoDto;
import com.br.alunoonline.dto.MatriculaAlunoNotasOnlyDto;
import com.br.alunoonline.entity.Disciplina;
import com.br.alunoonline.entity.MatriculaAluno;
import com.br.alunoonline.entity.RegistroDisciplina;
import com.br.alunoonline.repository.RegistroDisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistroDisciplinaService {

    @Autowired
    private RegistroDisciplinaRepository repository;

    static final Double gradesAvgToApprove = 7.0;

    public RegistroDisciplina create(RegistroDisciplina registroDisciplina) {
        return repository.save(registroDisciplina);
    }

    public HistoricoAlunoDto getHistoricoFromAluno(Long matriculaAlunoId) {
        List<RegistroDisciplina> registroDisciplina = repository.findByMatriculaId(matriculaAlunoId);

        HistoricoAlunoDto historicoAlunoDto = new HistoricoAlunoDto();

        List<DisciplinasAlunoDto> disciplinasAlunoDtoList = new ArrayList<>();

        if (!registroDisciplina.isEmpty()) {

            registroDisciplina.stream().forEach(d -> {

                historicoAlunoDto.setCurso(d.getDisciplina().getCurso());
                historicoAlunoDto.setNomeAluno(d.getMatricula().getAluno().getNome());

                DisciplinasAlunoDto disciplinasAlunoDto = new DisciplinasAlunoDto();

                disciplinasAlunoDto.setNome(d.getDisciplina().getNome());
                disciplinasAlunoDto.setNota1(d.getNota1());
                disciplinasAlunoDto.setNota2(d.getNota2());
                if ((d.getNota1() != null && d.getNota2() != null)) {
                    disciplinasAlunoDto.setMedia(d.getNota1() + d.getNota2() / 2);
                } else {
                    disciplinasAlunoDto.setMedia(null);
                }

                disciplinasAlunoDto.setStatus(d.getStatus());

                disciplinasAlunoDtoList.add(disciplinasAlunoDto);

            });

            historicoAlunoDto.setDisciplinasAlunoDtos(disciplinasAlunoDtoList);

            return historicoAlunoDto;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não possui matrículas.");
    }

    public void updateNotas(MatriculaAlunoNotasOnlyDto notasOnlyDto, Long matriculaAlunoId, Long disciplinaId) {
        Optional<RegistroDisciplina> registroDisciplina = Optional.ofNullable(repository.
                findByMatriculaIdAndDisciplinaId(matriculaAlunoId, disciplinaId));

        boolean needUpdate = false;

        if (StringUtils.hasLength(notasOnlyDto.getNota1().toString())) {
            registroDisciplina.ifPresent(matriculaAluno -> matriculaAluno.setNota1(notasOnlyDto.getNota1()));
            needUpdate = true;
        }

        if (StringUtils.hasLength(notasOnlyDto.getNota1().toString())) {
            registroDisciplina.ifPresent(matriculaAluno -> matriculaAluno.setNota2(notasOnlyDto.getNota2()));
            needUpdate = true;
        }

        if (needUpdate) {
            if (registroDisciplina.get().getNota1() != null && registroDisciplina.get().getNota2() != null) {
                if (registroDisciplina.get().getNota1() + registroDisciplina.get().getNota2() / 2 >= gradesAvgToApprove) {
                    registroDisciplina.ifPresent(r -> r.setStatus("APROVADO"));
                } else {
                    registroDisciplina.ifPresent(matriculaAluno -> matriculaAluno.setStatus("REPROVADO"));
                }

            }
            repository.save(registroDisciplina.get());
        }

    }
}
