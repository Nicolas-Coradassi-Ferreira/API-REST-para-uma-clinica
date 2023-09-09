package br.com.vollmed.api.controllers;


import br.com.vollmed.api.dto.paciente.DadosAtualizacaoPaciente;
import br.com.vollmed.api.dto.paciente.DadosCadastroPaciente;
import br.com.vollmed.api.dto.paciente.DadosDetalhamentoPaciente;
import br.com.vollmed.api.dto.paciente.DadosListagemPacientes;
import br.com.vollmed.api.model.Endereco;
import br.com.vollmed.api.model.paciente.*;
import br.com.vollmed.api.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody @Valid DadosCadastroPaciente dados,
            UriComponentsBuilder uriBuilder) {

        var paciente = repository.save(new Paciente(dados));
        var uri = uriBuilder
                .path("/pacientes/{id}")
                .buildAndExpand(paciente.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacientes>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemPacientes::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDados(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        var nome = dados.nome();
        var telefone = dados.telefone();
        if (nome != null && !nome.isBlank()) {
            paciente.setNome(nome);
        }
        if (telefone != null && !telefone.isBlank()) {
            paciente.setTelefone(telefone);
        }
        paciente.setEndereco(new Endereco(dados.endereco()));
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity inativar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.setAtivo(false);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> buscarDadosPaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}
