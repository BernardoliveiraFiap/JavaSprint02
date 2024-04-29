package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.entity.Resposavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Resposavel, Long> {
}
