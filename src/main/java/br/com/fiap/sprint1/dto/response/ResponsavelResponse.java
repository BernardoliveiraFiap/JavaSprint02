package br.com.fiap.sprint1.dto.response;

import lombok.Builder;

@Builder
public record ResponsavelResponse(

        Long id,
        String nome,
        String cpf,
        String telefone
) {
}
