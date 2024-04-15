package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.entity.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda,Long> {
}
