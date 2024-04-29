package br.com.fiap.sprint1.resource;


import br.com.fiap.sprint1.entity.Administrador;
import br.com.fiap.sprint1.repository.AdministradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/AdmEstoque")
public class AdministradorResource {

    @Autowired
    private AdministradorRepository repo;

    @GetMapping
    public List<Administrador> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Administrador findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }


    @Transactional
    @PostMapping
    public Administrador save(@RequestBody Administrador administrador) {
        return repo.save(administrador);
    }


}
