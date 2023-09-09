package br.com.vollmed.api.dto;

import br.com.vollmed.api.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        String numero,
        String complemento,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        @Pattern(regexp = "^[A-Z]{2}$")
        String uf,
        @NotBlank
        @Pattern(regexp = "^\\d{8}$")
        String cep) {

        public DadosEndereco(Endereco e) {
                this(e.getLogradouro(), e.getNumero(), e.getComplemento(), e.getBairro(), e.getCidade(), e.getUf(), e.getCep());
        }
}
