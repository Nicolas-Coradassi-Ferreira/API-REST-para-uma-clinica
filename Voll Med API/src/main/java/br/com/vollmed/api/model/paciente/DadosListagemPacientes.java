package br.com.vollmed.api.model.paciente;

import br.com.vollmed.api.model.paciente.PacienteEntity;

public record DadosListagemPacientes(
        Long id,
        String nome,
        String email,
        String cpf) {

    public DadosListagemPacientes(PacienteEntity paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
