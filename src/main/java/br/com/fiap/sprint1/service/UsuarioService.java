package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.request.UsuarioRequest;
import br.com.fiap.sprint1.dto.response.UsuarioResponse;
import br.com.fiap.sprint1.entity.Usuario;
import br.com.fiap.sprint1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse>{

    @Autowired
    UsuarioRepository repo;

    @Autowired
    ResponsavelService responsavelService;

    @Override
    public Collection<Usuario> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Usuario> findAll(Example<Usuario> example) {
        return repo.findAll(example);
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById( id ).orElse(null);
    }

    @Override
    public Usuario save(Usuario e) {
        return repo.save(e);
    }

    @Override
    public Usuario toEntity(UsuarioRequest dto) {
        var respo = responsavelService.findById(dto.responsavel().id());
        return Usuario.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .endereco(dto.endereco())
                .telefone(dto.telefone())
                .username(dto.username())
                .password(dto.password())
                .responsavel(respo)
                .build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario e) {
        var respo = responsavelService.toResponse(e.getResponsavel());
        return UsuarioResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .cpf(e.getCpf())
                .endereco(e.getEndereco())
                .telefone(e.getTelefone())
                .username(e.getUsername())
                .password(e.getPassword())
                .responsavel(respo)
                .build();
    }
}
