package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.entity.Morador;
import br.com.fiap.sprint1.entity.RespCond;
import br.com.fiap.sprint1.repository.MoradorRepository;
import br.com.fiap.sprint1.repository.RespCondRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/respcond")
public class RespCondResource {

    @Autowired
    private RespCondRepository repo;

    @GetMapping
    public List<RespCond> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public RespCond findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public RespCond save(@RequestBody RespCond respCond) {
        return repo.save(respCond);
    }
}
