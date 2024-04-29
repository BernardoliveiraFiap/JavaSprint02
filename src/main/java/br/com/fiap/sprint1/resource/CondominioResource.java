package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.entity.Condominio;
import br.com.fiap.sprint1.repository.CondominioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cadcond")
public class CondominioResource {

    @Autowired
    private CondominioRepository repo;

    @GetMapping
    public List<Condominio> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Condominio findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }


    @Transactional
    @PostMapping
    public Condominio save(@RequestBody Condominio condominio) {
        return repo.save(condominio);
    }



}
