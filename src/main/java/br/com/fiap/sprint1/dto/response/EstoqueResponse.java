package br.com.fiap.sprint1.dto.response;

import lombok.Builder;

@Builder
public record EstoqueResponse(
        Long id,
        String nome,
        String endereco,
        String chegada,
        String status,

        AdministradorResponse administrador
) {
}
