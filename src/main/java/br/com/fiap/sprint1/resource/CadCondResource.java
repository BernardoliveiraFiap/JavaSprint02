package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.entity.CadCond;
import br.com.fiap.sprint1.repository.CadCondRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cadcond")
public class CadCondResource {

    @Autowired
    private CadCondRepository repo;

    @GetMapping
    public List<CadCond> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public CadCond findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }


    @Transactional
    @PostMapping
    public CadCond save(@RequestBody CadCond cadCond) {
        return repo.save(cadCond);
    }



}
