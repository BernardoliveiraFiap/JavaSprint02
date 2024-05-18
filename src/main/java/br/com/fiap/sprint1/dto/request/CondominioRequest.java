package br.com.fiap.sprint1.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CondominioRequest(

        @NotNull(message = "Condominio precisa de cnpj")
        String cpnj,

        @NotNull(message = "Condominio precisa de nome")
        String nome,

        @NotNull(message = "Condominio precisa de endereco")
        String endereco,

        @NotNull(message = "Condominio precisa de telefone")
        String telefone,

        @Valid
        @NotNull(message = "Condominio precisa de responsavel")
        AbstractRequest responsavel
) {
}
