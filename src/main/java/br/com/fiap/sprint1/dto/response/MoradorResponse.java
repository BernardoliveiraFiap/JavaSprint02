package br.com.fiap.sprint1.dto.response;

import lombok.Builder;

@Builder
public record MoradorResponse(

        Long id,
        String nome,
        String cpf,
        String telefone,
        String endereco,

        ResponsavelResponse responsavel
) {
}
