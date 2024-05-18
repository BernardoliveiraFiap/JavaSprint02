package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.dto.request.LoginRequest;
import br.com.fiap.sprint1.dto.response.EncomendaResponse;
import br.com.fiap.sprint1.dto.response.LoginResponse;
import br.com.fiap.sprint1.entity.Encomenda;
import br.com.fiap.sprint1.entity.Login;
import br.com.fiap.sprint1.entity.Morador;
import br.com.fiap.sprint1.repository.LoginRepository;
import br.com.fiap.sprint1.repository.MoradorRepository;
import br.com.fiap.sprint1.service.EncomendaService;
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
@RequestMapping(value = "/login")
public class LoginResource implements ResourceDTO<LoginRequest, LoginResponse>{


    @Autowired
    private LoginService service;


    @GetMapping
    public ResponseEntity<Collection<LoginResponse>> findAll(
            @RequestParam(name = "username", required = false) final String username,
            @RequestParam(name = "password", required = false) final String password,
            @RequestParam(name = "morador.nome", required = false) final String moradorNome
    ){
        var morador = Morador.builder()
                .nome( moradorNome)
                .build();
        var log = Login.builder()
                .username(username)
                .password(password)
                .morador(morador)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Login> example = Example.of(log, matcher);

        var entity = service.findAll(example);

        var response = entity.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<LoginResponse> findById(@PathVariable final Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<LoginResponse> save(@RequestBody @Valid LoginRequest dto) {
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
