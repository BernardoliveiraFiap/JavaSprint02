package br.com.fiap.sprint1.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record EstoqueRequest(

        @NotNull(message = "Estoque precisa de nome")
        String nome,

        @NotNull(message = "Estoque precisa de endereço ")
        String endereco,

        @NotNull(message = "Estoque precisa de CGD")
        String chegada,

        @NotNull(message = "Estoque precisa de status")
        String status,

        @Valid
        @NotNull(message = "Estoque precisa de administarador")
        AbstractRequest administrador
) {
}
