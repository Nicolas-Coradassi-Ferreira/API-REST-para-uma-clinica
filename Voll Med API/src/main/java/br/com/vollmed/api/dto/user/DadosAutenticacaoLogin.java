package br.com.vollmed.api.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAutenticacaoLogin(
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @NotBlank
        String password) {
}
