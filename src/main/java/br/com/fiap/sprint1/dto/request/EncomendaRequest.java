package br.com.fiap.sprint1.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record EncomendaRequest(

        @NotNull(message = "A encomenda precisa de nome")
        String nome,

        @NotNull(message = "A encomenda precisa de cpf")
        String cpf,

        @NotNull(message = "A encomenda precisa do destinatario")
        String destinatario,

        @Valid
        @NotNull(message = "A encomenda precisa de um administrador")
        AbstractRequest administrador,

        @Valid
        @NotNull(message = "A encomenda precisa de um morador")
        AbstractRequest pessoa

) {
}
