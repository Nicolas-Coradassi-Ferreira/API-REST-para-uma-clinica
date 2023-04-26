package br.com.vollmed.api.model.medico;

import br.com.vollmed.api.model.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
