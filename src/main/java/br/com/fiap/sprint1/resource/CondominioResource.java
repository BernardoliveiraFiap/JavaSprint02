package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.dto.request.CondominioRequest;
import br.com.fiap.sprint1.dto.response.CondominioResponse;
import br.com.fiap.sprint1.dto.response.UsuarioResponse;
import br.com.fiap.sprint1.entity.Condominio;
import br.com.fiap.sprint1.entity.Responsavel;
import br.com.fiap.sprint1.entity.Usuario;
import br.com.fiap.sprint1.repository.CondominioRepository;
import br.com.fiap.sprint1.repository.ResponsavelRepository;
import br.com.fiap.sprint1.service.CondominioService;
import br.com.fiap.sprint1.service.ResponsavelService;
import br.com.fiap.sprint1.service.UsuarioService;
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
@RequestMapping(value = "/condominios")
public class CondominioResource implements ResourceDTO<CondominioRequest, CondominioResponse>{


    @Autowired
    private CondominioService service;


    @GetMapping
    public ResponseEntity<Collection<CondominioResponse>> findAll(
            @RequestParam(name = "cnpj", required = false) final String cnpj,
            @RequestParam(name = "endereco", required = false) final String endereco,
            @RequestParam(name = "nome", required = false) final String nome,
            @RequestParam(name = "telefone", required = false) final String telefone,
            @RequestParam(name = "responsavel.nome", required = false) final String responsavelNome
    ) {
        var responsavel = Responsavel.builder()
                .nome( responsavelNome)
                .build();

                var condominio = Condominio.builder()
                        .cpnj(cnpj)
                        .endereco(endereco)
                        .nome(nome)
                        .telefone(telefone)
                        .responsavel( responsavel)
                        .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Condominio> example = Example.of(condominio, matcher);

        var entity = service.findAll(example);

        var response = entity.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<CondominioResponse> findById(@PathVariable final Long id) {
        var entity = service.findById(id);
        if(Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<CondominioResponse> save(@RequestBody @Valid CondominioRequest dto) {
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
