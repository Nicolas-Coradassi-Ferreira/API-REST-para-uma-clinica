package br.com.vollmed.api.dto.medico;


import br.com.vollmed.api.model.medico.Especialidade;
import br.com.vollmed.api.model.medico.Medico;

public record DadosListagemMedicos(Long id,
                                   String nome,
                                   String email,
                                   String crm,
                                   Especialidade especialidade) {

    public DadosListagemMedicos(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
