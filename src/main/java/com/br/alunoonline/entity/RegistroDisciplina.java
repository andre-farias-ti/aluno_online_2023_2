package com.br.alunoonline.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegistroDisciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="matricula_id")
    private MatriculaAluno matricula;

    @ManyToOne
    @JoinColumn(name="disciplina_id")
    private Disciplina disciplina;

    private Double nota1;

    private Double nota2;

    private String status;

}
