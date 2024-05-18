package br.com.fiap.sprint1.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record MoradorRequest(

        @NotNull(message = "Morador precisa de nome")
        String nome,

        @NotNull(message = "Morador precisa de cpf")
        String cpf,

        @NotNull(message = "Morador precisa de telefone")
        String telefone,

        @NotNull(message = "Morador precisa de endereco")
        String endereco,

        @Valid
        @NotNull(message = "Morador precisa de responsável")
        AbstractRequest responsavel
) {
}
