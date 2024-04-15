package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.entity.AdmEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmEstoqueRepository extends JpaRepository<AdmEstoque, Long> {
}
