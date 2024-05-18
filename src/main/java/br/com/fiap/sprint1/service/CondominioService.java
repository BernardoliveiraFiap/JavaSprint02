package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.request.CondominioRequest;
import br.com.fiap.sprint1.dto.response.CondominioResponse;
import br.com.fiap.sprint1.entity.Condominio;
import br.com.fiap.sprint1.repository.CondominioRepository;
import br.com.fiap.sprint1.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CondominioService implements ServiceDTO<Condominio, CondominioRequest, CondominioResponse>{

    @Autowired
    CondominioRepository repo;

    @Autowired
    ResponsavelService responsavelService;
    @Override
    public Collection<Condominio> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Condominio> findAll(Example<Condominio> example) {
        return repo.findAll(example);
    }

    @Override
    public Condominio findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Condominio save(Condominio e) {
        return repo.save(e);
    }

    @Override
    public Condominio toEntity(CondominioRequest dto) {
        var respo = responsavelService.findById(dto.responsavel().id());
        return Condominio.builder()
                .nome(dto.nome())
                .cpnj(dto.cpnj())
                .endereco(dto.endereco())
                .telefone(dto.telefone())
                .responsavel(respo)
                .build();
    }

    @Override
    public CondominioResponse toResponse(Condominio e) {
        var respo = responsavelService.toResponse(e.getResponsavel());
        return CondominioResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .cpnj(e.getCpnj())
                .endereco(e.getEndereco())
                .telefone(e.getTelefone())
                .responsavel(respo)
                .build();
    }
}
