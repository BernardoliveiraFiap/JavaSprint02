package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.dto.request.EstoqueRequest;
import br.com.fiap.sprint1.dto.response.AdministradorResponse;
import br.com.fiap.sprint1.dto.response.EstoqueResponse;
import br.com.fiap.sprint1.entity.Administrador;
import br.com.fiap.sprint1.entity.Estoque;
import br.com.fiap.sprint1.repository.AdministradorRepository;
import br.com.fiap.sprint1.repository.EstoqueRepository;
import br.com.fiap.sprint1.repository.LoginRepository;
import br.com.fiap.sprint1.repository.MoradorRepository;
import br.com.fiap.sprint1.service.AdministradorService;
import br.com.fiap.sprint1.service.EstoqueService;
import br.com.fiap.sprint1.service.LoginService;
import br.com.fiap.sprint1.service.MoradorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(value = "/estoques")
public class EstoqueResource implements ResourceDTO<EstoqueRequest, EstoqueResponse> {

    @Autowired
    private EstoqueService service;


    @GetMapping
    public ResponseEntity<Collection<EstoqueResponse>> findAll(
            @RequestParam(name = "nome", required = false) final String nome,
            @RequestParam(name = "status", required = false) final String status,
            @RequestParam(name = "chegada", required = false) final String chegada,
            @RequestParam(name = "endereco", required = false) final String endereco,
            @RequestParam(name = "administrador.nome", required = false) final String administradorNome
    ){
        var administrador = Administrador.builder()
                .nome(administradorNome)
                .build();
        var est = Estoque.builder()
                .nome(nome)
                .status(status)
                .chegada(chegada)
                .endereco(endereco)
                .administrador(administrador)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Estoque> example = Example.of(est, matcher);

        var entity = service.findAll(example);

        var response = entity.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<EstoqueResponse> findById(@PathVariable final Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<EstoqueResponse> save(@RequestBody @Valid EstoqueRequest dto) {
        var entity = service.toEntity(dto);
        var saved = service.save(entity);
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        var response = service.toResponse(saved);

        return ResponseEntity.created(uri).body(response);
    }
}
