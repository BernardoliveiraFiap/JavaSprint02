package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.entity.CadEstoque;
import br.com.fiap.sprint1.repository.CadEstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/cadestoque")
public class CadEstoqueResource {

    @Autowired
    private CadEstoqueRepository repo;

    @GetMapping
    public List<CadEstoque> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public CadEstoque findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }


    @Transactional
    @PostMapping
    public CadEstoque save(@RequestBody CadEstoque cadEstoque) {
        return repo.save(cadEstoque);
    }


}
