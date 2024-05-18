package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.request.LoginRequest;
import br.com.fiap.sprint1.dto.response.LoginResponse;
import br.com.fiap.sprint1.entity.Login;
import br.com.fiap.sprint1.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LoginService implements ServiceDTO<Login, LoginRequest, LoginResponse> {

    @Autowired
    private LoginRepository repo;

    @Autowired
    private MoradorService moradorService;

    @Override
    public Collection<Login> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Login> findAll(Example<Login> example) {
        return repo.findAll(example);
    }

    @Override
    public Login findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Login save(Login e) {
        return repo.save(e);
    }

    @Override
    public Login toEntity(LoginRequest dto) {
        var morador = moradorService.findById(dto.morador().id());
        return Login.builder()
                .username(dto.username())
                .password(dto.password())
                .morador(morador)
                .build();
    }

    @Override
    public LoginResponse toResponse(Login e) {
        var morador = moradorService.toResponse(e.getMorador());
        return LoginResponse.builder()
                .id(e.getId())
                .username(e.getUsername())
                .password(e.getPassword())
                .morador(morador)
                .build();
    }
}
