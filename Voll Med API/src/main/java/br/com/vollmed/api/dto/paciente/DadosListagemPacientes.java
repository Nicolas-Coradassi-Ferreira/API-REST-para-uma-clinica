package br.com.vollmed.api.dto.paciente;

import br.com.vollmed.api.model.paciente.Paciente;

public record DadosListagemPacientes(
        Long id,
        String nome,
        String email,
        String cpf) {

    public DadosListagemPacientes(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
