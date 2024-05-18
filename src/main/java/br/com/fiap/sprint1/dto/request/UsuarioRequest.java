package br.com.fiap.sprint1.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(

        @NotNull(message = "Usuario precisa de username")
        String username,

        @NotNull(message = "Usuario precisa de password")
        String password,

        @NotNull(message = "Usuario precisa de nome")
        String nome,

        @NotNull(message = "Usuario precisa de cpf")
        String cpf,

        @NotNull(message = "Usuario precisa de endereco")
        String endereco,

        @NotNull(message = "Usuario precisa de telefone")
        String telefone,

        @Valid
        @NotNull(message = "Usuario precisa de responsavel")
        AbstractRequest responsavel

) {
}
