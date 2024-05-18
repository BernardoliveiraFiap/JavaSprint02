package br.com.fiap.sprint1.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(

        @NotNull(message = "Login precisa de username")
        String username,

        @NotNull(message = "Login precisa de password")
        String password,

        @Valid
        @NotNull(message = "Login necesssita de um morador")
        AbstractRequest morador

) {
}
