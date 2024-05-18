package br.com.fiap.sprint1.dto.response;

import lombok.Builder;

@Builder
public record CondominioResponse(
        Long id,
        String cpnj,
        String nome,
        String endereco,
        String telefone,

        ResponsavelResponse responsavel
) {
}
