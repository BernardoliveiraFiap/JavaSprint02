package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.entity.CadUsuario;
import br.com.fiap.sprint1.repository.CadUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cadusuario")
public class CadUsuarioResource {

    @Autowired
    private CadUsuarioRepository repo;

    @GetMapping
    public List<CadUsuario> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public CadUsuario findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }


    @Transactional
    @PostMapping
    public CadUsuario save(@RequestBody CadUsuario cadUsuario) {
        return repo.save(cadUsuario);
    }

}
