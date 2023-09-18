package com.br.alunoonline.service;

import com.br.alunoonline.entity.Email;
import com.br.alunoonline.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;

    public Email salvar(Email email) {
        return repository.save(email);
    }

    public Optional<Email> buscarPorId(Long id){
        return repository.findById(id);
    }
}
