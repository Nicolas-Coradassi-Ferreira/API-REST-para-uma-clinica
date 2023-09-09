package br.com.vollmed.api.dto.medico;

import br.com.vollmed.api.dto.DadosEndereco;
import br.com.vollmed.api.model.medico.Especialidade;
import br.com.vollmed.api.model.medico.Medico;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        DadosEndereco endereco
) {

    public DadosDetalhamentoMedico(Medico m) {
        this(m.getId(), m.getNome(), m.getEmail(), m.getCrm(), m.getEspecialidade(), new DadosEndereco(m.getEndereco()));
    }
}
