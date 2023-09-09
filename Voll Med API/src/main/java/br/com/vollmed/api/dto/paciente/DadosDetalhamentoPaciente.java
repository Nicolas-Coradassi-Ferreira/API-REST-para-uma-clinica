package br.com.vollmed.api.dto.paciente;

import br.com.vollmed.api.dto.DadosEndereco;
import br.com.vollmed.api.model.paciente.Paciente;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        DadosEndereco endereco) {

    public DadosDetalhamentoPaciente(Paciente p) {
        this(p.getId(), p.getNome(), p.getEmail(), p.getTelefone(), p.getCpf(), new DadosEndereco(p.getEndereco()));
    }
}
