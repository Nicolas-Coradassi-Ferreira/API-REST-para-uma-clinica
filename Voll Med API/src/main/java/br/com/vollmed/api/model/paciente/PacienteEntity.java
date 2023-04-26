package br.com.vollmed.api.model.paciente;


import br.com.vollmed.api.model.Endereco;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "PacienteEntity")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PacienteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    public PacienteEntity(DadosCadastroPaciente dados){
        this.id = null;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarDadosCadastrais(DadosAtualizacaoPaciente dados){
        if (dados.nome() != null && !dados.nome().isBlank()){
            this.nome = dados.nome();
        }

        if (dados.telefone() != null && !dados.telefone().isBlank()){
            this.telefone = dados.telefone();
        }

        this.endereco.atualizarDados(dados.endereco());
    }
}


