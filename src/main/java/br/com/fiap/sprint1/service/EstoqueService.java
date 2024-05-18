package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.request.EstoqueRequest;
import br.com.fiap.sprint1.dto.response.EstoqueResponse;
import br.com.fiap.sprint1.entity.Estoque;
import br.com.fiap.sprint1.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EstoqueService implements ServiceDTO<Estoque, EstoqueRequest, EstoqueResponse>{

    @Autowired
    private EstoqueRepository repo;

    @Autowired
    private AdministradorService administradorService;

    @Override
    public Collection<Estoque> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Estoque> findAll(Example<Estoque> example) {
        return repo.findAll( example );
    }

    @Override
    public Estoque findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Estoque save(Estoque e) {
        return repo.save( e );
    }

    @Override
    public Estoque toEntity(EstoqueRequest dto) {
        var admini = administradorService.findById(dto.administrador().id());
        return Estoque.builder()
                .nome(dto.nome())
                .endereco(dto.endereco())
                .chegada(dto.chegada())
                .status(dto.chegada())
                .administrador(admini)
                .build();

    }

    @Override
    public EstoqueResponse toResponse(Estoque e) {
        var adm = administradorService.toResponse(e.getAdministrador());
                return EstoqueResponse.builder()
                        .id(e.getId())
                        .nome(e.getNome())
                        .endereco(e.getEndereco())
                        .chegada(e.getChegada())
                        .status(e.getStatus())
                        .administrador(adm)
                        .build();
    }
}
