package br.com.vollmed.api.dto.paciente;

import br.com.vollmed.api.dto.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
