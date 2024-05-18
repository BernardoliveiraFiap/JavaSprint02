package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.dto.request.ResponsavelRequest;
import br.com.fiap.sprint1.dto.response.MoradorResponse;
import br.com.fiap.sprint1.dto.response.ResponsavelResponse;
import br.com.fiap.sprint1.entity.Morador;
import br.com.fiap.sprint1.entity.Responsavel;
import br.com.fiap.sprint1.repository.ResponsavelRepository;
import br.com.fiap.sprint1.service.MoradorService;
import br.com.fiap.sprint1.service.ResponsavelService;
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
@RequestMapping(value = "/responsavel")
public class ResponsavelResource implements ResourceDTO<ResponsavelRequest, ResponsavelResponse>{


    @Autowired
    private ResponsavelService service;

    @GetMapping
    public ResponseEntity<Collection<ResponsavelResponse>> findAll(
            @RequestParam(name = "cpf", required = false) final String cpf,
            @RequestParam(name = "nome", required = false) final String nome,
            @RequestParam(name = "telefone", required = false) final String telefone
    ) {
        var responsavel = Responsavel.builder()
                .cpf(cpf)
                .nome(nome)
                .telefone(telefone)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Responsavel> example = Example.of(responsavel, matcher);

        var entity = service.findAll(example);

        var response = entity.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ResponsavelResponse> findById(@PathVariable final Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<ResponsavelResponse> save(@RequestBody @Valid ResponsavelRequest dto) {
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
