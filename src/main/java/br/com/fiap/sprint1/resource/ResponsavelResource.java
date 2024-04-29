package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.entity.Resposavel;
import br.com.fiap.sprint1.repository.ResponsavelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/respcond")
public class ResponsavelResource {

    @Autowired
    private ResponsavelRepository repo;

    @GetMapping
    public List<Resposavel> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Resposavel findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Resposavel save(@RequestBody Resposavel resposavel) {
        return repo.save(resposavel);
    }
}
