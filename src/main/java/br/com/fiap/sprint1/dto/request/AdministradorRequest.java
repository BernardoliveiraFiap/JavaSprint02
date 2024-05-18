package br.com.fiap.sprint1.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AdministradorRequest(

        @NotNull(message = "Administrador precisa de nome")
        String nome,

        @NotNull(message = "Administrador precisa de username")
        String username,

        @NotNull(message = "Administrador precisa de password")
        String password,

        @Valid
        @NotNull(message = "Administrador precisa de login")
        AbstractRequest login

) {
}
