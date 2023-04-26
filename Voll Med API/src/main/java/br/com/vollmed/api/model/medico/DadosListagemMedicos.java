package br.com.vollmed.api.model.medico;


public record DadosListagemMedicos(Long id,
                                   String nome,
                                   String email,
                                   String crm,
                                   Especialidade especialidade) {

    public DadosListagemMedicos(MedicoEntity medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
