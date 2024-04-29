package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.entity.Estoque;
import br.com.fiap.sprint1.repository.EstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/cadestoque")
public class EstoqueResource {

    @Autowired
    private EstoqueRepository repo;

    @GetMapping
    public List<Estoque> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Estoque findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }


    @Transactional
    @PostMapping
    public Estoque save(@RequestBody Estoque estoque) {
        return repo.save(estoque);
    }


}
