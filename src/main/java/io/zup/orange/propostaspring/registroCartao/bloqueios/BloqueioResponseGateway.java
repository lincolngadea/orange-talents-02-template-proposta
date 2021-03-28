package io.zup.orange.propostaspring.registroCartao.bloqueios;

import io.zup.orange.propostaspring.registroCartao.Cartao;

import java.time.LocalDateTime;

public class BloqueioResponseGateway {

    private String id;

    private String bloqueadoEm;

    private String sistemaResponsavel;

    private boolean ativo;

    private Cartao cartao;

    public String getId() {
        return id;
    }

    public String getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Bloqueio toModel() {
        return new Bloqueio(id, LocalDateTime.parse(bloqueadoEm), sistemaResponsavel, ativo, cartao);
    }
}
