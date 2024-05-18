package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.dto.request.EncomendaRequest;
import br.com.fiap.sprint1.dto.response.EncomendaResponse;
import br.com.fiap.sprint1.dto.response.EstoqueResponse;
import br.com.fiap.sprint1.entity.Administrador;
import br.com.fiap.sprint1.entity.Encomenda;
import br.com.fiap.sprint1.entity.Estoque;
import br.com.fiap.sprint1.entity.Morador;
import br.com.fiap.sprint1.repository.AdministradorRepository;
import br.com.fiap.sprint1.repository.EncomendaRepository;
import br.com.fiap.sprint1.repository.MoradorRepository;
import br.com.fiap.sprint1.service.AdministradorService;
import br.com.fiap.sprint1.service.EncomendaService;
import br.com.fiap.sprint1.service.EstoqueService;
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
@RequestMapping(value = "/encomendas")
public class EncomendaResource implements ResourceDTO<EncomendaRequest,EncomendaResponse> {

    @Autowired
    private EncomendaService service;


    @GetMapping
    public ResponseEntity<Collection<EncomendaResponse>> findAll(
            @RequestParam(name = "nome", required = false) final String nome,
            @RequestParam(name = "cpf", required = false) final String cpf,
            @RequestParam(name = "destinatario", required = false) final String destinatario,
            @RequestParam(name = "pessoa.nome", required = false) final String pessoaNome,
            @RequestParam(name = "administrador.nome", required = false) final String administradorNome
    ){
        var pessoa = Morador.builder()
                .nome( pessoaNome)
                .build();

        var administrador = Administrador.builder()
                .nome(administradorNome)
                .build();

        var enco = Encomenda.builder()
                .nome(nome)
                .cpf(cpf)
                .destinatario(destinatario)
                .pessoa(pessoa)
                .administrador(administrador)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Encomenda> example = Example.of(enco, matcher);

        var entity = service.findAll(example);

        var response = entity.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<EncomendaResponse> findById(@PathVariable final Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<EncomendaResponse> save(@RequestBody @Valid EncomendaRequest dto) {
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
