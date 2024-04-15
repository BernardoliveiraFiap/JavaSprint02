package br.com.fiap.sprint1.resource;


import br.com.fiap.sprint1.entity.Morador;
import br.com.fiap.sprint1.repository.MoradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/morador")
public class MoradorResource {

    @Autowired
    private MoradorRepository repo;

    @GetMapping
    public List<Morador> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Morador findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Morador save(@RequestBody Morador morador) {
        return repo.save(morador);
    }


}
