package br.com.fiap.sprint1.resource;

import br.com.fiap.sprint1.entity.Login;
import br.com.fiap.sprint1.repository.LoginRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/login")
public class LoginResource {

    @Autowired
    private LoginRepository repo;

    @GetMapping
    public List<Login> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Login findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }


    @Transactional
    @PostMapping
    public Login save(@RequestBody Login login) {
        return repo.save(login);
    }

}
