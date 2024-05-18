package br.com.fiap.sprint1.dto.response;

import lombok.Builder;

@Builder
public record EncomendaResponse(

        Long id,
        String nome,
        String cpf,
        String destinatario,

        AdministradorResponse administrador,

        MoradorResponse pessoa
) {
}
