package br.com.vollmed.api.infra.security;

import br.com.vollmed.api.exception.ApiException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        var tokenJwt = recuperarToken(request);
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authToken = request.getHeader("Authorization");
        if (authToken == null) {
            throw new ApiException("Token JWT não enviado no cabeçalho da requisição!");
        }
        return authToken.replace("Bearer ", "");
    }
}
