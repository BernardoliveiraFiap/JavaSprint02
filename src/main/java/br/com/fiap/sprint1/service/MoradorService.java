package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.request.MoradorRequest;
import br.com.fiap.sprint1.dto.response.MoradorResponse;

import br.com.fiap.sprint1.entity.Morador;
import br.com.fiap.sprint1.repository.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MoradorService implements ServiceDTO<Morador, MoradorRequest, MoradorResponse>{

    @Autowired
    private MoradorRepository repo;

    @Autowired
    private ResponsavelService responsavelService;

    @Override
    public Collection<Morador> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Morador> findAll(Example<Morador> example) {
        return repo.findAll(example);
    }

    @Override
    public Morador findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Morador save(Morador e) {
        return repo.save(e);
    }

    @Override
    public Morador toEntity(MoradorRequest dto) {
        var responsavel = responsavelService.findById(dto.responsavel().id());
        return Morador.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .endereco(dto.endereco())
                .telefone(dto.telefone())
                .responsavel(responsavel)
                .build();
    }

    @Override
    public MoradorResponse toResponse(Morador e) {
        var responsavel = responsavelService.toResponse(e.getResponsavel());
        return MoradorResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .cpf(e.getCpf())
                .endereco(e.getEndereco())
                .telefone(e.getTelefone())
                .responsavel(responsavel)
                .build();
    }
}
