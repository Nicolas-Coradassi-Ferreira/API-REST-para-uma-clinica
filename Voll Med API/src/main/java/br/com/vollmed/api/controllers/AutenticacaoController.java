package br.com.vollmed.api.controllers;

import br.com.vollmed.api.infra.security.TokenJWT;
import br.com.vollmed.api.dto.user.DadosAutenticacaoLogin;
import br.com.vollmed.api.infra.security.TokenService;
import br.com.vollmed.api.model.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWT> logar(@RequestBody @Valid DadosAutenticacaoLogin dados) {
        var dadosAutenticacao = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
        var autenticacao = authManager.authenticate(dadosAutenticacao);
        var tokenJWT = tokenService.gerarToken((User) autenticacao.getPrincipal());
        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }

}
