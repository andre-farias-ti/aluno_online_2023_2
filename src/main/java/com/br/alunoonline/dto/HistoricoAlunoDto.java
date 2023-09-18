package com.br.alunoonline.dto;

import com.br.alunoonline.entity.Curso;
import com.br.alunoonline.entity.RegistroDisciplina;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class HistoricoAlunoDto {

    private String nomeAluno;
    private Curso curso;
    private List<DisciplinasAlunoDto> disciplinasAlunoDtos;
}
