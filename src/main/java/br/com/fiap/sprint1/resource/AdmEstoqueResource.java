package br.com.fiap.sprint1.resource;


import br.com.fiap.sprint1.entity.AdmEstoque;
import br.com.fiap.sprint1.repository.AdmEstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/AdmEstoque")
public class AdmEstoqueResource {

    @Autowired
    private AdmEstoqueRepository repo;

    @GetMapping
    public List<AdmEstoque> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public AdmEstoque findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }


    @Transactional
    @PostMapping
    public AdmEstoque save(@RequestBody AdmEstoque admEstoque) {
        return repo.save(admEstoque);
    }


}
