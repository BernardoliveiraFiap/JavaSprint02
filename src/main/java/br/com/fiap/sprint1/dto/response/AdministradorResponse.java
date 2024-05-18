package br.com.fiap.sprint1.dto.response;

import lombok.Builder;

@Builder
public record AdministradorResponse(

        Long id,
        String nome,
        String username,
        String password,
        LoginResponse login
) {
}
