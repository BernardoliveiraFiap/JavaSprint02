package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.request.ResponsavelRequest;
import br.com.fiap.sprint1.dto.response.ResponsavelResponse;
import br.com.fiap.sprint1.entity.Responsavel;
import br.com.fiap.sprint1.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ResponsavelService implements ServiceDTO<Responsavel, ResponsavelRequest, ResponsavelResponse> {

    @Autowired
    ResponsavelRepository repo;

    @Override
    public Collection<Responsavel> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Responsavel> findAll(Example<Responsavel> example) {
        return repo.findAll(example);
    }

    @Override
    public Responsavel findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Responsavel save(Responsavel e) {
        return repo.save(e);
    }

    @Override
    public Responsavel toEntity(ResponsavelRequest dto) {
        return Responsavel.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .telefone(dto.telefone())
                .build();
    }

    @Override
    public ResponsavelResponse toResponse(Responsavel e) {
        return ResponsavelResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .cpf(e.getCpf())
                .telefone(e.getTelefone())
                .build();
    }
}
