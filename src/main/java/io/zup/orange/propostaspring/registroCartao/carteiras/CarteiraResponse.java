package io.zup.orange.propostaspring.registroCartao.carteiras;

import io.zup.orange.propostaspring.registroCartao.Cartao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CarteiraResponse {

    private String id;
    private String email;
    private String associadaEm;
    private TipoDaCarteira emissor;
    private Cartao cartao;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAssociadaEm() {
        return associadaEm;
    }

    public TipoDaCarteira getEmissor() {
        return emissor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(id, LocalDateTime.parse(associadaEm),cartao,email,emissor);
    }
    public Carteira toModel() {
        return new Carteira(id, LocalDateTime.parse(associadaEm),cartao,email,emissor);
    }
}
