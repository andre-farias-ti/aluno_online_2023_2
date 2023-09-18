package com.br.alunoonline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
/*
O que é uma classe DTO?
-> Data Transfer Object: Padrão de Projeto utilizado para transportar dados de um local para outro.
-> Ele é apenas um OBJETO de dados, portanto não costuma ter comportamento próprio (se opondo aos models)
-> Neste exemplo nós queríamos mexer/ter acesso apenas com os atributos nota1 e nota2 do model MatriculaAluno
-> Resumindo: A ideia é agrupar um conjunto de atributos numa classe simples de forma a otimizar a comunicação.
-> É uma classe que provê apenas os atributos necessários em um determinado processo.
 */
public class MatriculaAlunoNotasOnlyDto {

    private Double nota1;
    private Double nota2;
}
