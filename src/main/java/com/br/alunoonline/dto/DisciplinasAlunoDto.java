package com.br.alunoonline.dto;

import com.br.alunoonline.entity.Disciplina;
import com.br.alunoonline.entity.RegistroDisciplina;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class DisciplinasAlunoDto {

    private String nome;
    private Double nota1;
    private Double nota2;
    private Double media;
    private String status;
}
