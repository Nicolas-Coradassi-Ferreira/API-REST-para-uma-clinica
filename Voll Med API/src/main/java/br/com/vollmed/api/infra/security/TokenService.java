package br.com.vollmed.api.infra.security;

import br.com.vollmed.api.exception.ApiException;
import br.com.vollmed.api.model.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.service.password}")
    private String tokenServicePassword;

    public String gerarToken(User user) {
        try {
            var algoritmo = Algorithm.HMAC256(tokenServicePassword);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException ex) {
            throw new ApiException("Erro ao gerar token de autenticação", ex);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
