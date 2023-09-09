package br.com.vollmed.api.controllers;


import br.com.vollmed.api.dto.medico.DadosAtualizacaoMedico;
import br.com.vollmed.api.dto.medico.DadosCadastroMedico;
import br.com.vollmed.api.dto.medico.DadosDetalhamentoMedico;
import br.com.vollmed.api.dto.medico.DadosListagemMedicos;
import br.com.vollmed.api.model.Endereco;
import br.com.vollmed.api.model.medico.*;
import br.com.vollmed.api.repository.MedicoRepository;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(
            @RequestBody @Valid DadosCadastroMedico dados,
            UriComponentsBuilder uriBuilder) {

        var medico = repository.save(new Medico(dados));
        var uri = uriBuilder
                .path("/medicos/{id}")
                .buildAndExpand(medico.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listar(
            @PageableDefault(size = 20, sort = {"nome"}) Pageable paginacao) {

        var page = repository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedicos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> atualizarDados(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        var nome = dados.nome();
        var telefone = dados.telefone();
        if (nome != null && !nome.isBlank()) {
            medico.setNome(dados.nome());
        }
        if (telefone != null && !telefone.isBlank()) {
            medico.setTelefone(dados.telefone());
        }
        medico.setEndereco(new Endereco(dados.endereco()));
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
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
        var medico = repository.getReferenceById(id);
        medico.setAtivo(false);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> buscarDadosMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
