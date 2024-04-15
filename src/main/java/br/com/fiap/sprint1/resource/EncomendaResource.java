package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.entity.Encomenda;
import br.com.fiap.sprint1.repository.EncomendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/encomenda")
public class EncomendaResource {

    @Autowired
    private EncomendaRepository repo;

    @GetMapping
    public List<Encomenda> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Encomenda findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }


    @Transactional
    @PostMapping
    public Encomenda save(@RequestBody Encomenda encomenda) {
        return repo.save(encomenda);
    }


}
