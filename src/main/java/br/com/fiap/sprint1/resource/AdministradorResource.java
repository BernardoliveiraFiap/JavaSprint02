package br.com.fiap.sprint1.resource;


import br.com.fiap.sprint1.dto.request.AdministradorRequest;
import br.com.fiap.sprint1.dto.response.AdministradorResponse;
import br.com.fiap.sprint1.entity.Administrador;
import br.com.fiap.sprint1.entity.Login;
import br.com.fiap.sprint1.repository.AdministradorRepository;
import br.com.fiap.sprint1.repository.LoginRepository;
import br.com.fiap.sprint1.service.AdministradorService;
import br.com.fiap.sprint1.service.LoginService;
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
@RequestMapping(value = "/administradores")
public class AdministradorResource implements ResourceDTO<AdministradorRequest, AdministradorResponse>{

    @Autowired
    AdministradorService service;

    @GetMapping
    public ResponseEntity<Collection<AdministradorResponse>> findAll(
            @RequestParam(name = "nome", required = false) final String nome,
            @RequestParam(name = "username", required = false) final String username,
            @RequestParam(name = "password", required = false) final String password,
            @RequestParam(name = "login.username", required = false) final String loginUsername


    ){
        var login = Login.builder()
                .username( loginUsername )
                .build();

        var admin = Administrador.builder()
                .nome(nome)
                .username(username)
                .password(password)
                .login( login )
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Administrador> example = Example.of(admin, matcher);

        var entity = service.findAll(example);

        var response = entity.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<AdministradorResponse> findById(@PathVariable final Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<AdministradorResponse> save(@RequestBody @Valid AdministradorRequest dto) {
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
