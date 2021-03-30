package io.zup.orange.propostaspring.autenticacao;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthClientFallbackFactory implements FallbackFactory<AuthClient> {
    @Override
    public AuthClient create(Throwable cause) {
        return request -> {

            System.out.println("cause = " + cause);
            return Optional.empty();
        };
    }
}