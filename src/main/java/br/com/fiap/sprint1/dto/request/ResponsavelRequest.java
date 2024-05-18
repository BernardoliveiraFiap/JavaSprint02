package br.com.fiap.sprint1.dto.request;

import jakarta.validation.constraints.NotNull;

public record ResponsavelRequest(

        @NotNull(message = "Responsável precisa de nome")
        String nome,

        @NotNull(message = "Responsável precisa de cpf")
        String cpf,

        @NotNull(message = "Responsavél precisa de telefone")
        String telefone

) {
}
