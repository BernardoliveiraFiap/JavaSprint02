package br.com.fiap.sprint1.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        Long id,
        String username,
        String password,
        MoradorResponse morador
) {
}
