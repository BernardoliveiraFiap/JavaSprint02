package br.com.fiap.sprint1.service;


import br.com.fiap.sprint1.dto.request.AdministradorRequest;
import br.com.fiap.sprint1.dto.response.AdministradorResponse;
import br.com.fiap.sprint1.entity.Administrador;
import br.com.fiap.sprint1.repository.AdministradorRepository;
import br.com.fiap.sprint1.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdministradorService implements ServiceDTO<Administrador, AdministradorRequest, AdministradorResponse> {

    @Autowired
    private AdministradorRepository repo;

    @Autowired
    private LoginService loginService;

    @Override
    public Collection<Administrador> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Administrador> findAll(Example<Administrador> example) {
        return repo.findAll( example );
    }

    @Override
    public Administrador findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Administrador save(Administrador e) {
        return repo.save( e );
    }

    @Override
    public Administrador toEntity(AdministradorRequest dto) {
        var log = loginService.findById(dto.login().id());
        return Administrador.builder()
                .nome(dto.nome())
                .username(dto.username())
                .password(dto.password())
                .login(log)
                .build();
    }

    @Override
    public AdministradorResponse toResponse(Administrador e) {
        var logi = loginService.toResponse(e.getLogin());
        return AdministradorResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .username(e.getUsername())
                .password(e.getPassword())
                .login(logi)
                .build();
    }
}
