package br.com.fiap.sprint1.repository;

import br.com.fiap.sprint1.entity.CadEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadEstoqueRepository extends JpaRepository<CadEstoque, Long> {
}
