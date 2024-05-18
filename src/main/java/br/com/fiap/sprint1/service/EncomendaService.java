package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.request.EncomendaRequest;
import br.com.fiap.sprint1.dto.response.EncomendaResponse;
import br.com.fiap.sprint1.entity.Encomenda;
import br.com.fiap.sprint1.repository.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EncomendaService implements ServiceDTO<Encomenda, EncomendaRequest, EncomendaResponse>{

    @Autowired
    private EncomendaRepository repo;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private MoradorService moradorService;

    @Override
    public Collection<Encomenda> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Encomenda> findAll(Example<Encomenda> example) {
        return repo.findAll( example );
    }

    @Override
    public Encomenda findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Encomenda save(Encomenda e) {
        return repo.save( e );
    }

    @Override
    public Encomenda toEntity(EncomendaRequest dto) {
        var pessoa = moradorService.findById(dto.pessoa().id());
        var admin = administradorService.findById(dto.administrador().id());
        return Encomenda.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .destinatario(dto.destinatario())
                .administrador(admin)
                .pessoa(pessoa)
                .build();
    }

    @Override
    public EncomendaResponse toResponse(Encomenda e) {
        var pessoa = moradorService.toResponse( e.getPessoa() );
        var admin = administradorService.toResponse(e.getAdministrador());
        return EncomendaResponse.builder()
                .id(e.getId())
                .cpf(e.getCpf())
                .destinatario(e.getDestinatario())
                .nome(e.getNome())
                .pessoa(pessoa)
                .administrador(admin)
                .build();
    }
}
